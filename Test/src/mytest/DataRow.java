package mytest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**  
 *   
 * 项目名称：EGameHallServer  
 * 类名称：DataRow  
 * 类描述：  数据库数据处理
 * @version   
 *   
 */
public class DataRow {

	/**
	 * 以map这种方式来存储值
	 */
	private HashMap<String, String> hm;
	/**
	 * 以list这种方式来存储值
	 */
	private ArrayList<String> al;

	public DataRow() {
		al = new ArrayList<String>();
		hm = new HashMap<String, String>();
	}

	/**
	 * 添加一项新纪录
	 * 
	 * @param s
	 *            纪录值
	 */
	public void addItem(String s) {
		if (al != null) {
			al.add(s);
		}
	}

	/**
	 * 添加一项新纪录
	 * 
	 * @param key
	 *            列名
	 * @param s
	 *            纪录值
	 */
	public void addItem(String key, String s) {
		if (hm != null) {
			hm.put(key.trim().toLowerCase(), s);
		}
		addItem(s);

	}

	/**
	 * 从列表里面获取一个值，从0开始
	 * 
	 * @param index
	 *            索引
	 * @return 该列的值
	 */
	public String getItem(int index) {
		String s = null;
		try {
			if (al != null)
				s = al.get(index);
		} catch (IndexOutOfBoundsException indexoutofboundsexception) {
			indexoutofboundsexception.printStackTrace();
		}
		return s;
	}

	/**
	 * 获取boolean值的数据
	 * 
	 * @param index
	 *            索引
	 * @return boolean值的数据
	 */
	public Boolean getBooleanItem(int index) {
		String value = getItem(index);
		if (value == null) {
			return null;
		} else {
			if (value.equals("1") || value.equals("true")) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * 获取int值的数据
	 * 
	 * @param index
	 * @return int值的数据
	 */
	public Integer getIntItem(int index) {
		if (al.get(index) == null) {
			return null;
		} else {
			try {
				if (al != null) {
					return Integer.parseInt(al.get(index));
				} else {
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

	}

	/**
	 * 获double值的数据
	 * 
	 * @param index
	 * @return double值的数据
	 */
	public Double getDoubleItem(int index) {
		if (al.get(index) == null) {
			return null;
		} else {
			try {
				if (al != null) {
					return Double.parseDouble(al.get(index));
				} else {
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	/**
	 * 获float值的数据
	 * 
	 * @param index
	 * @return float值的数据
	 */
	public Float getFloatItem(int index) {
		if (al.get(index) == null) {
			return null;
		} else {
			try {
				if (al != null) {
					return Float.parseFloat(al.get(index));
				} else {
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	/**
	 * 根据列名返回数据
	 * 
	 * @param key
	 *            列名
	 * @return 以字符串方式返回
	 */
	public String getItem(String key) {
		if (hm != null)
			return (String) hm.get(key.trim().toLowerCase());
		else
			return null;
	}

	/**
	 * 返回boolean值的数据
	 * 
	 * @param key
	 *            列名
	 * @return boolean值的数据
	 */
	public Boolean getBooleanItem(String key) {
		String value = getItem(key.trim().toLowerCase());
		if (value == null) {
			return null;
		} else {
			value = value.trim();
			if (value.equals("1") || value.equals("true")) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * 返回int值的数据
	 * 
	 * @param key
	 *            列名
	 * @return int值的数据
	 */
	public Integer getIntItem(String key) {

		String value = getItem(key.trim().toLowerCase());
		if (value == null) {
			return null;
		} else {
			value = value.trim();
			try {
				return Integer.parseInt(value);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	/**
	 * 返回long值的数据
	 * @param key  列名
	 * @return long值的数据
	 */
	public Long getLongItem(String key) {
		String value = getItem(key.trim().toLowerCase());
		if (value == null) {
			return null;
		} else {
			value = value.trim();
			try {
				return Long.parseLong(value);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
	/**
	 * 返回double值的数据
	 * 
	 * @param key
	 *            列名
	 * @return double值的数据
	 */
	public Double getDoubleItem(String key) {
		String value = getItem(key.trim().toLowerCase());
		if (value == null) {
			return null;
		} else {
			value = value.trim();
			try {
				return Double.parseDouble(value);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	/**
	 * 返回float值的数据
	 * 
	 * @param key
	 *            列名
	 * @return float值的数据
	 */
	public Float getFloatItem(String key) {
		String value = getItem(key.trim().toLowerCase());
		if (value == null) {
			return null;
		} else {
			value = value.trim();
			try {
				return Float.parseFloat(value);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	/**
	 * 以map方式放回一行的数据
	 * 
	 * @return 列名对应键值
	 */
	public Map<String, String> getDataAsMap() {
		return hm;
	}

	/**
	 * 以list的方式返回一行的数据
	 * 
	 * @return 以顺序机构返回
	 */
	public List<String> getDataAsList() {
		return al;
	}

	/**
	 * 打印DB返回值
	 * @return
	 */
	public String getDataRowValue(){
		return hm.toString();
	}
}
