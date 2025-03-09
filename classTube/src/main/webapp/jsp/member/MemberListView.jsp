<%-- <%@page import="spms.dto.MemberDto"%> --%>
<%-- <%@page import="java.util.ArrayList"%> --%>
<%@ page language='java' contentType='text/html; charset=UTF-8'
	pageEncoding='UTF-8'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>

<title>회원 목록</title>

</head>
<body>

	<h1>회원목록</h1>

	<p>
		<a href="./add">신규 회원</a>
	</p>

	<c:forEach var="memberDto" items="${memberList}">
	
		${memberDto.getNo()},
		<a href="./update?no=${memberDto.getNo()}">${memberDto.getName()}</a>,
		${memberDto.getEmail()},
		${memberDto.getCreatedDate()}
		<a href="./delete?no=${memberDto.getNo()}">[삭제]</a><br>
	
	</c:forEach>
   
	

</body>
</html>