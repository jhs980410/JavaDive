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

import JavaDive.dao.board.BoardCommentDao;
import JavaDive.dto.member.MemberDto;

/**
 * Servlet implementation class BoardCommentAdd
 */
@WebServlet("/BoardCommentUpdate")
public class CommentUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	public CommentUpdateController() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		
	}

	


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection conn = null;
		req.setCharacterEncoding("UTF-8");
		
		try {
			String comment = req.getParameter("content"); //post에 담긴 내용값 가져오기 
			int postId = Integer.parseInt(req.getParameter("postId"));
			int commentId = Integer.parseInt(req.getParameter("commentId"));

			HttpSession session = req.getSession();
			
			
			MemberDto loginUser = (MemberDto) session.getAttribute("member");

			System.out.println("댓글수정창 진입");
			
			if (loginUser == null) {
				req.getRequestDispatcher("/LoginPage.jsp").forward(req, res);
				return;
			}
			int memberNo = loginUser.getNo();
			ServletContext sc = getServletContext();      //db연결 
			conn =  (Connection) sc.getAttribute("conn");
			if (conn == null) {
			    throw new ServletException("DB 연결이 설정되지 않았습니다.");
			}
			
			BoardCommentDao boardCommentDao = new BoardCommentDao();
			boardCommentDao.setConn(conn);
			boardCommentDao.updateComment(commentId,comment);
			System.out.println("댓글업데이트됐음");
			res.sendRedirect(req.getContextPath() + "/boardView?postId=" + postId);

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "댓글 등록 중 오류 발생");
		    System.out.println("댓글 등록 중 오류 발생: " + e.getMessage()); 


		}
		
	}

}
