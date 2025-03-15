package JavaDive.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import JavaDive.dto.board.BoardCommentDto;

public class BoardCommentDao {

	private Connection conn;

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public void insertComment(int postId, int memberNo, String content) throws SQLException {
		String sql = "INSERT INTO NOTE_COMMENT (COMMENT_NO, NOTE_NO, MEMBER_NO, COMMENT_CONTENT, CREATE_AT, MODIFY_AT) "
				+ "VALUES (COMMENT_SEQ.NEXTVAL, ?, ?, ?, SYSDATE, SYSDATE)";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, postId);
			pstmt.setInt(2, memberNo);
			pstmt.setString(3, content);
			pstmt.executeUpdate();
		}
	}

	// 게시글내 댓글 전체조회 로직
	public List<BoardCommentDto> getCommentByPostId(int postId) {
		List<BoardCommentDto> commentList = new ArrayList<>();
		 String sql = "SELECT nc.COMMENT_NO, nc.MEMBER_NO, m.MEMBER_NAME, nc.COMMENT_CONTENT, nc.CREATE_AT "
		           + "FROM NOTE_COMMENT nc "
		           + "JOIN MEMBER m ON nc.MEMBER_NO = m.MEMBER_NO "
		           + "WHERE nc.NOTE_NO = ? "
		           + "ORDER BY nc.CREATE_AT ASC";
;
		
		 try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
		        pstmt.setInt(1, postId);
		        ResultSet rs = pstmt.executeQuery();

		        while (rs.next()) {
		        	BoardCommentDto comment = new BoardCommentDto();
		        	comment.setCommentNo(rs.getInt("COMMENT_NO"));
		        	comment.setCommentWriter(rs.getString("MEMBER_NAME"));  // 작성자 이름 가져오기
		        	comment.setCommentContent(rs.getString("COMMENT_CONTENT"));
		        	comment.setCreateAt(rs.getTimestamp("CREATE_AT"));
		        	comment.setMemberNo(rs.getInt("MEMBER_NO"));

		            commentList.add(comment);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return commentList;
		}
	//게시글 업데이트
	public void updateComment(int commentId, String content) {
		String sql = "UPDATE NOTE_COMMENT " 
		           + "SET COMMENT_CONTENT = ?, " 
		           + "MODIFY_AT = SYSDATE "
		           + "WHERE COMMENT_NO = ?";


	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	    	 pstmt.setString(1, content);
	        pstmt.setInt(2, commentId);
	       

	        int rowsUpdated = pstmt.executeUpdate();
	        System.out.println("✅ 수정된 행 개수: " + rowsUpdated);
	        
	        if (rowsUpdated == 0) {
	            System.out.println("⚠️ 업데이트된 데이터가 없습니다. (postId: " + commentId + ")");
	        }

	    } catch (SQLException e) {
	        System.err.println("❌ 댓글 업데이트 중 오류 발생: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
//수정할 댓글 id가져오기 . 
	public BoardCommentDto getCommentById(int commentNo) throws Exception {
	    BoardCommentDto comment = null;  // 단일 객체 반환
	   String sql = "SELECT nc.*, m.MEMBER_NAME  AS COMMENT_WRITER " + 
                "FROM NOTE_COMMENT nc " +
                "JOIN MEMBER m ON nc.MEMBER_NO = m.MEMBER_NO " +
                "WHERE nc.COMMENT_NO = ?";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, commentNo);  // ✅ 여기서는 commentNo로 변경!
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {  // ✅ 단일 결과만 처리
	                comment = new BoardCommentDto();
	                comment.setCommentNo(rs.getInt("COMMENT_NO"));
	                comment.setMemberNo(rs.getInt("MEMBER_NO"));
	                comment.setCommentContent(rs.getString("COMMENT_CONTENT"));
	                comment.setCommentWriter(rs.getString("COMMENT_WRITER"));
	                comment.setCreateAt(rs.getTimestamp("CREATE_AT"));
	                comment.setModifyAt(rs.getTimestamp("MODIFY_AT"));
	                comment.setNoteNo(rs.getInt("NOTE_NO"));
	            }
	        }
	    }
	    return comment;  // ✅ 단일 댓글 객체 반환
	}

	
}