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
import java.util.ArrayList;
import java.util.List;

import JavaDive.dao.board.BoardDao;
import JavaDive.dto.board.BoardDto;
import JavaDive.dto.member.MemberDto;

/**
 * Servlet implementation class BoardUpdateController
 */
@WebServlet({"/boardUpdate","/admin/boardUpdate"})
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardUpdateController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// 🔹 세션에서 DB 연결 객체 가져오기
		ServletContext sc = this.getServletContext();
		Connection conn = (Connection) sc.getAttribute("conn");

		// 🔹 DAO 객체 생성 및 DB 연결 설정
		BoardDao boardDao = new BoardDao();
	
	       boardDao.setConnection(conn);
	
		int postId = Integer.parseInt(req.getParameter("postId")); // postId 가져오기
		BoardDto board = boardDao.getBoardById(postId);

		req.setAttribute("board", board);
		
		String path;
        if (req.getRequestURI().contains("/admin")) { 
            path = "/jsp/admin/board/AdminboardUpdate.jsp";  // 관리자 검색 결과 페이지
        } else {
            path = "/jsp/board/boardUpdate.jsp";  // 일반 사용자 검색 결과 페이지
        }
		RequestDispatcher dispatcher = req.getRequestDispatcher(path);
		dispatcher.forward(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		ServletContext sc = this.getServletContext();
		Connection conn = (Connection) sc.getAttribute("conn");
		HttpSession session = req.getSession();
		MemberDto loginUser = (MemberDto) session.getAttribute("member");

		int postId = Integer.parseInt(req.getParameter("postId"));
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String category = req.getParameter("category");

		BoardDao boardDao = new BoardDao();
		boardDao.setConnection(conn);
	
		try {
			BoardDto board = boardDao.getBoardById(postId); // 게시글 정보 가져오기
		
			// 게시글 수정 진행
			// 게시글 수정 진행
			boardDao.updateBoard(postId, title, content);

			// 📌 최신 데이터 다시 조회
			BoardDto updatedBoard = boardDao.getBoardById(postId);
			session.setAttribute("boardDto", updatedBoard);

			// 📌 수정 후 상세 페이지로 이동 (최신 데이터 반영됨)
			String path;
			if (req.getRequestURI().contains("/admin")) {
			    path = "/jsp/admin/board/AdminBoardVIew.jsp?postId=" + postId; // 관리자 상세 페이지 이동
			} else {
			    path = "/jsp/board/boardView.jsp?postId=" + postId; // 일반 사용자 상세 페이지 이동
			}

			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, res);



		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/common/error.jsp"); 
            dispatcher.forward(req, res); 
		}
	}

}
