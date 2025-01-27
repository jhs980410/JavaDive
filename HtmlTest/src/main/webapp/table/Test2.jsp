<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>6x11 테이블</title>
<style>
table {
	border-collapse: collapse;
	width: 100%;
}

table, th, td {
	border: 1px solid black;
}

th, td {
	padding: 10px;
	text-align: center;
}

.row1 td:first-child {
	background-color: gray;
	text-align: left;
}

.row1 td:nth-child(2) {
	background-color: gray;
	text-align: center;
	padding: 1px;
}
</style>
</head>
<body>

	<table>
		<tr class="row1">
			<td colspan="5">자유게시판</td>
			<td>more</td>
			<td></td>
			<td colspan="5">자유게시판</td>
			<td>more</td>
		</tr>
		<tr>
			<td colspan="5">자유게시판컨텐츠1</td>
			<td>10</td>
			<td></td>
			<td colspan="5">모집공고 컨텐츠1</td>
			<td>10</td>
		</tr>
		<tr>
			<td colspan="5">자유게시판컨텐츠2</td>
			<td>10</td>
			<td></td>
			<td colspan="5">모집공고 컨텐츠2</td>
			<td>10</td>
		</tr>
		<tr>
			<td colspan="5">자유게시판컨텐츠3</td>
			<td>10</td>
			<td></td>
			<td colspan="5">모집공고 컨텐츠3</td>
			<td>10</td>
		</tr>
		<tr>
			<td colspan="5">자유게시판컨텐츠4</td>
			<td>10</td>
			<td></td>
			<td colspan="5">모집공고 컨텐츠4</td>
			<td>10</td>
		</tr>
		<tr>
			<td colspan="5">자유게시판컨텐츠4</td>
			<td>10</td>
			<td></td>
			<td colspan="5">모집공고 컨텐츠4</td>
			<td>10</td>
		</tr>
	</table>

</body>
</html>
