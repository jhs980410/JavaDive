package JavaDive.controller.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
    	doPost(req, res);
    }

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = res.getWriter();

        // 클라이언트가 입력한 이메일 가져오기
        String emailStr = req.getParameter("email");
        
     // 이메일 입력값이 비어있는 경우, 포워딩 사용
        if (emailStr == null || emailStr.trim().isEmpty()) {
        	out.println("<script>alert('이메일을 입력해주세요!'); window.history.back();</script>");
            return;
        }

        // 이메일 정규화 적용
        emailStr = normalizeEmail(emailStr);
        
     // 이메일 형식 검증 (정규식 검사 후 포워딩 처리)
        if (!EMAIL_PATTERN.matcher(emailStr).matches()) {
            req.setAttribute("errorMessage", "올바른 이메일 형식을 입력해주세요.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/MemberShip.jsp");
            dispatcher.forward(req, res);
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
            	// 중복된 이메일일 경우 회원가입 페이지로 포워딩
            	out.println("<script>alert('이미 사용 중인 이메일입니다.'); window.history.back();</script>");
            } else {
            	//사용 가능 이메일 서버에 저정
            	HttpSession session = req.getSession(); //세션 가져오기
            	session.setAttribute("emailChecked", emailStr); //세션에 이메일 저장
            	
            	// 사용 가능한 이메일일 경우 메시지를 설정하고 회원가입 페이지로 이동
            	out.println("<script>alert('사용 가능한 이메일입니다.'); window.history.back();</script>");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            // 서버 오류 발생 시 회원가입 페이지로 포워딩
            out.println("<script>alert('서버 오류가 발생했습니다. 다시 시도해주세요.'); window.history.back();</script>");
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
