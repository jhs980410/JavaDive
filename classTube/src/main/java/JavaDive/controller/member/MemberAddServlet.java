package JavaDive.controller.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
import jakarta.servlet.http.HttpSession;

@WebServlet("/join") // JSP에서 설정한 action 경로 유지
public class MemberAddServlet extends HttpServlet {
	
	// 전화번호 정규식 (010-1234-5678 또는 01012345678 허용)
    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^(010-\\d{4}-\\d{4}|010\\d{8})$");

    // 주민등록번호 정규식 (6자리-7자리 형식)
    private static final Pattern RRN_PATTERN =
            Pattern.compile("^\\d{6}-\\d{7}$");

    // 전화번호 정규식 검증 메서드
    private boolean isValidPhoneNumber(String phone) {
        return PHONE_PATTERN.matcher(phone).matches();
    }

    // 주민등록번호 정규식 검증 메서드
    private boolean isValidRrn(String rrn) {
        return RRN_PATTERN.matcher(rrn).matches();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/MemberShip.jsp");
        dispatcher.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html; charset=UTF-8");
        PrintWriter out = res.getWriter();

        //입력한 회원 정보 가져오기
        String nameStr = req.getParameter("name");
        String emailStr = req.getParameter("email");
        String pwdStr = req.getParameter("password");
        String pwdCheckStr = req.getParameter("password-confirm");
        String rrnStr = req.getParameter("id-number");
        String telStr = req.getParameter("phone");
        
        //세션에서 이메일 중복 확인 여부 가져오기
        HttpSession session = req.getSession();
        String checkedEmail = (String) session.getAttribute("emailChecked");
        
        // 필수 입력값 확인 (입력되지 않은 값이 있는지 체크)
        if (nameStr == null || nameStr.trim().isEmpty() ||
            emailStr == null || emailStr.trim().isEmpty() ||
            pwdStr == null || pwdStr.trim().isEmpty() ||
            pwdCheckStr == null || pwdCheckStr.trim().isEmpty() ||
            rrnStr == null || rrnStr.trim().isEmpty() ||
            telStr == null || telStr.trim().isEmpty()) {
            out.println("<script>alert('모든 항목을 입력해주세요.'); history.back();</script>");
            return;
        }

        // 비밀번호 확인 체크
        if (!pwdStr.equals(pwdCheckStr)) {
            out.println("<script>alert('비밀번호가 일치하지 않습니다.'); history.back();</script>");
            return;
        }
        
        // 주민등록번호 정규식 체크
        if (!isValidRrn(rrnStr)) {
            out.println("<script>alert('올바른 주민등록번호 형식을 입력하세요. (예: 990101-1234567)'); history.back();</script>");
            return;
        }
        
        // 전화번호 정규식 체크
        if (!isValidPhoneNumber(telStr)) {
            out.println("<script>alert('올바른 전화번호 형식을 입력하세요. (예: 010-1234-5678 또는 01012345678)'); history.back();</script>");
            return;
        }
        
        // 이메일 중복 확인 체크
        if (checkedEmail == null || !checkedEmail.equals(emailStr)) {
        	out.print("<script>alert('이메일 중복 확인을 해주세요.'); history.back();</script>");
        	return;
		}
        try {
            // DAO를 사용하여 DB연결 가져오기
            ServletContext sc = getServletContext();
            Connection conn = (Connection) sc.getAttribute("conn");
            
            MemberDao memberDao = new MemberDao();
            memberDao.setConnection(conn);
            
            MemberDto memberDto = new MemberDto();
            memberDto.setName(nameStr);
            memberDto.setEmail(emailStr);
            memberDto.setPwd(pwdStr); // 실제 운영 환경에서는 비밀번호 해싱 필요
            memberDto.setTel(telStr);
            memberDto.setRrn(rrnStr);
            memberDto.setPriv("USER");

            int result = memberDao.memberInsert(memberDto);

            if (result > 0) {
            	// 회원가입 후 세션에서 이메일체크 삭제
            	session.removeAttribute("emailChecked");
                out.println("<script>alert('회원가입이 완료되었습니다.'); window.location.href='LoginPage.jsp';</script>");
            } else {
                out.println("<script>alert('사용중인Email입니다. 다시 시도해주세요.'); history.back();</script>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<script>alert('서버 오류 발생. 다시 시도해주세요.'); history.back();</script>");
        }
    }
}
