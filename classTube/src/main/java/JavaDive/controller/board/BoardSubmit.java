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
import java.security.Timestamp;
import java.sql.Connection;

import JavaDive.dao.board.BoardDao;
import JavaDive.dto.board.BoardDto;


public class BoardSubmit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardSubmit() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection conn = null;
		req.setCharacterEncoding("UTF-8");
		try {
			// 1️ 요청 데이터 가져오기
			String category = req.getParameter("category");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			// 2️ 카테고리 번호 매핑
			int categoryNo = 0;
			if ("categoryNo1".equals(category)) {
				categoryNo = 1;
				category = "공지사항";
			} else if ("categoryNo2".equals(category)) {
				categoryNo = 2;
				category = "자유게시판";
			} else if ("categoryNo3".equals(category)) {
				categoryNo = 3;
				category = "정보";
			}

			// 3️ DB 연결 가져오기
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");

			if (conn == null) {
			    throw new ServletException("DB 연결이 설정되지 않았습니다.");
			}

			// 4️ BoardDto 객체 생성 및 데이터 세팅
			BoardDto boardDto = new BoardDto();
			boardDto.setTitle(title);
			boardDto.setWriter("user02");   // 로그인 구현 시 세션에서 가져오도록 수정 필요
			boardDto.setContent(content);
			boardDto.setCategory(category);
			boardDto.setCategoryNo(categoryNo);
			
			System.out.println(title);
			System.out.println(content);
			System.out.println(category);
			System.out.println(categoryNo);
			System.out.println();
			int recentPostId = 0;
			// 5️ DAO를 통한 DB 저장
			BoardDao boardDao = new BoardDao();
			boardDao.setConnection(conn);
			recentPostId = boardDao.boardInsert(boardDto);
			System.out.println(recentPostId);
			if (recentPostId > 0) {
			    System.out.println("게시글 등록 성공 게시글id: " + recentPostId);

			    //  세션 생성 및 데이터 저장
			    HttpSession session = req.getSession(); // 세션 가져오기 (없으면 새로 생성)
			    System.out.println(boardDto.getCreateDate());
			    session.setAttribute("boardDto", boardDto); // 게시글 데이터 저장
			    session.setAttribute("recentPostId", recentPostId); // 게시글 번호 저장
			    //회원가입 완성시 세션 수정 및 쿼리수정예정 
			    System.out.println("세션 boardDto: " + session.getAttribute("boardDto"));
			    System.out.println("세션 recentPostId: " + session.getAttribute("recentPostId")); 
			    
			    //  boardView.jsp로 이동
				/* res.sendRedirect("board/boardView.jsp?postId=" + recentPostId); */
			    RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/board/boardView.jsp");
			    dispatcher.forward(req, res);  //세션유지 서버내부이동  //

			    
			    return;
			} else {
			    System.out.println("게시글 등록 실패");
			    res.getWriter().println("<script>alert('게시글 등록 실패!'); history.back();</script>");
			    return;
			}


			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "게시글 등록 중 오류 발생");
		    System.out.println("게시글 등록 중 오류 발생: " + e.getMessage()); 


		}
	}
}
