<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="JavaDive.dto.board.BoardDto" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판보기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/board/boardView.css">
</head>
<body>
	<%@ include file="../common/sideBar.jsp" %>

    <div class="container">
          
        <h2>게시판 상세페이지</h2>
        <div class="form-group">
                
                <select name="category" class = "select">
                 <!-- 카테고리번호 예정 -->
                </select>
            </div>
            
        <form action="boardWrite" method="post">
        <c:set var="boardDto" value="${sesionScope.boardDto}" />
        <c:set var="recentPostId" value="${sesionScope.recentPostId}" />
      
            <div class="form-group">
                <label>제목</label>
                <p>${boardDto.title}</p>
            </div>
            <div class="form-group">
                <label>내용</label>
               <p>${boardDto.content}</p>
            </div>
              <c:remove var="boardDto" scope="session" />
    <c:remove var="recentPostId" scope="session" />
	
            <div class="button-group">
                <button type="reset" class="cancel">취소</button>
                <button type="submit">등록</button>
            </div>
        </form>
      <div>
    	
    </div>
    </div>

</body>
</html>