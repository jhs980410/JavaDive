package JavaDive.dao.odclass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import JavaDive.dto.odclass.ODClassDto;

public class ODClassDao {

	private Connection connection;

	public void setConnection(Connection conn) {
		this.connection = conn;
	}

	public List<ODClassDto> selectClassList() throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<ODClassDto> odClassList = new ArrayList<ODClassDto>();

		String sql = "";

		sql += "SELECT CLASS_NO, CLASS_NAME, PRICE, DESC, INSTRUCTOR, CREATE_AT, VIEWS, LIMIT, IMG";
		sql += " FROM ODCLASS";
		sql += " ORDER BY CLASS_NO ASC";

		try {
			pstmt = connection.prepareStatement(sql);

			rs = pstmt.executeQuery();

			int classNo = 0;
			String className = "";
			int price = 0;
			String desc = "";
			String instructor = "";
			Date create_at;
			int views = 0;
			int limit = 0;
			String img = "";

			while (rs.next()) {
				classNo = rs.getInt("CLASS_NO");
				className = rs.getString("CLASS_NAME");
				price = rs.getInt("PRICE");
				desc = rs.getString("DESC");
				instructor = rs.getString("INSTRUCTOR");
				create_at = rs.getDate("CREATE_AT");
				views = rs.getInt("VIEWS");
				limit = rs.getInt("LIMIT");
				img = rs.getString("IMG");

				ODClassDto odClassDto = 
						new ODClassDto(classNo, className, price, desc, instructor, instructor, views,limit, img);

				odClassList.add(odClassDto);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} // finally end

		return odClassList;
	}
	
	public int classInsert(ODClassDto odClassDto) throws Exception {
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			String className = odClassDto.getClassName();
			int price = odClassDto.getPrice();
			String desc = odClassDto.getDesc();
			String instructor = odClassDto.getInstructor();
			int limit = odClassDto.getClassLimit();
			String img = odClassDto.getImg();
			
			String sql = "";
			sql += "INSERT INTO ODCLASS";
			sql += " (CLASS_NO, CLASS_NAME, PRICE, DESC, INSTRUCTOR, CREATE_AT, VIEWS, LIMIT, IMG)";
			sql += " VALUES(CLASS_SEQ.NEXTVAL(), ?, ?, ?, ?, SYSDATE, 0, ?";
			
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, className);
			pstmt.setInt(2, price);
			pstmt.setString(3, desc);
			pstmt.setString(4, instructor);
			pstmt.setInt(5, limit);
			pstmt.setString(6, img);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if(pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} // finally 종료
		
		return result;
	}
	
	
	
}


//상세 정보 조회 때
//int classNo = 0;
//String className = "";
//int price = 0;
//String desc = "";
//String instructor = "";
//Date create_at;
//int views = 0;
//int limit = 0;
//
//while (rs.next()) {
//	classNo = rs.getInt("CLASS_NO");
//	className = rs.getString("CLASS_NAME");
//	price = rs.getInt("PRICE");
//	desc = rs.getString("DESC");
//	instructor = rs.getString("INSTRUCTOR");
//	create_at = rs.getDate("CREATE_AT");
//	views = rs.getInt("VIEWS");
//	limit = rs.getInt("LIMIT");