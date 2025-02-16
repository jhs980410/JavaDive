<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>회원관리 페이지</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        body {
            text-align: center;
            background-color: white;
        }

        .container {
            width: 40%;
            margin: 30px auto;
            background: white;
            padding: 30px;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0);
            border-radius: 8px;
        }

        h1 {
            margin-bottom: 20px;
        }

        .profile-image {
            width: 120px;
            height: 120px;
            background: #ddd;
            display: block;
            margin: 0 auto 20px;
            border-radius: 8px;
            line-height: 120px;
            text-align: center;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group input {
            width: 100%;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
        }

        .btn {
            width: 100%;
            padding: 15px;
            background-color: #2c3e50;
            color: white;
            border: none;
            font-size: 18px;
            cursor: pointer;
            margin-top: 15px;
            border-radius: 4px;
        }

        .btn:hover {
            background-color: #34495e;
        }

    </style>
</head>
<body>

    <div class="container">
        <h1>회원관리 페이지</h1>
        
        <div class="profile-image">이미지</div>

        <form action="UserUpdateServlet" method="post">
            <div class="form-group">
                <input type="text" name="userId" placeholder="아이디" required>
            </div>
            <div class="form-group">
                <input type="password" name="password" placeholder="비밀번호" required>
            </div>
            <div class="form-group">
                <input type="email" name="email" placeholder="이메일" required>
            </div>
            <div class="form-group">
                <input type="text" name="name" placeholder="이름" required>
            </div>
            <div class="form-group">
                <input type="date" name="birthDate" placeholder="생년월일">
            </div>
            <div class="form-group">
                <input type="text" name="phone" placeholder="휴대폰 번호" required>
            </div>
            <button type="submit" class="btn">수정 / 완료</button>
        </form>
    </div>

</body>
</html>