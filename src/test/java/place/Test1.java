package place;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.wh.mspentity.User;
import com.wh.util.DateUtil;

public class Test1 {
	public static void main(String[] args) throws Exception {
		/*Class clazz = User.class;
		Date date =new Date();
		long a=1L;
		User u = new User(1,"xiaomign","12e1e",date);
		
		for (Field f : clazz.getDeclaredFields()) {
			System.out.println(f.isAccessible());// 这里的结果是false
			f.setAccessible(true);
			System.out.println(f.getName() + ":" + f.get(u));
		}*/
		
		
		
	/*	Class ca= User.class;

		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		Date date=sdf.parse("1985-11-16");
		
		System.out.println(date);
		
		User user =new User(10,"fadfas","fasdf1111",date);

		Field[] fields=ca.getDeclaredFields();
		for (Field field : fields) {
			String fn=field.getName();
			boolean flag=field.isAccessible();
			System.out.println(flag);// 这里的结果是false
			field.setAccessible(true);
			System.out.println("属性名:"+fn+"       属性值:"+field.get(user));
			field.setAccessible(flag);
		}*/
		
		
		Date date =new Date();
		String kkxn=new SimpleDateFormat("yyyy").format(date);
		System.out.println(kkxn);
	}

}
