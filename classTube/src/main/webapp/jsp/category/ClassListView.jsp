<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>InsertTitle</title>

</head>

<body>
	<%@ include file="../common/sideBar.jsp"%>

	<div class="container">

		<h2>클래스 목록</h2>

		<div class="listWrap">
			<div class="list">
				<c:forEach var="odClassDto" items="${odClassList}">
					<div class="classObj">
						<a href="">
							<div class="cssClass">
								<img src="${odClassDto.getImg()}">
							</div>
							<div>
								<div><p>${odClassDto.getClassName()}</p></div>
								<div><p>${odClassDto.getInstructor()}</p></div>
								<div><p>${odClassDto.getRegion()}</p></div>
								<div><p>${odClassDto.getPrice()}</p></div>
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