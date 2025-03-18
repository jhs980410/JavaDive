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

	// 회원 등록
    public int memberInsert(MemberDto memberDto) throws Exception {
        int result = 0;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String emailStr = memberDto.getEmail();
            String pwdStr = memberDto.getPwd();
            String nameStr = memberDto.getName();
            String rrnStr = memberDto.getRrn();
            String telStr = memberDto.getTel();
            
            // 이메일 중복체크
            if (isEmailDuplicate(emailStr)) {
				return 0; //중복되면 0값을 반환하여 회원가입 차단
			}

            // 2. 회원 정보 삽입
            String sql = "INSERT INTO MEMBER (MEMBER_NO, MEMBER_EMAIL, MEMBER_PWD, MEMBER_NAME, RRN, TEL, CREATE_AT) ";
            sql += "VALUES (MEMBER_SEQ.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE)";

            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, emailStr);
            pstmt.setString(2, pwdStr);
            pstmt.setString(3, nameStr);
            pstmt.setString(4, rrnStr);
            pstmt.setString(5, telStr);

            result = pstmt.executeUpdate();
            

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    // 이메일 중복 검사 메서드
 	public boolean isEmailDuplicate(String email) throws SQLException {
 		String sql = "SELECT COUNT(*) FROM MEMBER WHERE MEMBER_EMAIL = ?";
 		
 		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
 			pstmt.setString(1, email);
 			try (ResultSet rs = pstmt.executeQuery()) {
 				if (rs.next()) {
 					return rs.getInt(1) > 0; // 중복이면 true 반환
 				}
 			} 
 		}
 		return false; 
 	}
 	
 // 입력된 이메일이 존재하는지 확인하는 메서드(가입되지 않은 이메일 확인)
 	public MemberDto getMemberByEmail(String email) throws Exception {
 	    MemberDto memberDto = null;
 	    PreparedStatement pstmt = null;
 	    ResultSet rs = null;

 	    try {
 	        String sql = "SELECT * FROM MEMBER WHERE MEMBER_EMAIL = ?";
 	        pstmt = connection.prepareStatement(sql);
 	        pstmt.setString(1, email);
 	        rs = pstmt.executeQuery();

 	        if (rs.next()) {
 	            memberDto = new MemberDto();
 	            memberDto.setNo(rs.getInt("MEMBER_NO"));
 	            memberDto.setEmail(rs.getString("MEMBER_EMAIL"));
 	            memberDto.setPwd(rs.getString("MEMBER_PWD")); // 비밀번호 비교를 위해 가져옴
 	            memberDto.setName(rs.getString("MEMBER_NAME"));
 	            memberDto.setTel(rs.getString("TEL"));
 	            memberDto.setRrn(rs.getString("RRN"));
 	            memberDto.setPriv(rs.getString("MEMBER_PRIV"));
 	        }
 	    } finally {
 	        if (rs != null) rs.close();
 	        if (pstmt != null) pstmt.close();
 	    }
 	    return memberDto;
 	}

	//회원목록
 	public List<MemberDto> selectList() throws Exception {

 	    PreparedStatement pstmt = null;
 	    ResultSet rs = null;
 	    
 	    ArrayList<MemberDto> memberList = new ArrayList<>();

 	    String sql = "SELECT MEMBER_NO, MEMBER_EMAIL, MEMBER_NAME, TEL, CREATE_AT, MEMBER_PRIV " +
 	                 " FROM MEMBER ORDER BY MEMBER_NO ASC";
 	    
 	    try {
 	        if (connection == null) { // connection이 null인지 확인
 	            return memberList; // 빈 리스트 반환
 	        }

 	        pstmt = connection.prepareStatement(sql);

 	        rs = pstmt.executeQuery();

 	        while (rs.next()) {

 	            MemberDto memberDto = new MemberDto(
 	                rs.getInt("MEMBER_NO"),
 	                rs.getString("MEMBER_EMAIL"),
 	                rs.getString("MEMBER_NAME"),
 	                rs.getString("TEL"),
 	                rs.getString("MEMBER_PRIV"),
 	                rs.getDate("CREATE_AT")
 	                
 	            );
 	            memberList.add(memberDto);
 	        }

 	    } catch (Exception e) {
 	        e.printStackTrace();
 	    } finally {
 	        try {
 	            if (rs != null) rs.close();
 	            if (pstmt != null) pstmt.close();
 	        } catch (Exception e) {
 	            e.printStackTrace();
 	        }
 	    }

 	    return memberList;
 	}

	
	//회원삭제
	public int memberDelete(int no) throws SQLException {
		int result = 0;
		
		String sql = "";
		sql += "DELETE FROM MEMBER";
		sql += " WHERE MEMBER_NO = ?";
		
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
		return result;
	}
	
	// 회원 상세 정보 조회
	public MemberDto memberSelectOne(int no) throws Exception {
		
		MemberDto memberDto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		
		sql += "SELECT MEMBER_NO, MEMBER_EMAIL, MEMBER_NAME, TEL, CREATE_AT, MEMBER_PRIV";
		sql += " FROM MEMBER";
		sql += " WHERE MEMBER_NO =?";
		
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			String member_email = "";
			String member_name = "";
			String tel = "";
			Date create_at = null;
			
			if (rs.next()) {
				memberDto = new MemberDto();
				
				memberDto.setNo(rs.getInt("MEMBER_NO"));
				memberDto.setEmail(rs.getString("MEMBER_EMAIL"));
				memberDto.setName(rs.getString("MEMBER_NAME"));
				memberDto.setTel(rs.getString("TEL"));
				memberDto.setCreate_at(rs.getDate("CREATE_AT"));
				memberDto.setPriv(rs.getString("MEMBER_PRIV"));
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
	
	// 회원 정보 변경 (변경된 값만 업데이트, 기존 데이터 유지)
	public int memberUpdate(MemberDto memberDto) throws SQLException {
	    int result = 0;
	    PreparedStatement pstmt = null;
	    List<Object> params = new ArrayList<>();

	    String sql = "UPDATE MEMBER SET ";
	    boolean first = true;

	    if (memberDto.getName() != null) {
	        sql += (first ? "" : ", ") + "MEMBER_NAME = ?";
	        params.add(memberDto.getName());
	        first = false;
	    }
	    if (memberDto.getTel() != null) {
	        sql += (first ? "" : ", ") + "TEL = ?";
	        params.add(memberDto.getTel());
	        first = false;
	    }
	    if (memberDto.getPriv() != null) {
	        sql += (first ? "" : ", ") + "MEMBER_PRIV = ?";
	        params.add(memberDto.getPriv());
	        first = false;
	    }
	    if (memberDto.getPwd() != null) {
	        sql += (first ? "" : ", ") + "MEMBER_PWD = ?";
	        params.add(memberDto.getPwd());
	        first = false;
	    }

	    // 강제 업데이트: 기존 데이터를 유지하도록 기본값 추가!
	    if (params.isEmpty()) {
	        sql += "MEMBER_NO = MEMBER_NO"; // 최소한의 업데이트 실행 (트리거 발동)
	    }

	    sql += " WHERE MEMBER_NO = ?";
	    params.add(memberDto.getNo());

	    try {
	        pstmt = connection.prepareStatement(sql);
	        for (int i = 0; i < params.size(); i++) {
	            pstmt.setObject(i + 1, params.get(i));
	        }

	        result = pstmt.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        if (pstmt != null) pstmt.close();
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
