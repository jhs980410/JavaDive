<%@page import="spms.dto.MemberDto"%> <%-- MemberDto 클래스를 사용하기 위해 import --%>
<%@page import="java.util.ArrayList"%> <%-- ArrayList를 사용하기 위해 import --%>
<%@ page language='java' contentType='text/html; charset=UTF-8'
	pageEncoding='UTF-8'%> <%-- 페이지의 인코딩을 UTF-8로 설정하여 한글 깨짐 방지 --%>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'> <%-- HTML 문서의 문자 인코딩을 UTF-8로 설정 --%>
<title>회원목록</title> <%-- 페이지 제목 설정 --%>
</head>
<body>
	<jsp:include page="/Header.jsp"/>
	<h1>회원목록</h1> <%-- 회원 목록 제목 표시 --%>

	<p>
		<a href='./add'>신규 회원</a> <%-- 신규 회원 추가 링크 --%>
	</p>
	
	<%
      // request 객체에서 "memberList" 속성을 가져와 ArrayList<MemberDto>로 형변환
      ArrayList<MemberDto> memberList = 
   	      (ArrayList<MemberDto>)request.getAttribute("memberList");

      // memberList에 있는 회원 정보를 반복문을 통해 출력
      for(MemberDto memberDto : memberList){
   %>   
      
      <%= memberDto.getNo() %>, <%-- 회원 번호 출력 --%> 
        <a href='./update?mNo=<%= memberDto.getNo() %>'><%= memberDto.getName() %></a>, <%-- 회원 정보 수정 링크 --%>
        <%= memberDto.getEmail() %>, <%-- 회원 이메일 출력 --%>
        <%= memberDto.getCreatedDate() %>, <%-- 회원 가입일 출력 --%>
        <a href='./delete?mNo=<%= memberDto.getNo() %>'>[삭제]</a> <%-- 회원 삭제 링크 --%>
   <br>
   <%   
      }
   %>

	<jsp:include page="/Tail.jsp"/>	

</body>
</html>  
