<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${odClassDto.getClassName()}</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/common/adminHeader.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/category/classDetail.css">
</head>
<body>
    <%@ include file="../common/commonHeader.jsp"%>
    <div class="container">
        <div class="class-detail">
            
            <div class="class-image">
                <!-- 이미지 자리 -->
                <img class="classImg" src="${odClassDto.getImg()}">
            </div>
            <h2>${odClassDto.getClassName()}</h2>
            <div class="detail-grid">
                <div class="detail-row">
                    <div class="detail-label">강사</div>
                    <div class="detail-value">${odClassDto.getInstructor()}</div>
                </div>
                <div class="detail-row">
                    <div class="detail-label">가격</div>
                    <div class="detail-value"><fmt:formatNumber type="number" value="${odClassDto.getPrice()}" />원</div>
                </div>
                <div class="detail-row">
                    <div class="detail-label">정원</div>
                    <div class="detail-value">${odClassDto.getClassLimit()} 명</div>
                </div>
                <div class="detail-row">
                    <div class="detail-label">카테고리</div>
                    <div class="detail-value">
                        <c:choose>
                            <c:when test="${odClassDto.getCategoryNo() == 1}">레져/스포츠</c:when>
                            <c:when test="${odClassDto.getCategoryNo() == 2}">뷰티</c:when>
                            <c:when test="${odClassDto.getCategoryNo() == 3}">자기계발</c:when>
                            <c:when test="${odClassDto.getCategoryNo() == 4}">핸드메이드</c:when>
                            <c:when test="${odClassDto.getCategoryNo() == 5}">쿠킹</c:when>
                            <c:otherwise>알 수 없음</c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="detail-row">
                    <div class="detail-label">지역</div>
                    <div class="detail-value">${odClassDto.getRegion()}</div>
                </div>
            </div>
            <div class="description">
                <h3>강의 설명</h3>
                <p>${odClassDto.getClassDesc()}</p>
            </div>
            <div class="button-group">
                <button class="list-btn" onclick="location.href='./view'">목록</button>
            </div>
        </div>
    </div>
</body>
</html>