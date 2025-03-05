<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판글쓰기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/board/boardForm.css">


</head>
<body>
	<%@ include file="../common/sideBar.jsp" %>

    <div class="container">
          
        <h2>게시판 글쓰기</h2>
<form action="boardSubmit" method="post">

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
                <button type="submit">등록</button>
            </div>
        </form>
      <div>
    	
    </div>
    </div>

</body>
</html>