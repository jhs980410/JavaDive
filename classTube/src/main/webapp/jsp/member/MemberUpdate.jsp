<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 정보 수정</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/member/memberUpdate.css">
</head>
<body>
    <div class="update-container">
    	<h2>회원 정보 수정</h2>

	<form action="${pageContext.request.contextPath}/memberUpdate" method="post" onsubmit="return validateForm();">
    	<div class="info-box">
	        <label for="name">이름:</label>
	        <input type="text" id="name" name="name" value="${memberDto.name}" required>
    	</div>

    	<div class="info-box">
        	<label for="tel">전화번호:</label>
        	<input type="text" id="tel" name="tel" value="${memberDto.tel}" required>
    	</div>

    	<div class="info-box">
        	<label for="newPwd">새 비밀번호:</label>
        	<input type="password" id="newPwd" name="newPwd">
    	</div>

    	<div class="info-box">
        	<label for="confirmPwd">비밀번호 확인:</label>
        	<input type="password" id="confirmPwd" name="confirmPwd">
    	</div>
	</div>

			<!-- 버튼을 입력 폼 바깥으로 분리 -->
		<div class="button-container">
    		<button type="submit" class="update-btn">정보 수정</button>
    		<button type="button" class="cancel-btn" onclick="cancelUpdate()">취소</button>
		</div>

<script>
    function cancelUpdate() {
        window.location.href = "${pageContext.request.contextPath}/myPageList";  // 마이페이지로 이동
    }
    
 // 전화번호 정규식 검사 (010-1234-5678 또는 01012345678 형식 허용)
    function isValidPhoneNumber(phone) {
        var phoneRegex = /^(010-\d{4}-\d{4}|010\d{8})$/;
        return phoneRegex.test(phone);
 	}
 // 비밀번호 확인 로직
    function validateForm() {
        var newPwd = document.getElementById("newPwd").value;
        var confirmPwd = document.getElementById("confirmPwd").value;
        var tel = document.getElementById("tel").value;

        // 🔹 전화번호 검증
        if (!isValidPhoneNumber(tel)) {
            alert("올바른 전화번호 형식을 입력하세요. (예: 010-1234-5678 또는 01012345678)");
            return false;  // 제출 방지
        }
        if (newPwd !== "" && newPwd !== confirmPwd) {
            alert("비밀번호가 일치하지 않습니다.");
            return false;  // 폼 제출 방지
        }
        return true;  // 정상 제출
    }
</script>

</body>
</html>
