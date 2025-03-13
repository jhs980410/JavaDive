package JavaDive.controller.board;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import JavaDive.dao.board.BoardDao;
import JavaDive.dto.board.BoardDto;

/**
 * Servlet implementation class BoardNoticeController
 */
@WebServlet({"/admin/boardNotice" , "/boardNotice"})
public class BoardNoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        BoardDao boardDao = new BoardDao();
        ServletContext sc = this.getServletContext();
        Connection conn = (Connection) sc.getAttribute("conn");
        boardDao.setConnection(conn);

        try {
         
        	// 공지사항 리스트 가져오기
        	List<BoardDto> noticeList = boardDao.getTopNotices(10);
        	System.out.println("📌 공지사항 리스트 접근");

        	// 일반 게시글 리스트 가져오기
        	List<BoardDto> boardList = (List<BoardDto>) session.getAttribute("boardList");

        	// 공지사항과 일반 게시글을 분리하여 세션에 저장
        	session.setAttribute("noticeList", noticeList); // 공지사항 전용 리스트
        	session.setAttribute("boardList", boardList);  // 기존 일반 게시글 리스트 유지

        	// 공지사항 전용 JSP 페이지로 이동
        	
        	String path;
	        if (req.getRequestURI().contains("/admin")) { 
	        	System.out.println("어드민에서 접근");
	            path = "/jsp/admin/board/AdminNoticeList.jsp";  // 관리자 검색 결과 페이지
	        } else {
	            path = "/jsp/board/boardNotice.jsp";  // 일반 사용자 검색 결과 페이지
	        }
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/common/error.jsp");
            dispatcher.forward(req, res);
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
