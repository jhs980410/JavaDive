<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판보기</title>
<style type="text/css">
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    display: flex;
}

/* ✅ 사이드바 스타일 */
.sidebar {
    width: 250px;
    background-color: #f8f9fa;
    padding: 20px;
    min-height: 100vh;
    position: fixed;
    left: 0;
    top: 0;
}

/* ✅ 본문 컨텐츠를 오른쪽으로 배치 */
.container {
    flex-grow: 1;
    padding: 20px;
    margin-left: 270px; /* 사이드바 너비 + 여백 추가 */
    width: calc(100% - 270px); /* 전체 너비에서 사이드바 공간을 제외 */
}

h2 {
    text-align: center;
    margin-bottom: 20px;
}

form {
    width: 80%;
    margin: 0 auto;
}

.form-group {
    margin-bottom: 15px;
}

label {
    font-weight: bold;
    display: block;
}
input[type="text"] {
    width: 100%; /* 부모 컨테이너 전체 너비 사용 */
    max-width: 800px; /* 최대 너비 설정 */
    height: 40px; /* 높이 조정 */
    padding: 10px;
    font-size: 16px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-sizing: border-box; /* padding 포함한 크기 조정 */
}



input[type="text"], textarea {
    width: 100%;
    height:55%;
    padding: 8px;
    border: 1px solid #ddd;
    border-radius: 5px;
}

.file-upload {
    display: flex;
    align-items: center;
}

.file-upload input {
    margin-left: 10px;
}

.button-group {
    display: flex;
    justify-content: flex-end;
    margin-top: 200px;
}

button {
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

button.cancel {
    background-color: #dc3545;
    color: white;
    margin-right: 10px;
}

button[type="submit"] {
    background-color: #007bff;
    color: white;
}

.select{
	margin-left: 75px;
	margin-bottom: 35px;
}

</style>
</head>
<body>
	<%@ include file="../common/sideBar.jsp" %>

    <div class="container">
          
        <h2>게시판 글쓰기</h2>
        <div class="form-group">
                
                <select name="category" class = "select">
                     <option value="default" selected>-- 말머리 --</option>
                    <option value="자유">자유</option>
                    <option value="질문">질문</option>
                    <option value="정보">정보</option>
                </select>
            </div>
            
        <form action="boardWrite" method="post">
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