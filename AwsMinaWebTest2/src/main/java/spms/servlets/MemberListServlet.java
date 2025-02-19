package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;

// @WebServlet 애노테이션을 사용하여 이 서블릿의 URL 매핑을 "/member/list"로 설정
@WebServlet("/member/list")
public class MemberListServlet extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) 
		throws ServletException, IOException {
		
		// 데이터베이스 연결을 위한 Connection 객체 선언
		Connection conn = null;
		// SQL 실행을 위한 Statement 객체 선언
		Statement stmt = null;
		// SQL 실행 결과를 저장하는 ResultSet 객체 선언
		ResultSet rs = null;
		ServletContext sc = this.getServletContext();
		// 데이터베이스 접속 정보 설정
		String url = sc.getInitParameter("url");
		String user = sc.getInitParameter("user");
	    String password = sc.getInitParameter("password");

		try {
			// 1. JDBC 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. 데이터베이스에 연결
			conn = DriverManager.getConnection(url, user, password);
			// 3. SQL 실행 객체 준비
			stmt = conn.createStatement();
			
			// 회원 목록을 조회하는 SQL문 작성
			String sql = "SELECT MNO, MNAME, EMAIL, CRE_DATE, PHONENUM"
					+ " FROM PM17"
					+ " ORDER BY MNO ASC";
			
			// 4. DB에 SQL문 실행 요청
			rs = stmt.executeQuery(sql);
			
			// 응답의 컨텐츠 타입을 HTML로 설정
			res.setContentType("text/html");
			// 문자 인코딩을 UTF-8로 설정
			res.setCharacterEncoding("UTF-8");
			
			// 응답을 출력하기 위한 PrintWriter 객체 생성
			PrintWriter out = res.getWriter();

			// HTML 문자열을 담을 변수 선언
			String htmlStr = "";
			
			// 신규 회원 추가 버튼을 위한 HTML 코드 추가
			htmlStr += "<p>";
			htmlStr += "<a href='./add'>신규 회원";
			htmlStr += "</a>";
			htmlStr += "</p>";
			
			// HTML 문서 시작
			out.println("<!DOCTYPE html><html>");
			out.println("<head><meta charset=\"UTF-8\">");
			out.println("<title>회원목록</title></head>");
			out.println("<body>");
			out.println("<h1>회원목록</h1>");
			
			// 신규 회원 추가 버튼 출력
			out.println(htmlStr);
			
			// 5. 데이터 활용 (회원 목록 출력)
			while (rs.next()) {
			    out.println(
			        rs.getInt("MNO") + ",  " + // 회원 번호 출력
			        "<a href='./update?mNo=" +
			            rs.getInt("MNO") + // 회원 번호를 포함한 링크 생성 (이름 클릭 시 수정 페이지로 이동)
			        "'>" + 	
			        rs.getString("MNAME") + "</a>,  " + // 회원 이름 출력
			        rs.getString("EMAIL") + ",  " + // 이메일 출력
			        rs.getString("CRE_DATE") + ",  "+ // 가입 날짜 출력
			        rs.getString("PHONENUM") +
			        "<form action='./delete' method='post' style='display:inline;'>"
			        + "<input type='hidden' name='mNo' value='" + rs.getInt("MNO") + "'>"  // 🔹 mNo 값 추가
			        + "<button type='submit' class='delete'>삭제</button>"
			        + "</form>" + "<br>"
			    );
			}

			
			// HTML 문서 종료
			out.println("</body></html>");
			
		} catch (ClassNotFoundException e) {
			// JDBC 드라이버 클래스를 찾지 못했을 때 예외 처리
			e.printStackTrace();
		} catch (SQLException e) {
			// SQL 실행 중 오류 발생 시 예외 처리
			e.printStackTrace();
		} finally {
			// 6. JDBC 객체 메모리 해제 (자원 반환)
			if (rs != null) {
				try {
					rs.close(); // ResultSet 닫기
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (stmt != null) {
				try {
					stmt.close(); // Statement 닫기
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
		} // finally 블록 종료
	}
}
