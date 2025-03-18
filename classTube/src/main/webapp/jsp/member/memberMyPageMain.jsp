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
				<p>이름: ${memberInfo.name}</p>
				<p>이메일: ${memberInfo.email}</p>
				<p>
					가입일:
					<fmt:formatDate value="${memberInfo.create_at}" pattern="yyyy-MM-dd" />
				</p>
			</c:if>

			<c:if test="${empty memberInfo}">
				<p>회원 정보를 불러올 수 없습니다.</p>
			</c:if>

		</form>
     
    
	</div>
</body>
</html>