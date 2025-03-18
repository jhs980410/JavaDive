package JavaDive.controller.member;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import JavaDive.dao.board.BoardDao;
import JavaDive.dao.member.MemberDao;
import JavaDive.dto.board.BoardDto;
import JavaDive.dto.member.MemberDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet({"/myPageList", "/note/myPageList"})
public class MyPageListController extends HttpServlet{

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException {
        
        // 🔹 세션에서 로그인된 사용자 정보 가져오기
        HttpSession session = req.getSession();
        MemberDto loginUser = (MemberDto) session.getAttribute("member");
        ServletContext sc = this.getServletContext();
        Connection conn = (Connection) sc.getAttribute("conn");
        
        
        
        // 🔹 로그인 여부 확인 (로그인이 안 되어 있으면 로그인 페이지로 리다이렉트)
        if (loginUser == null) {
            res.sendRedirect(req.getContextPath() + "/LoginPage.jsp");
            return;
        }
        
        // 🔹 사용자 ID를 기반으로 본인 정보 조회 (예제 DAO 호출)
        MemberDao memberDao = new MemberDao();
        memberDao.setConnection(conn);
        MemberDto memberInfo = null;
        try {
        	memberInfo = memberDao.memberSelectOne(loginUser.getNo());
        	System.out.println("memberNo : " +loginUser.getNo());
        } catch (Exception e) {
            e.printStackTrace();  // 예외 발생 시 서버 로그 출력
            req.setAttribute("errorMsg", "회원 정보를 불러오는 중 오류가 발생했습니다.");
        }
        
        
        // 🔹 사용자가 작성한 게시글 조회
        BoardDao boardDao = new BoardDao();
        boardDao.setConnection(conn);

        List<BoardDto> memberNoteList = boardDao.getBoardByMember(loginUser.getNo());
        System.out.println("📌 사용자가 작성한 게시글 개수: " + memberNoteList.size());
        // 🔹 조회한 정보를 JSP로 넘기기
        req.setAttribute("memberInfo", memberInfo);  // 사용자 정보
        req.setAttribute("memberNoteList", memberNoteList); // 작성한 글 리스트
        
        String path;
        if (req.getRequestURI().contains("/note")) { 
            path = "/jsp/member/MyPageNoteList.jsp";  // 내가 쓴 게시물조회 페이지 /
        } else {
            path = "/jsp/member/memberMyPageMain.jsp";  // 일반 사용자 검색 결과 페이지
        }

		
        // 🔹 마이페이지 JSP로 forward
        RequestDispatcher dispatcher = req.getRequestDispatcher(path);
        dispatcher.forward(req, res);
    }
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}
	
	
}
