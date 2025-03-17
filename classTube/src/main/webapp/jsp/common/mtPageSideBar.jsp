<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>사이드바</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/common/sideBar.css">


</head>
<body>
    <div class="sidebar">
        <div class="logo" onclick='location.href="${pageContext.request.contextPath}/main"'>
    	<img src="<%= request.getContextPath() %>/images/logo.PNG" alt="로고" width="150">
		</div>
<!-- <img src="로고이미지경로/logo.png" alt="로고" width="150">  -->
        
        <nav class="menu-list">
            <a onclick='location.href="${pageContext.request.contextPath}/main"' class="menu">메인페이지</a>    <!-- 메인페이지 정해지면 주소 수정필요 -->
            <a href="mypage.jsp" class="menu">회원정보</a>  <!-- 마이페이지 정해지면 주소 수정필요  -->
            
           
            <a href="category.jsp?type=sports" class="menu">회원정보수정</a>
            <a href="category.jsp?type=beauty" class="menu">회원탈퇴</a>
             <div class="menu-title">-------------------</div>
            <a href="category.jsp?type=dev" class="menu">내가 쓴 게시글</a>

        </nav>
    </div>
</body>
</html>
	
	
<!-- 	1px solid #ddd -->