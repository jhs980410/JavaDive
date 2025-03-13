<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>InsertTitle</title>

</head>

<body>

	<div class="container">

		<h1>클래스 수정</h1>
		<div class="">
		<form action='./update' method='post'>
			클래스 번호: <input type='text' name='classNo' value="${odClassDto.getClassNo()}" readonly><br>
			클래스명: <input type='text' name='className' value="${odClassDto.getClassName()}" readonly><br> 
			가격: <input type='text' name='price' value="${odClassDto.getPrice()} readonly"><br> 
			설명: <input type='text' name='classDesc' value="${odClassDto.getClassDesc()}" readonly><br>
			호스트:  <input type='text' name='instructor' value="${odClassDto.getInstructor()}" readonly><br>
			정원:  <input type='text' name='classLimit' value="${odClassDto.getClassLimit()}" readonly><br>
			지역:  <input type='text' name='region' placeholder="서울/성북구/종암로"  value="${odClassDto.getRegion()}" readonly><br>
			카테고리: <select name="categoryNo" class = "select"	>
                    	<option value="1">레져/스포츠</option>
                    	<option value="2">뷰티</option>
                    	<option value="3">자기계발</option>
                    	<option value="4">핸드메이드</option>
                    	<option value="5">쿠킹</option>
                	</select> 
				<input type='submit' value='변경'> <input type='button' value='삭제' onclick='location.href="./delete?classNo=${odClassDto.getClassNo()}";'>
				<input type='button' value='목록 보기' onclick='location.href="./list";'>
			
			</form>
		</div>
	</div>
</body>

<script type="text/javascript">
	
</script>

</html>