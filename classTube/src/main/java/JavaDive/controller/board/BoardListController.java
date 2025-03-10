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
@WebServlet("/boardList")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public BoardListController() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        BoardDao boardDao = new BoardDao();

        ServletContext sc = this.getServletContext();
        Connection conn = (Connection) sc.getAttribute("conn");
        boardDao.setConnection(conn);

        // ✅ 요청된 페이지 번호 받기 (기본값: 1)
        int page = 1;
        int pageSize = 8;  // ✅ 한 페이지당 게시글 개수

        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }

        try {
            // ✅ 전체 게시글 개수 가져오기
            int totalCount = boardDao.selectTotalCount();
          System.out.println("컨트롤러측 " + totalCount );
            int totalPage = (int) Math.ceil((double) totalCount / pageSize);

            System.out.println("토탈값: " + totalPage);
            page = Math.max(1, page); // 최소값 1로 고정
 // 총 페이지 수 계산

            List<BoardDto> boardList = boardDao.selectList(page, pageSize);
            System.out.println("현재 페이지: " + page);
            System.out.println("가져온 게시글 개수: " + boardList.size());

            req.setAttribute("boardList", boardList);
            req.setAttribute("currentPage", page);
            req.setAttribute("pageSize", pageSize);
            req.setAttribute("totalPage", totalPage);  // 🔥 추가된 부분

            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/board/boardList.jsp");
            dispatcher.forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect("error.jsp");
        }
    }



	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		super.doPost(req, res);
	}

}
