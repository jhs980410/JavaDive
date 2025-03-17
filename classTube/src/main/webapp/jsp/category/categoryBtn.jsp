<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<header class="header">
    <div class="logo" onclick='location.href="${pageContext.request.contextPath}/adminMain";'>
        <img src="<%= request.getContextPath() %>/images/logo.PNG" alt="로고">
    </div>

  <nav class="nav-menu">
	<a href="<%= request.getContextPath() %>/category/list" class="nav-link" onclick="setActive(this)">전체</a>
	<a href="<%= request.getContextPath() %>/category/list?categoryNo=1" class="nav-link" onclick="setActive(this)">스포츠/레져</a>
	<a href="<%= request.getContextPath() %>/category/list?categoryNo=2" class="nav-link" onclick="setActive(this)">뷰티</a>
	<a href="<%= request.getContextPath() %>/category/list?categoryNo=3" class="nav-link" onclick="setActive(this)">자기계발</a>
	<a href="<%= request.getContextPath() %>/category/list?categoryNo=4" class="nav-link" onclick="setActive(this)">핸드메이드</a>
	<a href="<%= request.getContextPath() %>/category/list?categoryNo=5" class="nav-link" onclick="setActive(this)">쿠킹</a>
</nav>

</header>
