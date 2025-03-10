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

import JavaDive.dao.board.BoardDao;
import JavaDive.dto.board.BoardDto;
import JavaDive.dto.member.MemberDto;

/**
 * Servlet implementation class BoardUpdateController
 */
@WebServlet("/boardUpdate")
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

		RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/board/boardUpdate.jsp");
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
		String  categoryName = "";
		try {
			BoardDto board = boardDao.getBoardById(postId); // 게시글 정보 가져오기
			if ("categoryNo1".equals(category)) {
				categoryName = "자유";
				
			} else if ("categoryNo2".equals(category)) {
				categoryName = "정보";
	
			} else if ("categoryNo3".equals(category)) {
				
			}
			// 🔥 게시글 작성자가 로그인한 사용자와 다르면 수정 불가
			if (loginUser == null || loginUser.getNo() != board.getMemberNo()) {
				 RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/common/error.jsp"); 
		            dispatcher.forward(req, res); //에러 페이지 이동
				return;
			}

			// 게시글 수정 진행
			boardDao.updateBoard(postId, title, content,categoryName);
			res.sendRedirect("/classTube/boardList"); // 서블릿으로 이동 (컨트롤러에서 처리)

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/common/error.jsp"); 
            dispatcher.forward(req, res); 
		}
	}

}
