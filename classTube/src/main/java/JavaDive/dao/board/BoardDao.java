package JavaDive.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import JavaDive.dto.board.BoardDto;

public class BoardDao {
	
	private Connection connection; //연결준비 //
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public void boardInsert(BoardDto boardDto) throws Exception {
		PreparedStatement pstmt = null; // 객체준비 //
	
		try {
		String title = boardDto.getTitle();
		String noteWriter = boardDto.getWriter();
		String categoryNo = boardDto.getCategoryNo();
		String content = boardDto.getContent();
		String category = boardDto.getCategory();
		String sql = "";
		sql = "INSERT INTO NOTE (NOTE_NO, NOTE_TITLE, NOTE_WRITER, CREATE_AT, MODIFY_AT, CONTENT, CATEGORY, CATEGORY_NO)\r\n"
				+ "VALUES (NOTE_SEQ.NEXTVAL, ?, ?, SYSDATE, SYSDATE, ?, ?, ?)"; 
		
		pstmt = connection.prepareStatement(sql);
		
		pstmt.setString(1, title);
		pstmt.setString(2, noteWriter);
		pstmt.setString(3, content);
		pstmt.setString(4, category);
		pstmt.setString(5, categoryNo);
		
		pstmt.executeUpdate();
		connection.commit();
		
		
		} catch (Exception e) {
		
			e.printStackTrace();
		}finally {
            // 자원 해제 (PreparedStatement가 열려 있다면 닫기)
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

	}
	
}
