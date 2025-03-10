package JavaDive.controller.board;

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
 * Servlet implementation class BoardDeleteController
 */
@WebServlet("/boardDelete")
public class BoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // 1. DB 연결 설정
        ServletContext sc = this.getServletContext();
        Connection conn = (Connection) sc.getAttribute("conn");

        // 2. DAO 객체 생성 및 연결
        BoardDao boardDao = new BoardDao();
        boardDao.setConnection(conn);

        try {
            int postId = Integer.parseInt(req.getParameter("postId"));

            HttpSession session = req.getSession();
            MemberDto loginUser = (MemberDto) session.getAttribute("member");

            BoardDto board = boardDao.getBoardById(postId);

            //  게시글 작성자만 삭제 가능하도록 체크
            if (loginUser == null || loginUser.getNo() != board.getMemberNo()) {
                res.sendRedirect("error.jsp"); // 권한 없는 경우 에러 페이지 이동
                return;
            }

            // 7. 게시글 삭제 실행
            boardDao.deleteBoard(postId);

            // 8. 삭제 후 목록 페이지로 이동
            res.sendRedirect("/classTube/boardList");

        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect("error.jsp");
        }
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}
