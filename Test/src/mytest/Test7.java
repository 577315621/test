package mytest;

import java.util.ArrayList;
import java.util.List;

public class Test7 {
	static int[][] tempa = new int[3][5];

	public static void main(String[] args) {
		// initPrizePool();
		// List<String> list = new ArrayList<String>();
		// for (int i = 0; i < 3; i++) {
		// for (int j = 0; j < 3; j++) {
		// for (int j2 = 0; j2 < 3; j2++) {
		// for (int k = 0; k < 3; k++) {
		// for (int o = 0; o < 3; o++) {
		// StringBuffer sb = new StringBuffer();
		// sb.append(tempa[i][0] + ",");
		// sb.append(tempa[j][1] + ",");
		// sb.append(tempa[j2][2] + ",");
		// sb.append(tempa[k][3] + ",");
		// sb.append(tempa[o][4]);
		// list.add(sb.toString());
		// }
		// }
		// }
		// }
		// }
		// for (String string : list) {
		// Util.save(string);
		// }
		// System.out.println(list.size());

//		System.out.println(getLocationById(33));
//		System.out.println(Util.selectByLocation("11,12,33,24"));
		System.out.println(Util.select2().toString());
		
		
		
	}
	
	/**
	 * 冒泡
	 */
	private static  void  getParam() {
		int[] temp =new int[5];
		temp[0]=19;temp[1]=5;temp[2]=12;temp[3]=40;temp[4]=1;
		
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp.length; j++) {
				if(temp[i]<temp[j]){
					int m =temp[i];
					temp[i] =temp[j];
					temp[j]=m;
					continue;
				}
			}
		}
		for (int i : temp) {
			System.out.println(i);
		}
	}

	/**
	 * 根据id获取位置
	 * 
	 * @param id
	 * @return
	 */
	public static String getLocationById(int id) {
		String temp = Util.selectById(id);
		if (check(temp)) {
			return temp;
		} else {
			return null;
		}
	}

	private static boolean check(String location) {
		// 数据不存在
		if (location == null || location.equals("")) {
			return false;
		}
		String[] array = location.split(",");
		if (array.length != 5) {
			return false;
		}
		// 3行5列，要保证每行 每列 有一个
		for (int i = 0; i < array.length - 1; i++) {
			int m = Integer.parseInt(array[i]) % 10;
			int n = Integer.parseInt(array[i + 1]) % 10;
			if (n - m != 1) {
				return false;
			}
		}
		return true;
	}

	private static void initPrizePool() {
		tempa = new int[3][5];
		tempa[0][0] = 11;
		tempa[0][1] = 12;
		tempa[0][2] = 13;
		tempa[0][3] = 14;
		tempa[0][4] = 15;

		tempa[1][0] = 21;
		tempa[1][1] = 22;
		tempa[1][2] = 23;
		tempa[1][3] = 24;
		tempa[1][4] = 25;

		tempa[2][0] = 31;
		tempa[2][1] = 32;
		tempa[2][2] = 33;
		tempa[2][3] = 34;
		tempa[2][4] = 35;

	}
}
