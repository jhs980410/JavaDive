<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/admin/common/adminHeader.css">

</head>

<body>
	<%@ include file="/jsp/admin/common/adminHeader.jsp" %>
	
	<form action="./adminMain" method="get">
	<div class="main-content">
	 <img src="<%= request.getContextPath() %>/images/Minions.gif" alt="로딩 이미지" width="1000">
    	<p>관리자 메인 페이지에 오신것을 환영합니다.</p>
	
	</div>
	</form>
	
	
</body>
</html>



