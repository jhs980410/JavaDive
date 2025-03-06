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

public class MemberUpdateServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, res);
		
		Connection conn = null;
		RequestDispatcher rd = null;
		
		String mNo = "";
		
		try {
			mNo = req.getParameter("no");
			int no = Integer.parseInt(mNo);
			
			ServletContext sc = this.getServletContext();
			
			conn = (Connection) sc.getAttribute("conn");
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			
			MemberDto memberDto = memberDao.memberSelectOne(no);
			
			if (memberDto == null) {
				throw new Exception("회원을 찾을 수 없습니다.");
			}
			
			req.setAttribute("memberDto", memberDto);
			rd = req.getRequestDispatcher("");
			rd.forward(req, res);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			req.setAttribute("error", e);
			rd = req.getRequestDispatcher("");
			rd.forward(req, res);
		}
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, res);
		
		MemberDto memberDto = null;
		
		Connection conn = null;
		
		try {
			String pwd = req.getParameter("pwd");
			String name = req.getParameter("name");
			String tel = req.getParameter("tel");
			String No = req.getParameter("No");
			int no = Integer.parseInt(No);
			
			memberDto = new MemberDto();
			memberDto.setPwd(pwd);
			memberDto.setName(name);
			memberDto.setTel(tel);
			memberDto.setNo(no);
			
			ServletContext sc = this.getServletContext();
			
			conn = (Connection) sc.getAttribute("conn");
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			
			int result = memberDao.memberUpdate(memberDto);
			
			if (result == 0) {
				System.out.println("회원 정보 조회 를 실패하였습니다");
			}
			
			res.sendRedirect("");
			
		} catch (Exception e) {
			e.printStackTrace();
			
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("");
			rd.forward(req, res);
		}
		
	}
	
}
