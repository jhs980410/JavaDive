package spms.dto;

import java.sql.Date;

public class MemberDto {

	private int no;
	private String email;
	private String pwd;
	private String name;
	private String rrn;
	private String tel;
	private String priv;
	private Date create_at;
	
	public MemberDto(int no, String email, String pwd, String name, String rrn, String tel, String priv,
			Date create_at) {
		super();
		
		this.no = no;
		this.email = email;
		this.pwd = pwd;
		this.name = name;
		this.rrn = rrn;
		this.tel = tel;
		this.priv = priv;
		this.create_at = create_at;
	}
	
	public MemberDto(int no, String email, String name, String tel, Date create_at) {
		super();
		this.no = no;
		this.email = email;
		this.name = name;
		this.tel = tel;
		this.create_at = create_at;
	}

	public MemberDto() {
		super();
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRrn() {
		return rrn;
	}
	public void setRrn(String rrn) {
		this.rrn = rrn;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPriv() {
		return priv;
	}
	public void setPriv(String priv) {
		this.priv = priv;
	}
	public Date getCreate_at() {
		return create_at;
	}
	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}

	@Override
	public String toString() {
		return "MemberDto [no=" + no + ", email=" + email + ", pwd=" 
				+ pwd + ", name=" + name + ", rrn=" + rrn
				+ ", tel=" + tel + ", priv=" + priv 
				+ ", create_at=" + create_at + "]";
	}
	
	
	
}
