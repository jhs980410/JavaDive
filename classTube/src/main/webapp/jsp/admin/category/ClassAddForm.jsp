<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>InsertTitle</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/common/adminHeader.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/category/classAdd.css">
</head>

<body>
<%@ include file="../common/adminHeader.jsp"%>
	<div class="container">
		<br>
		<br>
		<div class="addForm">
		<form action='./add' method='post'>
			<table>
				<tr>
					<td class="colsLabel"><label>클래스명</label></td>
					<td class="colsBlank"></td>
					<td colspan=2><input type='text' name='className'></td>
					<td colspan=4>
				</tr>
				<tr>
					<td class="colsLabel"><label>설명</label></td>
					<td class="colsBlank"></td>
					<td colspan=6><textarea name='classDesc' cols="30" rows="40"></textarea></td>
				</tr>
				<tr>
					<td class="colsLabel"><label>호스트</label></td>
					<td class="colsBlank"></td>
					<td colspan=2><input type='text' name='instructor'></td>
					<td colspan=4></td>
				</tr>
				<tr>
					<td class="colsLabel"><label>가격</label></td>
					<td class="colsBlank"></td>
					<td><input type='text' name='price'></td>
					<td></td>
					<td class="colsLabel left-align"><label>정원</label></td>
					<td class="colsBlank"></td>
					<td><input type='text' name='classLimit'></td>
					<td></td>
				</tr>
				<tr>
				<tr>
					<td class="colsLabel"><label>카테고리</label></td>
					<td class="colsBlank"></td>
					<td><select name="categoryNo" class="select">
                    		<option value="1">레져/스포츠</option>
                    		<option value="2">뷰티</option>
                  	  		<option value="3">자기계발</option>
                   		 	<option value="4">핸드메이드</option>
                    		<option value="5">쿠킹</option>
            		    </select> </td>
            		<td></td>
					<td class="colsLabel"><label>지역</label></td>
					<td class="colsBlank"></td>
					<td><input type='text' name='region' placeholder="서울/성북구/종암로"></td>
					<td></td>
				</tr>
				
				<tr class="btnGroup">
				<td colspan=8><input type='submit' class="btn" value='추가'> <input type='reset' class="btn" value='취소'></td>
				</tr>
			</table>
		</form>
		</div>
	</div>

</body>

<script type="text/javascript">
	
</script>

</html>