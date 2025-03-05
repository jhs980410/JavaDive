package JavaDive.dao.member;
import JavaDive.dto.member.MemberDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import spms.dto.MemberDto;

public class MemberDao {

	private Connection connection;

	public void setConnection(Connection conn) {
		this.connection = conn;
	}

	
	public int memberInsert(MemberDto memberDto) throws Exception{
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			String emailStr = memberDto.getEmail();
			String pwdStr = memberDto.getPwd();
			String nameStr = memberDto.getName();
			String rrnStr = memberDto.getRrn();
			String telStr = memberDto.getTel();
			
			String sql = "";
			sql += "INSERT INTO MEMBER";
			sql += " VALUE(MEMBER_NO, MEMBER_EMAIL, MEMBER_PWD, MEMBER_NAME, RRN, TEL, CREATE_AT)";
			sql += " VALUES(MEMBER_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, SYSDATE)";
			
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, emailStr);
			pstmt.setString(2, emailStr);
			pstmt.setString(3, emailStr);
			pstmt.setString(4, emailStr);
			pstmt.setString(5, emailStr);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
