package proto;

import java.util.List;

import com.google.protobuf.InvalidProtocolBufferException;

import sample.demo.ProtoDemo;
import sample.demo.ProtoDemo.Student;
import sample.demo.ProtoDemo.Student.PhoneNumber;

public class Test {

	/**
	 * protobuf是一种数据交换的格式，以二进制的格式进行数据交换，主要用于网络传输、配置文件、数据存储等诸多领域
	 * 
	 * 下面我们就将指定格式的信息转换成字节形式数据，然后将字节形式数据恢复成指定格式的信息
	 * 读者可以简单的看下.proto文件生成的ProtoDemo类的结构
	 */
	public static void main(String[] args) {
	
		//这里你我们将封装有数据的对象实例，转换为字节数组，用于数据传输、存储等
		byte[] stuByte =getByte();
		//这里得到了stuBte字节数组后，我们可以将该数据进行数据传输或存储，这里至于用什么技术传输就根据具体情况而定
		//假如这里stuByt通过传输，下面的代码接到了该数据
		printByte(stuByte);
		
	}
	
	public static byte[] getByte(){
		//获得Student对象
		//这里的Student对象构造器被私有化，我们通过通过Student的内部类Builder来构建builder
		ProtoDemo.Student.Builder builder =  ProtoDemo.Student.newBuilder();
		//Student类未提供相关属性的set方法，而Student的内部类builder提供了构建Student相关属性的set方法
		builder.setId(1);
		builder.setName("凌晨1点21分");
		builder.setEmail("2474600377@qq.com");
		builder.setSex(ProtoDemo.Student.Sex.MAN);
		//相同方法获取PhoneNumber对象
		ProtoDemo.Student.PhoneNumber.Builder phb = ProtoDemo.Student.PhoneNumber.newBuilder();
		phb.setNumber("123456789");
		phb.setType(ProtoDemo.Student.PhoneType.HOME);
		PhoneNumber pn = phb.build();
		builder.addPhone(pn);
		//这里再获取了一个PhoneNumber对象，原理一样
		PhoneNumber pn2 = ProtoDemo.Student.PhoneNumber.newBuilder()
				.setNumber("789456123").setType(ProtoDemo.Student.PhoneType.MOBILE).build();
		builder.addPhone(pn2);
		
		Student stu = builder.build();
		//这里你我们将封装有数据的对象实例，转换为字节数组，用于数据传输、存储等
		byte[] stuByte = stu.toByteArray();
		return stuByte;
	}

	public static void printByte(byte[] stuByte){

		//接收方 ,这里为了方便我们就写在一个类里面
		try {
			//将字节数据转换为对应的对象实例
			Student stu2 = ProtoDemo.Student.parseFrom(stuByte);
			//这里得到了Student实例了，就可以根据需要来操作里面的数据了
			System.out.println("学生ID:"+stu2.getId());
			System.out.println("姓名："+stu2.getName());
			System.out.println("性别："+(stu2.getSex().getNumber()==0?"男":"女"));
			System.out.println("邮箱："+stu2.getEmail());
			//这里phoneNumber字段
			List<PhoneNumber> phList = stu2.getPhoneList();
			for (PhoneNumber p : phList) {
				System.out.println(p.getType().toString()+"电话:"+p.getNumber());
			}
			
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
