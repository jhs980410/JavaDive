package JavaDive.controller.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import JavaDive.dao.board.BoardDao;
import JavaDive.dto.board.BoardDto;
import JavaDive.dto.member.MemberDto;

/**
 * Servlet implementation class BoardUpdateController
 */
@WebServlet("/BoardUpdate")
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        MemberDto loginUser = (MemberDto) session.getAttribute("member");

        int postId = Integer.parseInt(request.getParameter("postId"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        BoardDao boardDao = new BoardDao();
        
        try {
            BoardDto board = boardDao.getBoardById(postId);  // 게시글 정보 가져오기

            // 🔥 게시글 작성자가 로그인한 사용자와 다르면 수정 불가
            if (loginUser == null || loginUser.getNo() != board.getMemberNo()) {
                response.sendRedirect("error.jsp"); // 에러 페이지 이동
                return;
            }

            // 게시글 수정 진행
            boardDao.updateBoard(postId, title, content);
            response.sendRedirect("boardView.jsp?postId=" + postId);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

}
