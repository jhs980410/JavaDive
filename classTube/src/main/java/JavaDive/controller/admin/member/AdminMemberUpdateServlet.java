package JavaDive.controller.admin.member;

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

@WebServlet("/admin/member/update")  // URL 맵핑
public class AdminMemberUpdateServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String memberNoParam = req.getParameter("memberNo");
		int memberNo = 0;
		
		// 회원번호 유효성 검사 및 디버깅 로그 추가
        if (memberNoParam != null && !memberNoParam.trim().isEmpty()) {
            try {
                memberNo = Integer.parseInt(memberNoParam);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                res.sendRedirect(req.getContextPath() + "/admin/member/list");
                return;
            }
        } else {
            res.sendRedirect(req.getContextPath() + "/admin/member/list");
            return;
        }
        // DB 연결 가져오기
        ServletContext sc = getServletContext();
        Connection conn = (Connection) sc.getAttribute("conn");

        try {
            // DAO를 통해 회원 정보 조회
            MemberDao memberDao = new MemberDao();
            memberDao.setConnection(conn);
            
            // 회원 정보 조회
            MemberDto member = memberDao.memberSelectOne(memberNo);

            if (member == null) {
                res.sendRedirect(req.getContextPath() + "/admin/member/list");
                return;
            }

            // 조회된 회원 정보 JSP에 전달
            req.setAttribute("member", member);
            RequestDispatcher rd = req.getRequestDispatcher("/jsp/admin/member/adminMemberUpdate.jsp");
            rd.forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect(req.getContextPath() + "/admin/member/list");
        }
    }
}
