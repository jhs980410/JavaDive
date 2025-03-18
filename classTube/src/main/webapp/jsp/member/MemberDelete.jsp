<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 탈퇴</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/member/memberDelete.css">
</head>
<body>
    <div class="delete-container">
        <h2>회원 탈퇴</h2>
        <p>정말로 회원 탈퇴를 진행하시겠습니까?</p>
        <p>탈퇴 후에는 복구할 수 없습니다.</p>

        <!-- 회원 탈퇴 폼 -->
        <form id="deleteForm" action="${pageContext.request.contextPath}/memberDelete" method="post">
            <button type="submit" class="delete-btn">회원 탈퇴</button>
            <button type="button" class="cancel-btn" onclick="cancelDelete()">취소</button>
        </form>
    </div>

    <script>
        function cancelDelete() {
            window.location.href = "${pageContext.request.contextPath}/myPageList";  // 마이페이지로 이동
        }
    </script>
</body>
</html>
