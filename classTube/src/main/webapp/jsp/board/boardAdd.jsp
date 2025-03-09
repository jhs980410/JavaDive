<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판글쓰기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/board/boardAdd.css">


</head>
<body>
	<%@ include file="../common/sideBar.jsp" %>

    <div class="container">
          
        <h2>게시판 글쓰기</h2>
	<form action="${pageContext.request.contextPath}/boardAdd" method="post">
		<!--버튼을 누를때만 요청이 받음.-->
        <div class="form-group">
                
                <select name="category" class = "select">
                     <option value="default" selected>-- 말머리 --</option>
                    <option value="categoryNo1">공지사항</option>
                    <option value="categoryNo2">자유</option>
                    <option value="categoryNo3">정보</option>
                </select>
            </div>
            
        
            <div class="form-group">
                <label>제목</label>
                <input type="text" name="title" required>
            </div>
            <div class="form-group">
                <label>내용</label>
                <textarea name="content" rows="20" required></textarea>
            </div>
            <div class="button-group">
                <button type="reset" class="cancel">취소</button>
                <button id="SubBtu"type="submit" >등록</button>
            </div>
        </form>
      <div>
    	
    </div>
    </div>
	<script src="${pageContext.request.contextPath}/js/boardAdd.js"></script>
	<!--온클릭이벤트 생성예정 , 생성시 주석삭제 / -->
</body>
</html>