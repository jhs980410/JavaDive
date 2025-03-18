<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>오류 발생</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/common/error.css">

</head>
<body>

	<div class="error-container">
		<h1>오류 발생</h1>
		<p>요청을 처리하는 도중 오류가 발생했습니다.</p>

		<a href="${pageContext.request.contextPath}/myPageList"
					class="btn"> 마이페이지로 돌아가기 </a>

		



	</div>

</body>
</html>
