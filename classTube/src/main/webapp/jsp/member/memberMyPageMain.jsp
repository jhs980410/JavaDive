<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/member/memberMyPageMain.css">
</head>
<body>
	<%@ include file="../common/myPageSideBar.jsp"%>
	<div class="container">
		<form action="myPageList" method="get">
			<h2>내 정보</h2>
			<c:if test="${not empty memberInfo}">
				<div class="info-group">
				<span class="info-label">이름:</span> <span class="info-value">${memberInfo.name}</span>
			</div>
			<div class="info-group">
				<span class="info-label">이메일:</span> <span class="info-value">${memberInfo.email}</span>
			</div>
			<div class="info-group">
				<span class="info-label">전화번호:</span> <span class="info-value">${memberInfo.tel}</span>
			</div>
			<div class="info-group">
				<span class="info-label">회원등급:</span> <span class="info-value">${memberInfo.priv}</span>
			</div>
			<div class="info-group">
				<span class="info-label">가입일:</span> 
				<span class="info-value"><fmt:formatDate value="${memberInfo.create_at}" pattern="yyyy-MM-dd" /></span>
			</div>
			</c:if>

			<c:if test="${empty memberInfo}">
				<p>회원 정보를 불러올 수 없습니다.</p>
			</c:if>

		</form>
     
    
	</div>
</body>
</html>