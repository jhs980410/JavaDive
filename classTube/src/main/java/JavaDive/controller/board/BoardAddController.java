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
import java.sql.Timestamp;

import java.sql.Connection;

import JavaDive.dao.board.BoardDao;
import JavaDive.dto.board.BoardDto;
import JavaDive.dto.member.MemberDto;


public class BoardAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardAddController() {
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
			HttpSession session = req.getSession();
			MemberDto memberDto = (MemberDto)session.getAttribute("member"); //세션값 memberdto값 /
			System.out.println("boardAdd 세션의 memberDto: " + memberDto);
			// 2️ 카테고리 번호 매핑
			if (memberDto == null) {
	            System.out.println("오류: 세션에서 memberDto를 찾을 수 없음. 로그인 필요.");
	            res.sendRedirect(req.getContextPath() + "/LoginPage.jsp"); // 로그인 페이지로 이동
	            return;
	        }
			
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
			boardDto.setMemberno(memberDto.getNo());
			boardDto.setContent(content);
			boardDto.setCategory(category);
			boardDto.setCategoryNo(categoryNo);
			
			
			System.out.println();
			int recentPostId = 0;
			// 5️ DAO를 통한 DB 저장
			BoardDao boardDao = new BoardDao();
			boardDao.setConnection(conn);
			recentPostId = boardDao.boardInsert(boardDto, req); // 게시글 저장
			System.out.println("recentPostId: " + recentPostId);
			
			if (recentPostId > 0) {
			    System.out.println("게시글 등록 성공 게시글id: " + recentPostId);
			    
			    // 📌 DB에서 다시 조회하여 `createDate` 가져오기
			    boardDto = boardDao.getBoardById(recentPostId);
			    
			    session = req.getSession(); // 세션 가져오기
			    session.setAttribute("boardDto", boardDto); // 최신 데이터로 세션 업데이트
			    
			    System.out.println("업데이트된 세션 boardDto: " + session.getAttribute("boardDto"));

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
