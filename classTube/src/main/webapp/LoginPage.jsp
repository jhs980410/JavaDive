<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/member/LoginPage.css">
    
    
    <script type="text/javascript">
    
    </script>
</head>
<body>
	
	<div class="container">
	    <div class="logo-box">
	        <img src="<%= request.getContextPath() %>/images/logo.PNG" alt="로고">
	    </div>
	<form action="login" method="post">
	    <input type="text" class="input-box" placeholder="아이디" name="member_email">
	    <input type="password" class="input-box" placeholder="비밀번호" name="member_password">
	
	    <div class="login-button-box">
	        <button class="login-button-text">로그인</button>
	    </div>
	</form>
	    <div class="find-signup-box">
	        <button class="find-id-password">아이디/비밀번호 찾기</button>
	        <button class="signup" type="button" class="small-btn" onclick="location.href='./join'">회원가입</button>
	    </div>
	
	    <button class="google-login">구글 로그인</button>
	    <button class="naver-login">네이버 로그인</button>
	</div>
	

</body>
</html>