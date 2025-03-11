<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<header class="header">
    <div class="logo">
        <img src="<%= request.getContextPath() %>/images/ctube_logo.png" alt="로고" width="120">
    </div>

  <nav class="nav-menu">
    <a href="#" class="nav-link active">회원목록</a>
    <a href="#" class="nav-link">회원관리</a>
    <a href="<%= request.getContextPath() %>/admin/category/list" class="nav-link">클래스관리</a>
    <a href="<%= request.getContextPath() %>/admin/board/list"" class="nav-link">게시판관리</a>
</nav>

</header>
