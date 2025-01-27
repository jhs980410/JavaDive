<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>테이블</title>
<style>
/* 전체 내용을 가운데 정렬 */
body {
	text-align: center;
	font-family: Arial, sans-serif;
}

/* 테이블을 가운데 정렬 */
table {
	margin: 20px auto; /* 위 아래 여백을 주고, 좌우 중앙 정렬 */
	border-collapse: collapse; /* 테이블 경계선 겹침 방지 */
}

/* 테이블 셀에 경계선 추가 */
table, td {
	border: 1px solid #000; /* 테이블과 셀의 경계선 */
}

/* 테이블 셀의 여백 */
td {
	padding: 10px;
}

/* 첫 번째 테이블(그룹핑)을 두 번째 테이블과 구분 */
.first-table td {
	border-bottom: none; /* 첫 번째 테이블에서 셀 사이 경계선 없앰 */
}
</style>
</head>
<body>
	<h1>표 만들기</h1>
	<h2>셀 합치기</h2>

	<!-- 첫 번째 테이블 -->
	<table class="first-table" border="1">
		<tr>
			<td>그룹핑</td>
			<td></td>
		</tr>
		<tr>
			<td>div</td>
			<td>요소</td>
		</tr>
		<tr>
			<td>.</td>
			<td>홈</td>
		</tr>
		<tr>
			<td>●</td>
			<td>회사소개</td>
		</tr>
	</table>

	<p></p>
	<p></p>
	<p></p>
	<p></p>

	<!-- 두 번째 테이블 (COPYRIGHT) -->
	<table border="1">
		<tr>
			<td colspan="4">COPYRIGHT ⓒ iCox. All Rights Reserved</td>
		</tr>
	</table>

	<p></p>
	<p></p>
	<p></p>
	<p></p>

	<table border="1">
		<tr>
			<td>SPAN</td>
			<td>DYTH</td>
			<td></td>
		</tr>
		<tr>
			<td>●</td>
			<td colspan="2">오늘의 BOOK</td>
		</tr>
		<tr>
			<td></td>
			<td colspan="2">HTML5와 CSS3</td>
		</tr>
	</table>
</body>
</html>
