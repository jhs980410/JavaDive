package JavaDive.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import JavaDive.dto.board.BoardDto;
import jakarta.servlet.ServletContext;

public class BoardDao {
	
	private Connection connection; //연결준비 //
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public int boardInsert(BoardDto boardDto) throws Exception {
		PreparedStatement pstmt = null; // 객체준비 //
		ResultSet rs = null;
		int generatedNoteNo = 0;
		String sql = "";
		try {	
		String title = boardDto.getTitle();
		String noteWriter = boardDto.getWriter();
		int categoryNo = boardDto.getCategoryNo();
		String content = boardDto.getContent();
		String category = boardDto.getCategory();
		
		sql = "INSERT INTO NOTE (NOTE_NO, NOTE_TITLE, NOTE_WRITER, CREATE_AT, MODIFY_AT, CONTENT, CATEGORY, CATEGORY_NO)\r\n"
				+ "VALUES (NOTE_SEQ.NEXTVAL, ?, ?, SYSDATE, SYSDATE, ?, ?, ?)"; 
		
		pstmt = connection.prepareStatement(sql);
		
		pstmt.setString(1, title);
		pstmt.setString(2, noteWriter);
		pstmt.setString(3, content);
		pstmt.setString(4, category);
		pstmt.setInt(5, categoryNo);
		pstmt.executeUpdate();
		
		System.out.println(title);
		System.out.println(noteWriter);
		System.out.println(content);
		System.out.println(category);
		System.out.println(categoryNo);
		sql = "SELECT NOTE_NO FROM (SELECT NOTE_NO FROM NOTE ORDER BY NOTE_NO DESC) WHERE ROWNUM = 1";
		
		pstmt = connection.prepareStatement(sql);
		rs = pstmt.executeQuery();  // ⭐ 쿼리 실행 후 ResultSet에 저장 ⭐

		if (rs.next()) {
			generatedNoteNo = rs.getInt("NOTE_NO"); // 가장 최신 게시글의 NOTE_NO 저장
		}
		
		return generatedNoteNo;
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
		return generatedNoteNo;

	}
	
}
