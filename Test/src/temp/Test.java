package temp;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str;
		while (true) {
			str = sc.nextLine();
			str = str.replace(str.indexOf('吗') == 0 ? "1" : "", "");
			str = str.replace(str.indexOf('?') == 0 ? "1" : "", "!");
			System.out.println(str);
		}
	}
}
