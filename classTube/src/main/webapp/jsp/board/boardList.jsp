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
</head>
<body>

	<%@ include file="../common/sideBar.jsp"%>

	<div class="container">
		<h2 class="board-title">📌 게시판 목록</h2>
		<form action="boardList" method="get">
			<table class="board-list">
				<!-- 📌 테이블 헤더 -->
				<thead>
					<tr>
						<th class="boardHeader">번호</th>
						<th class="boardHeader">제목</th>
						<th class="boardHeader">카테고리</th>
						<th class="boardHeader">작성자</th>
						<th class="boardHeader">작성일</th>
					</tr>
				</thead>

				<!-- 📌 공지사항 (고정 데이터) -->
				<tbody class="notice-section">
					<tr class="notice-row">
						<td>1</td>
						<td><a href="boardView.jsp?postId=1">📢 첫 번째 공지</a></td>
						<td>공지사항</td>
						<td>admin</td>
						<td>2025-03-06</td>
					</tr>
					<tr class="notice-row">
						<td>2</td>
						<td><a href="boardView.jsp?postId=2">📢 두 번째 공지</a></td>
						<td>공지사항</td>
						<td>admin</td>
						<td>2025-03-05</td>
					</tr>
				</tbody>

				<!-- 📌 일반 게시글 (더미 데이터) -->

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
			<!-- 📌 페이지네이션 추가 -->
			<%-- 현재 페이지 그룹 구하기 (5개씩 표시) --%>

		</form>

		<c:set var="pageGroup" value="${(currentPage - 1) / 5}" />
		<c:set var="startPage" value="${pageGroup * 5 + 1}" />
		<c:set var="endPage" value="${totalPage}" />

		<%-- totalPages보다 endPage가 크면 조정 --%>
		<c:if test="${endPage > totalPage}">
			<c:set var="endPage" value="${totalPage}" />
		</c:if>

		<%-- 현재 페이지에 따라 endPage를 조정 (1부터 현재 페이지까지 표시) --%>
		<c:set var="endPage" value="${totalPage}" />

		<ul class="pagination">
			<%-- 이전 그룹 이동 버튼 (현재 페이지가 2 이상이면 표시) --%>
			<c:if test="${currentPage > 1}">
				<li class="page-item"><a class="page-link"
					href="boardList?page=${currentPage - 1}">이전</a></li>
			</c:if>

			<%-- 1부터 현재 페이지까지 숫자 출력 --%>
			<c:forEach var="i" begin="1" end="${endPage}">
				<li class="page-item ${currentPage == i ? 'active' : ''}"><a
					class="page-link" href="boardList?page=${i}">${i}</a></li>
			</c:forEach>

			<%-- 다음 그룹 이동 버튼 (현재 페이지가 5 이상일 때 표시) --%>
			<c:if test="${currentPage >= 5 && currentPage < totalPage}">
				<li class="page-item"><a class="page-link"
					href="boardList?page=${currentPage + 1}">다음</a></li>
			</c:if>
		</ul>

		<!-- "글쓰기" 버튼이 form 태그 안에 있으면 안됨! -->
		<form action="${pageContext.request.contextPath}/boardSubmit"
			method="post">

			<div class="button-group">
				<button type="button" class="button"
					onclick="location.href='jsp/board/boardAdd.jsp'">글쓰기</button>
			</div>
		</form>
		<div class="button-group">
			<button class="but-view"
				onclick="location.href='/classTube/boardList'">게시글 목록</button>

		</div>

		<p>
		<form action="boardSearch" method="get">
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
	</div>

<script src="${pageContext.request.contextPath}/js/board/boardList.js"></script>
</body>
</html>
