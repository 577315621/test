package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 离散法
 * 将奖品集合的概率划分区段放入数组中。概率区段通过该概率累计相加确定。利用随机数产生随机概率，加入数组并排序，该数据的下标，就是对应奖品集合中奖品的索引
 * @author tony
 *
 */
public class LotteryUtil {
	/**
	 * 抽奖
	 * 
	 * @return 奖品的索引
	 */
//	public static Prize lottery(Map<Integer, Prize> gifts) {
//		double sumRate = 0d;
//		for (Prize rate : gifts.values()) {
//			sumRate += rate.getProbability();
//		}
//		List<Double> sortOrignalRates = new ArrayList<Double>();
//		Double tempSumRate = 0d;
//		for (Prize rate : gifts.values()) {
//			tempSumRate += rate.getProbability();
//			sortOrignalRates.add(tempSumRate / sumRate);
//		}
//		double nextDouble = Math.random();
//		sortOrignalRates.add(nextDouble);
//		Collections.sort(sortOrignalRates);
//		int resultId = sortOrignalRates.indexOf(nextDouble);
//		return gifts.remove(resultId + 1);
//	}
	
	public static Prize drawGift(List<Prize> giftList){

        if(null != giftList && giftList.size()>0){
            List<Double> orgProbList = new ArrayList<Double>(giftList.size());
            for(Prize gift:giftList){
                //按顺序将概率添加到集合中
                orgProbList.add(gift.getGai());
            }
            return giftList.remove(draw(orgProbList));
        }
        return null;
    }

    public static int draw(List<Double> giftProbList){

        List<Double> sortRateList = new ArrayList<Double>();

        // 计算概率总和
        Double sumRate = 0D;
        for(Double prob : giftProbList){
            sumRate += prob;
        }

        if(sumRate != 0){
            double rate = 0D;   //概率所占比例
            for(Double prob : giftProbList){
                rate += prob;   
                // 构建一个比例区段组成的集合(避免概率和不为1)
                sortRateList.add(rate / sumRate);
            }
            // 随机生成一个随机数，并排序
            double random = Math.random();
            sortRateList.add(random);
            Collections.sort(sortRateList);
            // 返回该随机数在比例集合中的索引
            return sortRateList.indexOf(random);
        }
        return -1;
    }
	
    public static void main(String[] args) throws InterruptedException {
		List<Prize> list=new ArrayList<Prize>();
		list.add(Prize.Bell);
		list.add(Prize.Snail);
		list.add(Prize.FortuneCat);
		list.add(Prize.Button);
		list.add(Prize.Clover);
		list.add(Prize.Sandwich);
		
		Map<Integer, Integer> m =new HashMap<>();
		
		for (int i = 0; i < 3; i++) {
			Prize k =drawGift(list);
//			System.out.println(list.get(k).getName());
			if(k!= null && m.get(k.getId()) ==null){
				m.put(k.getId(), 0);
			}else{
				m.put(k.getId(), m.get(k.getId()) +1);
			}
		}
		for (Integer prize : m.keySet()) {
			System.out.println("id:"+prize +" 值:"+m.get(prize));
		}
	}
	
    enum Prize{
    	Bell(1,0.22),Snail(1,0.42),FortuneCat(1,0.72),Button(1,0.22),Clover(1,0.82),Sandwich(1,0.12);
    	
    	private int id;
    	private double gai;
    	Prize(int id,double gai){
    		this.id =id;
    		this.gai=gai;
    	}
    	public int getId(){
    		return id;
    	}
    	public double getGai(){
    		return gai;
    	}
    }
}
