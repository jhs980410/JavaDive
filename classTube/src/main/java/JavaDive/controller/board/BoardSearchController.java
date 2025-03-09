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
import java.util.List;

import JavaDive.dao.board.BoardDao;
import JavaDive.dto.board.BoardDto;

/**
 * Servlet implementation class BoardSearchController
 */
@WebServlet("/boardSearch")
public class BoardSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardSearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		BoardDao boardDao = new BoardDao();
		ServletContext sc = this.getServletContext();
		Connection conn = (Connection)sc.getAttribute("conn");
		boardDao.setConnection(conn);
		List<BoardDto> boardList = null; // dao에서 , keyword 입력예정 
		String keyWord = "";
		try {
			System.out.println("Search 컨트롤러진입"); 
			String keyword = req.getParameter("keyword");
			boardList = boardDao.searchBoard(keyword); 
		session.setAttribute("boardList", boardList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/board/boardList.jsp");
        dispatcher.forward(req, res);
			
			 
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}
