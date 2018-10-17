package mytest;

public class FunTest {

	public static void ads(Object... objects ){
		System.out.println(objects[0]);
//		System.out.println(objects[1]);
//		System.out.println(objects[2]);
	}
	
	
	public static void ad(int i,Object... objects ){
		System.out.println(objects[0]);
		ads(objects);
	}
	
	public static void main(String[] args) {
		ad(5,1);
	}
}
