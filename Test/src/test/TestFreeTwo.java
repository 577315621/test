package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TestFreeTwo {

	static List<Prize> column = null;

	static Random random = new Random();

	public static void main(String[] args) {

		// int[][] temp = new int[5][3];
		List<int[][]> list3 = new ArrayList<int[][]>();
		for (int i = 1; i < 16; i++) {
			for (int j = 1; j < 16; j++) {
				for (int k = 1; k < 16; k++) {
					int[][] temp = new int[3][5];
					temp[0][0] = i;
					temp[1][0] = j;
					temp[2][0] = k;
					temp[0][1] = 15;
					temp[1][1] = 15;
					temp[2][1] = 15;
					list3.add(temp);
				}
			}
		}

		for (int[][] is : list3) {
			getRe(is, 1);
		}

		for (int[][] is : list3) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 5; j++) {
					if (is[i][j] < 10) {
						System.out.print(is[i][j] + "  / ");
					} else {
						System.out.print(is[i][j] + " / ");
					}
				}
				System.out.println();
			}
			System.out.println("=================");
		}
		System.err.println(list3.size());
	}

	public static void getRe(int[][] list3, int loop) {
		if (loop == 10) {
			return;
		}
		int heng = (loop - 1) / 3 + 2; // 横坐标
		int zong = (loop - 1) % 3; // 纵坐标
		int i = 0;
		do {
			i = random.nextInt(14) + 1;
			list3[zong][heng] = i;
		} while (i == list3[0][0] || i == list3[1][0] || i == list3[2][0]);
		loop++;
		getRe(list3, loop);
	}

	/**
	 * 获取线结果
	 * 
	 * @param result
	 *            结果集
	 * @return
	 */
	private static CountItem getResult(int[][] result) {
		CountItem count = new CountItem();
		for (int k = 0; k < 3; k++) {
			for (int i = 0; i < 5; i++) {
				if (result[i][k] < 10) {
					System.out.print(result[i][k] + "  / ");
				} else {
					System.out.print(result[i][k] + " / ");
				}
			}
			System.out.println();
		}
		for (int i = 0; i < 3; i++) {
			int icount = 1;
			int p = result[i][0];
			for (int j2 = 1; j2 < 5; j2++) {
				boolean flag = false;
				for (int j = 0; j < 3; j++) {
					int mm = result[j][j2];
					if (p == mm) {
						flag = true;
					}
				}
				if (flag) {
					icount++;
				} else {
					break;
				}
			}
			if (icount > 2) {
				count.sum++;
				count.map.put(p, icount);
				break;
			}
		}
		return count;
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

}
