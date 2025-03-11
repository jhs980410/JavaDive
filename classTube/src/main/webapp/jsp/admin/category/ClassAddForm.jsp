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
			<label>클래스명: <input type='text' name='className'></label><br> 
			<label>가격: <input type='text' name='price'></label><br> 
			<label>설명: <textarea name="classDesc" cols="30" rows="5"></textarea></label><br>
			<label>호스트: <input type='text' name='instructor'></label><br>
			<label>정원: <input type='text' name='classLimit'></label><br>
			<label>지역: <input type='text' name='region' placeholder="서울/성북구/종암로"></label><br>
			<label>카테고리: <select name="categoryNo" class = "select">
                    	<option value="1">레져/스포츠</option>
                    	<option value="2">뷰티</option>
                    	<option value="3">자기계발</option>
                    	<option value="4">핸드메이드</option>
                    	<option value="5">쿠킹</option>
                </select> </label>
			<input type='submit' value='추가'> <input type='reset' value='취소'>
			
		</form>
	</div>

</body>

<script type="text/javascript">
	
</script>

</html>