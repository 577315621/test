package mytest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Util {
	static String driverName = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://192.168.0.233:3306/egame?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	static String user = "root";
	static String password = "root";

	public static List<String> select(){
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<>();
		try {
			// 加载驱动
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, user, password);
			stat = conn.createStatement();
			rs = stat.executeQuery("select location from line");
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	public static JSONObject select2(){
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
//		Map<Integer,String> list = new HashMap<Integer,String>();
		JSONObject js =new JSONObject();
		try {
			// 加载驱动
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, user, password);
			stat = conn.prepareStatement("select * from line ");  
			rs = stat.executeQuery();
			while (rs.next()) {
				String location = rs.getString("location");
				String[] arr = location.split(",");
				JSONArray array = JSONArray.fromObject(conver(arr));
				js.put(rs.getInt(1), array);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
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
	
	
	

	public static String selectById(int id){
		Connection conn = null;
		PreparedStatement stat = null;  
		ResultSet rs = null;
		try {
			// 加载驱动
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, user, password);
			stat = conn.prepareStatement("select location from line where id =?");  
		    stat.setInt(1,id); 
			rs = stat.executeQuery();
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public static int selectByLocation(String location){
		Connection conn = null;
		PreparedStatement stat = null;  
		ResultSet rs = null;
		try {
			// 加载驱动
			Class.forName(driverName);
			
			// 数据不存在
			if (location == null || location.equals("")) {
				return 0;
			}
			// 确保5个数据
			String[] array = location.split(",");
			if (array.length <3 || array.length >5) {
				return 0;
			}
			StringBuffer sb =new StringBuffer(location);
			if(array.length!=5){
				int shi =Integer.parseInt(array[array.length-1])/10;
				for (int i =0; i < 5-array.length; i++) {
					sb.append(","+shi+""+(array.length+i+1));
				}
			}
			conn = DriverManager.getConnection(url, user, password);
			stat = conn.prepareStatement("select id from line where location=?");  
		    stat.setString(1,sb.toString()); 
			rs = stat.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return -1;
	}
	
	public static List<Integer> selectByLocation(List<StringBuffer> locations){
		Connection conn = null;
		PreparedStatement stat = null;  
		ResultSet rs = null;
		List<Integer> list =new ArrayList<>();
		try {
			// 数据不存在
			if (locations == null || locations.size()==0) {
				return null;
			}
			// 加载驱动
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, user, password);
			
			StringBuffer sql =new StringBuffer("select id from line where  ");
			for (StringBuffer stringBuffer : locations) {
				System.out.println(stringBuffer);
				String[] array =stringBuffer.toString().split(",");
				if(array.length!=5){
					int shi =Integer.parseInt(array[array.length-1])/10;
					for (int i =0; i < 5-array.length; i++) {
						stringBuffer.append(","+shi+""+(array.length+i+1));
					}
				}
				sql.append("  location = '"+stringBuffer+"'  or");
			}
			sql.deleteCharAt(sql.length()-1);
			sql.deleteCharAt(sql.length()-1);
			stat = conn.prepareStatement(sql.toString());  
			rs = stat.executeQuery();
			while (rs.next()) {
				list.add(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	public static void save(int id,String data) {
		Connection conn = null;
		PreparedStatement stat = null;  
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, user, password);
			stat = conn.prepareStatement("insert into line (id,location,gameCode) values (?,?,'SpringDay')");
			stat.setInt(1,id); 
		    stat.setString(2,data); 
			int rows = stat.executeUpdate();
			if (rows == 1) {
				System.out.println("插入成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static JSONArray getCategory(){
		Connection conn = null;
		PreparedStatement stat = null;
		PreparedStatement stat2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		JSONArray json =new JSONArray();
		try {
			// 加载驱动
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, user, password);
			
			StringBuffer sql1 =new StringBuffer("SELECT * FROM category");
			StringBuffer sql2 =new StringBuffer("select g.`gameCode`,gameName,categoryCode,sort from game g left join game_server gs on g.`gameCode`=gs.`gameCode` left join game_category_sort gcs on gcs.`gameCode`=g.`gameCode` where gs.`state`=1 and g.`state`=1 ");
			stat = conn.prepareStatement(sql1.toString());
			stat2= conn.prepareStatement(sql2.toString());
			rs = stat.executeQuery();
			rs2 =stat2.executeQuery();
			List<Item> cate =new ArrayList<>();
			while (rs.next()) {
				Item i =new Item();
				i.gameCode =rs.getString("categoryCode");
				i.gameName = rs.getString("categoryName");
				i.sort =rs.getInt("sort");
				cate.add(i);
			}
			List<Item> lis =new ArrayList<>();
			while (rs2.next()) {
				Item i =new Item();
				i.gameCode =rs2.getString("gameCode");
				i.gameName = rs2.getString("gameName");
				i.categoryCode = rs2.getString("categoryCode");
				i.sort =rs2.getInt("sort");
				lis.add(i);
			}
			for (Item item : cate) {
				JSONObject obj = new JSONObject();
				obj.put("categoryCode", item.gameCode);
				obj.put("categoryName", item.gameName);
				obj.put("sort", item.sort);
				JSONArray gameServers = new JSONArray();
				for (Item object : lis) {
					if(item.gameCode.equals(object.categoryCode)){
						JSONObject obb =new JSONObject();
						obb.put("gameCode", object.gameCode);
						obb.put("gameName", object.gameName);
						obb.put("sort", object.sort);
						gameServers.add(obb);
					}
				}
				obj.put("gameServers", gameServers);
				json.add(obj);

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return json;
	}
	
	

	public static void main(String[] args) {
		System.out.println(Util.getCategory());
	}
	
}
class Item{
	public String gameCode;
	public String gameName;
	public String categoryCode;
	public int sort;
}
