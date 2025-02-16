<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
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
            width: 50%; /* 화면의 80% 차지 */
            height: 70vh; /* 전체 화면의 90% 차지 */
            background-color: white;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
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

        .btn {
            width: 100%;
            padding: 12px;
            margin-top: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            color: white;
        }

        .check-btn {
            background-color: #2c6ed5;
            padding: 8px;
            font-size: 14px;
            border-radius: 5px;
            cursor: pointer;
            color: white;
        }

        .verify-btn {
            background-color: #ff7b00;
            padding: 8px;
            font-size: 14px;
            border-radius: 5px;
            cursor: pointer;
            color: white;
        }

        .register-btn {
            background-color: #2c3e50;
        }

        .radio-group {
            display: flex;
            justify-content: center;
            gap: 10px;
            margin-top: 10px;
        }
    </style>
</head>
<body>

    <h1>name</h1>
    <div class="container">
        <h2>회원가입</h2>
        <!--  required =HTML5의 required 속성을 사용하면 자동으로 표시됨 
        
        js로 사용자 지정메시지 지정희망시 setCustomValidity() 사용 ,
         수정 필요  ;
        -->
        
        
        <form action="RegisterServlet" method="post">
            <input type="text" name="userId" class="input-box" placeholder="아이디" required>
            <button type="button" class="check-btn">중복 확인</button>

            <input type="password" name="password" class="input-box" placeholder="비밀번호" required>
            <input type="email" name="email" class="input-box" placeholder="이메일" required>
            
            <input type="text" name="name" class="input-box" placeholder="이름" required>
            <input type="date" name="birthdate" class="input-box" required>

            <div class="radio-group">
                <label><input type="radio" name="gender" value="male" required> 남자</label>
                <label><input type="radio" name="gender" value="female" required> 여자</label>
            </div>

            <input type="text" name="phone" class="input-box" placeholder="휴대폰 번호" required>
            <button type="button" class="verify-btn">인증 요청</button>

            <input type="text" name="authCode" class="input-box" placeholder="인증 번호 입력" required>

            <button type="submit" class="register-btn btn">회원가입</button>
        </form>
    </div>

</body>
</html>

<!-- 1️⃣ 기본 회원가입 폼
아이디 중복 체크 버튼 (중복 확인)
비밀번호, 이메일 입력 필드
이름, 생년월일 입력 필드
성별 선택 (라디오 버튼)
휴대폰 번호 입력 및 인증 요청
인증 코드 입력 필드
회원가입 버튼
2️⃣ 폼 제출 방식
<form action="RegisterServlet" method="post">
입력값을 RegisterServlet으로 POST 방식 전송
서블릿에서 DB와 연동하여 회원가입 진행 가능
3️⃣ 스타일링
반응형 디자인
버튼 색상 구별
회원가입 버튼 (#2c3e50)
중복 확인 버튼 (#2c6ed5)
인증 요청 버튼 (#ff7b00) -->

