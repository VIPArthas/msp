package com.wh.util.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.google.common.base.Strings;

public class ConfigManager {
	private final static Log log = Log.getLogger(ConfigManager.class);

	private static Map<String,ConfigManager> configMap = new HashMap<String,ConfigManager>();
	public static String commonsFile = "common-config.properties";
	private static String[] fileNames = new String[]{commonsFile};
	private File file = null;
	private long lastModifiedTime = 0;
	private Properties property = null;
	private String fileName = null;

	private ConfigManager(String fileName){
		this.fileName = fileName;
		
		// you need to use when debug.
//		file = new File(System.getProperty("user.dir")+"/bin/"+fileName);
		
		// you need to use when it in web context.
		URL url = ConfigManager.class.getClassLoader().getResource(fileName);
		file = new File(url.getPath());
		lastModifiedTime = file.lastModified();
		if(lastModifiedTime == 0){
			log.error("[ConfigManager.construct] file does not exit,filepath: {0};",fileName);
		}
		property = new Properties();
		try {
			property.load(new FileInputStream(file));
		} catch (Exception e) {
			log.error("[ConfigManager.construct] error info : {0};" ,e.getMessage());
			e.printStackTrace();
		}
	}
	
	//initialize config file.
	static {
		for(String fileName : fileNames){
			ConfigManager cm = new ConfigManager(fileName);
			configMap.put(fileName, cm);
		}
	}
	
	public synchronized  static ConfigManager getInstance(){
		return getInstance(commonsFile);
	}
	
	//get a file 
	public synchronized  static ConfigManager getInstance(String fileName){
		if(configMap.containsKey(fileName)){
			return configMap.get(fileName);
		}else{
			ConfigManager cm = new ConfigManager(fileName);
			configMap.put(fileName, cm);
			return configMap.get(fileName);
		}
	}
	
	// get value from input key. if key is not exists then return null.
	public Object getConfigItem(String key,String defaultValue){
		Long newTime = this.file.lastModified();
		if(newTime == 0){
			log.error("file is not exists");
		}else if(newTime > this.lastModifiedTime){
			this.property.clear();
			try {
				// you need to use when debug.
//				file = new File(System.getProperty("user.dir")+"/bin/"+fileName);
//				this.property.load(new FileInputStream(file));
				
				URL url = ConfigManager.class.getClassLoader().getResource(fileName);
				this.property.load(new FileInputStream(url.getPath()));
			} catch (Exception e) {
				log.error(e);
			}
		}
		this.lastModifiedTime = newTime;
		Object obj = this.property.get(key);
		if(obj == null){
			return defaultValue;
		}else{
			return obj;
		}
	}
	
	//set properties value.
	public void putConfigItem(String key,String value){
		if(Strings.isNullOrEmpty(value)){
			return;
		}
		this.property.setProperty(key, value);
		try {
			this.property.store(new FileOutputStream(file), null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// get value from input key. if key is not exists then return null.
	public Object getConfigItem(String key){
		Long newTime = this.file.lastModified();
		if(newTime == 0){
			log.error("file is not exists");
		}else if(newTime > this.lastModifiedTime){
			this.property.clear();
			try {
				// you need to use when debug.
//				file = new File(System.getProperty("user.dir")+"/bin/"+fileName);
//				thSis.property.load(new FileInputStream(file));
				
				URL url = ConfigManager.class.getClassLoader().getResource(fileName);
				this.property.load(new FileInputStream(url.getPath()));
			} catch (Exception e) {
				log.error(e);
			}
		}
		this.lastModifiedTime = newTime;
		Object obj = this.property.get(key);
		if(obj == null){
			return null;
		}else{
			return obj;
		}
	}
}
