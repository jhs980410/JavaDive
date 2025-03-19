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

		

		<c:choose>
			<c:when test="${fn:contains(sessionScope.lastRequestURI, '/admin')}">
			
				<a href="${pageContext.request.contextPath}/admin/category/list"
					class="btn"> 관리자 메인페이지으로 돌아가기 </a>
			</c:when>
			<c:otherwise>
				
				<a href="${pageContext.request.contextPath}/main" class="btn">
					메인페이지으로 돌아가기 </a>
			</c:otherwise>
		</c:choose>



	</div>

</body>
</html>
