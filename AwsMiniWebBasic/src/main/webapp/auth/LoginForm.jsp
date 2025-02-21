<%@ page language="java" contentType="text/html; charset=UTF-8
	"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 로그인</title>

<style type="text/css">

</style>

</head>

<body>
<H2>사용자 로그인 </H2>

<form action="./login" method="post">
	<label>이메일</label>
	<input type="text" name="email" placeholder="ex:sc9999@test.com" value="">
	<br>
	<label>암호</label>
	<input type="password" name="password" value="">
	<br>
	<input type="submit" value="로그인">
</form>
</body>

<script type="text/javascript">

</script>
</html>