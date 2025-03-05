package JavaDive.dto.odclass;

public class ODClassDto {
	
	private int classNo;
	private String className;
	private int price;
	private String desc;
	private String instructor;
	private String createAt;
	private int field;
	private int classLimit;
	private String img;
	
	public ODClassDto() {
		super();
	}
	
	public ODClassDto(int classNo, String className, int price, String desc, String instructor, String createAt,
			int field, int classLimit, String img) {
		super();
		this.classNo = classNo;
		this.className = className;
		this.price = price;
		this.desc = desc;
		this.instructor = instructor;
		this.createAt = createAt;
		this.field = field;
		this.classLimit = classLimit;
		this.img = img;
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

	public int getField() {
		return field;
	}

	public void setField(int field) {
		this.field = field;
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

	@Override
	public String toString() {
		return "ODClassDto [classNo=" + classNo + ", className=" + className + ", price=" + price + ", desc=" + desc
				+ ", instructor=" + instructor + ", createAt=" + createAt + ", field=" + field + ", classLimit="
				+ classLimit + ", img=" + img + "]";
	}
	
	
	
	
}
