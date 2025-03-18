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
<%@ include file="../common/commonHeader.jsp"%>
<%@ include file="./categoryBtn.jsp"%>
	<div class="container">
	<h2>일반 유저가 보는 detail 화면</h2>
		<br>
		<br>
		<div class="classView">
		<input type="hidden" name="classNo" value="${odClassDto.getClassNo()}">
			<table>
				<tr>
					<td class="colsLabel"><label>클래스명</label></td>
					<td class="colsBlank"></td>
					<td colspan=2><input type='text' name='className' value="${odClassDto.getClassName()}" readonly></td>
					<td colspan=4>
				</tr>
				<tr>
					<td class="colsLabel"><label>설명</label></td>
					<td class="colsBlank"></td>
					<td colspan=6><textarea name='classDesc' cols="30" rows="35" readonly>${odClassDto.getClassDesc()}</textarea></td>
				</tr>
				<tr>
					<td class="colsLabel"><label>호스트</label></td>
					<td class="colsBlank"></td>
					<td colspan=2><input type='text' name='instructor'  value="${odClassDto.getInstructor()}" readonly></td>
					<td colspan=4></td>
				</tr>
				<tr>
					<td class="colsLabel"><label>가격</label></td>
					<td class="colsBlank"></td>
					<td><input type='text' name='price' value="${odClassDto.getPrice()}" readonly></td>
					<td></td>
					<td class="colsLabel left-align"><label>정원</label></td>
					<td class="colsBlank"></td>
					<td><input type='text' name='classLimit' value="${odClassDto.getClassLimit()}" readonly></td>
					<td></td>
				</tr>
				<tr>
				<tr>
					<td class="colsLabel"><label>카테고리</label></td>
					<td class="colsBlank"></td>
					<td><select name="categoryNo" class="select"
						onFocus="this.initialSelect = this.selectedIndex;" 
        				onChange="this.selectedIndex = this.initialSelect;">
						<option value="1" ${odClassDto.getCategoryNo() == '1' ? 'selected' : ''}>레져/스포츠</option>
   						<option value="2" ${odClassDto.getCategoryNo() == '2' ? 'selected' : ''}>뷰티</option>
   						<option value="3" ${odClassDto.getCategoryNo() == '3' ? 'selected' : ''}>자기계발</option>
  						<option value="4" ${odClassDto.getCategoryNo() == '4' ? 'selected' : ''}>핸드메이드</option>
  						<option value="5" ${odClassDto.getCategoryNo() == '5' ? 'selected' : ''}>쿠킹</option> </select></td>
            		<td></td>
					<td class="colsLabel"><label>지역</label></td>
					<td class="colsBlank"></td>
					<td><input type='text' name='region' value="${odClassDto.getRegion()}" readonly></td>
					<td></td>
				</tr>
				
				<tr class="btnGroup">
				<td colspan=8><input type='button' class="btn" value='목록' onclick='location.href="./view";'></td>
				</tr>
			</table>
		
		</div>
	</div>

</body>

<script type="text/javascript">
	
</script>

</html>