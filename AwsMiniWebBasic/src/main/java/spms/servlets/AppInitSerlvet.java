package spms.servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import spms.dto.MemberDto;

public class AppInitSerlvet extends HttpServlet{

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("AppInitSerlvet 준비중...");
		
		ServletContext sc = this.getServletContext();
		// 데이터베이스 연결을 위한 Connection 객체 선언
		Connection conn = null;

		
		// 데이터베이스 접속 정보 설정
		String url = "";
		String user = "";
	    String password = "";
	    String driver = "";
	    
	    
		try {
			 url = sc.getInitParameter("url");
			 user = sc.getInitParameter("user");
		     password = sc.getInitParameter("password");
		     driver = sc.getInitParameter("driver");
		    
		    
			// 1. JDBC 드라이버 로드
			Class.forName(driver);
			// 2. 데이터베이스에 연결
			conn = DriverManager.getConnection(url, user, password);
			// 3. SQL 실행 객체 준비
			
			sc.setAttribute("conn", conn);
			System.out.println("DB 연결 성공");
		} catch (ClassNotFoundException e) {
			//예외처리
			System.out.println("JDBC API 오류 발생");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB 연결 오류 발생");
			e.printStackTrace();
		}
		
		

	}
	
	@Override
	public void destroy() {
		System.out.println("AppInitSerlvet 마무리...");
		
		ServletContext sc = this.getServletContext(); 
		
	
				
				
		try {
			Connection conn = (Connection)sc.getAttribute("conn");
			if (conn != null) {
					conn.close(); // Connection 닫기
					System.out.println("db연결 해제 ");
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		
		

	
			
	
		super.destroy();
	}
	
	
}
