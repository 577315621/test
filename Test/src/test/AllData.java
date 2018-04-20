package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllData {

	static List<Prize> column = null;

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
					list3.add(temp);
				}
			}
		}
		List<int[][]> list2 = getRe(list3, 1);

		for (int[][] is : list2) {
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
		System.err.println(list2.size());
	}

	public static List<int[][]> getRe(List<int[][]> list, int loop) {
		if (loop >12)
			return list;

		List<int[][]> res = new ArrayList<>();
		int heng = (loop - 1) / 3 + 1; // 横坐标
		int zong = (loop - 1) % 3; // 纵坐标
		for (int[][] is : list) {
			for (int i = 1; i < 15; i++) {
				int[][] temp = copyArray(is);
				if (loop < 3) {
					if (i == temp[0][0] || i == temp[1][0] || i == temp[2][0]) {
						continue;
					} else {
						temp[zong][heng] = i;
					}
				}else{
					temp[zong][heng] = i;
				}
				res.add(temp);
			}
		}
		loop++;
		return getRe(res, loop);
	}

	private static int[][] copyArray(int[][] is) {
		int[][] temp =new int[3][5];
		for (int i = 0; i < is.length; i++) {
			for (int j = 0; j < temp.length; j++) {
				temp[i][j] =is[i][j];
			}
		}
		return temp;
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
