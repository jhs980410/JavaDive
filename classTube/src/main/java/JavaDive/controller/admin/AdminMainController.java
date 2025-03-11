package JavaDive.controller.admin;

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
import JavaDive.dao.member.MemberDao;
import JavaDive.dto.board.BoardDto;
import JavaDive.dto.member.MemberDto;

/**
 * Servlet implementation class AdminMainController
 */@WebServlet("/adminMain")
 public class AdminMainController extends HttpServlet {
	    private static final long serialVersionUID = 1L;

	    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    	HttpSession session = req.getSession();
	    	BoardDao boardDao = new BoardDao();
	    	MemberDao memberDao = new MemberDao();

	    	// DB 연결 설정
	    	ServletContext sc = this.getServletContext();
	    	Connection conn = (Connection) sc.getAttribute("conn");
	    	boardDao.setConnection(conn);
	    	memberDao.setConnection(conn);

	    	// 페이징 변수 설정
	    	int page = 1;
	    	int pageSize = 10;
	    	if (req.getParameter("page") != null) {
	    	    page = Integer.parseInt(req.getParameter("page"));
	    	}

	    	try {
	    	    // 전체 게시글 수 조회
	    	    int totalCount = boardDao.selectTotalCount();
	    	    int totalPage = (int) Math.ceil((double) totalCount / pageSize);
	    	    page = Math.max(1, page);

	    	    // 게시글 리스트 조회 (관리자는 모든 게시글 조회)
	    	    List<BoardDto> boardList = boardDao.selectList(page, pageSize);

	    	    // 회원 리스트 조회 (관리자는 모든 회원 조회)
	    	    List<MemberDto> memberList = memberDao.selectList();

	    	    // JSP에서 사용할 데이터 저장
	    	    req.setAttribute("boardList", boardList);
	    	    req.setAttribute("memberList", memberList);
	    	    req.setAttribute("currentPage", page);
	    	    req.setAttribute("pageSize", pageSize);
	    	    req.setAttribute("totalPage", totalPage);

	    	    // 관리자 페이지로 포워딩
	    	    RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/admin/adminMain.jsp");
	    	    dispatcher.forward(req, res);

	    	} catch (Exception e) {
	    	    e.printStackTrace();
	    	    res.sendRedirect("error.jsp");
	    	}


	        // JSP로 포워딩
	        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/admin/adminMain.jsp");
	        dispatcher.forward(req, res);
	    }

	    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	        doGet(req, res);
	    }
	}
