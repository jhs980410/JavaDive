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

//@WebServlet({"/admin/boardNotice" , "/boardNotice"})
@WebServlet({"/BoardCommentAdd","/admin/BoardCommentAdd"})
public class CommentAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection conn = null;
		req.setCharacterEncoding("UTF-8");
		
		try {
			String comment = req.getParameter("content"); //post에 담긴 내용값 가져오기 
			int postId = Integer.parseInt(req.getParameter("postId"));
			HttpSession session = req.getSession();
			
			
			MemberDto loginUser = (MemberDto) session.getAttribute("member");


			
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
			  String path;
			    if (req.getRequestURI().contains("/admin")) { 
			        path = "/admin/board/AdminBoardView?postId=";  // 관리자 서블릿 경로 수정
			    } else {
			        path = "/boardView?postId=";  // 일반 사용자 페이지
			    }

			    // 올바른 경로로 리다이렉트
			   
			BoardCommentDao boardCommentDao = new BoardCommentDao();
			boardCommentDao.setConn(conn);
			boardCommentDao.insertComment(postId, memberNo, comment);
			System.out.println("댓글추가됐음");
			 res.sendRedirect(req.getContextPath() + path + postId);

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "댓글 등록 중 오류 발생");
		    System.out.println("댓글 등록 중 오류 발생: " + e.getMessage()); 


		}
		
	}

}
