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

	<div class="container">

		<h2>클래스 목록</h2>

		<div class="listWrap">
			<div class="list">
			
				<c:if test="${empty odClassList}">
					<p>클래스 목록이 비어 있습니다.</p>
				</c:if>	
				<c:forEach var="odClassDto" items="${odClassList}">
					<div class="classObj">
						<a href="" style="text-decoration:none;">
							<div class="cssClass">
								<img src="${odClassDto.getImg()}"> 이미지 대신
							</div>
							<div>
								<div>
									<p>${odClassDto.getClassName()}</p>
								</div>
								<div>
									<p>${odClassDto.getInstructor()}</p>
								</div>
								<div>
									<p>${odClassDto.getRegion()}</p>
								</div>
								<div>
									<p>${odClassDto.getPrice()}</p>
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