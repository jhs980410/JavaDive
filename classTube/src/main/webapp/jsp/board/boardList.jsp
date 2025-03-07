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
		</form>
		<!-- "글쓰기" 버튼이 form 태그 안에 있으면 안됨! -->
		<form action="${pageContext.request.contextPath}/boardSubmit"
			method="post">

			<div class="button-group">
				<button type="button" class="button"
					onclick="location.href='jsp/board/boardForm.jsp'">글쓰기</button>
			</div>
		</form>
		<p>
		<div class="search-container">
			<div class="search">
				<div class="ui icon input">
					<input class="prompt" type="text" placeholder="검색"> <i
						class="search icon"></i>
				</div>
				<div class="results">검색 결과 표시</div>
			</div>
		</div>
	</div>


</body>
</html>
