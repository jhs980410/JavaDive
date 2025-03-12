<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/board/boardList.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.js"></script>
</head>
<body>

	<%@ include file="../common/sideBar.jsp"%>

	<div class="container">

		<div class="button-group">
			<button class="but-view"
				onclick="location.href='/classTube/boardList'">게시글 목록</button>

			<form action="${pageContext.request.contextPath}/boardSubmit"
				method="post">
				<div class="button-group">
					<button type="button" class="button"
						onclick="location.href='jsp/board/boardAdd.jsp'">글쓰기</button>
				</div>
			</form>
		</div>

		<h2 class="board-title">📌 게시판 목록</h2>

		<!-- 🔍 검색 기능 -->


		<table class="board-list">
			<thead>
				<tr>
					<th class="boardHeader">번호</th>
					<th class="boardHeader">제목</th>
					<th class="boardHeader">카테고리</th>
					<th class="boardHeader">작성자</th>
					<th class="boardHeader">작성일</th>
				</tr>
			</thead>

			<tbody class="post-section">
				<c:forEach var="board" items="${boardList}">
					<tr>
						<td>${board.noteNo}</td>
						<td><a href="boardView?postId=${board.noteNo}">${board.title}</a></td>
						<td>${board.category}</td>
						<td>${board.writer}</td>
						<td>${board.createDate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<c:set var="pageGroupSize" value="10" />
		<!-- startPage 계산: currentPage가 10보다 크면 그룹이 바뀌어야 함 -->
		<c:set var="startPage"
			value="${(currentPage - 1) / pageGroupSize * pageGroupSize + 1}" />
		<!-- endPage 계산: startPage + pageGroupSize - 1, 단 totalPage보다 크면 endPage는 totalPage로 설정 -->
		<c:set var="endPage" value="${startPage + pageGroupSize - 1}" />

		<c:if test="${endPage > totalPage}">
			<c:set var="endPage" value="${totalPage}" />
		</c:if>

		<ul class="pagination">
			<c:if test="${totalPage > 1}">
				<!-- 이전 버튼 -->
				<c:if test="${currentPage > 1}">
					<li class="page-item"><a class="page-link"
						href="boardList?page=${currentPage - 1}${not empty keyword ? '&keyword=' : ''}${keyword}">이전</a>
					</li>
				</c:if>

				<!-- 페이지 숫자 버튼 -->
				<c:forEach var="i" begin="1" end="${endPage}">
					<li class="page-item ${currentPage == i ? 'active' : ''}"><a
						class="page-link"
						href="boardList?page=${i}${not empty keyword ? '&keyword=' : ''}${keyword}">${i}</a>
					</li>
				</c:forEach>

				<!-- 다음 버튼 -->
				<c:if test="${currentPage < totalPage}">
					<li class="page-item"><a class="page-link"
						href="boardList?page=${currentPage + 1}${not empty keyword ? '&keyword=' : ''}${keyword}">다음</a>
					</li>
				</c:if>
			</c:if>
		</ul>



		<form action="boardList" method="get">
			<div class="search-container">
				<div class="search">
					<div class="ui icon input">
						<input class="prompt" type="text" name="keyword" placeholder="검색"
							value="${param.keyword}"> <i class="search icon"></i>
					</div>
					<div class="results"></div>
				</div>
			</div>
		</form>

	</div>

	<script src="${pageContext.request.contextPath}/js/board/boardList.js"></script>
</body>
</html>
