package JavaDive.listener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class DBConnectionListener implements ServletContextListener {
    private Connection conn;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Class.forName("oracle.jdbc.OracleDriver"); // JDBC 드라이버 로드
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ctube", "ctube123");

            ServletContext sc = sce.getServletContext();
            sc.setAttribute("conn", conn); // DB 연결을 ServletContext에 저장
            System.out.println("DB 연결 성공!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("DB 연결 종료!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
