<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="JavaDive.dto.member.MemberDto" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 상세 정보</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin/common/adminHeader.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin/member/adminMemberInfo.css">
    
    <script type="text/javascript">
    	function updateActionFnc() {
    		var memberNoObj = document.getElementById('no');
    		
    		if (!memberNoObj || memberNoObj.value) {
				alert("회원 번호가 없습니다.")
			}
    		location.href = '<%= request.getContextPath() %>/admin/member/updateAction?no=' + memberNoObj.value;
		}
    </script>
</head>
<body>

<%@ include file="../common/adminHeader.jsp" %>

<div class="container">
    <h2>회원 상세 정보</h2>

    <% 
        MemberDto member = (MemberDto) request.getAttribute("member");
        if (member == null) { 
    %>
        <p>회원 정보를 불러올 수 없습니다.</p>
    <% } else { %>
    
    <table class="member-info-table">
        <tr>
            <th>회원번호</th>
            <td><%= member.getNo() %></td>
        </tr>
        <tr>
            <th>이메일</th>
            <td><%= member.getEmail() %></td>
        </tr>
        <tr>
            <th>이름</th>
            <td><%= member.getName() %></td>
        </tr>
        <tr>
            <th>휴대폰번호</th>
            <td><%= member.getTel() %></td>
        </tr>
        <tr>
            <th>계정 생성일</th>
            <td><%= member.getCreate_at() %></td>
        </tr>
    </table>

    <% } %>

    <div class="button-group">
    <button onclick="updateActionFnc();">수정하기
    </button>

    <button onclick="location.href='<%= request.getContextPath() %>/admin/member/list'">
        회원목록
    </button>
    
    <input id='no' type="hidden" value="${member.getNo()}">
</div>

</div>

</body>
</html>
