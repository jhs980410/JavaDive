<%@page import="spms.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
   MemberDto memberDto = (MemberDto)session.getAttribute("memberDto");
%>

<div style="background-color: #00008b; color:#ffffff; height:20px; padding:5px;">
   SPMS(Simple Project Management System)
   <span style="float: right;">
      <%=memberDto.getName()%>
   </span>
</div>