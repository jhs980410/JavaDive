<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link rel="stylesheet" href="css/MemberShip.css">
</head>
<body>

<div class="frame">
    <h2 class="title">회원가입</h2>

    <div class="form-container">
        <div class="input-group">
            <label for="name">이름</label>
            <input type="text" id="name" placeholder="이름 입력">
        </div>

        <div class="input-group">
            <label for="email">이메일</label>
            <input type="email" id="email" placeholder="이메일 입력">
            <button class="small-btn">중복확인</button>
        </div>

        <div class="input-group">
            <label for="password">비밀번호</label>
            <input type="password" id="password" placeholder="비밀번호 입력">
        </div>

        <div class="input-group">
            <label for="password-confirm">비밀번호 확인</label>
            <input type="password" id="password-confirm" placeholder="비밀번호 재입력">
        </div>

        <div class="input-group">
            <label for="id-number">주민등록번호</label>
            <input type="text" id="id-number" placeholder="예: 900101-1234567">
        </div>

        <div class="input-group">
            <label for="telecom">통신사</label>
            <select id="telecom">
                <option value="SKT">SKT</option>
                <option value="KT">KT</option>
                <option value="LGU+">LGU+</option>
            </select>
        </div>

        <div class="input-group">
            <label for="phone">휴대폰 번호</label>
            <input type="text" id="phone" placeholder="010-1234-5678">
            <button class="small-btn">인증요청</button>
        </div>

        <div class="input-group">
            <label for="verify-code">인증번호 입력</label>
            <input type="text" id="verify-code" placeholder="인증번호 입력">
        </div>

        <div class="button-group">
            <button class="main-btn">가입완료</button>
            <button class="main-btn cancel">뒤로</button>
        </div>
    </div>

</div>

</body>
</html>
