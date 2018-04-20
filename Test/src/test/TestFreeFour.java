package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TestFreeFour {

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
					temp[0][3] = 15;
					temp[1][3] = 15;
					temp[2][3] = 15;
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
		if (loop == 13) {
			return;
		}
		if (loop != 7 && loop != 8 && loop != 9 &&loop != 1 && loop != 2 && loop != 3) {
			int heng = (loop - 1) / 3 + 1; // 横坐标
			int zong = (loop - 1) % 3; // 纵坐标
			int i = 0;
			do {
				i = random.nextInt(14) + 1;
				list3[zong][heng] = i;
			} while (i == list3[0][0] || i == list3[1][0] || i == list3[2][0]);
		}
		loop++;
		getRe(list3, loop);
	}

}
