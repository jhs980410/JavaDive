package JavaDive.dto.odclass;

public class ODClassDto {
	
	private int classNo;
	private String className;
	private int price;
	private String desc;
	private String instructor;
	private String createAt;
	private int views;
	private int classLimit;
	private String img;
	private String region;
	private int categoryNo;
	
	public ODClassDto() {
		super();
	}

	public ODClassDto(int classNo, String className, int price, String desc, String instructor, String createAt,
			int views, int classLimit, String img, String region, int categoryNo) {
		super();
		this.classNo = classNo;
		this.className = className;
		this.price = price;
		this.desc = desc;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
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
		return "ODClassDto [classNo=" + classNo + ", className=" + className + ", price=" + price + ", desc=" + desc
				+ ", instructor=" + instructor + ", createAt=" + createAt + ", views=" + views + ", classLimit="
				+ classLimit + ", img=" + img + ", region=" + region + ", categoryNo=" + categoryNo + "]";
	}
	
}
