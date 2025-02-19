package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// HttpServlet 클래스를 상속받아 회원 정보를 조회 및 수정하는 서블릿 정의
@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {

    // GET 요청을 처리하여 회원 정보 수정 폼을 제공
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // 데이터베이스 연결을 위한 변수 선언
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        ServletContext sc = this.getServletContext();
        // web.xml에서 설정한 DB 연결 정보를 가져옴
        String driver = sc.getInitParameter("driver");
        String url = sc.getInitParameter("url");
        String user = sc.getInitParameter("user");
        String password = sc.getInitParameter("password");

        // 요청 파라미터에서 회원 번호(mNo) 값을 가져와 정수형으로 변환
        int mNo = Integer.parseInt(req.getParameter("mNo"));

        // SQL 문 선언
        String sql = "";

        System.out.println("오라클 드라이버 로드");
        try {
            // 1. JDBC 드라이버 로드
            Class.forName(driver);
            // 2. 데이터베이스 연결
            conn = DriverManager.getConnection(url, user, password);

            // 3. 특정한 회원 정보 조회 쿼리 작성
            sql = "SELECT MNO, EMAIL, MNAME, CRE_DATE FROM PM17 WHERE MNO = ?";

            // 4. PreparedStatement 객체 생성 및 SQL 실행 준비
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, mNo);

            // 5. SQL 실행 및 결과 저장
            rs = pstmt.executeQuery();

            // 회원 정보 저장을 위한 변수 선언
            String mName = "";
            String email = "";
            Date creDate = null;

            // 결과가 존재하면 회원 정보 저장
            while (rs.next()) {
                mName = rs.getString("MNAME");
                email = rs.getString("EMAIL");
                creDate = rs.getDate("CRE_DATE");
            }

            // 응답 타입 및 인코딩 설정
            res.setContentType("text/html");
            res.setCharacterEncoding("UTF-8");

            // HTML을 출력할 PrintWriter 객체 생성
            PrintWriter out = res.getWriter();

            // HTML 문서 작성
            String htmlStr = "";

            htmlStr += "<!DOCTYPE html><html><head><title>회원정보</title></head>";
            htmlStr += "<body>";
            htmlStr += "<h1>회원정보 조회</h1>";
            htmlStr += "<form action='./update' method='post'>";
            htmlStr += "번호: <input type='text' name='mNo' value='" + mNo
                    + "' readonly='readonly'><br>";
            htmlStr += "이름: <input type='text' name='mname' value='" + mName
                    + "'><br>";
            htmlStr += "이메일: <input type='text' name='email' value='" + email
                    + "'><br>";
            htmlStr += "가입일: " + creDate + "<br>";
            htmlStr += "<input type='submit' name=\"action\" value='저장'>";
            htmlStr += "<input type='button' value='취소' onclick='location.href=\"./list\"'>";
            htmlStr += "<input type='submit' name=\"action\" value='삭제'>";
            htmlStr += "</form>";
            htmlStr += "</body>";
            htmlStr += "</html>";

            // HTML 출력
            out.println(htmlStr);

            System.out.println("수정 잘 되나?");

        } catch (ClassNotFoundException e) {
            // JDBC 드라이버 클래스를 찾지 못했을 때 예외 처리
            e.printStackTrace();
        } catch (SQLException e) {
            // SQL 실행 중 오류 발생 시 예외 처리
            e.printStackTrace();
        } finally {
            // 자원 해제 (ResultSet, PreparedStatement, Connection)
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } // finally
    }

    // POST 요청을 처리하여 회원 정보를 수정
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        System.out.println("회원 정보 수정 post");

        // 요청 데이터 한글 깨짐 방지를 위한 인코딩 설정
        req.setCharacterEncoding("UTF-8");

        // 데이터베이스 연결을 위한 변수 선언
        Connection conn = null;
        PreparedStatement pstmt = null;

        // web.xml에서 설정한 DB 연결 정보를 가져옴
        ServletContext sc = this.getServletContext();
        // web.xml에서 설정한 DB 연결 정보를 가져옴
        String driver = sc.getInitParameter("driver");
        String url = sc.getInitParameter("url");
        String user = sc.getInitParameter("user");
        String password = sc.getInitParameter("password");

        // 클라이언트로부터 받은 수정할 회원 정보 가져오기
        String email = req.getParameter("email");
        String name = req.getParameter("mname");
        String mNo = req.getParameter("mNo");
        String action = req.getParameter("action");
        
        // SQL 문 선언
        String sql = "";
        System.out.println("액션값" + action);
        try {
            // 1. JDBC 드라이버 로드
            Class.forName(driver);
            // 2. 데이터베이스 연결
            conn = DriverManager.getConnection(url, user, password);
            if ("저장".equals(action)) {
            	 // 3. 회원 정보 수정 SQL문 작성
                sql = "UPDATE PM17 SET EMAIL=?, MNAME=? WHERE MNO=?";

                // 4. PreparedStatement 객체 생성 및 SQL 실행 준비
                pstmt = conn.prepareStatement(sql);
              
                pstmt.setString(1, email);
                pstmt.setString(2, name);
                pstmt.setString(3, mNo);  // MNO는 WHERE 절의 조건


                // 5. SQL 실행 (업데이트 수행)
                pstmt.executeUpdate();

                // 6. 회원 목록 페이지로 리디렉션
                res.sendRedirect("./list");
			}
            if ("삭제".equals(action)) {
            	 // 3. 회원 정보 수정 SQL문 작성
                sql = "DELETE FROM PM17 WHERE MNO=?";

                // 4. PreparedStatement 객체 생성 및 SQL 실행 준비
                pstmt = conn.prepareStatement(sql);
              
                pstmt.setString(1, mNo);
                	
                	
                // 5. SQL 실행 (업데이트 수행)
                pstmt.executeUpdate();
                res.sendRedirect("./list");
			}
            
           
            
            

            

        } catch (ClassNotFoundException e) {
            // JDBC 드라이버 클래스를 찾지 못했을 때 예외 처리
            e.printStackTrace();
        } catch (SQLException e) {
            // SQL 실행 중 오류 발생 시 예외 처리
            e.printStackTrace();
        } finally {
            // 자원 해제 (PreparedStatement, Connection)
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } // finally
    }

}
