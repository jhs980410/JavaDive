<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>클래스 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/category/classList.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/common/adminHeader.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/category/categoryBtn.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/category/classSearch.css">
</head>

<body>
<%@ include file="../common/adminHeader.jsp"%>
<%@ include file="/jsp/admin/category/categoryBtn.jsp"%>	
	<div class="container">
		
	<div class="listWrap">
		<div class="list">
			
			<c:if test="${empty odClassList}">
				<p>클래스 목록이 비어 있습니다.</p>
			</c:if>	
				<c:forEach var="odClassDto" items="${odClassList}">
					<div class="classObj">
						<a href="./detail?classNo=${odClassDto.getClassNo()}" style="text-decoration:none;">
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

		<c:set var="pageGroupSize" value="10" />
<!-- startPage 계산 -->
<c:set var="step1" value="${currentPage + 9}" />
<fmt:parseNumber var="step2" value="${step1 / 10}" integerOnly="true" />
<c:set var="step3" value="${step2 - 1}" />
<c:set var="startPage" value="${step3 * 10 + 1}" />

<!-- endPage 계산 -->
<c:set var="endPage" value="${startPage + pageGroupSize - 1}" />

<!-- totalPage보다 endPage가 크면 endPage를 totalPage로 조정 -->
<c:if test="${endPage > totalPage}">
    <c:set var="endPage" value="${totalPage}" />
</c:if>

<ul class="pagination">
    <c:if test="${totalPage > 1}">
        <!-- 이전 버튼 -->
        <c:if test="${currentPage > 1}">
            <li class="page-item">
				<c:choose>
				    <c:when test="${currentPage-pageGroupSize < 1}">
				        <a class="page-link" href="/classTube/admin/category/list?page=1${not empty keyword ? '&keyword=' : ''}${keyword}">이전</a>
				    </c:when>
				    <c:otherwise>
				        <a class="page-link" href="/classTube/admin/category/list?page=${currentPage - 10}${not empty keyword ? '&keyword=' : ''}${keyword}">이전</a>
				    </c:otherwise>
				</c:choose>
            </li>
        </c:if>

        <!-- 페이지 숫자 버튼 -->
        <c:forEach var="i" begin="${startPage}" end="${endPage}">
            <li class="page-item ${currentPage == i ? 'active' : ''}">
                <a class="page-link" href="/classTube/admin/category/list?page=${i}${not empty keyword ? '&keyword=' : ''}${keyword}">${i}</a>
            </li>
        </c:forEach>

        <!-- 다음 버튼 -->
        <c:if test="${currentPage < totalPage}">
            <li class="page-item">
	            <c:choose>
				    <c:when test="${currentPage+pageGroupSize < totalPage}">
				        <a class="page-link" href="/classTube/admin/category/list?page=${currentPage+pageGroupSize}${not empty keyword ? '&keyword=' : ''}${keyword}">다음</a>
				    </c:when>
				    <c:otherwise>
				        <a class="page-link" href="/classTube/admin/category/list?page=${totalPage}${not empty keyword ? '&keyword=' : ''}${keyword}">다음</a>
				    </c:otherwise>
				</c:choose>
            </li>
        </c:if>
    </c:if>
</ul>
		
	</div>
	
	<div class="bottom-container">
			<!-- 검색창 (왼쪽 정렬) -->
			<form action="/classTube/admin/category/search" method="get"
				class="search-form">
				<div class="search-container">
					<div class="search">
						<div class="ui icon input">
							<input class="prompt" type="text" name="keyword" placeholder="검색">
							<input type="submit" value="검색">
						</div>
						<div class="results"></div>
					</div>
				</div>
			</form>


<div class="addClass">
        <a href="./add" class="add-btn">클래스 추가</a>
    </div>

			<p>
		</div>

</body>

<script type="text/javascript">

</script>

</html>