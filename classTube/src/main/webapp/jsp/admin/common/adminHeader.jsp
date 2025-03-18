<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<header class="header">
    <div class="logo" onclick='location.href="${pageContext.request.contextPath}/adminMain";'>
        <img src="<%= request.getContextPath() %>/images/logo.PNG" alt="로고">
    </div>

  <nav class="nav-menu">
	<a href="<%= request.getContextPath() %>/admin/member/list" class="nav-link" onclick="setActive(this)">회원관리</a>
	<a href="<%= request.getContextPath() %>/admin/category/list" class="nav-link" onclick="setActive(this)">클래스관리</a>
	<a href="<%= request.getContextPath() %>/admin/boardList" class="nav-link" onclick="setActive(this)">게시판관리</a>
	<a href="<%= request.getContextPath() %>/logout" class="nav-link" onclick="setActive(this)">로그아웃</a>
</nav>

</header>
