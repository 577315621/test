

import java.util.ArrayList;
import java.util.List;

import com.egame.gameServer.db.DBManager;
import com.egame.gameServer.db.DataRow;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GameQueryUtil {

	/**
	 * 根据id获取location
	 * 
	 * @return 返回location xx,xx,xx,xx,xx
	 */
	public static String getLoactionById(int id) {
		String gameCode = com.egame.gameServer.util.GameUtil.getStringByKey("game.gameCode");
		Object[] objects = { gameCode, id };
		String sql = "select loaction from line where gameCode =? and id =? ";
		DataRow dr = DBManager.querySole(sql, objects);
		String location = dr.getItem("location");
		if (check(location)) {
			return location;
		} else {
			return null;
		}
	}

	/**
	 * 根据全部获取location
	 * 
	 * @return 返回location xx,xx,xx,xx,xx
	 */
	public static JSONObject getAllData() {
		String gameCode = com.egame.gameServer.util.GameUtil.getStringByKey("game.gameCode");
		Object[] objects = { gameCode };
		String sql = "select id,loaction from line where gameCode =?";
		ArrayList<DataRow> dr = DBManager.query(sql, objects);
		JSONObject js = new JSONObject();
		for (DataRow dataRow : dr) {
			String location = dataRow.getItem("location");
			if (check(location)) {
				String[] arr = location.split(",");
				JSONArray array = JSONArray.fromObject(conver(arr));
				js.put(dataRow.getIntItem(1), array);
			}
		}
		return js;
	}
	
	private static  Integer[] conver(String[] temp) {
		Integer[] res =new Integer[temp.length];
		for (int i = 0; i < temp.length; i++) {
			res[i] =Integer.parseInt(temp[i]);
		}
		return res;
	}

	/**
	 * 根据location 获取id
	 * 
	 * @return 返回id 没有则返回-1
	 */
	public static int getIdByLoaction(String location) {
		// 数据不存在
		if (location == null || location.equals("")) {
			return -1;
		}
		// 确保5个数据
		String[] array = location.split(",");
		if (array.length < 3 || array.length > 5) {
			return -1;
		}
		String gameCode = com.egame.gameServer.util.GameUtil.getStringByKey("game.gameCode");
		StringBuffer sb = new StringBuffer(location);
		if (array.length != 5) {
			int shi = Integer.parseInt(array[array.length - 1]) / 10;
			for (int i = 0; i < 5 - array.length; i++) {
				sb.append("," + shi + "" + (array.length + i + 1));
			}
		}
		Object[] objects = { gameCode, sb.toString() };
		String sql = "select id from line where gameCode =? and location =? ";
		DataRow dr = DBManager.querySole(sql, objects);
		int id = dr.getIntItem("id");
		return id;
	}

	/**
	 * 根据多个location 获取id集合
	 * 
	 * @return 返回id 没有则返回-1
	 */
	public static List<Integer> getIdsByLoaction(List<StringBuffer> locations) {
		// 数据不存在
		if (locations == null || locations.size()==0) {
			return null;
		}
		String gameCode = com.egame.gameServer.util.GameUtil.getStringByKey("game.gameCode");
		StringBuffer sql =new StringBuffer("select id from line where   gameCode =? and ( ");
		Object[] objects = new Object[locations.size()+1];
		objects[0] =gameCode;
		
		for (int i=1;i<locations.size()+1;i++) {
			StringBuffer stringBuffer = locations.get(i-1);
			String[] array =stringBuffer.toString().split(",");
			if(array.length!=5){
				int shi =Integer.parseInt(array[array.length-1])/10;
				for (int k =0; k < 5-array.length; k++) {
					stringBuffer.append(","+shi+""+(array.length+k+1));
				}
			}
			sql.append("  location = '"+stringBuffer+"'  or");
			objects[i] =stringBuffer;
		}
		sql.deleteCharAt(sql.length()-1);
		sql.deleteCharAt(sql.length()-1);
		sql.append(")");
		
		ArrayList<DataRow> dr = DBManager.query(sql.toString(), objects);
		List<Integer> result =new ArrayList<>();
		for (DataRow dataRow : dr) {
			result.add(dataRow.getIntItem("id"));
		}
		return result;
	}
	
	/**
	 * 判断location是否合法
	 * 
	 * @param location
	 * @return true 合法，false不合法
	 */
	private static boolean check(String location) {
		// 数据不存在
		if (location == null || location.equals("")) {
			return false;
		}
		// 确保5个数据
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
}
