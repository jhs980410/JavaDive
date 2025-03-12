package JavaDive.controller.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;

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
    // 이메일 검증용 정규식 (RFC 5322 표준 기반)
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");

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

        // 입력값 받기
        String emailStr = req.getParameter("email");
        String pwdStr = req.getParameter("pwd");
        String pwdCheckStr = req.getParameter("pwdCheck"); // 🔹 비밀번호 확인 값 추가
        String nameStr = req.getParameter("name");
        String rrnStr = req.getParameter("rrn");
        String telStr = req.getParameter("tel");
        
        // 🔹 이메일 정규화 - 공백 제거 & 소문자로 변환
        if (emailStr != null) {
            emailStr = emailStr.trim().toLowerCase();
        }

        // 🔹 이메일 형식 검사
        if (!EMAIL_PATTERN.matcher(emailStr).matches()) {
            out.println("<script>alert('유효하지 않은 이메일 형식입니다.'); history.back();</script>");
            return;
        }
        
     // 🔹 비밀번호 확인 체크 (추가됨)
        if (pwdStr == null || pwdStr.trim().isEmpty()) {
            out.println("<script>alert('비밀번호를 입력해주세요.'); history.back();</script>");
            return;
        }

        if (!pwdStr.equals(pwdCheckStr)) { // 🔹 비밀번호 확인 로직 추가
            out.println("<script>alert('비밀번호가 일치하지 않습니다.'); history.back();</script>");
            return;
        }

        try {
            ServletContext sc = this.getServletContext();
            conn = (Connection) sc.getAttribute("conn");

           MemberDao memberDao = new MemberDao();
           memberDao.setConnection(conn);
            
           MemberDto memberDto = new MemberDto();
           memberDto.setEmail(emailStr);
           memberDto.setPwd(pwdStr);
           memberDto.setName(nameStr);
           memberDto.setRrn(rrnStr);
           memberDto.setTel(telStr);
           
           int result = memberDao.memberInsert(memberDto);

            if (result > 0) {
                out.println("<script>alert('회원가입이 완료되었습니다.'); window.location.href='/login';</script>"); // 🔹 회원가입 성공 후 로그인 페이지 이동 추가
            } else {
                out.println("<script>alert('회원가입 실패. 다시 시도해주세요.'); history.back();</script>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<script>alert('서버 오류 발생. 다시 시도해주세요.'); history.back();</script>");
        }
    }
}
