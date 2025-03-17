<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>클래스 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/category/classList.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/common/adminHeader.css">
</head>

<body>
<%@ include file="../common/adminHeader.jsp"%>
	<div class="container">		
	<div class="listWrap">
		<div class="list">
			<div class="addClass"><a href="./add">추가</a></div>
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

		<c:set var="pageGroupSize" value="10" />
		<!-- startPage 계산: currentPage가 10보다 크면 그룹이 바뀌어야 함 -->
		<c:set var="startPage"
			value="${(currentPage - 1) / pageGroupSize * pageGroupSize + 1}" />
		<!-- endPage 계산: startPage + pageGroupSize - 1, 단 totalPage보다 크면 endPage는 totalPage로 설정 -->
		<c:set var="endPage" value="${startPage + pageGroupSize - 1}" />

		<c:if test="${endPage > totalPage}">
			<c:set var="endPage" value="${totalPage}" />
		</c:if>

		<ul class="pagination">
			<c:if test="${totalPage > 1}">
				<!-- 이전 버튼 -->
				<c:if test="${currentPage > 1}">
					<li class="page-item"><a class="page-link"
						href="/classTube/admin/boardList?page=${currentPage - 1}${not empty keyword ? '&keyword=' : ''}${keyword}">이전</a>
					</li>
				</c:if>

				<!-- 페이지 숫자 버튼 -->
				<c:forEach var="i" begin="1" end="${endPage}">
					<li class="page-item ${currentPage == i ? 'active' : ''}"><a
						class="page-link"
						href="/classTube/admin/boardList?page=${i}${not empty keyword ? '&keyword=' : ''}${keyword}">${i}</a>
					</li>
				</c:forEach>

				<!-- 다음 버튼 -->
				<c:if test="${currentPage < totalPage}">
					<li class="page-item"><a class="page-link"
						href="/classTube/admin/boardList?page=${currentPage + 1}${not empty keyword ? '&keyword=' : ''}${keyword}">다음</a>
					</li>
				</c:if>
			</c:if>
		</ul>

	</div>

</body>

<script type="text/javascript">

</script>

</html>