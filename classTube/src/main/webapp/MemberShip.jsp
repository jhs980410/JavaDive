<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link rel="stylesheet" href="css/MemberShip.css">
    
    <script>
        function validateFormFnc() {
            var password = document.getElementById("password").value;
            var passwordConfirm = document.getElementById("password-confirm").value;

            if (password !== passwordConfirm) {
                alert("비밀번호가 다릅니다. 다시 입력해주세요.");
                return false; // 폼 제출 막기
            }
            return true; // 폼 제출 허용
        }
        
     // 🔹 이메일 중복 확인 함수 (새로운 코드 추가)
        function checkEmail() {
            var email = document.getElementById("email").value;

            if (!email) {
                alert("이메일을 입력하세요.");
                return;
            }

    </script>
    
</head>
<body>

<div class="frame">
    <h2 class="title">회원가입</h2>

	<form action="join" method="post" onsubmit="return validateFormFnc();">
     <div class="form-container">
        <div class="input-group">
            <label for="name">이름</label>
            <input type="text" id="name" placeholder="이름 입력">
        </div>

        <div class="input-group">
            <label for="email">이메일</label>
            <input type="email" id="email" name="email" placeholder="이메일 입력" required>
            <button type="button" class="small-btn" onclick="checkEmail();">중복확인</button>
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
            <label for="phone">휴대폰 번호</label>
            <input type="text" id="phone" placeholder="010-1234-5678">
            
        </div>

        <div class="button-group">
             <button type="submit" class="main-btn">가입완료</button>
             <button type="button" class="main-btn cancel" onclick="history.back();">뒤로</button>
        </div>
   	 </div>
    </form>

</div>

</body>
</html>
