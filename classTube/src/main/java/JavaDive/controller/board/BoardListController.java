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

        // 📌 DB 연결 가져오기
        ServletContext sc = this.getServletContext();
        Connection conn = (Connection) sc.getAttribute("conn");
        boardDao.setConnection(conn);

        try {
            List<BoardDto> boardList = boardDao.selectList();
            if (boardList == null) {
				/*
				 * System.out.println(); boardList = new ArrayList<>();
				 */
            	            	
			}
            req.setAttribute("boardList", boardList);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/board/boardList.jsp");
            dispatcher.forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect("error.jsp"); // 에러 페이지로 이동
        }
    }

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		super.doPost(req, res);
	}

}
