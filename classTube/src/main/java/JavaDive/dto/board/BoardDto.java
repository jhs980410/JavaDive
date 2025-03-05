package JavaDive.dto.board;

import java.sql.Date;


public class BoardDto {

	private int noteNo; // 게시글 번호
	private String title; // 제목
	private String writer; // 작성자
	private Date createDate; // 작성일
	private Date updateDate; // 수정일
	private String content; // 내용
	private String category; // 카테고리 (말머리)
	private int categoryNo; // 카테고리 번호 (외래키)

	public BoardDto() {
	}

	public BoardDto(int noteNo, String title, String writer, Date createDate, Date updateDate, String content,
			String category, int categoryNo) {
		this.noteNo = noteNo;
		this.title = title;
		this.writer = writer;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.content = content;
		this.category = category;
		this.categoryNo = categoryNo;
	}

	// Getters & Setters
	public int getNoteNo() {
		return noteNo;
	}

	public void setNoteNo(int noteNo) {
		this.noteNo = noteNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
}

