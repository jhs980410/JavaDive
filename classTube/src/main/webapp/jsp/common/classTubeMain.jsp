<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/category/classList.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/common/adminHeader.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/category/categoryBtn.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/classTubeMain.css">
</head>

<body>
<%@ include file="./commonHeader.jsp"%>
	
	<form class= "formlogout" action="/classTube/logout" method="get">
		<button class= "logout" type="submit">로그아웃</button>
	</form>

<%@ include file="/jsp/category/categoryBtn.jsp"%>

<div class="container">
	
		

		<div class="listWrap">
			<div class="list">
				<c:if test="${empty odClassList}">
					<p>클래스 목록이 비어 있습니다.</p>
				</c:if>	
				<c:forEach var="odClassDto" items="${odClassList}">
					<div class="classObj">
						<a href="./category/detail?classNo=${odClassDto.getClassNo()}">
							<div class="cssClass">
								<img class="classImg" src="${odClassDto.getImg()}">
							</div>
							<div>
							 	<div class="name">
									<p>${odClassDto.getClassName()}</p>
								</div>
								<div class="instructor">
									<p>${odClassDto.getInstructor()}</p>
								</div>
								<div class="region">
									<p>${odClassDto.getRegion()}</p>
								</div>
								<div class="price">
									<p><fmt:formatNumber type="number" value="${odClassDto.getPrice()}" />원</p>
								</div>
							</div>
						</a>
					</div>
				</c:forEach>
			</div>

		</div>



	</div>

</body>
<script src="${pageContext.request.contextPath}/js/admin/adminHeader.js"></script>

</html>