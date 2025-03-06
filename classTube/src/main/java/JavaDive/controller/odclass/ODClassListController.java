package JavaDive.controller.odclass;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import JavaDive.dao.odclass.ODClassDao;
import JavaDive.dto.odclass.ODClassDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/category/list")
public class ODClassListController  extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		
		try {

			ServletContext sc = this.getServletContext();
			
			conn = (Connection)sc.getAttribute("conn");
			
			ODClassDao odClassDao = new ODClassDao();
//			주입
				odClassDao.setConnection(conn);
				
			ArrayList<ODClassDto> odClassList = null;
			
			odClassList = (ArrayList<ODClassDto>)odClassDao.selectClassList();
			
			req.setAttribute("odClassList", odClassList);
			
			RequestDispatcher dispatcher = 
				req.getRequestDispatcher("/jsp/category/ClassListView.jsp");
			
			dispatcher.forward(req, res);
			
		} catch (Exception e) {
//			throw new ServletException(e);
			System.out.println("회원 목록에서 예외 발생");
			e.printStackTrace();
			
			req.setAttribute("error", e);
			
			RequestDispatcher dispatcher =
					req.getRequestDispatcher("/Error.jsp");
			dispatcher.forward(req, res);
		}
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	
}
