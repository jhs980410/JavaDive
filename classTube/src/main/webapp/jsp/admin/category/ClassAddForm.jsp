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

		<h1>클래스 등록</h1>
		<form action='./add' method='post'>
			클래스명: <input type='text' name='className'><br> 
			가격: <input type='text' name='price'><br> 
			설명: <input type='text' name='classDesc'><br>
			호스트:  <input type='text' name='instructor'><br>
			정원:  <input type='text' name='classLimit'><br>
			지역:  <input type='text' name='region' placeholder="서울/성북구/종암로"><br>
			카테고리: <select name="categoryNo" class = "select">
                    	<option value="1">레져/스포츠</option>
                    	<option value="2">뷰티</option>
                    	<option value="3">자기계발</option>
                    	<option value="4">핸드메이드</option>
                    	<option value="5">쿠킹</option>
                </select> 
			<input type='submit' value='추가'> <input type='reset' value='취소'>
			
		</form>
	</div>

</body>

<script type="text/javascript">
	
</script>

</html>