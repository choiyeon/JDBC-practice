package day0304;

/**
 *Singleton pattern이 도입된 클래스 
 */
public class Singleton {
	private static Singleton single;//객체를 유지하기 위해서 선언한 클래스변수
	//1. 클래스외부에서 객체생성을 막기위해 생성자를 private으로 설정
	private Singleton() {
		
	}
	
	//2. 클래스 외부에서 객체를 사용할 수 있도록 method 작성
	public static Singleton getInstance() {
		//객체를 하나로 유지하면서 생성하는 코드 작성.
		if(single == null) {
			single = new Singleton();
		}//end if
		return single;
	}//getInstance
}//class
