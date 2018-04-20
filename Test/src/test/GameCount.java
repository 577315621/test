package test;

import java.util.HashMap;
import java.util.Map;

/**
 * 计算线束
 * @author tony
 *
 */
public class GameCount {

	/**
	 * 获取线结果
	 * 
	 * @param result
	 *            结果集
	 * @return
	 */
	public static int getResult(int[][] result) {
		for (int k = 0; k < 3; k++) {
			for (int i = 0; i < 5; i++) {
				if (result[k][i] < 10) {
					System.out.print(result[k][i] + "  / ");
				} else {
					System.out.print(result[k][i] + " / ");
				}
			}
			System.out.println();
		}
		int xcount = 0;
		for (int i = 0; i < 3; i++) {
			int p = result[i][0];
			int loop = 0;
			int counta = gg(result, p, loop, 1);
			if (counta > 0) {
				System.out.println("第"+(i+1)+"行");
				System.out.println("线的ID[" + p + "]");
				System.out.println("线数[" + counta + "]");
				System.out.println("++++++++++++++");
			}
			xcount += counta;
		}
		System.out.println("总线数:" + xcount);
		return xcount;
	}

	private static int gg(int[][] result, int target, int loop, int re) {
		if (loop >= 4) {
			return re;
		}
		int count = 0;
		for (int i = 0; i < 3; i++) {
			int k = result[i][loop + 1];
			if (k == target) {
				count++;
			}
		}
		if (count == 0 && loop < 2) {
			return 0;
		}
		if (count == 0) {
			return re;
		} else {
			re = re * count;
			loop++;
			return gg(result, target, loop, re);
		}
	}

	/**
	 * 结果集
	 * 
	 * @author tony
	 *
	 */
	static class CountItem {
		public int sum = 0; // 总共线数
		public Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // 每条线有多少个节
	}

	public static void main(String[] args) {
		int[][] temp = new int[3][5];
		temp[0][0] = 5;
		temp[0][1] = 5;
		temp[0][2] = 5;
		temp[0][3] = 5;
		temp[0][4] = 6;
		temp[1][0] = 5;
		temp[1][1] = 5;
		temp[1][2] = 5;
		temp[1][3] = 5;
		temp[1][4] = 6;
		temp[2][0] = 5;
		temp[2][1] = 5;
		temp[2][2] = 5;
		temp[2][3] = 5;
		temp[2][4] = 6;
		getResult(temp);
	}
}
