package JavaDive.controller.admin.member;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import JavaDive.dao.member.MemberDao;
import JavaDive.dto.member.MemberDto;

@WebServlet("/admin/member/list") // 관리자 회원 목록 조회 URL맵핑
public class AdminMemberListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	// 로그인 세션 확인
        HttpSession session = req.getSession();
        MemberDto memberDto = (MemberDto) session.getAttribute("member");
        
        if (memberDto == null || !"ADMIN".equals(memberDto.getPriv())) {
            res.sendRedirect(req.getContextPath() + "/LoginPage.jsp");
            return;
        }
        

        Connection conn = null;

        try {
            // DB 연결 가져오기
            ServletContext sc = getServletContext();
            conn = (Connection) sc.getAttribute("conn");
            
            if (conn == null) {
                return;
            }
            
            MemberDao memberDao = new MemberDao();
            memberDao.setConnection(conn);
            
            // 회원 목록 조회
            List<MemberDto> memberList = memberDao.selectList();
            
            // JSP로 데이터 전달
            req.setAttribute("memberList", memberList);
            // 관리자 회원 목록 페이지로 이동
            RequestDispatcher rd = req.getRequestDispatcher("/jsp/admin/member/adminMemberList.jsp");
            rd.forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect(req.getContextPath() + "/error.jsp"); // 오류 발생 시 에러 페이지 이동
        }
    }
}
