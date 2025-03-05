package JavaDive.controller.odclass;

import java.io.IOException;
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
import spms.dto.MemberDto;

@WebServlet("/class/add")
public class ODClassAddController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub

		res.sendRedirect("");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;

		String className = req.getParameter("className");
		int price = Integer.parseInt(req.getParameter("price"));
		String desc = req.getParameter("desc");
		String instructor = req.getParameter("instructor");
		int limit = Integer.parseInt(req.getParameter("limit"));

		ODClassDto odClassDto = new ODClassDto();

		odClassDto.setClassName(className);
		odClassDto.setPrice(price);
		odClassDto.setDesc(desc);
		odClassDto.setInstructor(instructor);
		odClassDto.setClassLimit(limit);

		try {
			ServletContext sc = this.getServletContext();

			conn = (Connection) sc.getAttribute("conn");

			ODClassDao odClassDao = new ODClassDao();
			odClassDao.setConnection(conn);

			int result = 0;

			result = odClassDao.classInsert(odClassDto);

			if (result == 0) {
				System.out.println("회원가입 실패");
			}

			res.sendRedirect("");

		} catch (Exception e) {
			e.printStackTrace();

			req.setAttribute("error", e);
			
			RequestDispatcher dispatcher = 
					req.getRequestDispatcher("/Error.jsp");
			
			dispatcher.forward(req, res);
		}

	}
}
