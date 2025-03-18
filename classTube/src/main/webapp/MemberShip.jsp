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

    <!-- 이메일 중복 확인 여부를 저장하는 hidden input -->
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

            <!-- ✅ 중복 확인 버튼을 독립적인 폼으로 분리 -->
            <div class="input-group">
                <label for="email">이메일</label>
                <input type="email" id="email" name="email" placeholder="이메일 입력" required oninput="resetEmailCheck()">
                    <button type="button" class="small-btn" onclick="checkEmail()">중복확인</button>
                   	<!-- 서버에서 전달되 메시지 표시 -->
                    <p id="emailMessage" class="message"></p>
            </div>

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
	function checkEmail() {
	    var email = document.getElementById("email").value;
	
	    // 이메일 입력 여부 확인
	    if (!email.trim()) {
	        alert("이메일을 입력해주세요!");
	        return;
	    }
	
	    var form = document.createElement("form");
        form.method = "post";
        form.action = "checkEmail";
        form.style.display = "none";

        var emailInput = document.createElement("input");
        emailInput.type = "hidden";
        emailInput.name = "email";
        emailInput.value = email;

        form.appendChild(emailInput);
        document.body.appendChild(form);
        form.submit();
	}
	
	function resetEmailCheck() {
        // 이메일이 변경되면, 중복 확인 상태를 초기화
        document.getElementById("emailChecked").value = "false";
    }
</script>

