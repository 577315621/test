package mytest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListTest {

	
	
	public static <T extends Comparable<T>> boolean compare(List<T> a, List<T> b) {
		  if(a.size() != b.size())
		    return false;
		  Collections.sort(a);
		  Collections.sort(b);
		  for(int i=0;i<a.size();i++){
		    if(!a.get(i).equals(b.get(i)))
		      return false;
		  }
		  return true;
		}
		//测试方法如下：
		public static void main(String[] args) {
		  List<String> a = Arrays.asList("112","212","312","412");
		  List<String> b = Arrays.asList("412","312","212","112");
		  System.out.println(compare(a, b));
		}
}
