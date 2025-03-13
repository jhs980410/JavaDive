package JavaDive.controller.admin.board;

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
 * Servlet implementation class AdminBoardList
 */
@WebServlet("/admin/board/list")
public class AdminBoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		BoardDao boardDao = new BoardDao();

		ServletContext sc = this.getServletContext();
		Connection conn = (Connection) sc.getAttribute("conn");
		boardDao.setConnection(conn);

		// ✅ 요청된 페이지 번호 받기 (기본값: 1)
		int page = 1;
		int pageSize = 8; // ✅ 한 페이지당 게시글 개수

		
		if (req.getParameter("page") != null && !req.getParameter("page").isEmpty()) {
		    try {
		        page = Integer.parseInt(req.getParameter("page")); // 정상 숫자만 변환
		    } catch (NumberFormatException e) {
		        System.out.println("잘못된 페이지 값, 기본값(1)으로 설정");
		    }
		}


		try {
			// ✅ 전체 게시글 개수 가져오기
			int totalCount = boardDao.selectTotalCount();
			System.out.println("관리자 컨트롤러측 " + totalCount);
			int totalPage = (int) Math.ceil((double) totalCount / pageSize);

			System.out.println(" 관리자 토탈값: " + totalPage);
			page = Math.max(1, page); // 최소값 1로 고정
			// 총 페이지 수 계산

			List<BoardDto> boardList = boardDao.adminSelectList(page, pageSize);
			System.out.println("현재 페이지: " + page);

			session.setAttribute("boardList", boardList);
			session.setAttribute("currentPage", page);
			session.setAttribute("pageSize", pageSize);
			session.setAttribute("totalPage", totalPage); //세션업데이트

			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/admin/board/AdminBoardList.jsp");
			dispatcher.forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
			res.sendRedirect("error.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}
