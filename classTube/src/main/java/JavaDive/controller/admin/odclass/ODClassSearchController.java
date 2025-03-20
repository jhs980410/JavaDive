package JavaDive.controller.admin.odclass;

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
import java.util.ArrayList;
import java.util.List;

import JavaDive.dao.board.BoardDao;
import JavaDive.dao.odclass.ODClassDao;
import JavaDive.dto.board.BoardDto;
import JavaDive.dto.odclass.ODClassDto;

/**
 * Servlet implementation class BoardSearchController
 */
@WebServlet({"/category/search", "/admin/category/search"}) 
public class ODClassSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ODClassSearchController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		ServletContext sc = this.getServletContext();
		Connection conn = (Connection) sc.getAttribute("conn");
		ODClassDao odClassDao = new ODClassDao();
		odClassDao.setConnection(conn);
		ArrayList<ODClassDto> odClassList = null; // dao에서 , keyword 입력예정
		
		try {
			
			
			System.out.println("Search 컨트롤러진입");
		
			// 🔹 현재 페이지 가져오기 (없으면 기본값 1)
			int currentPage = 1;  // 기본 페이지는 1
			String pageParam = req.getParameter("page");
			
			if (pageParam != null && pageParam.matches("\\d+")) { // 숫자인지 확인
				currentPage = Integer.parseInt(pageParam);
			}
			
			int pageSize = 6;  // 한 페이지에 6개씩 조회
			
			
			String keyword = req.getParameter("keyword");
			Integer categoryNo = null;
			
			if (req.getParameter("categoryNo") == null) {
				odClassList = (ArrayList<ODClassDto>) odClassDao.selectClassList(keyword, currentPage, pageSize);
			} else {
				categoryNo = Integer.parseInt(req.getParameter("categoryNo"));
				odClassList = (ArrayList<ODClassDto>) odClassDao.selectClassList(categoryNo, keyword, currentPage, pageSize);
			}
			

	        session.setAttribute("odClassList", odClassList);
	        
	        int totalRecords = odClassDao.getTotalClassCount(keyword, categoryNo);
			int totalPage = (int) Math.ceil((double)totalRecords / pageSize); // 총 페이지 수

	        // 📌 추가: 현재 페이지와 페이지 크기도 세션에 저장 (페이징 유지)
			session.setAttribute("currentPage", currentPage);
			session.setAttribute("pageSize", pageSize);
			session.setAttribute("keyword", keyword);
			session.setAttribute("categoryNo", categoryNo);
			session.setAttribute("totalPage", totalPage);
			
			String path;
			
	        if (req.getRequestURI().contains("/admin")) { 
	            path = "/jsp/admin/category/ClassListSearchView.jsp";  // 관리자 검색 결과 페이지
	        } else {
	            path = "/jsp/category/classListCategoryView.jsp";  // 일반 사용자 검색 결과 페이지
	        }

	        RequestDispatcher dispatcher = req.getRequestDispatcher(path);
	        dispatcher.forward(req, res);

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/common/error.jsp");
			dispatcher.forward(req, res);
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}
