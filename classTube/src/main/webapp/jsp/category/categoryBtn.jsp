<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

  <nav class="nav-sub-menu">
		<form action="<%= request.getContextPath() %>/category/search" method="get">
		    <input type="hidden" name="categoryNo" value="1">
		    <button type="submit">스포츠/레저</button>
		</form>
		
		<form action="<%= request.getContextPath() %>/category/search" method="get">
		    <input type="hidden" name="categoryNo" value="2">
		    <button type="submit">뷰티</button>
		</form>
		
		<form action="<%= request.getContextPath() %>/category/search" method="get">
		    <input type="hidden" name="categoryNo" value="3">
		    <button type="submit">자기계발</button>
		</form>
		
		<form action="<%= request.getContextPath() %>/category/search" method="get">
		    <input type="hidden" name="categoryNo" value="4">
		    <button type="submit">핸드메이드</button>
		</form>
		
		<form action="<%= request.getContextPath() %>/category/search" method="get">
		    <input type="hidden" name="categoryNo" value="5">
		    <button type="submit">쿠킹</button>
		</form>
</nav>
