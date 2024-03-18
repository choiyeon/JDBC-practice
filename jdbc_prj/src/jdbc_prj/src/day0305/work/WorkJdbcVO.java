package day0305.work;

import java.sql.Date;

public class WorkJdbcVO {
	
	private int num, age;
	private String name, image; 
	private Date input_date;
	
	public WorkJdbcVO() {
	}

	public WorkJdbcVO(int num, int age, String name, String image, Date input_date) {
		this.num = num;
		this.age = age;
		this.name = name;
		this.image = image;
		this.input_date = input_date;
	}

	@Override
	public String toString() {
		return "WorkJDBCVO [num=" + num + ", age=" + age + ", name=" + name + ", image=" + image + ", input_date="
				+ input_date + "]";
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getInput_date() {
		return input_date;
	}

	public void setInput_date(Date input_date) {
		this.input_date = input_date;
	}
	
}//clss
