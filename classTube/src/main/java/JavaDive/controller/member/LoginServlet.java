package JavaDive.controller.member;

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import JavaDive.dao.member.MemberDao;
import JavaDive.dto.member.MemberDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 회원가입 버튼 클릭시 회원가입 페이지로 이동
		String MemberShip = req.getParameter("MemberShip");

		if ("register".equals(MemberShip)) { // 회원가입 버튼이 눌렸을 때
			RequestDispatcher rd = req.getRequestDispatcher("/MemberShip.jsp");
			rd.forward(req, res);
			return;
		}
		// 기본 로그인 페이지로 이동
		RequestDispatcher rd = req.getRequestDispatcher("/LoginPage.jsp");
		rd.forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();

		try {
			String email = req.getParameter("member_email");
			String pwd = req.getParameter("member_password");
			
			if (email == null || email.trim().isEmpty() || pwd == null || pwd.trim().isEmpty()) {
	               out.println("<script>alert('이메일과 비밀번호를 입력해주세요.'); history.back();</script>");
	               return;
	        }
			
			//디비 정보 가져오기
			ServletContext sc = this.getServletContext();
			Connection conn = (Connection) sc.getAttribute("conn");
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);

			// 입력된 이메일 존재 확인
			MemberDto memberDto = memberDao.getMemberByEmail(email);
			
			if (memberDto == null) {
				out.println("<script>alert('가입되어 있지 않은 이메일입니다.'); history.back();</script>");
                return;
			}
			
			 // 비밀번호 검증
			if (!memberDto.getPwd().equals(pwd)) {
	            out.println("<script>alert('비밀번호가 올바르지 않습니다.'); history.back();</script>");
	            return;
	        }
			
			// 로그인 성공 세션 저장
			HttpSession session = req.getSession();
			session.setAttribute("member", memberDto); 
			
			// 권한에 따라 페이지 이동
			if ("ADMIN".equals(memberDto.getPriv())) {

				session.setAttribute("lastRequestURI", "/adminMain");

				res.sendRedirect("./adminMain");  // 상대 경로

			}else {
			

				session.setAttribute("lastRequestURI", "/main");
				res.sendRedirect("./main");

				 // 관리자 페이지 이동

			}
			// 로그인 성공시 세션에 담고 로그인 완료 페이지로 이동
		

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
