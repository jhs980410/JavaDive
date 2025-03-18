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

@WebServlet("/memberUpdate")
public class MemberUpdateServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
        RequestDispatcher rd = null;

        try {
            // 🔹 세션에서 로그인한 사용자 정보 가져오기
            HttpSession session = req.getSession();
            MemberDto loginUser = (MemberDto) session.getAttribute("member");

            if (loginUser == null) {
                res.sendRedirect(req.getContextPath() + "/LoginPage.jsp");
                return;
            }

            ServletContext sc = this.getServletContext();
            conn = (Connection) sc.getAttribute("conn");

            MemberDao memberDao = new MemberDao();
            memberDao.setConnection(conn);

            // 🔹 현재 로그인한 회원 정보 가져오기
            MemberDto memberDto = memberDao.memberSelectOne(loginUser.getNo());

            if (memberDto == null) {
                throw new Exception("회원을 찾을 수 없습니다.");
            }

            req.setAttribute("memberDto", memberDto);
            rd = req.getRequestDispatcher("jsp/member/MemberUpdate.jsp");
            rd.forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", e);
            rd = req.getRequestDispatcher("/jsp/error.jsp");
            rd.forward(req, res);
        }
    }
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;

        try {
            // 🔹 세션에서 로그인한 사용자 정보 가져오기
            HttpSession session = req.getSession();
            MemberDto loginUser = (MemberDto) session.getAttribute("member");

            if (loginUser == null) {
                res.sendRedirect(req.getContextPath() + "/LoginPage.jsp");
                return;
            }

            String name = req.getParameter("name");
            String tel = req.getParameter("tel");
            String newPwd = req.getParameter("newPwd");
            String confirmPwd = req.getParameter("confirmPwd");

            ServletContext sc = this.getServletContext();
            conn = (Connection) sc.getAttribute("conn");

            MemberDao memberDao = new MemberDao();
            memberDao.setConnection(conn);

            // 🔹 현재 회원 정보 가져오기
            MemberDto currentMember = memberDao.memberSelectOne(loginUser.getNo());

            if (currentMember == null) {
                throw new Exception("회원 정보를 찾을 수 없습니다.");
            }

            // 🔹 비밀번호 변경 확인 (새 비밀번호 입력 시)
            if (newPwd != null && !newPwd.isEmpty() && newPwd.equals(confirmPwd)) {
                currentMember.setPwd(newPwd);
            }

            // 🔹 이름과 전화번호 업데이트
            currentMember.setName(name);
            currentMember.setTel(tel);

            // 🔹 회원 정보 업데이트 실행
            int result = memberDao.memberUpdate(currentMember);

            if (result == 0) {
                throw new Exception("회원 정보 수정 실패");
            }

            // 🔹 세션 업데이트
            session.setAttribute("member", currentMember);

            // 🔹 수정 완료 후 마이페이지 이동
            res.sendRedirect(req.getContextPath() + "/myPageList");

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/jsp/error.jsp");
            rd.forward(req, res);
        }
    }
}
