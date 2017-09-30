package com.wh.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;

/**
 * @Title: PaginationInterceptor.java
 * @Description: 利用myBatis物理分页
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PaginationInterceptor implements Interceptor {
	public Logger logger = Logger.getLogger(this.getClass());
	private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
	private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
	private static String DEFAULT_PAGE_SQL_ID = ".*ListPage$"; // 需要拦截的ID(正则匹配)
	private static String DEFAULT_PAGE_MOBILE_SQL_ID = ".*MobileListPage$"; // 需要拦截的ID(正则匹配)
	public static final ThreadLocal<BaseModel> localPage = new ThreadLocal<BaseModel>();

	/**
	 * 开始分页
	 * 
	 * @param pageNum
	 * @param pageSize
	 */
	public static void startPage(int currentpage, int rscount) {
		BaseModel page = new BaseModel();
		page.setCurrentpage(currentpage);
		page.setRscount(rscount);
		localPage.set(page);
	}

	/**
	 * 结束分页并返回结果，该方法必须被调用，否则localPage会一直保存下去，直到下一次startPage
	 * 
	 * @return
	 */
	public static BaseModel endPage() {
		BaseModel page = localPage.get();
		localPage.remove();
		return page;
	}

	@SuppressWarnings("incomplete-switch")
    @Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY,
				DEFAULT_OBJECT_WRAPPER_FACTORY);
		RowBounds rowBounds = (RowBounds) metaStatementHandler.getValue("delegate.rowBounds");
		// 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环可以分离出最原始的的目标类)
		while (metaStatementHandler.hasGetter("h")) {
			Object object = metaStatementHandler.getValue("h");
			metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
		}
		// 分离最后一个代理对象的目标类
		while (metaStatementHandler.hasGetter("target")) {
			Object object = metaStatementHandler.getValue("target");
			metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
		}

		// property在mybatis settings文件内配置
		Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");

		// 设置pageSqlId
		String pageSqlId = configuration.getVariables().getProperty("pageSqlId");
		if (null == pageSqlId || "".equals(pageSqlId)) {
			logger.warn("Property pageSqlId is not setted,use default '.*Page$' ");
			pageSqlId = DEFAULT_PAGE_SQL_ID;
		}
		Dialect.Type databaseType = Dialect.Type
				.valueOf(configuration.getVariables().getProperty("dialect").toUpperCase());
		Dialect dialect = null;
		if (databaseType == null || "".equals(pageSqlId)) {
			logger.debug("没有在SqlMapConfig.xml配置文件中设置数据库类型: " + configuration.getVariables().getProperty("dialect"));
		}
		if (null == databaseType || "".equals(databaseType)) {
			dialect = new MysqlDialect();
		} else {
            switch (databaseType) {
			case MYSQL:
				dialect = new MysqlDialect();
			}
		}

		MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
		// 只重写需要分页的sql语句。通过MappedStatement的ID匹配，默认重写以Page结尾的MappedStatement的sql
		if (mappedStatement.getId().matches(pageSqlId)) {
			BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");

			// 通过查询Sql语句获取到对应的计算总记录数的sql语句
			Object parameterObject = boundSql.getParameterObject();

			if (parameterObject == null) {
				throw new NullPointerException("parameterObject is null!");
			} else {
				String sql = boundSql.getSql();
				
				String countSql = null;
				if(!mappedStatement.getId().matches(DEFAULT_PAGE_MOBILE_SQL_ID)){//如果是手机端查询不在查询总条数，避免浪费时间
					countSql = dialect.getLimitTotal(sql);
				}
				
				Connection connection = (Connection) invocation.getArgs()[0];
				BaseModel page = localPage.get();
				if (page != null) {
					if(!mappedStatement.getId().matches(DEFAULT_PAGE_MOBILE_SQL_ID)){//如果是手机端查询不在查询总条数，避免浪费时间
						setPageParameter(countSql, connection, mappedStatement, boundSql, page);
					}
					metaStatementHandler.setValue("delegate.boundSql.sql",
							dialect.getLimitString(sql, rowBounds.getOffset(), rowBounds.getLimit()));

					// 重写sql
					// 采用物理分页后，就不需要mybatis的内存分页了，所以重置下面的两个参数
					metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
					metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
				}
			}
		}
		// 将执行权交给下一个拦截器
		return invocation.proceed();
	}

	/**
	 * @Description: 重设分页参数里的总页数
	 * @param
	 */
	private int setPageParameter(String countSql, Connection connection, MappedStatement mappedStatement,
			BoundSql boundSql, BaseModel page) {
		PreparedStatement countStmt = null;
		ResultSet rs = null;
		try {
			countStmt = connection.prepareStatement(countSql);
			BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql,
					boundSql.getParameterMappings(), boundSql.getParameterObject());
			Field metaParamsField = ReflectUtil.getFieldByFieldName(boundSql, "metaParameters");
			if (metaParamsField != null) {
				MetaObject mo = (MetaObject) ReflectUtil.getValueByFieldName(boundSql, "metaParameters");
				ReflectUtil.setValueByFieldName(countBS, "metaParameters", mo);
			}
			setParameters(countStmt, mappedStatement, countBS, boundSql.getParameterObject());
			rs = countStmt.executeQuery();
			int totalCount = 0;
			if (rs.next()) {
				totalCount = rs.getInt(1);
				page.setRscount(totalCount);
			}
			return totalCount;
		} catch (SQLException e) {
			logger.error("Ignore this exception", e);
		} catch (IllegalAccessException e) {
			logger.error("Ignore this IllegalAccessException", e);
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			logger.error("Ignore this NoSuchFieldException", e);
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.error("Ignore this exception", e);
			}
			try {
				countStmt.close();
			} catch (SQLException e) {
				logger.error("Ignore this exception", e);
			}
		}
		return 0;
	}

	/**
	 * @Description: 对SQL参数(?)设值
	 * @param
	 */
	private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql,
			Object parameterObject) throws SQLException {
		ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
		parameterHandler.setParameters(ps);
	}

	@Override
	public Object plugin(Object target) {
		// 当目标类是StatementHandler类型时，才包装目标类，否者直接返回目标本身,减少目标被代理的次数
		if (target instanceof StatementHandler) {
			return Plugin.wrap(target, this);
		} else {
			return target;
		}
	}

	@Override
	public void setProperties(Properties arg0) {

	}

}
