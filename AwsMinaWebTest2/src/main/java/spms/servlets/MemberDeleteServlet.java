package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// @WebServlet 어노테이션을 사용하여 '/member/delete' 경로로 매핑
@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException {

        // 데이터베이스 연결을 위한 변수 선언
        Connection conn = null;
        PreparedStatement pstmt = null;

        // DB 설정 정보 가져오기
        ServletContext sc = this.getServletContext();
        String driver = sc.getInitParameter("driver");
        String url = sc.getInitParameter("url");
        String user = sc.getInitParameter("user");
        String password = sc.getInitParameter("password");

        // 삭제할 회원의 ID 가져오기
        String mNo = req.getParameter("mNo");

        try {
            // 1. JDBC 드라이버 로드
            Class.forName(driver);
            // 2. 데이터베이스 연결
            conn = DriverManager.getConnection(url, user, password);
            // 3. 삭제 SQL문 작성
            String sql = "DELETE FROM PM17 WHERE MNO = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, mNo);

            // 4. SQL 실행 (삭제 수행)
            pstmt.executeUpdate();

            // 5. 삭제 후 회원 목록으로 리디렉션
            res.sendRedirect("./list");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // 자원 해제
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
        }
    }
}
