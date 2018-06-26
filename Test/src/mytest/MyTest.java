package mytest;

import java.text.DecimalFormat;

public class MyTest {

	public static void main(String[] args) {
		DecimalFormat df =new DecimalFormat("#.00");
		System.out.println(Double.parseDouble(df.format(9942.299)));
		prinsssss(Double.parseDouble(df.format(9942.29)));
		double a =0;
		a +=Double.parseDouble(df.format(9942.29));
		System.out.println(a);
	}
	
	
	public static void prinsssss(double aa ){
		
		System.out.println(aa);
	}
}
