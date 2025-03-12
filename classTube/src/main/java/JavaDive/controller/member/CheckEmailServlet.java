package JavaDive.controller.member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/checkEmail")
public class CheckEmailServlet extends HttpServlet {
	
	// 이메일 정규식 (RFC 5322 표준 기반)
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");

    // 이메일 정규화 (소문자로 변환 + 공백 제거)
    private String normalizeEmail(String email) {
        if (email == null) return null;
        return email.trim().toLowerCase();
    }


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
			}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.setContentType("text/html; charset=UTF-8");

        // 클라이언트가 입력한 이메일 가져오기
        String emailStr = req.getParameter("email");
        
        // 이메일 입력값이 비어있는 경우
        if (emailStr == null || emailStr.trim().isEmpty()) {
            res.getWriter().write("<script>alert('이메일을 입력해주세요.'); history.back();</script>");
            return;
        }

        // 이메일 정규화 적용
        emailStr = normalizeEmail(emailStr);
        
        //이메일 형식 검증
        if (!EMAIL_PATTERN.matcher(emailStr).matches()) {
            res.getWriter().write("<script>alert('올바른 이메일 형식을 입력해주세요.'); history.back();</script>");
            return;
        }
        // 이메일 형식 검증 (정규식 검사)
        if (emailStr == null || !EMAIL_PATTERN.matcher(emailStr).matches()) {
            res.getWriter().print("invalid_format"); // 잘못된 이메일 형식
            return;
        }

     // DB에서 이메일 중복 확인
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // DB 연결 가져오기
            ServletContext sc = getServletContext();
            conn = (Connection) sc.getAttribute("conn");

            // 이메일 중복 검사 쿼리 실행
            String sql = "SELECT COUNT(*) FROM MEMBER WHERE MEMBER_EMAIL = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, emailStr);
            rs = pstmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                res.getWriter().write("<script>alert('이미 사용 중인 이메일입니다.'); history.back();</script>");
            } else {
                res.getWriter().write("<script>alert('사용 가능한 이메일입니다.'); history.back();</script>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            res.getWriter().write("<script>alert('서버 오류가 발생했습니다.'); history.back();</script>");
        } finally {
        	
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}
}
