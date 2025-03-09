package JavaDive.dto.odclass;

import java.util.Date;

public class ODClassDto {
	
	private int classNo;
	private String className;
	private int price;
	private String classDesc;
	private String instructor;
	private Date createAt;
	private int views;
	private int classLimit;
	private String img;
	private String region;
	private int categoryNo;
	
	public ODClassDto() {
		super();
	}

	public ODClassDto(int classNo, String className, int price, String classDesc, String instructor, Date createAt,
			int views, int classLimit, String img, String region, int categoryNo) {
		super();
		this.classNo = classNo;
		this.className = className;
		this.price = price;
		this.classDesc = classDesc;
		this.instructor = instructor;
		this.createAt = createAt;
		this.views = views;
		this.classLimit = classLimit;
		this.img = img;
		this.region = region;
		this.categoryNo = categoryNo;
	}

	public int getClassNo() {
		return classNo;
	}

	public void setClassNo(int classNo) {
		this.classNo = classNo;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getClassDesc() {
		return classDesc;
	}

	public void setClassDesc(String classDesc) {
		this.classDesc = classDesc;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public int getClassLimit() {
		return classLimit;
	}

	public void setClassLimit(int classLimit) {
		this.classLimit = classLimit;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}

	@Override
	public String toString() {
		return "ODClassDto [classNo=" + classNo + ", className=" + className + ", price=" + price + ", classDesc=" + classDesc
				+ ", instructor=" + instructor + ", createAt=" + createAt + ", views=" + views + ", classLimit="
				+ classLimit + ", img=" + img + ", region=" + region + ", categoryNo=" + categoryNo + "]";
	}
	
}
