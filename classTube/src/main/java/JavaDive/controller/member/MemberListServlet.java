package JavaDive.controller.member;

import java.io.IOException;
import java.net.Authenticator.RequestorType;
import java.sql.Connection;
import java.util.ArrayList;

import JavaDive.dao.member.MemberDao;
import JavaDive.dto.member.MemberDto;
import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberListServlet extends HttpServlet{

//	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		
		try {
			
			ServletContext sc = this.getServletContext();
			
			conn = (Connection)sc.getAttribute("conn");
			
			MemberDao memberDao = new MemberDao();
			
			memberDao.setConnection(conn);
			
			ArrayList<MemberDto> memberList = null;
			
			memberList = (ArrayList<MemberDto>)memberDao.selectList();
			
			req.setAttribute("memberList", memberList);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/member/MemberListView.jsp");
			
			dispatcher.forward(req, res);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			req.setAttribute("error", e);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("");
			dispatcher.forward(req, res);
			
		}
	}
	
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			// TODO Auto-generated method stub
		}
	
}