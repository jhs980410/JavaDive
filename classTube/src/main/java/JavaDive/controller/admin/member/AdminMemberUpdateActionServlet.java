package JavaDive.controller.admin.member;

import java.io.IOException;
import java.sql.Connection;

import JavaDive.dao.member.MemberDao;
import JavaDive.dto.member.MemberDto;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/member/updateAction")
public class AdminMemberUpdateActionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String memberNoParam = req.getParameter("no");
        int memberNo = 0;

        // 회원번호 유효성 검사
        if (memberNoParam != null && !memberNoParam.trim().isEmpty()) {
            try {
                memberNo = Integer.parseInt(memberNoParam);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                res.sendRedirect(req.getContextPath() + "jsp/common/error.jsp");
                return;
            }
        } else {
            res.sendRedirect(req.getContextPath() + "jsp/common/error.jsp");
            return;
        }

        // 입력된 회원 정보 가져오기
        String Pwd = req.getParameter("pwd");
        String name = req.getParameter("name");
        String tel = req.getParameter("tel");
        String priv = req.getParameter("priv");

        // DB 연결 가져오기
        ServletContext sc = getServletContext();
        Connection conn = (Connection) sc.getAttribute("conn");

        try {
            MemberDao memberDao = new MemberDao();
            memberDao.setConnection(conn);

            // DTO에 업데이트할 정보 저장
            MemberDto memberDto = new MemberDto();
            memberDto.setNo(memberNo);
            memberDto.setPwd((Pwd != null && !Pwd.trim().isEmpty()) ? Pwd : null);
            memberDto.setName((name != null && !name.trim().isEmpty()) ? name : null);
            memberDto.setTel((tel != null && !tel.trim().isEmpty()) ? tel : null);
            memberDto.setPriv((priv != null && !priv.trim().isEmpty()) ? priv : null);

            // 회원 정보 업데이트 실행
            int result = memberDao.memberUpdate(memberDto);

            if (result > 0) {
                res.sendRedirect(req.getContextPath() + "/admin/member/info?memberNo=" + memberNo);
            } else {
                res.sendRedirect(req.getContextPath() + "jsp/common/error.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect(req.getContextPath() + "jsp/common/error.jsp");
        }
    }
}
