package com.wh.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

/**
 *@description		 读取配置文件工具类
 *@author            liz
 *@created           2013-11-16
 *@version           1.0
 *
 */

public class ConfigUtil
{
	 @SuppressWarnings("rawtypes")
	private static HashMap messages = new HashMap();
	 private static Properties props = new Properties();
	 
	/**
	 * @param args
	 */
	static  
	{
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        
		InputStream is = null;
        is = classLoader.getResourceAsStream("config.properties");
        if (is != null) 
        {
            try
			{
				props.load(is);
			}
			catch (IOException e)
			{				
				e.printStackTrace();
			}
			finally
			{
				try
				{
					is.close();
				}
				catch (IOException e)
				{					
					e.printStackTrace();
				}
			}
        }
        
	}
	
	@SuppressWarnings("unchecked")
	public static String getProperty(String key)
	{
		String msg = (String)messages.get(key);
		if (msg == null)
		{
			String pros = props.getProperty(key);
			if( pros != null)
			{
				messages.put(key,pros);
			}
						
			msg = pros;
		}
		
		return msg;
	}

}
