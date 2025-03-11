package admin.controller.odclass;

import java.io.IOException;
import java.sql.Connection;

import JavaDive.dao.odclass.ODClassDao;
import JavaDive.dto.member.MemberDto;
import JavaDive.dto.odclass.ODClassDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/category/delete")
public class ODClassDeleteController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		
		int classId = Integer.parseInt(req.getParameter("classNo"));
		
		ODClassDto odClassDto = new ODClassDto();
		
		odClassDto.setClassNo(classId);
		
		try {ServletContext sc = this.getServletContext();
		
		conn = (Connection)sc.getAttribute("conn");
		
		ODClassDao odClassDao = new ODClassDao();
		odClassDao.setConnection(conn);
		
		int result = 0;
         
        result = odClassDao.classDelete(classId);
        
        System.out.println("???????: " + result);
         if(result == 0) {
        	 System.out.println("클래스 삭제 실패");
         }
		
		res.sendRedirect("./list");

	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();

		req.setAttribute("error", e);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/Error.jsp");

		dispatcher.forward(req, res);
	}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
}
