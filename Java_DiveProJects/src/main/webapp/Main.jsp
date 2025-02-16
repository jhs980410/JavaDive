<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }
        body {
            display: flex;
            flex-direction: column;
            align-items: center;
            background-color: #f9f9f9;
        }
        
        /* 헤더 */
        .header {
            width: 100%;
            padding: 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .logo {
            font-size: 24px;
            font-weight: bold;
        }

        .search-container {
            display: flex;
            align-items: center;
            gap: 10px;
        }
		/*겅색창 크기조절,로고 택스트 크기에따라 조절필요,  */
        .search-bar {
            width: 500px;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .search-btn {
            padding: 8px 12px;
            background-color: #2c6ed5;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 5px;
        }

        .login-btn {
            padding: 8px 12px;
            background-color: #2c6ed5;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 5px;
        }

        /* 네비게이션 바 */
        .nav-bar {
            width: 80%;
            display: flex;
            justify-content: space-around;
            padding: 15px;
            background-color: #fff;
            box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
            margin-top: 10px;
        }

        .nav-item {
            padding: 10px 20px;
            cursor: pointer;
            border: 1px solid #ddd;
            background-color: #fff;
            border-radius: 5px;
        }

        /* 인기 클래스 */
        .popular-section {
            display: flex;
            align-items: center;
            margin: 20px 0;
        }

        .popular-btn {
            padding: 15px;
            background-color: #2c6ed5;
            color: white;
            cursor: pointer;
            border: none;
            border-radius: 5px;
        }

        .popular-content {
            width: 600px;
            text-align: center;
            padding: 15px;
            border: 1px solid #ddd;
            background-color: #fff;
            border-radius: 5px;
        }

        /* 추천 클래스 목록 */
        .class-list {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 15px;
            margin: 20px 0;
        }

        .class-item {
            width: 180px;
            height: 150px;
            display: flex;
            align-items: center;
            justify-content: center;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);
        }
        /* 이미지의 스타일 조정 CSS  */
        .class-item img {
   		 width: 100%;
    	height: auto;
    	border-radius: 8px;
		}

        /* 하단 광고 및 이용약관 */
        .footer {
            width: 80%;
            text-align: center;
            padding: 15px;
            margin: 20px 0;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
    </style>
</head>
<body>

    <!-- 헤더 -->
    <div class="header">
        <div class="logo">JAVA_DIVE</div>
        <div class="search-container">
            <input type="text" class="search-bar" placeholder="검색창">
            <button class="search-btn">검색</button>
        </div>
        <button class="login-btn">마이페이지</button>
    </div>

    <!-- 네비게이션 바 -->
    <div class="nav-bar">
        <div class="nav-item">카테고리</div>
        <div class="nav-item">회원게시판</div>
    </div>

    <!-- 인기 클래스 -->
    <div class="popular-section">
        <button class="popular-btn"><<</button>
        <div class="popular-content">인기 클래스 TOP 10</div>
        <button class="popular-btn">>></button>
    </div>

    <!-- 추천 클래스 목록 -->
    <div class="class-list">
        <div class="class-item">추천 클래스 목록</div>
        <div class="class-item">추천 클래스 목록</div>
        <div class="class-item">추천 클래스 목록</div>
        <div class="class-item">추천 클래스 목록</div>
        <div class="class-item">추천 클래스 목록</div>
        <div class="class-item">추천 클래스 목록</div>
        <div class="class-item">추천 클래스 목록</div>
        <div class="class-item">추천 클래스 목록</div>
        <div class="class-item">추천 클래스 목록</div>
    </div>  
  <!--   1.정적인 이미지 추가 (img 태그사용) 혹은
    2. 동적으로 데이터베이스에서 불러 오기 (JSP ,JS ,API등등 )
 -->
    <!-- 광고 -->
    <div class="footer">광고</div>

    <!-- 이용 약관 -->
    <div class="footer">이용 약관</div>

</body>
</html>
