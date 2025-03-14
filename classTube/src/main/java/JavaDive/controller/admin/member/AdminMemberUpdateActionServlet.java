package JavaDive.controller.admin.member;

import java.io.IOException;
import java.sql.Connection;

import JavaDive.dao.member.MemberDao;
import JavaDive.dto.member.MemberDto;
import jakarta.security.auth.message.callback.SecretKeyCallback.Request;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/member/updateAction")  // URL 맵핑
public class AdminMemberUpdateActionServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String paramValue = req.getParameter("id") ;// 예를 들어 id 값을 가져온다고 가정
		int id = 0; // 기본값 설정

		try {
		    if (paramValue != null && !paramValue.trim().isEmpty()) {
		        id = Integer.parseInt(paramValue);
		    } else {
		        System.out.println("Invalid or null parameter value: " + paramValue);
		    }
		} catch (NumberFormatException e) {
		    System.out.println("Failed to parse integer from parameter: " + paramValue);
		    e.printStackTrace();
		}

		
		 // 요청에서 회원 정보 가져오기
        int memberNo = Integer.parseInt(req.getParameter("no"));
        String Pwd = req.getParameter("pwd");
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String tel = req.getParameter("tel");
        String priv = req.getParameter("priv"); // 회원 등급 (ADMIN, USER 등)

     // DB 연결 가져오기
        ServletContext sc = getServletContext();
        Connection conn = (Connection) sc.getAttribute("conn");

        try {
            // DAO 생성 및 회원 정보 업데이트
            MemberDao memberDao = new MemberDao();
            memberDao.setConnection(conn);

            // DTO에 업데이트할 정보 저장
            MemberDto memberDto = new MemberDto();
            memberDto.setNo(memberNo);
            memberDto.setPwd(Pwd);
            memberDto.setEmail(email);
            memberDto.setName(name);
            memberDto.setTel(tel);
            memberDto.setPriv(priv);

            // 회원 정보 업데이트 실행
            int result = memberDao.memberUpdate(memberDto);

            if (result > 0) {
                // 업데이트 성공 → 회원 상세 페이지로 이동
                res.sendRedirect(req.getContextPath() + "/admin/member/info?memberNo=" + memberNo);
            } else {
                // 업데이트 실패 → 오류 페이지로 이동
                res.sendRedirect(req.getContextPath() + "/error.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect(req.getContextPath() + "/error.jsp");
        }
    }
}
