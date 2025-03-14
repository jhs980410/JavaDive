<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin/common/adminHeader.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin/board/adminBoardList.css">
</head>
<body>
	<%@ include file="../common/adminHeader.jsp"%>

	<div class="container">

		<div class="button-group">
		<button class="but-view" onclick="location.href='/classTube/admin/boardNotice'">공지사항목록</button>
			<button class="but-view"
				onclick="location.href='/classTube/admin/boardList'">게시글
				목록</button>

			<form action="${pageContext.request.contextPath}/boardSubmit"
				method="post">
				<div class="button-group">
					<button type="button" class="button"
						 onclick="location.href='${pageContext.request.contextPath}/jsp/admin/board/AdminBoardAdd.jsp'">글쓰기</button>
				</div>
			</form>
		</div>

		<h2 class="board-title">📌 게시판 목록</h2>
		<form action="${pageContext.request.contextPath}/admin/boardList" method="get">

			<table class="board-list">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>카테고리</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>관리</th>
						<!-- 삭제 버튼 열 추가 -->
					</tr>
				</thead>
				<tbody>
					<c:forEach var="board" items="${boardList}">
						<tr>
							<td>${board.noteNo}</td>
							<td><a
								href="${pageContext.request.contextPath}/admin/board/AdminBoardView?postId=${board.noteNo}">${board.title}</a></td>



							<td>${board.category}</td>
							<td>${board.writer}</td>
							<td><fmt:formatDate value="${board.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							
							<td>
								<!-- 개별 삭제 form -->
								<form action="/classTube/admin/boardDelete" method="get">
									<input type="hidden" name="postId" value="${board.noteNo}">
									<button type="submit">삭제</button>
								</form>
							</td>
							
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
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
						href="/classTube/admin/boardList?page=${currentPage - 1}${not empty keyword ? '&keyword=' : ''}${keyword}">이전</a>
					</li>
				</c:if>

				<!-- 페이지 숫자 버튼 -->
				<c:forEach var="i" begin="1" end="${endPage}">
					<li class="page-item ${currentPage == i ? 'active' : ''}"><a
						class="page-link"
						href="/classTube/admin/boardList?page=${i}${not empty keyword ? '&keyword=' : ''}${keyword}">${i}</a>
					</li>
				</c:forEach>

				<!-- 다음 버튼 -->
				<c:if test="${currentPage < totalPage}">
					<li class="page-item"><a class="page-link"
						href="/classTube/admin/boardList?page=${currentPage + 1}${not empty keyword ? '&keyword=' : ''}${keyword}">다음</a>
					</li>
				</c:if>
			</c:if>
		</ul>




		<!-- "글쓰기" 버튼이 form 태그 안에 있으면 안됨! -->
		<div class="bottom-container">
			<!-- 검색창 (왼쪽 정렬) -->
			<form action="/classTube/admin/boardList" method="get"
				class="search-form">
				<div class="search-container">
					<div class="search">
						<div class="ui icon input">
							<input class="prompt" type="text" name="keyword" placeholder="검색">
							<i class="search icon"></i>
						</div>
						<div class="results"></div>
					</div>
				</div>
			</form>



			<p>
		</div>
	</div>
</body>
</html>