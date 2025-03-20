package JavaDive.controller.admin.odclass;

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
import jakarta.servlet.http.HttpSession;


@WebServlet("/admin/category/add")
public class ODClassAddController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member"); 
		RequestDispatcher dispatcher = 
				 req.getRequestDispatcher("/jsp/admin/category/ClassAddForm.jsp");
		
		dispatcher.forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;

		String classNameStr = req.getParameter("className");
		int priceStr = Integer.parseInt(req.getParameter("price"));
		String descStr = req.getParameter("classDesc");
		String instructorStr = req.getParameter("instructor");
		int limitStr = Integer.parseInt(req.getParameter("classLimit"));
		String imgStr = req.getParameter("img");
		String regionStr = req.getParameter("region");
		int categoryNo = Integer.parseInt(req.getParameter("categoryNo"));
		
		ODClassDto odClassDto = new ODClassDto();

		odClassDto.setClassName(classNameStr);
		odClassDto.setPrice(priceStr);
		odClassDto.setClassDesc(descStr);
		odClassDto.setInstructor(instructorStr);
		odClassDto.setClassLimit(limitStr);
		odClassDto.setImg(imgStr);
		odClassDto.setRegion(regionStr);
		odClassDto.setCategoryNo(categoryNo);

		try {
			ServletContext sc = this.getServletContext();

			conn = (Connection) sc.getAttribute("conn");

			ODClassDao odClassDao = new ODClassDao();
			odClassDao.setConnection(conn);

			int result = 0;

			result = odClassDao.classInsert(odClassDto);

			if (result == 0) {
				System.out.println("클래스 추가 실패");
			}

			res.sendRedirect("./list");

		} catch (Exception e) {
			e.printStackTrace();

			req.setAttribute("error", e);
			
			RequestDispatcher dispatcher = 
					req.getRequestDispatcher("/jsp/common/error.jsp");
			
			dispatcher.forward(req, res);
		}

	}
}
