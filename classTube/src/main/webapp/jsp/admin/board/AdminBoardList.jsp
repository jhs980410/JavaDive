<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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
			<button class="but-view"
				onclick="location.href='/classTube/admin/boardNotice'">공지사항목록</button>
			<button class="but-view"
				onclick="location.href='/classTube/admin/boardList'">게시글 목록</button>

			<form action="${pageContext.request.contextPath}/boardSubmit"
				method="post">
				<div class="button-group">
					<button type="button" class="button"
						onclick="location.href='${pageContext.request.contextPath}/jsp/admin/board/AdminBoardAdd.jsp'">글쓰기</button>
				</div>
			</form>
		</div>

		<h2 class="board-title">📌 게시판 목록</h2>
		<form action="${pageContext.request.contextPath}/admin/boardList"
			method="get">

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
							<td><fmt:formatDate value="${board.createDate}"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>

							<td>
								<!-- 개별 삭제 form -->
								<form action="/classTube/admin/boardDelete" method="get">
									<input type="hidden" name="postId" value="${board.noteNo}">
									<button
										onclick="confirmPostDelete('/classTube/admin/boardDelete?postId=${boardDto.noteNo}')">
										삭제</button>
								</form>
							</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
		<c:set var="pageGroupSize" value="10" />

		<!-- 현재 페이지 그룹의 첫 번째 페이지 계산 (10단위) -->
		<c:set var="step1" value="${currentPage + 9}" />
		<fmt:parseNumber var="step2" value="${step1 / 10}" integerOnly="true" />
		<c:set var="step3" value="${step2 - 1}" />
		<c:set var="startPage" value="${step3 * 10 + 1}" />
		<c:set var="endPage" value="${(startPage + pageGroupSize - 1) div 1}" />


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
					href="boardList?page=${fn:substringBefore((startPage - 1), '.')}">이전</a></li>
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
					href="boardList?page=${fn:substringBefore((startPage + 1), '.')}">다음</a></li>
			</c:if>


			<!-- ▶▶ 마지막 페이지 버튼 -->
			<c:if test="${currentPage < totalPage}">
				<li class="page-item"><a class="page-link"
					href="boardList?page=${totalPage}&keyword=${param.keyword}">끝</a></li>
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
	<script
		src="${pageContext.request.contextPath}/js/admin/adminBoardList.js"></script>
</body>
</html>