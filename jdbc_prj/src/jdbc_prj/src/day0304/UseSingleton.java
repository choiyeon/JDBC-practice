package day0304;

public class UseSingleton {

	public static void main(String[] args) {

//		Singleton single = new Singleton();
		Singleton single = Singleton.getInstance();
		Singleton single2 = Singleton.getInstance();
		Singleton single3 = Singleton.getInstance();
		
		
	}//main

}//class
