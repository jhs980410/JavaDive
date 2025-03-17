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
          
        <h2>게시판 수정</h2>
	<form action="${pageContext.request.contextPath}/boardUpdate" method="post">
		<!--버튼을 누를때만 요청이 받음.-->
        <div class="form-group">
                <input type="hidden" name="postId" value="${board.noteNo}">

            </div>
            
        
            <div class="form-group">
                <label>제목</label>
                <input type="text" name="title" value="${board.title}" required>
            </div>

            <div class="form-group">
                <label>내용</label>
                <textarea name="content" rows="20" required>${board.content}</textarea>
            </div>

            <div class="button-group">
            <button type ="button" class="but-view"
				onclick="location.href='/classTube/boardList'">뒤로 가기</button>
                <button type="reset" class="cancel">취소</button>
                <button id="SubBtn" type="submit">수정</button>
            </div>
        </form>
      <div>
    	
    </div>
    </div>
	<script src="${pageContext.request.contextPath}/js/board/boardUpdate.js"></script>
	<!--온클릭이벤트 생성예정 , 생성시 주석삭제 / -->
</body>
</html>