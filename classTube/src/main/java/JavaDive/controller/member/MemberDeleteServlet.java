package JavaDive.controller.member;

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

@WebServlet("/memberDelete") // 서블릿 매핑 추가
public class MemberDeleteServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		
		try {
			// 세션에서 로그인한 사용자 정보 가져오기
			HttpSession session = req.getSession();
			MemberDto loginUser = (MemberDto) session.getAttribute("member");
			
			if (loginUser == null) {
				res.sendRedirect(req.getContextPath() + "/LoginPage.jsp");
				return;
			}
			
			ServletContext sc =this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			
			// 회원 삭제 싱행
			int result = memberDao.memberDelete(loginUser.getNo());
			
			if (result == 0) {
				throw new Exception("회원 탈퇴 실패");
			}
			
			// 세션 초기화 후 로그인 페이지 이동
			session.invalidate();
			res.sendRedirect(req.getContextPath() + "/LoginPage.jsp");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/error.jsp");
			rd.forward(req, res);
		}
	}
	
}
