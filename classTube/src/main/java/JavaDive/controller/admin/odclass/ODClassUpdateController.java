package JavaDive.controller.admin.odclass;

import java.io.IOException;
import java.lang.reflect.Member;
import java.sql.Connection;

import JavaDive.dao.odclass.ODClassDao;
import JavaDive.dto.odclass.ODClassDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/category/update")
public class ODClassUpdateController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		
		int classNo = Integer.parseInt(req.getParameter("classNo"));

		try {
			ServletContext sc = this.getServletContext();

			conn = (Connection)sc.getAttribute("conn");
			
			ODClassDao odClassDao = new ODClassDao();
			odClassDao.setConnection(conn);
			
			ODClassDto odClassDto = odClassDao.classSelectOne(classNo);
			
			req.setAttribute("odClassDto", odClassDto);
			
			RequestDispatcher dispatcher =
					req.getRequestDispatcher("/jsp/admin/category/ClassUpdateForm.jsp");
			
			dispatcher.forward(req, res);
			
			
		} catch (Exception e) {
			// TODO: handle exception
//			throw new ServletException(e);
			System.out.println("클래스 업데이트에서 예외 발생");
			e.printStackTrace();
			
			req.setAttribute("error", e);
			RequestDispatcher dispatcher =
					req.getRequestDispatcher("/jsp/common/error.jsp");
			
			dispatcher.forward(req, res);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		
		int classNoStr = Integer.parseInt(req.getParameter("classNo"));
		String classNameStr = req.getParameter("className");
		int priceStr = Integer.parseInt(req.getParameter("price"));
		String descStr = req.getParameter("classDesc");
		String instructorStr  = req.getParameter("instructor");
		int classLimitStr = Integer.parseInt(req.getParameter("classLimit"));
		String regionStr = req.getParameter("region");
		int categoryNoStr = Integer.parseInt(req.getParameter("categoryNo"));
		
		ODClassDto odClassDto = new ODClassDto();
		
		odClassDto.setClassNo(classNoStr);
		odClassDto.setClassName(classNameStr);
		odClassDto.setPrice(priceStr);
		odClassDto.setClassDesc(descStr);
		odClassDto.setInstructor(instructorStr);
		odClassDto.setClassLimit(classLimitStr);
		odClassDto.setRegion(regionStr);
		odClassDto.setCategoryNo(categoryNoStr);
		

		
		try {
			ServletContext sc = this.getServletContext();

			conn = (Connection)sc.getAttribute("conn");
			
			ODClassDao odClassDao = new ODClassDao();
			odClassDao.setConnection(conn);
			
			int result = 0;
			
			result = odClassDao.odClassUpdate(odClassDto);
			
			if(result == 0) {
				System.out.println("클래스 정보 변경 실패");
			}
			
			res.sendRedirect("./list");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			req.setAttribute("error", e);
			RequestDispatcher dispatcher =
					req.getRequestDispatcher("/jsp/common/error.jsp");
			
			dispatcher.forward(req, res);
		}
	}
}
