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
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String MemberShip = req.getParameter("MemberShip");

        if ("register".equals(MemberShip)) { // 회원가입 버튼이 눌렸을 때
            RequestDispatcher rd = req.getRequestDispatcher("/MemberShip.jsp");
            rd.forward(req, res);
            return;
        }
		
		RequestDispatcher rd = req.getRequestDispatcher("/LoginPage.jsp");
		rd.forward(req, res);
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		System.out.println("로그인 컨트롤러 진입");
		Connection conn = null;
		
		try {
			String email = req.getParameter("member_email");
			String pwd = req.getParameter("member_password");
			
			ServletContext sc = this.getServletContext();
			
			conn = (Connection) sc.getAttribute("conn");
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			
			MemberDto memberDto = memberDao.memberExist(email, pwd);
			
			if(memberDto == null) {
				System.out.println("로그인 실패");
				
				RequestDispatcher rd = req.getRequestDispatcher("/LoginPage.jsp");
				rd.forward(req, res);
				return;
			}
			
			// 로그인 성공시 세션에 담고 로그인 완료 페이지로 이동
			HttpSession session = req.getSession();
			session.setAttribute("member", memberDto); // 기존 memberdto 
 
			System.out.println("로그인 컨트롤러에서 의 memberdto : " + memberDto);
			System.out.println("성공");
			
			res.sendRedirect("./main");

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
