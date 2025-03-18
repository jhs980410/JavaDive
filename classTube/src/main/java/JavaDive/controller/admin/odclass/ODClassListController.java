package JavaDive.controller.admin.odclass;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import JavaDive.dao.odclass.ODClassDao;
import JavaDive.dto.board.BoardDto;
import JavaDive.dto.odclass.ODClassDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet({"/admin/category/list", "/main"})
public class ODClassListController  extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		Connection conn = null;
		
		try {

			ServletContext sc = this.getServletContext();
			
			conn = (Connection)sc.getAttribute("conn");
			
			ODClassDao odClassDao = new ODClassDao();
//			주입
			odClassDao.setConnection(conn);
				
			ArrayList<ODClassDto> odClassList = null;
			
			
			// 🔹 현재 페이지 가져오기 (없으면 기본값 1)
			int currentPage = 1;
			String pageParam = req.getParameter("page");
			
			if (pageParam != null && pageParam.matches("\\d+")) { // 숫자인지 확인
				currentPage = Integer.parseInt(pageParam);
			}

			int pageSize = 12;
			String keyword = req.getParameter("keyword");
			
			 if (req.getRequestURI().contains("/admin")) { 
		            pageSize = 6;
		        }

			try {
				// 🔹 클래스 개수 조회
				// 클래스의 총 개수 가져오기
				int totalRecords = odClassDao.getTotalClassCount(keyword);
				int totalPage = (int) Math.ceil((double)totalRecords / pageSize); // 총 페이지 수

				System.out.println("📌 totalRecords: " + totalRecords); // 🔍 조회된 개수 확인
				System.out.println("📌 totalPage 계산 결과: " + totalPage); // 🔍 totalPage 계산 값 확인
				if (keyword == null || keyword.trim().isEmpty()) {
					// 검색어가 없을 때 일반 리스트 출력
					odClassList = (ArrayList<ODClassDto>) odClassDao.selectClassList(currentPage, pageSize);
				} else {
					// 검색 시 공지사항 제외하고 검색 리스트 가져오기
					odClassList = (ArrayList<ODClassDto>) odClassDao.selectClassList(keyword, currentPage, pageSize);
				}

				// 🔹 session에 저장
				session.setAttribute("odClassList", odClassList);
				session.setAttribute("currentPage", currentPage);
				session.setAttribute("pageSize", pageSize);
				session.setAttribute("keyword", keyword);
				session.setAttribute("totalPage", totalPage); // 🔹 페이지네이션을 위한 totalPage 추가

			 	String path;
		        if (req.getRequestURI().contains("/admin")) { 
		            path = "/jsp/admin/category/ClassListView.jsp";  // 관리자 검색 결과 페이지
		        } else {
		            path = "/jsp/common/classTubeMain.jsp";  // 일반 사용자 검색 결과 페이지
		        }
				RequestDispatcher dispatcher = req.getRequestDispatcher(path);
				dispatcher.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/common/error.jsp");
				dispatcher.forward(req, res);
			}
			
		} catch (Exception e) {
//			throw new ServletException(e);
			System.out.println("클래스 목록에서 예외 발생");
			e.printStackTrace();
			
			req.setAttribute("error", e);
			
			RequestDispatcher dispatcher =
					req.getRequestDispatcher("/jsp/common/error.jsp");
			dispatcher.forward(req, res);
		}
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	
}
