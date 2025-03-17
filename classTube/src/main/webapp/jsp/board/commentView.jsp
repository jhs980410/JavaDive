<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 수정</title>
<style>
body {
	font-family: Arial, sans-serif;
	padding: 20px;
	background: #f8f9fa;
}

.container {
	width: 500px;
	background: white;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
}

.comment-header {
	font-size: 18px;
	font-weight: bold;
	margin-bottom: 10px;
}

.comment-info {
	font-size: 14px;
	color: #666;
	margin-bottom: 10px;
}

textarea {
	width: 100%;
	height: 80px;
	resize: none;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
	font-size: 14px;
}

.button-container {
	text-align: right;
	margin-top: 10px;
}

button {
	padding: 8px 12px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-size: 14px;
}

.btn-save {
	background-color: #007bff;
	color: white;
}

.btn-close {
	background-color: #dc3545;
	color: white;
}
</style>
</head>
<body>

	<div class="container">
		<div class="comment-header">댓글 수정</div>

		<div class="comment-info">
			작성자: <strong>${comment.commentWriter}</strong> | 작성일: <fmt:formatDate value="${comment.createAt}" pattern="yyyy-MM-dd HH:mm:ss" />
		</div>

		<!-- 기존 댓글 내용 표시 (내용만 수정 가능) -->
		<form action="${pageContext.request.contextPath}/BoardCommentUpdate"
			method="post">
			<input type="hidden" name="commentId" value="${comment.commentNo}">
			<input type="hidden" name="postId" value="${postId}">

			<textarea name="content">${comment.commentContent}</textarea>

			<div class="button-container">
				<button type="submit" class="btn-save">수정 완료</button>
				<button type="button" class="btn-close" onclick="window.close();">닫기</button>
			</div>
		</form>
	</div>

</body>
</html>
