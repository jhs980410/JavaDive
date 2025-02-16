<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }
        body {
            display: flex;
            height: 100vh;
            background-color: #f4f4f4;
        }
        /* 사이드바 스타일 */
        .sidebar {
            width: 250px;
            background-color: #2c3e50;
            color: white;
            padding-top: 20px;
        }
        .sidebar h2 {
            text-align: center;
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }
        .sidebar ul {
            list-style: none;
            padding: 0;
        }
        .sidebar ul li {
            padding: 15px;
            cursor: pointer;
            text-align: center;
            position: relative;
        }
        .sidebar ul li:hover {
            background-color: #34495e;
        }
        
        .sidebar h2 {
    	text-align: center;
    	padding: 10px;
    	border-bottom: 1px solid #ddd;
    	margin-top: 100px; /* 관리자 메뉴를 아래로 내림 */
		}
        
        
        /* 숨겨진 서브메뉴 */
        .submenu {
            list-style: none;
            padding: 0;
            overflow: hidden;
            max-height: 0;
            transition: max-height 0.4s ease-in-out; /* 부드러운 슬라이드 효과 */
            background-color: #3b4b5a;
        }
        
        .submenu li {
            padding: 10px;
            text-align: center;
            cursor: pointer;
        }
        
        .submenu li:hover {
            background-color: #465a6e;
        }
        
        /* 메인 컨텐츠 영역 */
        .main-content {
            flex: 1;
            padding: 20px;
        }
        .dashboard {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 20px;
            margin-top: 20px;
        }
        .dashboard-card {
            background: white;
            padding: 20px;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            text-align: center;
        }
    </style>
</head>
<body>

    <!-- 사이드바 -->
    <div class="sidebar">
        <h2>관리자 메뉴</h2>
        <ul>
            <li onclick="toggleMenu('memberMenu')">회원 관리</li>
            <ul class="submenu" id="memberMenu">
                <li>가입회원 정보관리</li>
                <li>회원등급 관리</li>
                <li>정지회원 관리</li>
            </ul>

            <li onclick="toggleMenu('boardMenu')">게시판 관리</li>
            <ul class="submenu" id="boardMenu">
                <li>공지사항 추가</li>
                <li>게시글 삭제</li>
            </ul>

            <li onclick="toggleMenu('statsMenu')">통계</li>
            <ul class="submenu" id="statsMenu">
                <li>사용자 분석</li>
                <li>순위 (업체순위)</li>
            </ul>
        </ul>
    </div>

    <!-- 메인 컨텐츠 -->
    <div class="main-content">
        <h1>관리자 대시보드</h1>
        <div class="dashboard">
            <div class="dashboard-card">📊 방문자 수: 1200명</div>
            <div class="dashboard-card">📝 게시판 활동: 35개</div>
            <div class="dashboard-card">👥 신규 가입자: 12명</div>
        </div>
    </div>

    <script>
        function toggleMenu(menuId) {
            var menu = document.getElementById(menuId);
            
            if (menu.style.maxHeight === "0px" || menu.style.maxHeight === "") {
                menu.style.maxHeight = menu.scrollHeight + "px"; // 펼치기
            } else {
                menu.style.maxHeight = "0px"; // 접기
            }
        }
    </script>

</body>
</html>
