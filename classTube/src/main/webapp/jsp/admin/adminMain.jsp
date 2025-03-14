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
	
	<!-- 🔹 헤더 외부에 로그아웃 버튼 추가 -->
    <div class="logout-container">
        <form id="logoutForm" action="<%= request.getContextPath() %>/logout" method="post">
            <button type="submit" class="logout-btn">로그아웃</button>
        </form>
    </div>
	
	<form action="./adminMain" method="get">
	<div class="main-content">
	
	
	본문 내용
	</div>
	</form>
	
	
</body>
</html>



