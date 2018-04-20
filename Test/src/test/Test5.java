package test;

import java.util.ArrayList;
import java.util.List;

import mytest.Util;

/**
 * 五图的
 * 
 * @author tony
 *
 */
public class Test5 {
	static List<Prize> column = null;

	public static void main(String[] args) {
		List<int[][]> result = new ArrayList<int[][]>();

		int m = 1;
		// for (int m = 1; m < 16; m++) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				for (int j2 = 0; j2 < 3; j2++) {
					for (int k = 0; k < 3; k++) {
						for (int o = 0; o < 3; o++) {
							int[][] temp = new int[3][5];
							temp[i][0] = m;
							temp[j][1] = m;
							temp[j2][2] = m;
							temp[k][3] = m;
							temp[o][4] = m;
							result.add(temp);
						}
					}
				}
			}
		}
		// }

		List<String> res = new ArrayList<>();
		for (int[][] integers : result) {
			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < 5; j++) {
				for (int i = 0; i < 3; i++) {
					if (integers[i][j] == 1) {
						sb.append((i + 1) + "" + (j + 1) + ",");
					}
				}
			}
			sb.deleteCharAt(sb.length() - 1);
			res.add(sb.toString());
		}
		System.out.println(res.size());

		for (int i =1; i<res.size()+1;i++) {
			String temp =res.get(i-1);
			Util.save(i,temp);
		}
	}

}
