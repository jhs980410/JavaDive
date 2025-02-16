<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>로그인</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }
        body {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            background-color: #f4f4f4;
        }

        .container {
            width: 450px;
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        h2 {
            margin-bottom: 20px;
        }

        .input-box {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .login-btn, .social-login {
            width: 100%;
            padding: 12px;
            margin-top: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            color: white;
        }

        .login-btn {
            background-color: #2c6ed5;
        }

        .social-login {
            background-color: #ffeb00;
            color: black;
        }

        .nav-links {
            display: flex;
            justify-content: space-between;
            margin-top: 15px;
        }

        .nav-links a {
            text-decoration: none;
            color: #2c6ed5;
            font-size: 14px;
        }

        .social-login.kakao {
            background-color: #ffeb00;
            color: black;
        }

        .social-login.naver {
            background-color: #2db400;
        }
    </style>
</head>
<body>

    <h1>Java_Dive</h1>
    <div class="container">
        <h2>로그인</h2>
        
        <form action="LoginServlet" method="post">
            <input type="text" name="userId" class="input-box" placeholder="아이디" required>
            <input type="password" name="password" class="input-box" placeholder="비밀번호" required>
            <button type="submit" class="login-btn">로그인</button>
        </form>

        <div class="nav-links">
            <a href="find-password.jsp">아이디/비밀번호 찾기</a>
            <a href="register.jsp">회원가입</a>
        </div>

        <button class="social-login kakao">카카오로 로그인</button>
        <button class="social-login naver">네이버 로그인</button>
    </div>

</body>
</html>