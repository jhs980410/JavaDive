package JavaDive.controller.board;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

import JavaDive.dao.board.BoardDao;
import JavaDive.dto.board.BoardDto;

/**

 */

@WebServlet("/boardView") 
public class BoardViewController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String postIdParam = req.getParameter("postId");
        int postId = (postIdParam != null) ? Integer.parseInt(postIdParam) : -1;

        BoardDao boardDao = new BoardDao();
        boardDao.setConnection((Connection) getServletContext().getAttribute("conn"));
        BoardDto boardDto = boardDao.getBoardById(postId);
        
        if (boardDto == null) {
            System.out.println("❌ postId " + postId + "에 해당하는 게시글이 존재하지 않습니다.");
            res.sendRedirect("boardList.jsp");
            return;
        }

        req.setAttribute("boardDto", boardDto);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/board/boardView.jsp");
        dispatcher.forward(req, res);
    }
}
