<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>InsertTitle</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/category/classList.css">
</head>

<body>

	<div class="container">
	<img class="logo" src="../images/ctube.jpg">
		<h2>클래스 목록</h2>

		<div class="listWrap">
			<div class="list">
			
				<c:if test="${empty odClassList}">
					<p>클래스 목록이 비어 있습니다.</p>
				</c:if>	
				<c:forEach var="odClassDto" items="${odClassList}">
					<div class="classObj">
						<a href="./update?classNo=${odClassDto.getClassNo()}" style="text-decoration:none;">
							<div class="cssClass">
								<%-- <img src="${odClassDto.getImg()}"> --%>
								<img class="classImg" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSZ37Yi1ANvy4Ma4F2lnGQvHWi7OmoaAHS9Lg&s">
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
									<p>${odClassDto.getPrice()}원</p>
								</div>
							</div>
						</a>
					</div>
				</c:forEach>
			</div>

		</div>



	</div>

</body>

<script type="text/javascript">
	
</script>

</html>