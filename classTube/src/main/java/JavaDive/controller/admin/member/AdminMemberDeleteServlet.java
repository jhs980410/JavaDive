package JavaDive.controller.admin.member;

import java.io.IOException;
import java.sql.Connection;

import JavaDive.dao.member.MemberDao;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/member/delete")
public class AdminMemberDeleteServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String memberNoparam = req.getParameter("no");
		int memberNo = 0;
		
		// 회원번호 유효성 검사
		if (memberNoparam != null && !memberNoparam.trim().isEmpty()) {
			try {
				memberNo = Integer.parseInt(memberNoparam);
			} catch (NumberFormatException e) {
				// TODO: handle exception
				res.sendRedirect(req.getContextPath() + "/error.jsp");
				return;
			}
		}else {
			res.sendRedirect(req.getContextPath() + "/error.jsp");
			return;
		}
		
		// DB 연결 가져오기
		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("conn");
		
		
		try {
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			
			// 회원 삭제 실행
			int result = memberDao.memberDelete(memberNo);
			
			if (result > 0) {
				res.sendRedirect(req.getContextPath() + "/admin/member/list");
			} else {
				res.sendRedirect(req.getContextPath() + "/error.jsp");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			res.sendRedirect(req.getContextPath() + "/error.jsp");
		}
	}

}
