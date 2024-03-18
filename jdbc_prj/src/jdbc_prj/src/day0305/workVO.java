package day0305;

import java.sql.Date;

public class workVO {

	private int num, age;
	private String name, img;
	private Date input_date;
	
	public workVO() {
	}
	
	public workVO(int num, int age, String name, String img, Date input_date) {
		super();
		this.num = num;
		this.age = age;
		this.name = name;
		this.img = img;
		this.input_date = input_date;
	}
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getImg() {
		return img;
	}
	
	public void setImg(String img) {
		this.img = img;
	}
	
	public Date getInput_date() {
		return input_date;
	}
	
	public void setInput_date(Date input_date) {
		this.input_date = input_date;
	}
	
	@Override
	public String toString() {
		return "workVO [num=" + num + ", age=" + age + ", name=" + name + ", img=" + img + ", input_date=" + input_date
				+ "]";
	}
	
	
	
}//class
