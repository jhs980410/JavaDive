package JavaDive.controller.board;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/common/error.jsp"); 
            dispatcher.forward(req, res);



            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("boardDto", boardDto);

        System.out.println("✅ 업데이트된 세션 boardDto: " + session.getAttribute("boardDto"));

        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/board/boardView.jsp");
        dispatcher.forward(req, res);
    }
}
