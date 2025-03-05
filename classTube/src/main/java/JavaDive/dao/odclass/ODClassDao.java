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
	
	public ODClassDto classSelectOne(int classNo) throws Exception{
		
		ODClassDto odClassDto = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";

		sql = "SELECT CLASS_NO, CLASS_NAME, PRICE, DESC, INSTRUCTOR, CREATE_AT, VIEWS, LIMIT, IMG";
		sql += " FROM ODCLASS";
		sql += " WHERE CLASS_NO =?";
		
		try {
			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, classNo);
			
			rs = pstmt.executeQuery();
			
			String className = "";
			int price = 0;
			String desc = "";
			String instructor = "";
			int limit = 0;
			String img = "";
			Date createAt = null;
			
			if (rs.next()) {
				className = rs.getString("CLASS_NAME");
				price = rs.getInt("PRICE");
				desc = rs.getString("DESC");
				instructor = rs.getString("INSTRUCTOR");
				createAt = rs.getDate("CREATE_AT");
				limit = rs.getInt("LIMIT");
				img = rs.getString("IMG");
				
				odClassDto = new ODClassDto();
				
				odClassDto.setClassNo(classNo);
				odClassDto.setClassName(className);
				odClassDto.setPrice(price);
				odClassDto.setDesc(desc);
				odClassDto.setInstructor(instructor);
				odClassDto.setImg(img);
			} else {
				throw new Exception("해당 번호의 클래스를 찾을 수 없습니다.");
			}
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} // finally 종료
		
		return odClassDto;
	}
	
	public int odClassUpdate(ODClassDto odClassDto) throws SQLException {
		int result = 0;
		
		PreparedStatement pstmt = null;

		String sql = "";
		sql = "UPDATE ODCLASS";
		sql += " SET CLASS_NAME=?, PRICE=?, DESC=?, INSTRUCTOR=?, IMG=?";
		sql += " WHERE CLASS_NO=?";
		
		try {
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, odClassDto.getClassName());
			pstmt.setInt(2, odClassDto.getPrice());
			pstmt.setString(3, odClassDto.getDesc());
			pstmt.setString(4, odClassDto.getInstructor());
			pstmt.setString(5, odClassDto.getImg());
			pstmt.setInt(6, odClassDto.getClassNo());

			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} // finally 종료
		
		return result;
	}
	
}