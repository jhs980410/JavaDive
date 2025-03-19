package JavaDive.controller.admin.member;

import java.io.IOException;
import java.net.http.HttpClient;
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

@WebServlet("/admin/member/info")  // URL맵핑
public class AdminMemberInfoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
			int memberNo = 0;
			try {
				memberNo = Integer.parseInt(req.getParameter("memberNo"));
			} catch (NumberFormatException e) {
				// TODO: handle exception
				res.sendRedirect(req.getContextPath() + "/admin/member/list");
				return;
			}
		
			Connection conn = null;
			
        try {
            // 🔹 DB 연결 가져오기
            ServletContext sc = getServletContext();
            conn = (Connection) sc.getAttribute("conn");

            // 🔹 DAO 생성 및 회원 정보 조회
            MemberDao memberDao = new MemberDao();
            memberDao.setConnection(conn);
            MemberDto member = memberDao.memberSelectOne(memberNo);

            if (member == null) {
                res.sendRedirect(req.getContextPath() + "/jsp/admin/member/adminMemberList.jsp");
                return;
            }

            // 🔹 JSP로 데이터 전달
            req.setAttribute("member", member);
            RequestDispatcher rd = req.getRequestDispatcher("/jsp/admin/member/adminMemberInfo.jsp");
            rd.forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect(req.getContextPath() + "/admin/member/list");
        }
    }
}
