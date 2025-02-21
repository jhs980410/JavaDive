package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import spms.dto.MemberDto;

@WebServlet(value = "/auth/login") // 해당 서블릿이 "/auth/login" 경로로 요청을 처리하도록 설정
public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		// 로그인 폼 페이지로 이동 (LoginForm.jsp를 클라이언트에게 보여줌)
		RequestDispatcher rd = req.getRequestDispatcher("./LoginForm.jsp");
		rd.forward(req, res); // 클라이언트에게 LoginForm.jsp 페이지를 출력하도록 포워딩
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 데이터베이스 연결을 위한 객체 선언
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 클라이언트가 입력한 이메일과 비밀번호를 받아옴
		String email = req.getParameter("email");
		String pwd = req.getParameter("password");
		String name = ""; // 회원 이름을 저장할 변수

		// SQL 쿼리문을 저장할 변수
		String sql = "";
		int colIndex = 1; // SQL 파라미터 인덱스
		
		try {
			// 서블릿 컨텍스트에서 데이터베이스 연결 객체 가져오기 (Application Scope)
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn"); // "conn"이라는 속성명으로 저장된 DB 연결 객체 가져오기	

			// SQL 쿼리문 작성
			sql += "SELECT EMAIL, MNAME";  // 회원 이메일과 이름 조회
			sql += " FROM MEMBERS";        // MEMBERS 테이블에서
			sql += " WHERE EMAIL = ? AND PWD = ?"; // 입력된 이메일과 비밀번호가 일치하는 데이터 찾기

			// SQL 문을 실행할 PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 입력된 이메일과 비밀번호를 SQL 쿼리의 ?(플레이스홀더)에 바인딩
			pstmt.setString(colIndex++, email); // 첫 번째 ?에 email 값 설정
			pstmt.setString(colIndex, pwd);     // 두 번째 ?에 pwd 값 설정

			// SQL 실행 및 결과 저장
			rs = pstmt.executeQuery();

			// 조회된 결과가 존재하면 (로그인 성공)
			if (rs.next()) {
				// 데이터베이스에서 가져온 이메일과 이름을 변수에 저장
				email = rs.getString("EMAIL");
				name = rs.getString("MNAME");

				// 로그인한 사용자 정보를 저장할 DTO 객체 생성
				MemberDto memberDto = new MemberDto();
				memberDto.setEmail(email); // DTO에 이메일 설정
				memberDto.setName(name);   // DTO에 이름 설정
				
				// 세션을 가져와 로그인 사용자 정보 저장
				HttpSession session = req.getSession();
				session.setAttribute("memberDto", memberDto); // 세션에 memberDto 저장
				
				// 로그인 성공 후 회원 목록 페이지로 이동
				res.sendRedirect("../member/list");
			}
		} catch (Exception e) {
			// 예외 발생 시 오류 메시지 출력
			e.printStackTrace();
		} finally {
			// ResultSet 객체 닫기 (DB 리소스 해제)
			if (rs != null) {
				try {
					rs.close(); 
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			// PreparedStatement 객체 닫기 (DB 리소스 해제)
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
