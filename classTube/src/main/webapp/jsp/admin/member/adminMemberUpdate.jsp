<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="JavaDive.dto.member.MemberDto" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 정보 수정</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin/common/adminHeader.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin/member/adminMemberUpdate.css">
</head>

<body>

<%@ include file="../common/adminHeader.jsp" %> 

<%
    // 🔹 회원 정보 가져오기
    MemberDto member = (MemberDto) request.getAttribute("member");
    if (member == null) {
        response.sendRedirect("adminMemberList.jsp");
        return;
    }
%>

<div class="container">
    <h2>회원 정보 수정</h2>

    <!-- 회원 정보 수정 폼 -->
    <form action="<%= request.getContextPath() %>/admin/member/updateAction" method="post">
        <input type="hidden" name="no" value="<%= member.getNo() %>">
        
        <table class="member-info-table">
            <tr>
                <th>이메일</th>
                <td><input type="text" value="<%= member.getEmail() %>" disabled></td>
            </tr>
            <tr>
                <th>비밀번호</th>
                <td><input type="password" name="pwd" placeholder="변경할 비밀번호 입력"></td>
            </tr>
            <tr>
                <th>이름</th>
                <td><input type="text" name="name" value="<%= member.getName() %>"></td>
            </tr>
            <tr>
                <th>휴대폰번호</th>
                <td><input type="text" name="tel" value="<%= member.getTel() %>"></td>
            </tr>
            <tr>
                <th>회원등급</th>
                <td>
                    <select name="priv">
                        <option value="USER" <%= member.getPriv().equals("USER") ? "selected" : "" %>>일반회원</option>
                        <option value="ADMIN" <%= member.getPriv().equals("ADMIN") ? "selected" : "" %>>관리자</option>
                    </select>
                </td>
            </tr>
        </table>

        <div class="button-group">
            <button type="button" class="update-btn">수정하기</button>
            <button type="button" class="cancel-btn" onclick="history.back();">취소</button>
        </div>
    </form>
</div>

<script>
    document.querySelector(".update-btn").addEventListener("click", function() {
        alert("폼이 제출됩니다!");
        document.querySelector("form").submit(); // 폼 제출 실행
    });
    
    window.onload = function () {
        document.querySelector(".update-btn").addEventListener("click", function () {
            alert("폼이 제출됩니다!");
            document.querySelector("form").submit();
        });
    };
</script>

</body>
</html>
