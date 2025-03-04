package JavaDive.dto.board;

import java.sql.Date;

public class BoardDto {

	private int noteNo;    //게시글번호
	private String title;  // 게시글 제목 
	private String writer; // 게시글작성자
	private Date createDate; //게시글 작성일 
	private Date updaDate;  // 게시글 수정일 
	private String content; //게시글 내용 
	private String category; //게시글 카테고리 (말머리)
	private String categoryNo; //게시글 카테고리번호 fk;
	public BoardDto() {
		super();
	}
	public BoardDto(int noteNo, String title, String writer, Date createDate, Date updaDate, String content,
			String category) {
		super();
		this.noteNo = noteNo;
		this.title = title;
		this.writer = writer;
		this.createDate = createDate;
		this.updaDate = updaDate;
		this.content = content;
		this.category = category;
	}
	public int getNoteNo() {
		return noteNo;
	}
	public String getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
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
	public Date getUpdaDate() {
		return updaDate;
	}
	public void setUpdaDate(Date updaDate) {
		this.updaDate = updaDate;
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
	
	
}
