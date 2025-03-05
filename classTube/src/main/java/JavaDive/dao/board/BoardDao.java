package JavaDive.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import JavaDive.dto.board.BoardDto;
import jakarta.servlet.ServletContext;

public class BoardDao {

	private Connection connection; // 연결준비 //

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
			// 쿼리 업데이트 //

			pstmt.executeUpdate();
			String generetedNo = "SELECT NOTE_NO " + "FROM (SELECT NOTE_NO FROM NOTE ORDER BY NOTE_NO DESC) "
					+ "WHERE ROWNUM = 1";
			
			PreparedStatement pstmt2 = null;
			try {
				pstmt2 = connection.prepareStatement(generetedNo);
				rs = pstmt2.executeQuery();
				if (rs.next()) {
					generatedNoteNo = rs.getInt(1);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// ⭐ 리소스 해제 필수 ⭐
				try {
					if (rs != null) {
						rs.close();
					}
					if (pstmt2 != null) {
						pstmt2.close();
					}
						
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			return generatedNoteNo;

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			// 자원 해제 (PreparedStatement가 열려 있다면 닫기)
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return generatedNoteNo;
	}
	
	
	public BoardDto getBoardById(int postId) {
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    BoardDto boardDto = null;

	    String sql = "SELECT * FROM NOTE WHERE NOTE_NO = ?"; 
	    
	    try {
	        pstmt = connection.prepareStatement(sql);
	        pstmt.setInt(1, postId);
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            boardDto = new BoardDto();
	            boardDto.setNoteNo(rs.getInt("NOTE_NO"));
	            boardDto.setTitle(rs.getString("NOTE_TITLE"));
	            boardDto.setWriter(rs.getString("NOTE_WRITER"));
	            boardDto.setContent(rs.getString("CONTENT"));
	            boardDto.setCategory(rs.getString("CATEGORY"));
	            boardDto.setCategoryNo(rs.getInt("CATEGORY_NO"));
	            boardDto.setCreateDate(rs.getDate("createDate"));


	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return boardDto; // 게시글 정보를 담은 DTO 반환
	}

	
	

}
