package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Authenticator.RequestorType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import spms.dto.MemberDto;

// @WebServlet 애노테이션을 사용하여 이 서블릿의 URL 매핑을 "/member/list"로 설정
@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 데이터베이스 연결을 위한 Connection 객체 선언
				Connection conn = null;
				// SQL 실행을 위한 Statement 객체 선언
				PreparedStatement pstmt = null;
				// SQL 실행 결과를 저장하는 ResultSet 객체 선언
				ResultSet rs = null;
				
				// 데이터베이스 접속 정보 설정

			    
			    
				try {
					
					ServletContext sc = this.getServletContext();
					
					conn = (Connection)sc.getAttribute("conn");
					// 1. JDBC 드라이버 
										
					// 회원 목록을 조회하는 SQL문 작성
					String sql = "SELECT MNO, MNAME, EMAIL, CRE_DATE, MOD_DATE"
							+ " FROM MEMBERS"
							+ " ORDER BY MNO ASC";
					
					// 4. DB에 SQL문 실행 요청
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					
					// 응답의 컨텐츠 타입을 HTML로 설정
					res.setContentType("text/html");
					// 문자 인코딩을 UTF-8로 설정
					res.setCharacterEncoding("UTF-8");
					
					
					int mno = 0;
					String mname = "";
					String email = "";
					Date creDate = null;
					
					ArrayList<MemberDto> memberList = new ArrayList();
					// 5. 데이터 활용 (회원 목록 출력)
					while (rs.next() == true) {
						mno = rs.getInt("MNO");
						mname = rs.getString("MNAME");
						email = rs.getString("EMAIL");
						creDate = rs.getDate("CRE_DATE");
						System.out.println("회원 정보 - MNO: " + mno + ", NAME: " + mname + ", EMAIL: " + email + ", DATE: " + creDate);
						MemberDto memberDto = new MemberDto(mno,mname,email,creDate);
						memberList.add(memberDto);
								
					}
					
					req.setAttribute("memberList", memberList);
					
					RequestDispatcher dispatcher = req.getRequestDispatcher("/member/MemberListView.jsp");
					
					dispatcher.include(req, res);
					
							
					
					
					
				} catch (Exception e) {
					//예외처리
					System.out.println("회원 목록에서 예외 발생");
					e.printStackTrace();
					req.setAttribute("error", e);
					RequestDispatcher dispatcher = req.getRequestDispatcher("/Error.jsp");
					dispatcher.forward(req, res);
				} finally {
					// 6. JDBC 객체 메모리 해제 (자원 반환)
					if (rs != null) {
						try {
							rs.close(); // ResultSet 닫기
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					
					if (pstmt != null) {
						try {
							pstmt.close(); // Statement 닫기
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					
					
				} // finally 블록 종료
			}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(request, resp);
	}
}
