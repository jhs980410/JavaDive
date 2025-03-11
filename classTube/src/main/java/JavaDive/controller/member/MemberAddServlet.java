package JavaDive.controller.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import JavaDive.dao.member.MemberDao;
import JavaDive.dto.member.MemberDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/join")
public class MemberAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/MemberShip.jsp");
        dispatcher.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html; charset=UTF-8");
        PrintWriter out = res.getWriter();
        Connection conn = null;

        String emailStr = req.getParameter("email");
        String pwdStr = req.getParameter("pwd");
        String nameStr = req.getParameter("name");
        String rrnStr = req.getParameter("rrn");
        String telStr = req.getParameter("tel");

        try {
            ServletContext sc = this.getServletContext();
            conn = (Connection) sc.getAttribute("conn");

            String checkEmailSql = "SELECT COUNT(*) FROM MEMBER WHERE MEMBER_EMAIL = ?";
            PreparedStatement pstmt = conn.prepareStatement(checkEmailSql);
            pstmt.setString(1, emailStr);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                out.println("<script>alert('이미 가입된 이메일입니다.'); history.back();</script>");
                return;
            }
            rs.close();
            pstmt.close();

            // 회원가입 처리 로직
            String insertSql = "INSERT INTO MEMBER (MEMBER_NO, MEMBER_EMAIL, MEMBER_PWD, MEMBER_NAME, TEL, CREATE_AT) " +
                               "VALUES (MEMBER_SEQ.NEXTVAL, ?, ?, ?, ?, SYSDATE)";
            pstmt = conn.prepareStatement(insertSql);
            pstmt.setString(1, emailStr);
            pstmt.setString(2, pwdStr);
            pstmt.setString(3, nameStr);
            pstmt.setString(4, telStr);

            int result = pstmt.executeUpdate();
            pstmt.close();

            if (result > 0) {
                out.println("<script>alert('회원가입이 완료되었습니다.'); window.location.href='/login';</script>");
            } else {
                out.println("<script>alert('회원가입 실패'); history.back();</script>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<script>alert('서버 오류 발생'); history.back();</script>");
        }
    }
}
