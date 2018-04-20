package test;

import java.util.ArrayList;
import java.util.List;

public class Test3 {
	static List<Prize> column = null;

	public static void main(String[] args) {
		List<int[][]> result = new ArrayList<int[][]>();

		for (int m = 1; m < 16; m++) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					for (int j2 = 0; j2 < 3; j2++) {
						int[][] temp = new int[3][3];
						temp[i][0] = m;
						temp[j][1] = m;
						temp[j2][2] = m;
						result.add(temp);
					}
				}
			}
		}

		for (int[][] integers : result) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					System.out.print(integers[i][j] + "/");
				}
				System.out.println();
			}
			System.out.println("=======");
		}
		System.out.println(result.size());
	}

}
