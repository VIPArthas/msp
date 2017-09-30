package com.wh.util.msp;

//数据库连接池  单例模式

import java.sql.Connection;
import java.sql.SQLException;

import com.wh.util.base.ConfigManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
/**
采用 高性能数据库连接池  HikariCP 数据源
*
*/
public final class ConnectionManager {
  private static ConnectionManager instance;
  private HikariDataSource dsRead;
  private HikariDataSource dsWrite;
  private ConnectionManager() throws Exception {
/*   	HikariConfig config = new HikariConfig();
   	config.setMaximumPoolSize(25);
   	config.setMinimumIdle(5);
   	config.addDataSourceProperty("prepStmtCacheSize", "250");
   	config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
   	config.setConnectionTestQuery("SELECT 1");
   	config.setDataSourceClassName(DataUtil.getProperties().getProperty(prefix+"dataSourceClassName"));
   	config.addDataSourceProperty("serverName", DataUtil.getProperties().getProperty(prefix+"readServerName"));
   	config.addDataSourceProperty("port",DataUtil.getProperties().getProperty(prefix+"readPortNumber"));
   	config.addDataSourceProperty("databaseName",DataUtil.getProperties().getProperty(prefix+"readDatabaseName"));
   	config.addDataSourceProperty("user",DataUtil.getProperties().getProperty(prefix+"readUser"));
   	config.addDataSourceProperty("password",DataUtil.getProperties().getProperty(prefix+"readPassword"));
   	//增加以下两行代码
   	config.addDataSourceProperty("useUnicode", "true");
  // 	config.addDataSourceProperty("characterEncoding", "utf8");
   	dsRead = new HikariDataSource(config);*/
   	/**
   	 * 增加两个数据源配置 写入的
   	 */
   	HikariConfig	config1 = new HikariConfig();
   	config1.setMaximumPoolSize(25);
   	config1.setMinimumIdle(5);
   	config1.addDataSourceProperty("prepStmtCacheSize", "250");
   	config1.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
   	config1.setJdbcUrl((String) ConfigManager.getInstance("db.properties").getConfigItem("jdbcUrl2"));
   	config1.setUsername((String) ConfigManager.getInstance("db.properties").getConfigItem("user2"));
   	config1.setPassword((String) ConfigManager.getInstance("db.properties").getConfigItem("password2"));
   	config1.setDriverClassName((String) ConfigManager.getInstance("db.properties").getConfigItem("driverClass2"));
   	dsWrite = new HikariDataSource(config1);
  }

  
  /**
   * 进行线程同步
   * 需要在创建类的时候进行同步，所以只要将创建和getInstance()分开，单独为创建加synchronized关键字
   */
  private static synchronized void syncInit() {  
      if (instance == null) {  
      	 try {
               instance = new ConnectionManager();
           } catch (Exception e) {
               e.printStackTrace();
           } 
      }  
  }  
  
  
  public  static  final ConnectionManager getInstance() {
      if (instance == null) {
      	syncInit(); 
      }
      return instance;
  }

  public   final Connection getConnection(String ...mes) {
  	Connection con = null;
      try {
      	
//      	if(mes.length>=1&&mes[0].equals("R"))
//				con=  dsRead.getConnection();
//      	else if(mes.length>=1&&mes[0].equals("W"))
//				con=  dsWrite.getConnection();
//      	else 
      	con = dsWrite.getConnection();
      } catch (SQLException e) {
          e.printStackTrace();
      }
      return con;
  }

  protected void finalize() throws Throwable {
  	dsRead.close();//关闭datasource
  	dsWrite.close();
  }

}
