package com.wh.constants;

import org.apache.commons.lang.ArrayUtils;

import com.wh.util.PropertiesUtil;

/**
 * 常量类
 * 
 * @author Administrator
 *
 */
public class Constants {
	
	
	 public static final Integer statusZero = 0;// 待定
	 public static final Integer statusOne = 1;// 删除
	 public static final Integer statusTwo = 2;// 已提交
	 public static final Integer statusThree = 3;// 暂存
	 
	 public static final Integer statusFour =4;// 已返工
	 
	 public static final Integer count = 6;//透照张数
    // 密码
    public static final String passwordStr = "123456";
    
    public static final String platformId = "1";

    // 校联网后台管理员用户已登录标志
    public static final String manage_session_user_info = "manage_session_user_info";
    public static final String session_user_info = "session_user_info";

    // 根据角色定义菜单常量
    public static final String role_menus = "role_menus";// 一级菜单，父id为null
    // type为1，且父id不为null的二级菜单

    public static final String role_second_menus = "role_second_menus";

    public static final String user_resource_menus_button = "user_resource_menus_button";// 用户资源功能菜单

    //微信自动回复微信号（多个时以|作为分割符）
    public static final String AUTOREPLY_APPCOUNTS = "";

    /**
     * 登录超时KEY，ajax登录超时时使用
     */
    public final static String Ajax_login_Time_Out_Key = "ajax_login_time_out";

    // 用户状态
    public static final Integer statusWaiting = 1;// 待定

    public static final Integer statusNormal = 2;// 正常

    // 树形结构中部门type为1，人员type为2
    public static final String deptType = "1";

    public static final String userType = "2";

    public static final String serviceType = "3";

    // 节点图标
    public static final String deptTree = "deptTree";// 部门节点图标

    public static final String userTree = "userTree";// 用户节点图标

    public static final String roleTree = "roleTree";// 角色节点图标

    public static final String menuTree = "menuTree";// 菜单节点图标

    public static final String operateTree = "operateTree";// 功能操作节点图标，例如：新增删除

    public static final String postTree = "postTree";// 岗位节点图标

    //微门户批量导入用户模板
    public static final String wmhUserExcelTemplet = "/resource/upload/templet/wmhUser.xls";
    
    // 单页显示数据条数
    public static final Integer pageSize = 10;
    //校联微门户后台用户管理列表单页显示条数
    public static final Integer wmhPageSize = 10;
    // 全网兼职中单页显示条数
    public static final Integer newPageSize = 30;
    //运营统计的后台单页显示条数
    public static final Integer yytgPageSize = 100;
    /**终端编码web*/
    public static final String terminalCodeWeb = "web";
    /**终端编码wx*/
    public static final String terminalCodeWx = "wx";
    /**终端编码wap*/
    public static final String terminalCodeWap = "wap";
    /**终端编码andriod*/
    public static final String terminalCodeAndriod = "andriod";
    /**终端编码ios*/
    public static final String terminalCodeIos = "ios";
    /**终端编码web对应的值 0*/
    public static final Integer terminalWeb = 0;
    /**终端编码wx对应的值 1*/
    public static final Integer terminalWx = 1;
    /**终端编码wap对应的值 2*/
    public static final Integer terminalWap = 2;
    /**终端编码andriod对应的值 3*/
    public static final Integer terminalAndriod = 3;
    /**终端编码ios对应的值 4*/
    public static final Integer terminalIos = 4;
    
    /**行为操作级别-基本操作 1*/
    public static final Integer logTypeBase = 1;
    /**行为操作级别-普通操作 2*/
    public static final Integer logTypeNormal = 2;
    /**行为操作级别-重要操作 3*/
    public static final Integer logTypeImportant = 3;
    /**行为操作级别-极重要操作 4*/
    public static final Integer logTypeVeryImportant = 4;
    
    /**行为操作级别-基本操作对应的积分值 1*/
    public static final Integer logTypeBaseScore = 1;
    /**行为操作级别-普通操作对应的积分值 50*/
    public static final Integer logTypeNormalScore = 50;
    /**行为操作级别-重要操作对应的积分值 200*/
    public static final Integer logTypeImportantScore = 200;
    /**行为操作级别-极重要操作对应的积分值 500*/
    public static final Integer logTypeVeryImportantScore = 500;
    
    /**行为操作级别-基本操作对应的积分值限制 300*/
    public static final Integer logTypeBaseScoreLimit = 300;
    /**行为操作级别-普通操作对应的积分值限制 500*/
    public static final Integer logTypeNormalScoreLimit = 500;
    /**行为操作级别-重要操作对应的积分值限制 2000*/
    public static final Integer logTypeImportantScoreLimit = 2000;
    /**行为操作级别-极重要操作对应的积分值限制 5000*/
    public static final Integer logTypeVeryImportantScoreLimit = 5000;
    
    /**行为操作级别-基本操作对应的积分值到积分的兑换基数 6000 6秒钟1积分*/
    public static final Integer logTypeBaseScoreRadix = 6000;
    
//    /**配置文件路径：WEB-INF/classes/db.properties*/
//    public static final String ACCOUNT_PROPERTIES_PATH = "WEB-INF/classes/db.properties";
//    /**配置文件路径：WEB-INF/classes/messageTemplate.properties*/
//    public static final String MESSAGE_PATH = "WEB-INF/classes/messageTemplate.properties";
    /**配置文件路径：WEB-INF/classes/config.properties*/
    public static final String CONFIG_PROPERTIES_PATH = "WEB-INF/classes/config.properties";
    public static String ARDROID_URL;
    static {
        BASE_SERVER = PropertiesUtil.getPropertie(CONFIG_PROPERTIES_PATH,"base_server");
        ARDROID_URL = PropertiesUtil.getPropertie(CONFIG_PROPERTIES_PATH,"android_Url");
    }
    public static String BASE_SERVER;
    
    public static String QR_CODE_PATH;//岗位二维码保存路径
    public static String PROJECT_LOCATION;//项目绝对路径

    /**
     * 设置图片，视频，文档等格式后缀格式
     */
    public static final String[] doc_allow_type = new String[] {"doc", "docx", "xls", "xlsx", "ppt", "pptx"};

    public static final String[] img_allow_type = new String[] {"jpg", "jpeg", "gif", "bmp", "png"};//

    public static final String[] video_allow_type = new String[] {"avi", "rmvb", "rm", "asf", "wmv", "mkv"};

    public static final String[] allow_type =
        (String[])ArrayUtils.addAll((String[])ArrayUtils.addAll(img_allow_type, doc_allow_type), video_allow_type);

    
    

    /**
     * 微信服务号类型（用户类型）
     */
    public static final String PLATFORM_TYPE_COMPANY = "company";//企业
    public static final String PLATFORM_TYPE_STUDENT = "student";//学生
    public static final String PLATFORM_TYPE_SCHOOL = "school";//学校

    /**
     * 最后一题序号
     */
    public static final  Integer LASTITEM = 6;

    public static String noTypeCareerId = "9a56dd54f55b11e5a619000c2903d605";

    public static final String xlwAdminSchoolMoney = "fe6c96eff6154dda8ceaf3c77e267b21";

    public static final String MANAGEID = "d0fda1de053511e6a619000c2903d605";
    
    /**
     * 付费状态：1：成功
     */
    public static final int PAY_STATUS_SUCESS = 1;//
    
    /**
     * 付费状态：0：失败
     */
    public static final int PAY_STATUS_FAIL = 0;//
    
    /**
     * 支付类型：1：任务保障金
     */
    public static final int PAY_TYPE_RWBZJ = 1;    
   
    /**
     * 支付类型：2：接单保障金
     */
    public static final int PAY_TYPE_JDBZJ = 2;
    
    /**
     * 支付类型：3：支付赏金
     */
    public static final int PAY_TYPE_ZFSJ = 3;
    
    /**
     * 支付类型：4：收到赏金
     */
    public static final int PAY_TYPE_SDSJ = 4;
    
    /**
     * 支付类型：5：凑钱
     */
    public static final int PAY_TYPE_CQ = 5;
    
    /**
     * 支付类型：6：收钱
     */
    public static final int PAY_TYPE_SQ = 6;
    
    /**
     * 支付类型：7：充值
     */
    public static final int PAY_TYPE_CZ = 7;
    
    /**
     * 支付类型：8：提现
     */
    public static final int PAY_TYPE_TX = 8;
    
    /**
     * 支付类型：9：收到退款
     */
    public static final int PAY_TYPE_SDTK = 9;
    
    /**
     * 支付类型：10：支付赔偿金
     */
    public static final int PAY_TYPE_ZFPC = 10;
    
    /**
     * 支付类型：11：收到赔偿金
     */
    public static final int PAY_TYPE_SDPC = 11;
    /**
     * 支付类型：12：缴纳考试费
     */
    public static final int PAY_TYPE_KSF = 12;
    /**
     * 支付类型：13：缴纳学费
     */
    public static final int PAY_TYPE_XF = 13;
    /**
     * 支付类型：14：缴纳资料费
     */
    public static final int PAY_TYPE_ZLF = 14;
    /**
     * 支付类型：15：爱心捐助
     */
    public static final int PAY_TYPE_AXJZ = 15;



    /**分类id*/
    /**促销导购*/
    public static final String shoppingGuideId="331c0168523711e6870a000c2903d605";
    /**礼仪模特*/
    public static final String modelId="331d549d523711e6870a000c2903d605";
    /**物流快递*/
    public static final String expressId="331ec704523711e6870a000c2903d605";
    /**分享代理*/
    public static final String sharingId="33203056523711e6870a000c2903d605";
    /**家教老师*/
    public static final String tutorId="3322339b523711e6870a000c2903d605";
    /**保安*/
    public static final String securityId="3324bb3a523711e6870a000c2903d605";
    /**话务员*/
    public static final String operatorId="33271d1f523711e6870a000c2903d605";
    /**酒店帮工*/
    public static final String hotelId="332965a7523711e6870a000c2903d605";
    /**寒暑假工*/
    public static final String summerId="332b74c6523711e6870a000c2903d605";
    /**其他*/
    public static final String otherId="272b21a67d6b11e69c4000163e10206f";
    /**发单举牌*/
    public static final String placardsId="ee693e99523611e6870a000c2903d605";
    //兼职信息的首次进入标志
    public static final String jzFirst = "jzFirst";
    //寒假工 首次进入标示
    public static final String hjgFirst = "hjgFirst";

    /**百度地图服务端ak密钥*/
    public static final String BDWebAK = "HAlmjEeWoe9FwN0u6pmqSZiYuXpmaDuV";
    /**百度地图浏览器ak密钥*/
    //public static final String BDBrowserAK = "kGUAhIvOpjGNVF4Ct2ARnG462xcbFbNj";
    
    /**百度地图浏览器ak密钥,新申请*/
    public static final String BDBrowserAK = "2o6XFIXBU7luxvvk39ooS6Ba9GwXprBM";
    
    /**
     * 校缘网易云音乐/虾米 登陆时调用python 程序接口 url
     */
    public static final String XIAOYUANLOGINURL = "http://139.224.201.124:8553/api/login";
    

    

}