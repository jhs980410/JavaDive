package JavaDive.controller.member;

import java.io.Console;
import java.io.IOException;
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
		Connection conn = null;

		try {
			String email = req.getParameter("member_email");
			String pwd = req.getParameter("member_password");
			String admin = "";
			
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);

			MemberDto memberDto = memberDao.memberExist(email, pwd);
			
			if (memberDto == null) {

				RequestDispatcher rd = req.getRequestDispatcher("/LoginPage.jsp");
				rd.forward(req, res);
				return;
			}
			
			HttpSession session = req.getSession();
			session.setAttribute("member", memberDto); 
			
			if ("ADMIN".equals(memberDto.getPriv())) {
				// 기존 memberdto

				System.out.println("로그인 컨트롤러에서 의 memberdto : " + memberDto);
				
				System.out.println("권한자: " + admin);
				System.out.println("성공");

				res.sendRedirect("./adminMain");  // 상대 경로

			}else {
				; // 기존 memberdto

				System.out.println("로그인 컨트롤러에서 의 memberdto : " + memberDto);
				System.out.println("권한자: " + admin);
				System.out.println("성공");

				res.sendRedirect("./main");
			}
			// 로그인 성공시 세션에 담고 로그인 완료 페이지로 이동
		

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
