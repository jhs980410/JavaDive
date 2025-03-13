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
 * Servlet implementation class BoardSearchController
 */
@WebServlet({"/boardSearch", "/admin/boardSearch"}) 
public class BoardSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardSearchController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		BoardDao boardDao = new BoardDao();
		ServletContext sc = this.getServletContext();
		Connection conn = (Connection) sc.getAttribute("conn");
		boardDao.setConnection(conn);
		List<BoardDto> boardList = null; // dao에서 , keyword 입력예정
		String keyWord = "";
		
		 int currentPage = 1;  // 기본 페이지는 1
		    int pageSize = 8;  // 한 페이지에 10개씩 조회
		    
		try {
			System.out.println("Search 컨트롤러진입");
			String keyword = req.getParameter("keyword");
			String category = req.getParameter("category");
		    boardList = boardDao.searchBoard(keyword, currentPage, pageSize, req);
	        session.setAttribute("boardList", boardList);

	        // 📌 추가: 현재 페이지와 페이지 크기도 세션에 저장 (페이징 유지)
	        session.setAttribute("currentPage", currentPage);
	        session.setAttribute("pageSize", pageSize);
			String path;
	        if (req.getRequestURI().contains("/admin")) { 
	            path = "/jsp/admin/board/AdminBoardList.jsp";  // 관리자 검색 결과 페이지
	        } else {
	            path = "/jsp/board/boardList.jsp";  // 일반 사용자 검색 결과 페이지
	        }

	        RequestDispatcher dispatcher = req.getRequestDispatcher(path);
	        dispatcher.forward(req, res);

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/common/error.jsp");
			dispatcher.forward(req, res);
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}
