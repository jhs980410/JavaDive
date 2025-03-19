<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
				onclick="location.href='/classTube/boardNotice'">공지사항목록</button>
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
						<td><fmt:formatDate value="${board.createDate}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<!-- 한 번에 표시할 페이지 개수 -->
		<c:set var="pageGroupSize" value="10" />

		<!-- 현재 페이지 그룹의 첫 번째 페이지 계산 (10단위) -->
		<c:set var="groupSize" value="10" />
		<c:set var="currentGroup" value="${(currentPage - 1) div groupSize}" />
		<c:set var="step1" value="${currentPage + 9}" />
		<fmt:parseNumber var="step2" value="${step1 / 10}" integerOnly="true" />
		<c:set var="step3" value="${step2 - 1}" />
		<c:set var="startPage" value="${step3 * 10 + 1}" />
		<c:set var="endPage" value="${startPage + groupSize - 1}" />
		<!-- 마지막 페이지가 전체 페이지 수보다 크면 조정 -->
		<c:if test="${endPage > totalPage}">
			<c:set var="endPage" value="${totalPage}" />
		</c:if>

		<ul class="pagination">
			<!-- ◀◀ 처음 페이지 버튼 -->
			<c:if test="${currentPage > 1}">
				<li class="page-item"><a class="page-link"
					href="boardList?page=1">처음</a></li>
			</c:if>

			<!-- ◀ 이전 그룹 버튼 -->
			<c:if test="${startPage > 1}">
				<li class="page-item"><a class="page-link"
					href="boardList?page=${fn:substringBefore((startPage - pageGroupSize), '.')}">이전</a></li>
			</c:if>

			<!-- 페이지 숫자 버튼 -->
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<li class="page-item ${currentPage == i ? 'active' : ''}"><a
					class="page-link"
					href="boardList?page=${i}&keyword=${param.keyword}">${i}</a></li>
			</c:forEach>

			<!-- ▶ 다음 그룹 버튼 -->
			<c:if test="${endPage < totalPage}">
				<li class="page-item"><a class="page-link"
					href="boardList?page=${fn:substringBefore((startPage + pageGroupSize), '.')}">다음</a></li>
			</c:if>


			<!-- ▶▶ 마지막 페이지 버튼 -->
			<c:if test="${currentPage < totalPage}">
				<li class="page-item"><a class="page-link"
					href="boardList?page=${totalPage}">끝</a></li>
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
