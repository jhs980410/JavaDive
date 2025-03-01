package JavaDive; // 추가 (패키지명 확인 필요)

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionTest {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; // DB 주소 확인
        String user = "edu"; // Oracle 계정
        String password = "edu12"; // Oracle 비밀번호

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                System.out.println("✅ Oracle DB 연결 성공!");
            } else {
                System.out.println("❌ Oracle DB 연결 실패!");
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
