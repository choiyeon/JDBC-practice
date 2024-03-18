package day0307;

public class CarVO {
	private String country, maker, model, car_year, car_option;
	private int price;
	
	public CarVO() {
	}
	
	public CarVO(String country, String maker, String model, String car_year, String car_option, int price) {
		super();
		this.country = country;
		this.maker = maker;
		this.model = model;
		this.car_year = car_year;
		this.car_option = car_option;
		this.price = price;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getMaker() {
		return maker;
	}
	
	public void setMaker(String maker) {
		this.maker = maker;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getCar_year() {
		return car_year;
	}
	
	public void setCar_year(String car_year) {
		this.car_year = car_year;
	}
	
	public String getCar_option() {
		return car_option;
	}
	
	public void setCar_option(String car_option) {
		this.car_option = car_option;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "CarVO [country=" + country + ", maker=" + maker + ", model=" + model + ", car_year=" + car_year
				+ ", car_option=" + car_option + ", price=" + price + "]";
	}
	
	
}//class
