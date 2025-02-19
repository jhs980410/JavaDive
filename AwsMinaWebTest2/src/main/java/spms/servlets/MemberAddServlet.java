package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// HttpServlet 클래스를 상속받아 서블릿 클래스를 정의
@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {

	// GET 요청을 처리하는 메서드 (회원 등록 폼을 제공)
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		// 로그 출력
		System.out.println("doGet을 탄다");
		
		// 응답의 컨텐츠 타입을 HTML로 설정
		res.setContentType("text/html");
		// 문자 인코딩을 UTF-8로 설정
		res.setCharacterEncoding("UTF-8");
		
		// 응답을 출력하기 위한 PrintWriter 객체 생성
		PrintWriter out = res.getWriter();
		
		// HTML 문자열을 담을 변수 선언
		String htmlStr = "";
		
		// HTML 문서 시작
		htmlStr += "<!DOCTYPE html><html><head><title>회원등록</title></head>";
		htmlStr += "<body>";
		htmlStr += "<h1>회원등록</h1>"; // 제목 표시
		// 회원 등록 폼 생성 (POST 방식으로 데이터 전송)
		htmlStr += "<form action='add' method='post'>";
		htmlStr += "이름: <input type='text' name='mname'><br>"; // 이름 입력 필드
		htmlStr += "이메일: <input type='text' name='email'><br>"; // 이메일 입력 필드
		htmlStr += "암호: <input type='password' name='password'><br>";
		htmlStr += "전화번호: <input type='text' name='phonenum'><br>";// 비밀번호 입력 필드
		htmlStr += "<input type='submit' value='추가'>"; // 제출 버튼
		htmlStr += "<input type='reset' value='취소'>"; // 초기화 버튼
		htmlStr += "</form>";
		htmlStr += "</body>";
		htmlStr += "</html>"; // HTML 문서 끝
		
		// HTML 코드 출력
		out.println(htmlStr);
	}
	
	// POST 요청을 처리하는 메서드 (회원 정보를 데이터베이스에 저장)
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		// 로그 출력
		System.out.println("doPost 수행함");
		
		// 데이터베이스 연결을 위한 Connection 객체 선언
		Connection conn = null;
		// SQL 실행을 위한 PreparedStatement 객체 선언
		PreparedStatement pstmt = null;
		
		// 데이터베이스 접속 정보 설정
		ServletContext sc = this.getServletContext();
		
		 String url = sc.getInitParameter("url"); // 오라클 기본 연결 포트
		 String user = sc.getInitParameter("user"); // 데이터베이스 사용자 이름
		 String password = sc.getInitParameter("password"); // 데이터베이스 비밀번호

		// 요청 데이터의 문자 인코딩을 UTF-8로 설정
		req.setCharacterEncoding("UTF-8");
		
		// 클라이언트로부터 받은 회원 정보를 변수에 저장
		String emailStr = req.getParameter("email"); // 이메일 입력값 저장
		String pwdStr = req.getParameter("password"); // 비밀번호 입력값 저장
		String nameStr = req.getParameter("mname"); // 이름 입력값 저장
		String numStr = req.getParameter("phonenum");
		try {
			// JDBC 드라이버 로드 (1단계)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 데이터베이스에 연결 (2단계)
			conn = DriverManager.getConnection(url, user, password);
			
			// SQL INSERT문 작성 (바인딩 변수 `?` 사용)
			String sql = "INSERT INTO PM17"
			        + " (MNO, EMAIL, PWD, MNAME, CRE_DATE, PHONENUM)"
			        + " VALUES (MEMBERS_MNO_SEQ.NEXTVAL, ?, ?, ?, SYSDATE, ?)";

			// SQL 실행을 위한 PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 바인딩 변수 값 설정
			pstmt.setString(1, emailStr); // 첫 번째 ? 자리에 emailStr 값 설정
			pstmt.setString(2, pwdStr); // 두 번째 ? 자리에 pwdStr 값 설정
			pstmt.setString(3, nameStr); // 세 번째 ? 자리에 nameStr 값 설정
			pstmt.setString(4, numStr);
			// SQL 실행 (데이터 삽입)
			pstmt.executeUpdate();
			
			// 회원 목록 페이지로 리다이렉트
			res.sendRedirect("./list");
			
//			// 응답을 HTML로 설정
//			res.setContentType("text/html");
//			res.setCharacterEncoding("UTF-8");
//			
//			// 응답을 출력하기 위한 PrintWriter 객체 생성
//			PrintWriter out = res.getWriter();
//			
//			// HTML 문서 시작
//			String htmlStr = "";
//			htmlStr += "<!DOCTYPE html><html><head><title>회원등록결과</title>";
//			// 5초 후 자동으로 list 페이지로 이동
//			htmlStr += "<meta http-equiv='Refresh' content='5; url=list'></head>";
//			htmlStr += "<body>";
//			htmlStr += "<p>등록 성공입니다!!</p>"; // 성공 메시지 표시
//			htmlStr += "</body>";
//			htmlStr += "</html>";
//			
//			// HTML 코드 출력
//			out.println(htmlStr);
		} catch (ClassNotFoundException e) {
			// JDBC 드라이버 클래스를 찾지 못했을 때 예외 처리
			e.printStackTrace();
		} catch (SQLException e) {
			// SQL 실행 중 오류 발생 시 예외 처리
			e.printStackTrace();
		} finally {
			// 자원 해제 (데이터베이스 연결 종료)
			if (pstmt != null) {
				try {
					pstmt.close(); // PreparedStatement 닫기
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (conn != null) {
				try {
					conn.close(); // Connection 닫기
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
