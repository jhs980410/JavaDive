<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link rel="stylesheet" href="css/member/MemberShip.css">
       
</head>
<body>

<div class="frame">
    <h2 class="title">회원가입</h2>
    
     <!-- 이메일 중복 확인 여부를 저장하는 hidden input 추가 -->
    <input type="hidden" id="emailChecked" name="emailChecked" 
           value="<%= session.getAttribute("emailChecked") != null ? "true" : "false" %>">
           
           <!-- 서버에서 전달된 에러 메시지 출력 -->
    <% if (request.getAttribute("errorMessage") != null) { %>
        <script>alert("<%= request.getAttribute("errorMessage") %>");</script>
    <% } %>

    <form id="signupForm" action="join" method="post">
        <div class="form-container">
            <div class="input-group">
                <label for="name">이름</label>
                <input type="text" id="name" name="name" placeholder="이름 입력" required>
            </div>

            <div class="input-group">
                <label for="email">이메일</label>
                <input type="email" id="email" name="email" placeholder="이메일 입력" required
                oninput="resetEmailCheck()">
                <button type="button" class="small-btn" onclick="checkEmail()">중복확인</button>
            </div>
            
			             <!-- ✅ 서버에서 전달된 메시지를 출력하는 부분 -->
			    <% if (request.getAttribute("message") != null) { %>
			        <p class="message"><%= request.getAttribute("message") %></p>
			    <% } %>

            <div class="input-group">
                <label for="password">비밀번호</label>
                <input type="password" id="password" name="password" placeholder="비밀번호 입력" required>
            </div>

            <div class="input-group">
                <label for="password-confirm">비밀번호 확인</label>
                <input type="password" id="password-confirm" name="password-confirm" placeholder="비밀번호 재입력" required>
            </div>

            <div class="input-group">
                <label for="id-number">주민등록번호</label>
                <input type="text" id="id-number" name="id-number" placeholder="예: 900101-1234567" required>
            </div>

            <div class="input-group">
                <label for="phone">휴대폰 번호</label>
                <input type="text" id="phone" name="phone" placeholder="010-1234-5678" required>
            </div>

            <div class="button-group">
                <button type="submit" class="main-btn">가입완료</button>
                <button type="button" class="main-btn cancel" onclick="location.href='<%= request.getContextPath() %>/login';">뒤로</button>
            </div>
        </div>
    </form>

</div>


<script>
	<!-- 추가된 JavaScript: 이메일 값을 hidden 필드에 설정 -->
    function setEmailValue() {
        document.getElementById("emailHidden").value = document.getElementById("email").value;
    }
    
    <!-- 이메일 중복 확인 및 비밀번호 검증하는 JavaScript 추가 -->
    function validateSignup() {
        var emailChecked = document.getElementById("emailChecked").value;
        var password = document.getElementById("password").value;
        var confirmPassword = document.getElementById("password-confirm").value;

        // 이메일 중복 확인 여부 체크
        if (emailChecked !== "true") {
            alert("이메일 중복 확인을 해주세요!");
            return false;
        }

        // 비밀번호 일치 확인
        if (password !== confirmPassword) {
            alert("비밀번호가 일치하지 않습니다!");
            return false;
        }

        return true;
    }
    
    function resetEmailCheck() {
    	document.getElementById("emailChecked").value = "false";
	}
    
    function checkEmail() {
        var email = document.getElementById("email").value;

        // 이메일 입력 여부 확인
        if (!email.trim()) {
            alert("이메일을 입력해주세요!");
            return;
        }

        // 중복 확인 요청을 직접 폼 제출 방식으로 변경
        var form = document.createElement("form");
        form.method = "POST";
        form.action = "checkEmail";

        var emailInput = document.createElement("input");
        emailInput.type = "hidden";
        emailInput.name = "email";
        emailInput.value = email;

        form.appendChild(emailInput);
        document.body.appendChild(form);
        form.submit();
    }
    
</script>

</body>
</html>
