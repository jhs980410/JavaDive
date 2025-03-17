<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<header class="header">
    <div class="logo" onclick='location.href="${pageContext.request.contextPath}/adminMain";'>
        <img src="<%= request.getContextPath() %>/images/logo.PNG" alt="로고">
    </div>

  <nav class="nav-menu">
	<a href="<%= request.getContextPath() %>/admin/member/list" class="nav-link" onclick="setActive(this)">회원목록</a>
	<a href="#" class="nav-link" onclick="setActive(this)">회원관리</a>
	<a href="<%= request.getContextPath() %>/admin/category/list" class="nav-link" onclick="setActive(this)">카테고리</a>
	<a href="<%= request.getContextPath() %>/boardList" class="nav-link" onclick="setActive(this)">게시판</a>

</nav>

</header>
