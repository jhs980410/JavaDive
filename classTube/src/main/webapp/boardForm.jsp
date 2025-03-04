<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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

input[type="text"], textarea {
    width: 100%;
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
    justify-content: center;
    margin-top: 20px;
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



</style>
</head>
<body>
	<%@ include file="WEB-INF/jsp/common/sideBar.jsp" %>

    <div class="container">
        <h2>게시판 글쓰기</h2>
        <form action="boardWrite" method="post">
            <div class="form-group">
                <label>제목</label>
                <input type="text" name="title" required>
            </div>
            <div class="form-group">
                <label>내용</label>
                <textarea name="content" rows="5" required></textarea>
            </div>
            <div class="form-group">
                <label>작성자</label>
                <input type="text" name="writer" required>
            </div>
            <div class="button-group">
                <button type="reset" class="cancel">취소</button>
                <button type="submit">등록</button>
            </div>
        </form>
    </div>
</body>
</html>