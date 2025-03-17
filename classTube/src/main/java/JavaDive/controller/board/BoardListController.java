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
 * Servlet implementation class boardListController
 */
@WebServlet({"/boardList", "/admin/boardList"})
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardListController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		BoardDao boardDao = new BoardDao();
		ServletContext sc = this.getServletContext();
		Connection conn = (Connection) sc.getAttribute("conn");
		boardDao.setConnection(conn);

		List<BoardDto> boardList = null;
		List<BoardDto> noticeList = null;

		// 🔹 현재 페이지 가져오기 (없으면 기본값 1)
		int currentPage = 1;
		String pageParam = req.getParameter("page");
		if (pageParam != null && pageParam.matches("\\d+")) { // 숫자인지 확인
			currentPage = Integer.parseInt(pageParam);
		}

		int pageSize = 8;
		String keyword = req.getParameter("keyword");

		try {
			// 🔹 공지사항 가져오기 (항상 유지)
			noticeList = boardDao.getTopNotices(2);

			// 🔹 일반 게시물 개수 조회 (공지사항 제외)
			// 총 일반 게시글 개수 가져오기 (공지사항 제외)
			int totalRecords = boardDao.getTotalBoardCount(keyword);
			int basePages = 10; // 기본 페이지 그룹 크기 (1~10페이지)
			int extraPages = 0;

			// 게시글이 10개 이상이면, 추가 페이지 수 계산
			if (totalRecords > basePages * pageSize) {
				extraPages = (int) Math.ceil((double) (totalRecords - (basePages * pageSize)) / pageSize);
			}

			int totalPage = (int) Math.ceil((double) totalRecords / pageSize);


			System.out.println("📌 totalRecords: " + totalRecords); // 🔍 조회된 개수 확인
			System.out.println("📌 totalPage 계산 결과: " + totalPage); // 🔍 totalPage 계산 값 확인
			if (keyword == null || keyword.trim().isEmpty()) {
				// 검색어가 없을 때 일반 리스트 출력
				boardList = boardDao.getBoardList(null, currentPage, pageSize);
			} else {
				// 검색 시 공지사항 제외하고 검색 리스트 가져오기
				boardList = boardDao.getBoardList(keyword, currentPage, pageSize);
			}

			// 🔹 공지사항 + 게시물 리스트 합치기
			List<BoardDto> finalList = new ArrayList<>();
			finalList.addAll(noticeList); // 공지사항 추가
			finalList.addAll(boardList); // 일반 게시물 추가
			System.out.println("📌 공지사항 포함된 최종 리스트: " + finalList);
			// 🔹 세션에 저장
			System.out.println("📌 공지사항 포함된 최종 리스트 크기: " + finalList.size());

			session.setAttribute("boardList", finalList);
			session.setAttribute("currentPage", currentPage);
			session.setAttribute("pageSize", pageSize);
			session.setAttribute("keyword", keyword);
			session.setAttribute("totalPage", totalPage); // 🔹 페이지네이션을 위한 totalPage 추가

		 	String path;
	        if (req.getRequestURI().contains("/admin")) { 
	            path = "/jsp/admin/board/AdminBoardList.jsp";  // 관리자 검색 결과 페이지
	        } else {
	            path = "/jsp/board/boardList.jsp";  // 일반 사용자 검색 결과 페이지
	        }
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, res);

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/common/error.jsp");
			dispatcher.forward(req, res);
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		super.doPost(req, res);
	}

}
