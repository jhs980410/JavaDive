package JavaDive.dao.member;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import JavaDive.dto.member.MemberDto;

public class MemberDao {

	private Connection connection;
	private ResultSet rs;

	public void setConnection(Connection conn) {
		this.connection = conn;
	}

	//회원등록
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
	
	
	
	//회원목록
	public List<MemberDto> selectList() throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<MemberDto> memberList = new ArrayList<MemberDto>();
	
		String sql = "";
	
		sql += "SELECT MEMBER_NO, MEMBER_EMAIL, MEMBER_NAME, TEL, CREATE_AT";
		sql += " FROM MEMBER";
		sql += " ORDER BY MNO ASC";
		
		try {
			pstmt = connection.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			int no = 0;
			String email = "";
			String name = "";
			String tel = "";
			Date creDate = null;
			
			while (rs.next()) {
				no = rs.getInt("MEMBER_NO");
				email = rs.getString("MEMBER_EMAIL");
				name = rs.getString("MEMBER_NAME");
				tel = rs.getString("TEL");
				creDate = rs.getDate("CREATE_AT");
				
				MemberDto memberDto = new MemberDto(no, email, name, tel, creDate);
				memberList.add(memberDto);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
			return memberList;	
	}
	
	//회원삭제
	public int memberDelete(int no) throws SQLException {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = "";
		sql += "DELETE FROM MEMBERS";
		sql += " WHERE MNO = ?";
		
		try {
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	// 회원 상세 정보 조회
	public MemberDto memberSelectOne(int no) throws Exception {
		
		MemberDto memberDto = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		
		sql += "SELECT MEMBER_NO, MEMBER_EMAIL, MEMBER_NAME, TEL, CREATE_AT";
		sql += " FROM MEMBERS";
		sql += " WHERE MNO =?";
		
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			String member_email = "";
			String member_name = "";
			String tel = "";
			Date create_at = null;
			
			if (rs.next()) {
				member_email = rs.getString("MEMBER_EMAIL");
				member_name = rs.getString("MEMBER_NAME");
				tel = rs.getString("TEL");
				create_at = rs.getDate("REATE_AT");
				
				memberDto = new MemberDto();
				
				memberDto.setNo(no);
				memberDto.setEmail(member_email);
				memberDto.setName(member_name);
				memberDto.setTel(tel);
				memberDto.setCreate_at(create_at);
			}else {
				throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
			
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
		
		return memberDto;
	}
	
	// 회원 정보 변경
	public int memberUpdate(MemberDto memberDto) throws SQLException{
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = "";
		sql += "UPDATE MEMBER";
		sql += " SET MEMBER_PWD=?, MEMBER_NAME=?, TEL=?,";
		sql += " WHERE MNO =?";
		
		try {
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, memberDto.getPwd());
			pstmt.setString(2, memberDto.getName());
			pstmt.setString(3, memberDto.getTel());
			pstmt.setInt(4, memberDto.getNo());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	// 가입정보 없으면 null 리턴
	
	public MemberDto memberExist(String member_email, String member_pwd)
		throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDto memberDto= null;
		String sql = "";
		sql += "SELECT MEMBER_NO, MEMBER_EMAIL, MEMBER_PWD, MEMBER_NAME, TEL, MEMBER_PRIV, CREATE_AT";
		sql += " FROM MEMBER";
		sql += " WHERE LOWER(MEMBER_EMAIL) = LOWER(?) AND LOWER(MEMBER_PWD) = LOWER(?)";
		
		int mNo;
		String mEmail = "";
		String mName = "";
		String mPwd = "";
		String mTel = "";
		String mPriv = "";
		Date createAt = null;
		
		try {
			pstmt = connection.prepareStatement(sql);
			

			pstmt.setString(1, member_email);
			pstmt.setString(2, member_pwd);
			System.out.println("Executing SQL: " + sql);
			rs = pstmt.executeQuery();
			
			if (rs == null) {
			    System.out.println("ResultSet이 null입니다!");
			} else {
			    System.out.println("ResultSet이 생성되었습니다.");
			}
			
			if (rs.next()) {
				memberDto = new MemberDto();
				mNo = rs.getInt("MEMBER_NO");
				mEmail = rs.getString("MEMBER_EMAIL");
				mPwd = rs.getString("MEMBER_PWD");
				mName = rs.getString("MEMBER_NAME");
				mTel = rs.getString("TEL");
				mPriv = rs.getString("MEMBER_PRIV");
				createAt = rs.getDate("CREATE_AT");
				
				memberDto.setNo(mNo);
				memberDto.setEmail(mEmail);
				memberDto.setPwd(mPwd);
				memberDto.setName(mName);
				memberDto.setTel(mTel);
				memberDto.setPriv(mPriv);
				memberDto.setCreate_at(createAt);
			
				// 회원 정보 조회 확인
				return memberDto;
			} else if(rs.next() == false) {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 회원이 조회가 안된다면
		return null;
	}
		
}
