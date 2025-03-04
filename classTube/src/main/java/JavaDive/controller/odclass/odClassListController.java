package JavaDive.controller.odclass;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import spms.dao.MemberDao;
import spms.dto.MemberDto;

public class odClassListController  extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		
		try {

			ServletContext sc = this.getServletContext();
			
			conn = (Connection)sc.getAttribute("conn");
			
			ODClassDao odClassDao = new ODClassDao();
//			주입
			memberDao.setConnection(conn);
			
			ArrayList<MemberDto> memberList = null;
			
			memberList = (ArrayList<MemberDto>)memberDao.selectList();
			
			request.setAttribute("memberList", memberList);
			
			RequestDispatcher dispatcher = 
				request.getRequestDispatcher("./MemberListView.jsp");
			
			dispatcher.include(request, response);
			
		} catch (Exception e) {
//			throw new ServletException(e);
			System.out.println("회원 목록에서 예외 발생");
			e.printStackTrace();
			
			request.setAttribute("error", e);
			
			RequestDispatcher dispatcher =
				request.getRequestDispatcher("/Error.jsp");
			dispatcher.forward(request, response);
		}
	
	}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	
}
