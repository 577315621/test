package proto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializeDemo {

	//测试入口
    public static void main(String[] args) throws Exception{
        Student student=new Student();
        student.setId("1");
        student.setName("王老三");
        String serialStr=serializeToString(student);
        System.out.println(serialStr);
        Student deserialStudent=(Student) deserializeToObject(serialStr);
        System.out.println(deserialStudent.getName());//输出王老三
    }
    //序列化
    public static String serializeToString(Object obj) throws Exception{
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();  
        ObjectOutputStream objOut = new ObjectOutputStream(byteOut);  
        objOut.writeObject(obj);  
        String str = byteOut.toString("ISO-8859-1");//此处只能是ISO-8859-1,但是不会影响中文使用
        return str;
    }
    //反序列化
    public static Object deserializeToObject(String str) throws Exception{
         ByteArrayInputStream byteIn = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));  
         ObjectInputStream objIn = new ObjectInputStream(byteIn);  
         Object obj =objIn.readObject();  
         return obj;  
    }
  
}
//Student类用于演示对象的序列化
class Student implements Serializable{//Student类通过实现Serializable接口拥有了序列化的能力
   private String id;
   private String name;
   public String getId() {
       return id;
   }
   public void setId(String id) {
       this.id = id;
   }
   public String getName() {
       return name;
   }
   public void setName(String name) {
       this.name = name;
   }
}
