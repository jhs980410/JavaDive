package JavaDive.dto.board;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class BoardCommentDto {
	
	private int commentNo;   //댓글번호 
	private String commentWriter;  //댓글 작성자 
	private String commentContent; //댓글 내용 
	private Timestamp createAt;   //댓글날짜 
	private Timestamp modifyAt;   //수정날짜 
	private int memberNo;
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	private int noteNo;   //fk키 게시글번호  
	public BoardCommentDto() {
		super();
	}
	public BoardCommentDto(int commentNo, String commentWriter, String commentContent, Timestamp createAt, Timestamp modifyAt,
			int noteNo) {
		super();
		this.commentNo = commentNo;
		this.commentWriter = commentWriter;
		this.commentContent = commentContent;
		this.createAt = createAt;
		this.modifyAt = modifyAt;
		this.noteNo = noteNo;
	}
	

	   
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public String getCommentWriter() {
		return commentWriter;
	}
	public void setCommentWriter(String commentWriter) {
		this.commentWriter = commentWriter;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public Timestamp getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}
	public Timestamp getModifyAt() {
		return modifyAt;
	}
	public void setModifyAt(Timestamp modifyAt) {
		this.modifyAt = modifyAt;
	}
	public int getNoteNo() {
		return noteNo;
	}
	public void setNoteNo(int noteNo) {
		this.noteNo = noteNo;
	}
	
	

	
	
}
