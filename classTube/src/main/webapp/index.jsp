<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
	<h2>게시판</h2>

	<!-- 게시판 글쓰기 버튼 -->
	<form action="<%=request.getContextPath()%>/boardForm" method="get">
		<button type="submit">게시판 글쓰기</button>
	</form>

</body>
</html>
