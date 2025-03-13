<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin/common/adminHeader.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin/board/adminBoardView.css">

</head>
<body>
	<%@ include file="../common/adminHeader.jsp"%>


	<div class="container">
		<h2>관리자 게시판 상세</h2>

		<c:set var="boardDto" value="${sessionScope.boardDto}" />
		<c:set var="recentPostId" value="${sessionScope.recentPostId}" />
		<div class="form-group"></div>
		<div class="form-group">
			<div class="member">
				작성자 : ${boardDto.writer}
				<c:if test="${not empty boardDto.createDate}">
					<span class="date"> 작성일 : ${boardDto.createDate} </span>
				</c:if>
			<button class="updatebutton"
  onclick="location.href='/classTube/admin/boardUpdate?postId=${boardDto.noteNo}'">
  수정
</button>
			</div>
		</div>
		<div class="form-group">
			<span>제목</span> <span class="title">${boardDto.title}</span>
		</div>

		<div class="form-group">
			<span>내용</span> <span class="content">${boardDto.content}</span>
		</div>


		<!-- ✅ 댓글 영역 추가 -->
		<div class="comment-section">
			<h3>댓글</h3>
			<div class="comment">댓글 1</div>
			<div class="comment">댓글 2</div>
			<div class="comment">댓글 3</div>
			<!-- 댓글 추가 예정 -->
		</div>

		<!-- ✅ 버튼 그룹 -->
		<div class="button-group">
			<button class="but-view"
				onclick="location.href='/classTube/admin/boardList'">게시글 목록</button>

		</div>
</body>
</html>