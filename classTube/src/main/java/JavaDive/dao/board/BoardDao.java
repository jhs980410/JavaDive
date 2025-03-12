package JavaDive.dao.board;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;
import java.sql.Date;
import JavaDive.dto.board.BoardDto;
import JavaDive.dto.member.MemberDto;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class BoardDao {

	private Connection connection; // 연결준비 //

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public int boardInsert(BoardDto boardDto, HttpServletRequest req) throws Exception {
		PreparedStatement pstmt = null; // 객체준비 //
		ResultSet rs = null;
		int generatedNoteNo = 0;
		HttpSession session = req.getSession(); // 요청온곳의 세션값 담기
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		String sql = "";
		try {
			int memberNo = memberDto.getNo();
			String title = boardDto.getTitle();
			String noteWriter = memberDto.getName();
			int categoryNo = 0;
			String content = boardDto.getContent();

			sql = "INSERT INTO NOTE (NOTE_NO, NOTE_TITLE, MEMBER_NO, CREATE_AT, MODIFY_AT, NOTE_CONTENT, CATEGORY_NO)\r\n"
					+ "VALUES (SEQ_NOTE_NO.NEXTVAL, ?, ?, SYSDATE, SYSDATE, ?, ?)";

			String categoryValue = req.getParameter("category");

			if (categoryValue.equals("categoryNo1")) {
				categoryNo = 1;
			} else if (categoryValue.equals("categoryNo2")) {
				categoryNo = 2;
			} else if (categoryValue.equals("categoryNo3")) {
				categoryNo = 3;
			}

			System.out.println("카테고리 no" + categoryNo);
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, title);
			pstmt.setInt(2, memberNo);
			pstmt.setString(3, content);
			pstmt.setInt(4, categoryNo);
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

		// ✅ MEMBER 테이블 조인하여 작성자 이름(MEMBER_NAME) 가져오기
		String sql = "SELECT N.*, M.MEMBER_NAME AS WRITER " + "FROM NOTE N "
				+ "JOIN MEMBER M ON N.MEMBER_NO = M.MEMBER_NO " + "WHERE N.NOTE_NO = ?";

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, postId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				boardDto = new BoardDto();
				boardDto.setNoteNo(rs.getInt("NOTE_NO"));
				boardDto.setTitle(rs.getString("NOTE_TITLE"));
				boardDto.setMemberno(rs.getInt("MEMBER_NO"));
				boardDto.setContent(rs.getString("NOTE_CONTENT"));
				boardDto.setCategoryNo(rs.getInt("CATEGORY_NO"));
				boardDto.setCreateDate(rs.getDate("CREATE_AT"));

				// ✅ 작성자 정보 추가
				boardDto.setWriter(rs.getString("WRITER"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return boardDto;
	}

	public List<BoardDto> selectList(int page, int pageSize) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardDto> boardList = new ArrayList<>();

		String sql = "";

		try {
			sql += "SELECT * ";
			sql += "FROM ( ";
			sql += "    SELECT ";
			sql += "        N.NOTE_NO, ";
			sql += "        N.NOTE_TITLE, ";
			sql += "        M.MEMBER_NAME AS WRITER, ";
			sql += "        N.CREATE_AT, ";
			sql += "        C.CATEGORY_NAME AS CATEGORY, ";
			sql += "        ROW_NUMBER() OVER (ORDER BY N.NOTE_NO DESC) AS RNUM ";
			sql += "    FROM NOTE N ";
			sql += "    JOIN MEMBER M ON N.MEMBER_NO = M.MEMBER_NO ";
			sql += "    JOIN BOARD_CATEGORY C ON N.CATEGORY_NO = C.CATEGORY_NO ";
			sql += "    WHERE N.CATEGORY_NO != 1 "; // 공지사항 제외
			sql += ") ";
			sql += "WHERE RNUM BETWEEN ? AND ?"; // 🔥 페이지네이션 추가 (범위 지정)

			pstmt = connection.prepareStatement(sql);

			// ⚡ OFFSET & LIMIT 계산
			int startRow = (page - 1) * pageSize + 1; // 시작 행 번호
			int endRow = page * pageSize; // 끝 행 번호

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			System.out.println("dao 현재 페이지: " + page);
			System.out.println("dao startRow: " + startRow);
			System.out.println("dao endRow: " + endRow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDto boardDto = new BoardDto();
				boardDto.setNoteNo(rs.getInt("NOTE_NO"));
				boardDto.setTitle(rs.getString("NOTE_TITLE"));
				boardDto.setWriter(rs.getString("WRITER"));
				boardDto.setCreateDate(rs.getDate("CREATE_AT"));
				boardDto.setCategory(rs.getString("CATEGORY"));

				boardList.add(boardDto);
				System.out.println("dao 가져온 게시글 개수: " + boardList.size());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return boardList;
	}

	public List<BoardDto> adminSelectList(int page, int pageSize) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardDto> boardList = new ArrayList<>();

		String sql = "";

		try {
			sql += "SELECT * FROM ( ";
			sql += "    SELECT * FROM ( ";
			sql += "        SELECT ";
			sql += "            N.NOTE_NO, ";
			sql += "            N.NOTE_TITLE, ";
			sql += "            M.MEMBER_NAME AS WRITER, ";
			sql += "            N.CREATE_AT, ";
			sql += "            C.CATEGORY_NAME AS CATEGORY, ";
			sql += "            0 AS RNUM ";
			sql += "        FROM NOTE N ";
			sql += "        JOIN MEMBER M ON N.MEMBER_NO = M.MEMBER_NO ";
			sql += "        JOIN BOARD_CATEGORY C ON N.CATEGORY_NO = C.CATEGORY_NO ";
			sql += "        WHERE N.CATEGORY_NO = 1 ";
			sql += "        ORDER BY N.NOTE_NO DESC ";
			sql += "    ) WHERE ROWNUM <= 2 "; 
			sql += ") ";
			sql += "UNION ALL ";
			sql += "SELECT * FROM ( ";
			sql += "    SELECT ";
			sql += "        N.NOTE_NO, ";
			sql += "        N.NOTE_TITLE, ";
			sql += "        M.MEMBER_NAME AS WRITER, ";
			sql += "        N.CREATE_AT, ";
			sql += "        C.CATEGORY_NAME AS CATEGORY, ";
			sql += "        ROW_NUMBER() OVER (ORDER BY N.NOTE_NO DESC) AS RNUM ";
			sql += "    FROM NOTE N ";
			sql += "    JOIN MEMBER M ON N.MEMBER_NO = M.MEMBER_NO ";
			sql += "    JOIN BOARD_CATEGORY C ON N.CATEGORY_NO = C.CATEGORY_NO ";
			sql += "    WHERE N.CATEGORY_NO != 1 ";
			sql += ") ";
			sql += "WHERE RNUM BETWEEN ? AND ?";  


			pstmt = connection.prepareStatement(sql);

			int startRow = (page - 1) * pageSize + 1; // 시작 행 번호
			int endRow = page * pageSize; // 끝 행 번호

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			System.out.println("관리자 dao 현재 페이지: " + page);
			System.out.println("관리자 dao startRow: " + startRow);
			System.out.println("관리자 dao endRow: " + endRow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDto boardDto = new BoardDto();
				boardDto.setNoteNo(rs.getInt("NOTE_NO"));
				boardDto.setTitle(rs.getString("NOTE_TITLE"));
				boardDto.setWriter(rs.getString("WRITER"));
				boardDto.setCreateDate(rs.getDate("CREATE_AT"));
				boardDto.setCategory(rs.getString("CATEGORY"));

				boardList.add(boardDto);
				System.out.println("관리자가 가져온 게시글 개수: " + boardList.size());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return boardList;
	}

	public List<BoardDto> searchBoard(String keyword, HttpServletRequest req) {
		PreparedStatement pstmt = null; // 쿼리실행준비
		ResultSet rs = null; // sql 실행결과 객체담을 그릇 준비

		ArrayList<BoardDto> boardList = new ArrayList<BoardDto>(); //
		HttpSession session = req.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		String sql = "";

		try {
			sql = "SELECT N.NOTE_NO, N.NOTE_TITLE, M.MEMBER_NAME AS WRITER, N.CREATE_AT, B.CATEGORY_NAME "
					+ "FROM NOTE N " + "JOIN MEMBER M ON N.MEMBER_NO = M.MEMBER_NO "
					+ "JOIN BOARD_CATEGORY B ON N.CATEGORY_NO = B.CATEGORY_NO "
					+ "WHERE LOWER(N.NOTE_TITLE) LIKE LOWER(?)";
			pstmt = connection.prepareStatement(sql);

			if (keyword == null || keyword.trim().isEmpty()) {

				keyword = "%"; // 전체 검색을 위한 기본값

			} else {

				keyword = "%" + keyword.trim() + "%"; // 앞뒤에 % 추가

			}
			pstmt.setString(1, keyword);
			rs = pstmt.executeQuery();
			int noteNo = 0;
			String title = "";
			String writer = "";
			Date createDate = null;
			String category = "";

			while (rs.next()) {
				noteNo = rs.getInt("NOTE_NO");
				title = rs.getString("NOTE_TITLE");
				writer = rs.getString("WRITER");
				createDate = rs.getDate("CREATE_AT");
				category = rs.getString("CATEGORY_NAME");

				BoardDto boardDto = new BoardDto();
				boardDto.setCreateDate(createDate);
				boardDto.setNoteNo(noteNo);
				boardDto.setTitle(title);
				boardDto.setWriter(writer);
				boardDto.setCategory(category);
				boardList.add(boardDto);
				// 리스트에 담았음 //
				// 컨트롤러에서 수신예정//

			}

			return boardList;
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

		return boardList;

	}

	// 📌 전체 게시글 개수 조회 메서드
	public int selectTotalCount() throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalCount = 0;

		String sql = "SELECT COUNT(*) AS total FROM NOTE WHERE CATEGORY_NO != 1"; // 공지 제외

		try {
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				totalCount = rs.getInt("total");
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

	public void updateBoard(int postId, String title, String content) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "UPDATE NOTE " + "SET NOTE_TITLE = ?, " + "NOTE_CONTENT = ?,	 " + "MODIFY_AT = SYSDATE "
				+ "WHERE NOTE_NO = ?";

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, postId);

			int rowsUpdated = pstmt.executeUpdate(); // ✅ 영향을 받은 행 개수 반환
			System.out.println("수정된 행 개수: " + rowsUpdated);

		} finally {
			if (pstmt != null)
				pstmt.close();
		}
	}

	public void deleteBoard(int postId) throws SQLException {
		String sql = "DELETE FROM NOTE WHERE NOTE_NO = ?";

		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, postId);
			pstmt.executeUpdate();
		}
	}

}
