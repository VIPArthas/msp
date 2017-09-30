package com.wh.util.msp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wh.mspentity.User;
import com.wh.util.StringUtil;

public class JdbcTest {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/*
	 * 增加
	 */
	public void create() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			// 获取连接
			connection = JdbcUtils.getConnection();
			// 准备sql语句
			String sql = "INSERT INTO user(user_name,user_password,user_birth) VALUES(?,?,?)";
			// 获取PrepareStatement对象
			preparedStatement = connection.prepareStatement(sql);
			// 填充占位符
			preparedStatement.setString(1, "mary");
			preparedStatement.setString(2, "zhang123");

			String str1 = "1985-08-01";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = sdf.parse(str1);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(date);
			System.out.println(date.getTime());
			System.out.println(new java.sql.Date(date.getTime()));
			preparedStatement.setDate(3, new java.sql.Date(date.getTime()));
			// 执行sql
			int num = preparedStatement.executeUpdate();// 返回影响到的行数

			System.out.println("一共影响到" + num + "行");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseDB(connection, preparedStatement, null);
		}
	}

	/*
	 * 读取查询
	 */
	public void retrieve() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JdbcUtils.getConnection();
			String sql = "SELECT user_name,user_password,user_birth FROM user";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			// 遍历结果集
			while (resultSet.next()) {
				String username = resultSet.getString(1);
				String password = resultSet.getString(2);
				Date userbirth = resultSet.getDate(3);

				System.out.println(username + ":" + password + ":" + userbirth);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			JdbcUtils.releaseDB(connection, preparedStatement, resultSet);
		}

	}

	public List<User> getUserList() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<User> userList = null;
		try {
			connection = JdbcUtils.getConnection();
			String sql = "SELECT user_id,user_name,user_password,user_birth FROM user";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			userList = JdbcUtils.rsToClass(resultSet, User.class);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			JdbcUtils.releaseDB(connection, preparedStatement, resultSet);
		}

		return userList;

	}

	public List<Map<String, Object>> getUserList1() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Map<String, Object>> userList = null;
		try {
			connection = JdbcUtils.getConnection();
			String sql = "SELECT user_id,user_name,user_password,user_birth FROM user";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			userList = JdbcUtils.rsToMap(resultSet);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			JdbcUtils.releaseDB(connection, preparedStatement, resultSet);
		}

		return userList;

	}

	/**
	 * 学生缴费信息
	 * 
	 * @return
	 */
	public List<Map<String, Object>> xsjf(String xh) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Map<String, Object>> map = null;
		try {
			connection = JdbcUtils.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select sfqjmc,xh,sfxmmc,yjje,sjje,jmje,qfje from USR_DATAI.T_CW_XSJFXX t ");
			List<Object> params = new ArrayList<Object>();
			if (StringUtil.isNotEmpty(xh)) {
				sql.append("where t.xh = ? ");
				params.add(xh);
			}
			preparedStatement = connection.prepareStatement(sql.toString());
			setParameter(params, preparedStatement);
			resultSet = preparedStatement.executeQuery();
			map = JdbcUtils.rsToMap(resultSet);
		} catch (Exception e) {
		} finally {
			JdbcUtils.releaseDB(connection, preparedStatement, resultSet);
		}

		return map;

	}

	/**
	 * 图书借阅
	 * 
	 * @param xh
	 *            学号
	 * @param bookName
	 *            图书名称
	 * @param bookStatus
	 *            图书状态
	 * @return
	 */
	public List<Map<String, Object>> tsjy(String xh, String bookName, String bookStatus) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Map<String, Object>> map = null;
		try {
			connection = JdbcUtils.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select wid,sfrzh,tsmc,jsrq,yhrq,xhrq,jhbz from USR_DATAI.T_TS_JY ");
			List<Object> params = new ArrayList<Object>();
			if (StringUtil.isNotEmpty(xh)) {
				sql.append("where SFRZH = ? ");
				params.add(xh);
			}
			if (StringUtil.isNotEmpty(bookName)) {
				sql.append("and TSMC like ? ");
				params.add("%" + bookName + "%");
			}
			if (StringUtil.isNotEmpty(bookStatus)) {
				sql.append("and JHBZ = ? ");
				params.add(bookStatus);
			}
			sql.append("order by jsrq desc");
			log.info("图书借阅sql语句:{}", sql.toString());
			preparedStatement = connection.prepareStatement(sql.toString());
			setParameter(params, preparedStatement);
			resultSet = preparedStatement.executeQuery();
			map = JdbcUtils.rsToMap(resultSet);
		} catch (Exception e) {
		} finally {
			JdbcUtils.releaseDB(connection, preparedStatement, resultSet);
		}

		return map;

	}

	/**
	 * 学生课程安排
	 * 
	 * @param classId
	 *            班级代码
	 * @param kkxn
	 *            开学学年
	 * @param kkxq
	 *            开学学期
	 * @return
	 */
	public List<Map<String, Object>> kcapS(String classId, String kkxn, String kkxq) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Map<String, Object>> map = null;
		try {
			connection = JdbcUtils.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append(
					"SELECT T1.JCZ,T1.KCMC || '<br/>[学分:' || T1.XF || ']' || '[学时:' || T1.XS ||']</br>'|| T1.SKAP || '<br/>[性质:' || T3.MC || ']<br/>[讲师:' || T1.JSXM||']<br/>[地点:'||t4.jsmc||']</br>' as kcap ");
			sql.append(
					"FROM USR_DATAI.T_BZKS_KCAP T1,USR_ZXBZ.T_ZXBZ_KCLX  T2,USR_ZXBZ.T_ZXBZ_KSFS T3,usr_datai.t_js t4 ");
			List<Object> params = new ArrayList<Object>();
			// 班级代码
			if (StringUtil.isNotEmpty(classId)) {
				sql.append("WHERE T1.BJDM = ? ");
				params.add(classId);
			}
			// 开课学年
			if (StringUtil.isNotEmpty(kkxn)) {
				sql.append("AND T1.KKXN = ? ");
				params.add(kkxn);
			}
			if (StringUtil.isNotEmpty(kkxq)) {
				sql.append("AND T1.KKXQ = ? ");
				params.add(kkxq);
			}
			sql.append("AND T1.KSFSDM = T3.DM(+) AND T1.KCLBDM = T2.DM(+) AND t1.jsdm = t4.jsdm(+) ");
			log.info("学生课程安排sql语句:{}", sql.toString());
			preparedStatement = connection.prepareStatement(sql.toString());
			setParameter(params, preparedStatement);
			resultSet = preparedStatement.executeQuery();
			map = JdbcUtils.rsToMap(resultSet);
		} catch (Exception e) {
		} finally {
			JdbcUtils.releaseDB(connection, preparedStatement, resultSet);
		}

		return map;

	}

	/**
	 * 教职工课程安排
	 * 
	 * @param zgh
	 *            教职工号
	 * @param kkxn
	 *            开学学年
	 * @param kkxq
	 *            开学学期
	 * @return
	 */
	public List<Map<String, Object>> kcapT(String zgh, String kkxn, String kkxq) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Map<String, Object>> map = null;
		try {
			connection = JdbcUtils.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append(
					"SELECT T1.JCZ,T1.KCMC || '<br/>[班级:'||T6.BJMC||']</br>[学分:' || T1.XF || ']' || '[学时:' || T1.XS ||']</br>' || T1.SKAP || '<br/>[性质:' || T3.MC || ']</br>[讲师:' || T1.JSXM || ']<br/>[地点:' || T4.JSMC || ']</br>' AS KCAP ");
			sql.append(
					"FROM USR_DATAI.T_BZKS_KCAP T1,USR_ZXBZ.T_ZXBZ_KCLX  T2,USR_ZXBZ.T_ZXBZ_KSFS  T3,USR_DATAI.T_JS T4,USR_DATAI.T_JZG T5,USR_DATAI.T_BZKS_XZB t6 WHERE  T1.KSFSDM = T3.DM (+) ");
			sql.append("AND T1.KCLBDM = T2.DM (+) AND T1.JSDM = T4.JSDM(+) ");
			sql.append("AND T1.Bjdm = T6.BH (+) AND T1.Jsgh = T5.Zgh ");

			List<Object> params = new ArrayList<Object>();
			// 教职工号
			if (StringUtil.isNotEmpty(zgh)) {
				sql.append("AND T5.ZGH = ? ");
				params.add(zgh);
			}
			// 开课学年
			if (StringUtil.isNotEmpty(kkxn)) {
				sql.append("AND T1.KKXN = ? ");
				params.add(kkxn);
			}
			if (StringUtil.isNotEmpty(kkxq)) {
				sql.append("AND T1.KKXQ = ? ");
				params.add(kkxq);
			}

			log.info("教师课程安排sql语句:{}", sql.toString());
			preparedStatement = connection.prepareStatement(sql.toString());
			setParameter(params, preparedStatement);
			resultSet = preparedStatement.executeQuery();
			map = JdbcUtils.rsToMap(resultSet);
		} catch (Exception e) {
		} finally {
			JdbcUtils.releaseDB(connection, preparedStatement, resultSet);
		}

		return map;

	}

	/**
	 * 学生缴费合计
	 * 
	 * @param xh
	 * @return
	 */
	public Map<String, Object> xsjfTotal(String xh) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Map<String, Object> map = null;
		try {
			connection = JdbcUtils.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append(
					"select t.xh,sum(t.yjje) as yjje,sum(t.yjje)-sum(t.qfje) as sjje,sum(t.jmje) as jmje,sum(t.qfje) as qfje from USR_DATAI.T_CW_XSJFXX t ");
			List<Object> params = new ArrayList<Object>();
			if (StringUtil.isNotEmpty(xh)) {
				sql.append("where t.xh = ? group by t.xh");
				params.add(xh);
			}
			preparedStatement = connection.prepareStatement(sql.toString());
			setParameter(params, preparedStatement);
			resultSet = preparedStatement.executeQuery();
			map = JdbcUtils.rsToOneMap(resultSet);
		} catch (Exception e) {
		} finally {
			JdbcUtils.releaseDB(connection, preparedStatement, resultSet);
		}

		return map;

	}

	/**
	 * 学生成绩查询
	 * 
	 * @param xh
	 *            学号
	 * @param xn
	 *            学年
	 * @return
	 */
	public List<Map<String, Object>> cjcx(String xh, String xn) {
		ConnectionManager cm= ConnectionManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Map<String, Object>> map = null;
		try {
			connection = JdbcUtils.getConnection();
			connection = cm.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT T1.XH,T1.XN,T2.MC ,T3.KCMC,T1.KSCS,T1.BFZKSCJ,T1.DJZKSCJ,T1.SFTG,T1.XF,T1.JD,T1.XKKCH ");
			sql.append(
					"FROM USR_DATAI.T_BZKS_KSCJ T1,USR_ZXBZ.T_ZXBZ_XQ T2,USR_DATAI.T_BZKS_KC T3 WHERE T1.XQDM = T2.DM (+) AND T1.KCDM = T3.KCDM(+) ");
			List<Object> params = new ArrayList<Object>();
			if (StringUtil.isNotEmpty(xh)) {
				sql.append("AND T1.XH = ? ");
				params.add(xh);
			}			
			if (StringUtil.isNotEmpty(xn)) {
				sql.append("AND T1.XN = ? ");
				params.add(xn);	
			}	
			sql.append("ORDER BY T1.XN DESC, T1.XQDM DESC");
			log.info("图书借阅sql语句:{}", sql.toString());
			preparedStatement = connection.prepareStatement(sql.toString());
			setParameter(params, preparedStatement);
			resultSet = preparedStatement.executeQuery();
			map = JdbcUtils.rsToMap(resultSet);
		} catch (Exception e) {
		} finally {
			JdbcUtils.releaseDB(connection, preparedStatement, resultSet);
		}

		return map;

	}
	
	public List<Map<String, Object>> cjcx1(String xh, String xn) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Map<String, Object>> map = null;
		try {
			connection = JdbcUtils.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT T1.XH,T1.XN,T2.MC ,T3.KCMC,T1.KSCS,T1.BFZKSCJ,T1.DJZKSCJ,T1.SFTG,T1.XF,T1.JD,T1.XKKCH ");
			sql.append(
					"FROM USR_DATAI.T_BZKS_KSCJ T1,USR_ZXBZ.T_ZXBZ_XQ T2,USR_DATAI.T_BZKS_KC T3 WHERE T1.XQDM = T2.DM (+) AND T1.KCDM = T3.KCDM(+) ");
			List<Object> params = new ArrayList<Object>();
			if (StringUtil.isNotEmpty(xh)) {
				sql.append("AND T1.XH = ? ");
				params.add(xh);
			}			
			if (StringUtil.isNotEmpty(xn)) {
				sql.append("AND T1.XN = ? ");
				params.add(xn);	
			}	
			sql.append("ORDER BY T1.XN DESC, T1.XQDM DESC");
			log.info("图书借阅sql语句:{}", sql.toString());
			preparedStatement = connection.prepareStatement(sql.toString());
			setParameter(params, preparedStatement);
			resultSet = preparedStatement.executeQuery();
			map = JdbcUtils.rsToMap(resultSet);
		} catch (Exception e) {
		} finally {
			JdbcUtils.releaseDB(connection, preparedStatement, resultSet);
		}

		return map;

	}
	
	
	
	

	/*
	 * 修改更新
	 */
	public void update() {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JdbcUtils.getConnection();
			String sql = "UPDATE USER SET user_password = ? WHERE user_name = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "passwordupdate");
			preparedStatement.setString(2, "mary");
			int num = preparedStatement.executeUpdate();

			System.out.println("一共影响到" + num + "行");
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			JdbcUtils.releaseDB(connection, preparedStatement, null);
		}
	}

	/*
	 * 删除
	 */
	public void delete() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JdbcUtils.getConnection();
			String sql = "DELETE FROM user WHERE user_id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, 3);
			int num = preparedStatement.executeUpdate();

			System.out.println("一共影响到" + num + "行");
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			JdbcUtils.releaseDB(connection, preparedStatement, null);
		}

	}

	public void setParameter(List<Object> params, PreparedStatement preparedStatement) throws SQLException {
		for (int i = 0; i < params.size(); i++) {
			Object p = params.get(i);
			if (p instanceof Integer) {
				preparedStatement.setInt(i + 1, (Integer) p);
			} else if (p instanceof String) {
				preparedStatement.setString(i + 1, (String) p);
			}
		}
	}

	/*
	 * public void queryPaging(Paging<Dealer> paging,String userName,String
	 * telephone,String beginTime,String endTime){ try { connection =
	 * DbFactory.getConnection(); List<String> params = new ArrayList<String>();
	 * //查询总记录数 StringBuffer countSql = new StringBuffer(
	 * "select ifnull(count(*),0) from weixin_dealer where 1=1 "); //查询记录
	 * StringBuffer querySql =new StringBuffer(
	 * "select id,user_name,telephone,add_time from weixin_dealer where 1=1 ");
	 * 
	 * if(StringUtils.isNotBlank(userName)){ countSql.append(
	 * " and user_name like ? "); querySql.append(" and user_name like ? ");
	 * params.add("%"+userName.trim()+"%"); }
	 * if(StringUtils.isNotBlank(telephone)){ countSql.append(
	 * " and telephone=? "); querySql.append(" and telephone=? ");
	 * params.add(telephone.trim()); } if(StringUtils.isNotBlank(beginTime)){
	 * countSql.append(" and add_time >= STR_TO_DATE(?,'%Y-%m-%d') ");
	 * querySql.append(" and add_time >= STR_TO_DATE(?,'%Y-%m-%d') ");
	 * params.add(beginTime); } if(StringUtils.isNotBlank(endTime)){
	 * countSql.append(" and add_time <= STR_TO_DATE(?,'%Y-%m-%d') ");
	 * querySql.append(" and add_time <= STR_TO_DATE(?,'%Y-%m-%d') ");
	 * params.add(endTime); } logger.info("分页查询,总记录 sql,countSql="
	 * +countSql.toString()); preparedStatement =
	 * connection.prepareStatement(countSql.toString()); for(int
	 * i=0;i<params.size();i++){ preparedStatement.setString(i+1,
	 * params.get(i)); }
	 * 
	 * resultSet = preparedStatement.executeQuery(); int count = 0;
	 * while(resultSet.next()){ count = resultSet.getInt(1); }
	 * paging.setTotalCount(count);
	 * DbFactory.close(resultSet,preparedStatement);
	 * 
	 * querySql.append(" order by add_time desc limit ?,? "); logger.info(
	 * "分页查询,查询 sql,querySql="+querySql.toString());
	 * 
	 * preparedStatement = connection.prepareStatement(querySql.toString()); int
	 * j=1; for(String param : params){ preparedStatement.setString(j,param);
	 * j++; } preparedStatement.setInt(j,paging.getFirst());
	 * preparedStatement.setInt(j+1, paging.getPageSize());
	 * 
	 * resultSet = preparedStatement.executeQuery(); List<Dealer> list = new
	 * ArrayList<Dealer>(); Dealer dealer = null; while(resultSet.next()){
	 * dealer = new Dealer(); dealer.setId(resultSet.getInt("id"));
	 * dealer.setUserName(resultSet.getString("user_name"));
	 * dealer.setTelephone(resultSet.getString("telephone"));
	 * dealer.setAddTime(resultSet.getTimestamp("add_time"));
	 * 
	 * list.add(dealer); } paging.setResultList(list); } catch (Exception e) {
	 * logger.error("分页查询出错.....",e); }finally{
	 * DbFactory.close(resultSet,preparedStatement,connection); } }
	 */

}