package JavaDive.controller.admin.odclass;

import java.io.IOException;
import java.lang.reflect.Member;
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
import jakarta.servlet.http.HttpSession;

@WebServlet({"/admin/category/detail", "/category/detail"})
public class ODClassDetailController extends HttpServlet {
	
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
			
			HttpSession session = req.getSession();
			 
			MemberDto memberDto = (MemberDto) session.getAttribute("member"); 
			
			RequestDispatcher dispatcher = null;
					
			if ("ADMIN".equals(memberDto.getPriv())) {
				
				dispatcher = req.getRequestDispatcher("/jsp/admin/category/ClassDetailView.jsp");

			}else {

				dispatcher = req.getRequestDispatcher("/jsp/category/classDetailView.jsp");
			}
			
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
		super.doPost(req, res);
	}
}
