package JavaDive.dao.odclass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import JavaDive.dto.board.BoardDto;
import JavaDive.dto.member.MemberDto;
import JavaDive.dto.odclass.ODClassDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ODClassDao {

	private Connection connection;

	public void setConnection(Connection conn) {
		this.connection = conn;
	}
	
	public List<ODClassDto> selectClassListForMain() throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<ODClassDto> odClassList = new ArrayList<ODClassDto>();

		String sql = "";

		sql += "SELECT * FROM ( ";
		sql += "    SELECT CLASS_NO, CLASS_NAME, PRICE, CLASS_DESC, INSTRUCTOR, ";
		sql += "           CREATE_AT, VIEWS, CLASS_LIMIT, IMG, REGION, CATEGORY_NO, ";
		sql += "           ROW_NUMBER() OVER (ORDER BY CLASS_NO DESC) AS RNUM ";
		sql += "    FROM ODCLASS ";
		sql += ") WHERE RNUM BETWEEN ? AND ?";
		
		try {
			pstmt = connection.prepareStatement(sql);
			int paramIndex = 1;
			
			pstmt.setInt(paramIndex++, 1);
			pstmt.setInt(paramIndex, 12);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				ODClassDto odClassDto = new ODClassDto();
				odClassDto.setClassNo(rs.getInt("CLASS_NO"));
				odClassDto.setClassName(rs.getString("CLASS_NAME"));
				odClassDto.setPrice(rs.getInt("PRICE"));
				odClassDto.setClassDesc(rs.getString("CLASS_DESC"));
				odClassDto.setInstructor(rs.getString("INSTRUCTOR"));
				odClassDto.setCreateAt(rs.getDate("CREATE_AT"));
				odClassDto.setViews(rs.getInt("VIEWS"));
				odClassDto.setClassLimit(rs.getInt("CLASS_LIMIT"));
				odClassDto.setImg(rs.getString("IMG"));
				odClassDto.setRegion(rs.getString("REGION"));
				odClassDto.setCategoryNo(rs.getInt("CATEGORY_NO"));

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
	
	public ArrayList<ODClassDto> selectClassList(Integer categoryNo, String keyword, int currentPage, int pageSize) {
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    ArrayList<ODClassDto> odClassList = new ArrayList<ODClassDto>();
	    
	    String sql = "";

	    try {
	        if (categoryNo == null && (keyword == null || keyword.trim().isEmpty())) {
	            // 기본 리스트 출력
	            sql = "SELECT * FROM (SELECT CLASS_NO, CLASS_NAME, PRICE, CLASS_DESC, INSTRUCTOR, "
	                + "CREATE_AT, VIEWS, CLASS_LIMIT, IMG, REGION, CATEGORY_NO, "
	                + "ROW_NUMBER() OVER (ORDER BY CLASS_NO DESC) AS RNUM "
	                + "FROM ODCLASS) WHERE RNUM BETWEEN ? AND ?";
	            
	            pstmt = connection.prepareStatement(sql);
	            pstmt.setInt(1, (currentPage - 1) * pageSize + 1);
	            pstmt.setInt(2, currentPage * pageSize);
	        } else if (categoryNo != null && (keyword == null || keyword.trim().isEmpty())) {
	            // 카테고리만 선택했을 때
	            sql = "SELECT * FROM (SELECT CLASS_NO, CLASS_NAME, PRICE, CLASS_DESC, INSTRUCTOR, "
	                + "CREATE_AT, VIEWS, CLASS_LIMIT, IMG, REGION, CATEGORY_NO, "
	                + "ROW_NUMBER() OVER (ORDER BY CLASS_NO DESC) AS RNUM "
	                + "FROM ODCLASS WHERE CATEGORY_NO = ?) WHERE RNUM BETWEEN ? AND ?";
	            
	            pstmt = connection.prepareStatement(sql);
	            pstmt.setInt(1, categoryNo);
	            pstmt.setInt(2, (currentPage - 1) * pageSize + 1);
	            pstmt.setInt(3, currentPage * pageSize);
	        } else if (categoryNo == null && keyword != null) {
	            // 검색만 했을 때 (keyword를 사용)
	            sql = "SELECT * FROM (SELECT CLASS_NO, CLASS_NAME, PRICE, CLASS_DESC, INSTRUCTOR, "
	                + "CREATE_AT, VIEWS, CLASS_LIMIT, IMG, REGION, CATEGORY_NO, "
	                + "ROW_NUMBER() OVER (ORDER BY CLASS_NO DESC) AS RNUM "
	                + "FROM ODCLASS WHERE LOWER(CLASS_NAME) LIKE LOWER(?)) WHERE RNUM BETWEEN ? AND ?";
	            
	            pstmt = connection.prepareStatement(sql);
	            pstmt.setString(1, "%" + keyword.trim() + "%");
	            pstmt.setInt(2, (currentPage - 1) * pageSize + 1);
	            pstmt.setInt(3, currentPage * pageSize);
	        }

	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            ODClassDto odClassDto = new ODClassDto();
	            odClassDto.setClassNo(rs.getInt("CLASS_NO"));
	            odClassDto.setClassName(rs.getString("CLASS_NAME"));
	            odClassDto.setPrice(rs.getInt("PRICE"));
	            odClassDto.setClassDesc(rs.getString("CLASS_DESC"));
	            odClassDto.setInstructor(rs.getString("INSTRUCTOR"));
	            odClassDto.setCreateAt(rs.getDate("CREATE_AT"));
	            odClassDto.setViews(rs.getInt("VIEWS"));
	            odClassDto.setClassLimit(rs.getInt("CLASS_LIMIT"));
	            odClassDto.setImg(rs.getString("IMG"));
	            odClassDto.setRegion(rs.getString("REGION"));
	            odClassDto.setCategoryNo(rs.getInt("CATEGORY_NO"));

	            odClassList.add(odClassDto);
	        }

	        return odClassList;
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
	    return odClassList;
	}
	
	public List<ODClassDto> selectClassList(String keyword, int page, int pageSize) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<ODClassDto> odClassList = new ArrayList<ODClassDto>();

		String sql = "";

		sql += "SELECT * FROM ( ";
		sql += "    SELECT CLASS_NO, CLASS_NAME, PRICE, CLASS_DESC, INSTRUCTOR, ";
		sql += "           CREATE_AT, VIEWS, CLASS_LIMIT, IMG, REGION, CATEGORY_NO, ";
		sql += "           ROW_NUMBER() OVER (ORDER BY CLASS_NO DESC) AS RNUM ";
		sql += "    FROM ODCLASS ";

		if (keyword != null && !keyword.trim().isEmpty()) {
		    sql += "    WHERE LOWER(CLASS_NAME) LIKE LOWER(?) ";
		}

		sql += ") WHERE RNUM BETWEEN ? AND ?";
		
		try {
			pstmt = connection.prepareStatement(sql);
			int paramIndex = 1;

			if (keyword != null && !keyword.trim().isEmpty()) {
				pstmt.setString(paramIndex++, "%" + keyword.trim() + "%");
			}
			pstmt.setInt(paramIndex++, (page - 1) * pageSize + 1);
			pstmt.setInt(paramIndex, page * pageSize);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				ODClassDto odClassDto = new ODClassDto();
				odClassDto.setClassNo(rs.getInt("CLASS_NO"));
				odClassDto.setClassName(rs.getString("CLASS_NAME"));
				odClassDto.setPrice(rs.getInt("PRICE"));
				odClassDto.setClassDesc(rs.getString("CLASS_DESC"));
				odClassDto.setInstructor(rs.getString("INSTRUCTOR"));
				odClassDto.setCreateAt(rs.getDate("CREATE_AT"));
				odClassDto.setViews(rs.getInt("VIEWS"));
				odClassDto.setClassLimit(rs.getInt("CLASS_LIMIT"));
				odClassDto.setImg(rs.getString("IMG"));
				odClassDto.setRegion(rs.getString("REGION"));
				odClassDto.setCategoryNo(rs.getInt("CATEGORY_NO"));

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
	
	public List<ODClassDto> selectClassList(int page, int pageSize) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<ODClassDto> odClassList = new ArrayList<ODClassDto>();

		String sql = "";

		sql += "SELECT * FROM ( ";
		sql += "    SELECT CLASS_NO, CLASS_NAME, PRICE, CLASS_DESC, INSTRUCTOR, ";
		sql += "           CREATE_AT, VIEWS, CLASS_LIMIT, IMG, REGION, CATEGORY_NO, ";
		sql += "           ROW_NUMBER() OVER (ORDER BY CLASS_NO DESC) AS RNUM ";
		sql += "    FROM ODCLASS ";
		sql += ") WHERE RNUM BETWEEN ? AND ?";
		
		try {
			pstmt = connection.prepareStatement(sql);
			int paramIndex = 1;

			pstmt.setInt(paramIndex++, (page - 1) * pageSize + 1);
			pstmt.setInt(paramIndex, page * pageSize);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				ODClassDto odClassDto = new ODClassDto();
				odClassDto.setClassNo(rs.getInt("CLASS_NO"));
				odClassDto.setClassName(rs.getString("CLASS_NAME"));
				odClassDto.setPrice(rs.getInt("PRICE"));
				odClassDto.setClassDesc(rs.getString("CLASS_DESC"));
				odClassDto.setInstructor(rs.getString("INSTRUCTOR"));
				odClassDto.setCreateAt(rs.getDate("CREATE_AT"));
				odClassDto.setViews(rs.getInt("VIEWS"));
				odClassDto.setClassLimit(rs.getInt("CLASS_LIMIT"));
				odClassDto.setImg(rs.getString("IMG"));
				odClassDto.setRegion(rs.getString("REGION"));
				odClassDto.setCategoryNo(rs.getInt("CATEGORY_NO"));

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
			String classDesc = odClassDto.getClassDesc();
			String instructor = odClassDto.getInstructor();
			int limit = odClassDto.getClassLimit();
			String img = odClassDto.getImg();
			String region = odClassDto.getRegion();
			int categoryNo = odClassDto.getCategoryNo();
			
			String sql = "";
			sql += "INSERT INTO ODCLASS";
			sql += " (CLASS_NO, CLASS_NAME, PRICE, CLASS_DESC, INSTRUCTOR, CREATE_AT, VIEWS, CLASS_LIMIT, IMG, REGION, CATEGORY_NO)";
			sql += " VALUES(SEQ_CLASS_NO.NEXTVAL, ?, ?, ?, ?, SYSDATE, 0, ?, ?, ?, ?)";
			
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, className);
			pstmt.setInt(2, price);
			pstmt.setString(3, classDesc);
			pstmt.setString(4, instructor);
			pstmt.setInt(5, limit);
			pstmt.setString(6, img);
			pstmt.setString(7, region);
			pstmt.setInt(8, categoryNo);
			
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

		sql = "SELECT CLASS_NO, CLASS_NAME, PRICE, CLASS_DESC, INSTRUCTOR, CREATE_AT, VIEWS, CLASS_LIMIT, IMG, REGION, CATEGORY_NO";
		sql += " FROM ODCLASS";
		sql += " WHERE CLASS_NO=?";
		
		try {
			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, classNo);
			
			rs = pstmt.executeQuery();
			
			
			if (rs.next()) {
				odClassDto = new ODClassDto();
				
				odClassDto.setClassNo(rs.getInt("CLASS_NO"));
				odClassDto.setClassName(rs.getString("CLASS_NAME"));
				odClassDto.setPrice(rs.getInt("PRICE"));
				odClassDto.setClassDesc(rs.getString("CLASS_DESC"));
				odClassDto.setInstructor(rs.getString("INSTRUCTOR"));
				odClassDto.setCreateAt(rs.getDate("CREATE_AT"));
				odClassDto.setViews(rs.getInt("VIEWS"));
				odClassDto.setClassLimit(rs.getInt("CLASS_LIMIT"));
				odClassDto.setImg(rs.getString("IMG"));
				odClassDto.setRegion(rs.getString("REGION"));
				odClassDto.setCategoryNo(rs.getInt("CATEGORY_NO"));
				
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
		sql += " SET CLASS_NAME=?, PRICE=?, CLASS_DESC=?, INSTRUCTOR=?, CLASS_LIMIT=?, IMG=?, REGION=?";
		sql += " WHERE CLASS_NO=?";
		
		try {
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, odClassDto.getClassName());
			pstmt.setInt(2, odClassDto.getPrice());
			pstmt.setString(3, odClassDto.getClassDesc());
			pstmt.setString(4, odClassDto.getInstructor());
			pstmt.setInt(5, odClassDto.getClassLimit());
			pstmt.setString(6, odClassDto.getImg());
			pstmt.setString(7, odClassDto.getRegion());
			pstmt.setInt(8, odClassDto.getClassNo());
			
			

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

	public int classDelete(int classId) {
		int result = 0;
		PreparedStatement pstmt = null;

		try {
			String sql = "DELETE FROM ODCLASS";
			sql += " WHERE CLASS_NO=?";

			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, classId);

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
				// TODO: handle exception
				e.printStackTrace();
			}

		}

		return result;
	}
	
	public int selectTotalCount() throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalCount = 0;

		String sql = "SELECT COUNT(CLASS_NO) AS TOTAL FROM ODCLASS";

		try {
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				totalCount = rs.getInt("TOTAL");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		}

		return totalCount;
	}
	
	//전체반환되는 클래스의 카운트 
		public int getTotalClassCount(String keyword, Integer categoryNo) throws Exception {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int totalRecords = 0;

			String sql = "SELECT COUNT(*) FROM ODCLASS";

			// 🔹 검색어가 있을 경우 WHERE 절 추가
			if ((keyword != null && !keyword.trim().isEmpty()) && categoryNo == null) {
				sql += " WHERE LOWER(CLASS_NAME) LIKE LOWER(?) ";
			} else if ((keyword == null || keyword.trim().isEmpty()) && categoryNo != null) {
				sql += " WHERE CATEGORY_NO=?";
			} else if ((keyword != null && !keyword.trim().isEmpty()) && categoryNo != null) {
				sql += " WHERE LOWER(CLASS_NAME) LIKE LOWER(?) AND CATEGORY_NO=?";
			}

			try {
				pstmt = connection.prepareStatement(sql);
				if ((keyword != null && !keyword.trim().isEmpty()) && categoryNo == null) {
					pstmt.setString(1, "%" + keyword.trim() + "%");
				} else if ((keyword == null || keyword.trim().isEmpty()) && categoryNo != null) {
					pstmt.setInt(1, categoryNo);
				}else if ((keyword != null && !keyword.trim().isEmpty()) && categoryNo != null) {
					pstmt.setString(1, "%" + keyword.trim() + "%");
					pstmt.setInt(2, categoryNo);
				}
				
				rs = pstmt.executeQuery();
				if (rs.next()) {
					totalRecords = rs.getInt(1);
				}
			} finally {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			}
			return totalRecords;
		}

		public List<ODClassDto> searchClass(String keyword, int currentPage, int pageSize) {
			// TODO Auto-generated method stub
			PreparedStatement pstmt = null; // 쿼리실행준비
			ResultSet rs = null; // sql 실행결과 객체담을 그릇 준비

			ArrayList<ODClassDto> odClassList = new ArrayList<ODClassDto>(); //
			
			String sql = "";

			try {
				sql += "SELECT * FROM ( ";
				sql += "    SELECT CLASS_NO, CLASS_NAME, PRICE, CLASS_DESC, INSTRUCTOR, ";
				sql += "           CREATE_AT, VIEWS, CLASS_LIMIT, IMG, REGION, CATEGORY_NO, ";
				sql += "           ROW_NUMBER() OVER (ORDER BY CLASS_NO DESC) AS RNUM ";
				sql += "    FROM ODCLASS ";
				sql += "    WHERE LOWER(CLASS_NAME) LIKE LOWER(?) ";
				sql += ") WHERE RNUM BETWEEN ? AND ?";
				
				pstmt = connection.prepareStatement(sql);

				if (keyword == null || keyword.trim().isEmpty()) {

					keyword = "%"; // 전체 검색을 위한 기본값

				} else {

					keyword = "%" + keyword.trim() + "%"; // 앞뒤에 % 추가

				}
				pstmt.setString(1, keyword);
				int startRow = (currentPage - 1) * pageSize + 1;
				int endRow = currentPage * pageSize;

				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					ODClassDto odClassDto = new ODClassDto();
					odClassDto.setClassNo(rs.getInt("CLASS_NO"));
					odClassDto.setClassName(rs.getString("CLASS_NAME"));
					odClassDto.setPrice(rs.getInt("PRICE"));
					odClassDto.setClassDesc(rs.getString("CLASS_DESC"));
					odClassDto.setInstructor(rs.getString("INSTRUCTOR"));
					odClassDto.setCreateAt(rs.getDate("CREATE_AT"));
					odClassDto.setViews(rs.getInt("VIEWS"));
					odClassDto.setClassLimit(rs.getInt("CLASS_LIMIT"));
					odClassDto.setImg(rs.getString("IMG"));
					odClassDto.setRegion(rs.getString("REGION"));
					odClassDto.setCategoryNo(rs.getInt("CATEGORY_NO"));

					odClassList.add(odClassDto);
					// 리스트에 담았음 //
					// 컨트롤러에서 수신예정//

				}

				return odClassList;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// 자원 해제 (PreparedStatement가 열려 있다면 닫기)
				try {
					if (pstmt != null) {
						pstmt.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}				
			}
			return odClassList;
		}

		
		
}