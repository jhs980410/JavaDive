<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List, JavaDive.dto.member.MemberDto" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원관리 페이지</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin/common/adminHeader.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin/member/adminMemberList.css">
</head>
<body>

<%@ include file="../common/adminHeader.jsp" %> <!-- 🔹 관리자 헤더 포함 -->

<div class="container">
    <h2>회원관리 페이지</h2>

    <!-- 회원 목록 테이블 -->
    <table class="member-table">
        <thead>
            <tr>
                <th>회원번호</th>
                <th>E_mail</th>
                <th>휴대폰번호</th>
                <th>회원등급</th>
                <th>계정생성일</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<MemberDto> memberList = (List<MemberDto>) request.getAttribute("memberList");
            
                if (memberList != null && !memberList.isEmpty()) {
                    for (MemberDto member : memberList) {
            %>
            <tr>
                <td><%= member.getNo() %></td>
                <td><%= member.getEmail() %></td>
                <td><%= member.getTel() %></td>
                <td><%= member.getPriv() %></td>
                <td><%= member.getCreate_at() %></td>
            </tr>
            <% 
                    }
                } else { 
            %>
            <tr>
                <td colspan="6">회원 정보가 없습니다.</td>
            </tr>
            <% } %>
        </tbody>
    </table>
</div>

</body>
</html>
