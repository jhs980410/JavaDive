<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
				<c:forEach var="board" items="${boardList}" varStatus="status">

				<tr>
				<td>${board.noteNo}</td>
				<td><a href="boardView.jsp?postId=${board.noteNo}">${board.title}</a></td>
				<td>${board.category}</td>
				<td>${board.writer}</td>
				<td>${board.createDate}</td>
				
				
				</tr>
				</c:forEach>
				
			</tbody>
		</table>
		</form>
		<div class="button-group">
			<button class="button">글쓰기</button>
		</div>
		
		<p>
			<input type="text">
	</div>

</body>
</html>
