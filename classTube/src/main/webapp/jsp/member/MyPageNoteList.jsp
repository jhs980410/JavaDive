<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 게시물리스트</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/member/memberMyPageMain.css">
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/member/memberNoteList.css">
	
</head>
<body>
	<%@ include file="../common/myPageSideBar.jsp"%>
	<div class="container">
		<form action="/note/myPageList" method="get">
			<h2>내가 작성한 게시물</h2>
			<c:if test="${not empty memberInfo}">
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
				<c:forEach var="board" items="${memberNoteList}">
					<tr>
						<td>${board.noteNo}</td>
						<td><a href="../boardView?postId=${board.noteNo}">${board.title}</a></td>
						<td>${board.category}</td>
						<td>${board.writer}</td>
						<td><fmt:formatDate value="${board.createDate}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>	

				
			</c:if>

			<c:if test="${empty memberInfo}">
				<p>회원 정보를 불러올 수 없습니다.</p>
			</c:if>
			
			
			
		
		
		
		</form>
     

	</div>
</body>
</html>