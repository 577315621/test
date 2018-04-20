package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mytest.Util;

public class Test8 {

	private static Test8 te;

	public static Test8 getInstance() {
		if (te == null) {
			te = new Test8();
		}
		return te;
	}

	Random random = new Random();
	// 普通奖池
	Prize[] pool = { Prize.prizeOne, Prize.prizeTwo, Prize.prizeThree, Prize.prizeFour, Prize.prizeFive, Prize.prizeSix,
			Prize.prizeSeven, Prize.prizeEight, Prize.prizeNine, Prize.prizeTen, Prize.prizeEleven, Prize.prizeTwelve,
			Prize.specialOne, Prize.specialTwo, Prize.specialThree };
	// 特殊奖池 不包含百变号
	Prize[] specialpool = { Prize.prizeOne, Prize.prizeTwo, Prize.prizeThree, Prize.prizeFour, Prize.prizeFive,
			Prize.prizeSix, Prize.prizeSeven, Prize.prizeEight, Prize.prizeNine, Prize.prizeTen, Prize.prizeEleven,
			Prize.prizeTwelve, Prize.specialTwo, Prize.specialThree };

	// 获取普通奖池 不含百变 不等于目标奖
	private Prize getSpecialPrize(Prize p) {
		if (p == null) {
			return specialpool[random.nextInt(specialpool.length)];
		}
		Prize temp = null;
		do {
			temp = specialpool[random.nextInt(specialpool.length)];
		} while (temp.getName().equals(p.getName()));
		return temp;
	}

	/**
	 * 传入中奖列，和触发线数获得 完整15张图片id
	 * 
	 * @param lie
	 *            中奖列
	 * @param count
	 *            触发次数
	 * @return 中奖id字符串 用逗号间隔
	 */
	public String getXianDetail(int lie, int count) {
		int[][] re = getResult(lie, count);
		StringBuffer sb = new StringBuffer();
		// 随机一个中奖id
		Prize p = getSpecialPrize(null);
		String[] temp = new String[3]; // 第一列数据
		boolean flag = false; // 是否出现了百变
		for (int j = 0; j < 5; j++) {
			boolean lieFlag =false; //记录列是否出现百变
			for (int i = 0; i < 3; i++) {
				String num = fill(p, j, temp, re[i][j], flag);
				sb.append(num + ",");
				re[i][j] = Integer.parseInt(num);
				if (j == 0) {
					temp[i] = num;
				}
				// 记录出现百变
				if (num.equals(Prize.specialOne.getName())) {
					lieFlag = true;
				}
			}
			if(lieFlag){
				flag  =true;
			}
		}
		sb.deleteCharAt(sb.length() - 1);

		System.out.println("==========打印输出===========");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(re[i][j] + "//");
			}
			System.out.println();
		}
		System.out.println("===========================");
		return sb.toString();
	}

	private String fill(Prize p, int lie, String[] temp, int flag, boolean baiBian) {
		// 第1，5列不能为百变
		if (lie == 0 || lie == 4) {
			if (flag == 1) {
				return p.getName();
			} else {
				return getSpecialPrize(p).getName();
			}
		}
		return getPrizeName(temp, baiBian, p, flag);
	}

	/**
	 * 获取一个奖池id，
	 * 
	 * @return
	 */
	private String getPrizeName(String[] temp, boolean flag, Prize p, int shu) {
		if (shu == 1) {
			if (flag) {
				return p.getName();
			} else {
				int k = random.nextInt(10);
				// 30%的几率出现百变
				if (k > 6) {
					return Prize.specialOne.getName();
				} else {
					return p.getName();
				}
			}
		} else {
			Prize tempPrize = null;
			do {
				tempPrize = getSpecialPrize(p);
			} while (tempPrize.getName().equals(temp[0]) || tempPrize.getName().equals(temp[1])
					|| tempPrize.getName().equals(temp[2]));
			return tempPrize.getName();
		}
	}

	/**
	 * 获取中奖路线多点图
	 * 
	 * @param lie
	 *            列
	 * @param count
	 *            次数
	 * @return
	 */
	private int[][] getResult(int lie, int count) {

		int[] data = null;
		switch (lie) {
		// 获取按照线的个数和列，所能够组成的所以情况分散点
		case 3:
			data = getDataThree(count);
			break;
		case 4:
			data = getDataFour(count);
			break;
		case 5:
			data = getDataFive(count);
			break;
		default:
			return null;
		}
		// 如果该选择的线列不能所组合，则返回null，例如 3列5线， 5列10线 不可能存在
		if (data == null) {
			return null;
		}
		List<int[][]> temp = new ArrayList<>();

		// 进入递归
		List<int[][]> result = getResultByLoop(data, 0, temp);

		// 在组成的点中随机获取一个
		int[][] re = result.get(random.nextInt(result.size()));
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(re[i][j] + "//");
			}
			System.out.println();
		}
		return re;
	}

	// 获取三列的数据
	private int[] getDataThree(int count) {
		List<int[]> result = new ArrayList<>();
		for (int i = 1; i < 4; i++) {
			for (int j = 1; j < 4; j++) {
				for (int j2 = 1; j2 < 4; j2++) {
					if (i * j * j2 == count) {
						int[] temp = new int[3];
						temp[0] = i;
						temp[1] = j;
						temp[2] = j2;
						result.add(temp);
					}
				}
			}
		}
		if (result.size() > 0) {
			return result.get(random.nextInt(result.size()));
		} else {
			return null;
		}
	}

	// 获取四列的基数
	private int[] getDataFour(int count) {
		List<int[]> result = new ArrayList<>();
		for (int i = 1; i < 4; i++) {
			for (int j = 1; j < 4; j++) {
				for (int j2 = 1; j2 < 4; j2++) {
					for (int k = 1; k < 4; k++) {
						if (i * j * j2 * k == count) {
							int[] temp = new int[4];
							temp[0] = i;
							temp[1] = j;
							temp[2] = j2;
							temp[3] = k;
							result.add(temp);
						}
					}
				}
			}
		}
		if (result.size() > 0) {
			return result.get(random.nextInt(result.size()));
		} else {
			return null;
		}
	}

	// 获取五列的基数
	private int[] getDataFive(int count) {
		List<int[]> result = new ArrayList<>();
		for (int i = 1; i < 4; i++) {
			for (int j = 1; j < 4; j++) {
				for (int j2 = 1; j2 < 4; j2++) {
					for (int k = 1; k < 4; k++) {
						for (int k2 = 1; k2 < 4; k2++) {
							if (i * j * j2 * k * k2 == count) {
								int[] temp = new int[5];
								temp[0] = i;
								temp[1] = j;
								temp[2] = j2;
								temp[3] = k;
								temp[4] = k2;
								result.add(temp);
							}
						}
					}
				}
			}
		}
		if (result.size() > 0) {
			return result.get(random.nextInt(result.size()));
		} else {
			return null;
		}
	}

	// 递归决定每一列有多少中情况
	private List<int[][]> getResultByLoop(int[] data, int loop, List<int[][]> temp) {
		if (loop == data.length) {
			return temp;
		}
		// 每次递归保存新的结果集传入下一次递归
		List<int[][]> result = new ArrayList<>();
		if (loop == 0) {
			// 第一次即没前一列，则建一空数组
			result.addAll(getArray(0, data[loop], new int[3][5]));
		} else {
			for (int[][] is : temp) {
				// 遍历前一列所以应对的后一列有多少中情况
				result.addAll(getArray(loop, data[loop], is));
			}
		}
		loop++;
		return getResultByLoop(data, loop, result);
	}

	/**
	 * 获得数组
	 * 
	 * @param lie
	 *            当前列
	 * @param count
	 *            当前列有几个点
	 * @param is
	 *            上一列的一种情况
	 * @return
	 */
	private List<int[][]> getArray(int lie, int count, int[][] is) {
		List<int[][]> result = new ArrayList<>();
		if (count == 1) {
			for (int k = 0; k < 3; k++) {
				int[][] temp = copyArray(is);
				temp[k][lie] = 1;
				result.add(temp);
			}
		}
		if (count == 2) {
			for (int i = 0; i < 3; i++) {
				for (int k = i + 1; k < 3; k++) {
					int[][] temp = copyArray(is);
					temp[i][lie] = 1;
					temp[k][lie] = 1;
					result.add(temp);
				}
			}
		}
		if (count == 3) {
			int[][] temp = copyArray(is);
			temp[0][lie] = 1;
			temp[1][lie] = 1;
			temp[2][lie] = 1;
			result.add(temp);
		}
		return result;
	}

	/**
	 * 建新的空间完全复制数组
	 * 
	 * @param is
	 * @return
	 */
	private int[][] copyArray(int[][] is) {
		int[][] temp = new int[3][5];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {
				temp[i][j] = is[i][j];
			}
		}
		return temp;
	}

	public static void main(String[] args) {

		System.out.println(Test8.getInstance().getXianDetail(4, 4));
	}

}
