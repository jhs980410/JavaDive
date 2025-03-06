package JavaDive.controller.member;

import java.io.IOException;
import java.sql.Connection;

import JavaDive.dao.member.MemberDao;
import JavaDive.dto.member.MemberDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberAddServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	// TODO Auto-generated method stub
	super.doGet(req, res);
	
	
}

@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	Connection conn = null;
	
	String emailStr = req.getParameter("email");
	String pwdStr = req.getParameter("pwd");
	String pwdCheckStr = req.getParameter("pwdCheck");
	String nameStr = req.getParameter("name");
	String rrnStr = req.getParameter("rrn");
	String telStr = req.getParameter("tel");
	
	if(pwdStr != pwdCheckStr) {
		return;
	}
	
	MemberDto memberDto = new MemberDto();
	
	memberDto.setEmail(emailStr);
	memberDto.setPwd(pwdStr);
	
	
	try {
		ServletContext sc = this.getServletContext();
		
		conn = (Connection)sc.getAttribute("conn");
		
		MemberDao memberDao = new MemberDao();
		memberDao.setConnection(conn);
		
		int result = 0;
		
		result = memberDao.memberInsert(memberDto);
				
		System.out.println("확인?" + result);
		if (result == 0) {
			
			System.out.println("회원가입 실패");
		}
		
		res.sendRedirect("./list");
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		
		req.setAttribute("error", e);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/Error.jsp");
		dispatcher.forward(req, res);
	}
	}

}
