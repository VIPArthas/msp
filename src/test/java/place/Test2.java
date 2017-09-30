package place;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.wh.util.PropertiesUtil;
import com.wh.util.base.ConfigManager;

public class Test2 {

	public static void main(String[] args) {
		Date date =new Date();
		String kkxn=new SimpleDateFormat("yyyy").format(date);
	/*	String kkxq="";
		try {		
			Date defaultDate =new SimpleDateFormat("yyyy-MM-dd").parse(kkxn+"-08-15");
			if (date.before(defaultDate)) {
				kkxq="1";
			}else{
				kkxq="0";
			}
			System.out.println(kkxq);
		} catch (ParseException e) {			
			e.printStackTrace();
		}*/
		
		String driverClass2=(String) ConfigManager.getInstance("db.properties").getConfigItem("driverClass2");
		//String driverClass2=PropertiesUtil.getPropertie("db.properties","driverClass2");
		System.out.println(driverClass2);
	}

}
