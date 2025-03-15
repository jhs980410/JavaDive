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
import JavaDive.dto.board.BoardCommentDto;
import JavaDive.dto.member.MemberDto;

@WebServlet({ "/admin/CommentView", "/CommentView" })
public class CommentViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CommentViewController() {
		super();

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		MemberDto loginUser = (MemberDto) session.getAttribute("member"); // 로그인한 사용자 정보

		int commentId = -1; // 기본값 설정
		int postId = Integer.parseInt(req.getParameter("postId"));
		try {
			commentId = Integer.parseInt(req.getParameter("commentId")); // URL에서 commentId 받기
			System.out.println("조회할 수정할 댓글 번호: " + commentId);

			System.out.println("수정할 댓글의 게시글 번호: " + postId);
		} catch (NumberFormatException e) {
			System.out.println("올바르지 않은 commentId 요청: " + req.getParameter("commentId"));
			res.getWriter().write("<script>alert('올바르지 않은 요청입니다.'); window.close();</script>");
			return;
		}
		System.out.println("댓글 내용 확인창 접근");
		try {
			ServletContext sc = getServletContext();
			Connection conn = (Connection) sc.getAttribute("conn");

			BoardCommentDao commentDao = new BoardCommentDao();
			commentDao.setConn(conn);
			BoardCommentDto comment = commentDao.getCommentById(commentId); // DB에서 댓글 조회

			if (comment == null) {
				System.out.println("해당 commentId의 댓글을 찾을 수 없습니다.");
				res.getWriter().write("<script>alert('존재하지 않는 댓글입니다.'); window.close();</script>");
				return;
			}

			req.setAttribute("comment", comment); // JSP로 데이터 전달
			req.setAttribute("postId", postId);
			String viewPath = "/jsp/board/commentView.jsp"; // 기본값 (일반 게시판)

			// 관리자 페이지에서 호출된 경우, 다른 JSP로 이동
			if (req.getRequestURI().contains("/admin/")) {
				viewPath = "/jsp/admin/board/AdmincommentView.jsp";
			}

			req.getRequestDispatcher(viewPath).forward(req, res);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("댓글 조회 중 오류 발생: " + e.getMessage());
			res.getWriter().write("<script>alert('댓글 조회 중 오류가 발생했습니다.'); window.close();</script>");
		}
	}

}
