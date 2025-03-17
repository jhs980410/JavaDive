<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 추가</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin/common/adminHeader.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin/board/adminBoardAdd.css">
</head>

</head>
<body>
	<%@ include file="../common/adminHeader.jsp"%>

	<div class="container">
		

		<h2>공지사항 추가</h2>
		<form action="${pageContext.request.contextPath}/admin/boardAdd"
			method="post">
			<!--버튼을 누를때만 요청이 받음.-->
			<div class="form-group">

				<select name="category" class="select">

					<c:set var="user" value="${sessionScope.member}" />
					<c:if test="${user.priv == 'ADMIN'}">
						<option value="categoryNo1" selected>공지사항</option>
					</c:if>

					<option value="categoryNo2">자유</option>
					<option value="categoryNo3">정보</option>
				</select>
			</div>


			<div class="form-group">
				<label>제목</label> <input type="text" name="title" required>
			</div>
			<div class="form-group">
				<label>내용</label>
				<textarea name="content" rows="20" required></textarea>
			</div>
			<div class="button-group">
			<button type="button" class="but-view"
				onclick="location.href='/classTube/admin/boardList'">게시글
				목록</button>
				<button type="reset" class="cancel">취소</button>
				<button id="SubBtu" type="submit">등록</button>
			</div>
		</form>
		<div></div>
	</div>
	<script src="${pageContext.request.contextPath}/js/board/boardAdd.js"></script>
	<!--온클릭이벤트 생성예정 , 생성시 주석삭제 / -->
</body>
</html>