<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<header class="header">
    <div class="logo" onclick='location.href="${pageContext.request.contextPath}/main";'>
        <img src="<%= request.getContextPath() %>/images/logo.PNG" alt="로고">
    </div>

  <nav class="nav-menu">
	<a href="<%= request.getContextPath() %>/main" class="nav-link" onclick="setActive(this)">메인페이지</a>
	<a href="<%= request.getContextPath() %>/category/view" class="nav-link" onclick="setActive(this)">카테고리</a>
	<a href="<%= request.getContextPath() %>/boardList" class="nav-link" onclick="setActive(this)">게시판</a>
	<a href="<%= request.getContextPath() %>/myPageList" class="nav-link" onclick="setActive(this)">마이페이지</a>
	
	

</nav>

</header>
