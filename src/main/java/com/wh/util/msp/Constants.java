package com.wh.util.msp;

/**
 * 移动校园平台常量类
 * 
 * @author Administrator
 *
 */
public class Constants {
	/**
	 * 常量说明： 此处定义的常量需要持久化，可以保存在数据库中，在需要的地方读取。 在多企业号中，最好以每个应用来定义。
	 */
	public static final int AGENTID = 1;

	// 通讯录 platformId =1 address list;
	public static final String LV_ROOTDEP_ID = "01";

	// 通讯录 platformId =1 address list;
	public static final int ALPLATFORMID = 1;

	// 移动校园平台 2
	public static final int MSPPLATFORMID = 2;
	// 移动校园平台应用id
	public static final int MSPAGENTID = 1000002;

	// 企业号id
	public static final String CORPID = "wxcb34702222b6d5c5";

	// 通讯录address list secret
	public static final String Secret = "SWyHSosgEhPnbiMh7zgnHAktL-Hmoy5dXjZySrQcVP8";
	// 移动服务平台secret
	public static final String MSPSECRET = "TD0LbzRYhxbZTvwHLLApC2CNYFJgi1zmDYGIz__QdVc";

	// 跳转首页
	public static final String REDIRECT_URL = "http://msp.uni-uni.cn/wmh/wmhUser/wx/goIndex.htm?sign=3";
	// 后台个人信息入口
	public static final String PERSON_REDIRECT_URL = "http://msp.uni-uni.cn/msp/mspUser/wx/goMyCenter.htm";

	// 后台个人信息入口
	public static final String LOGIN_REDIRECT_URL = "http://msp.uni-uni.cn/msp/mspUser/wx/goLogin.htm?type=TYPE";

	
	
	
	// 旅职部署真实配置
	/*public static final String CORPID = "wx3f615c37f08c4acb";
	public static final String Secret = "-py-CEPtAtxkQB_Aoi5myZ_ueA3iDeakS_yiXvktYDw";
	public static final String MSPSECRET = "rbiVRaitAYfk4DPLMk2CXJ9XS8BGYxj9aeADzKpR-wg";
	public static final String REDIRECT_URL = "http://msp.zztrc.edu.cn/wmh/wmhUser/wx/goIndex.htm?sign=3";

	public static final String PERSON_REDIRECT_URL = "http://msp.zztrc.edu.cn/msp/mspUser/wx/goMyCenter.htm";

	// 后台个人信息入口
	public static final String LOGIN_REDIRECT_URL = "http://msp.zztrc.edu.cn/msp/mspUser/wx/goLogin.htm?type=TYPE";

	*/
	
	
	// 1 跳转个人信息html
	public static final String PERSON_HTML_URL = "/resource/msp/wx/html/my/mycenter.html";

	// 2 跳转消息通知html
	public static final String MESSAGE_HTML_URL = "/resource/msp/wx/html/message/message.html";

	// 3 跳转代班信息html
	public static final String DAIBAN_HTML_URL = "/resource/msp/wx/html/tongxun/daiban.html";

	// 4 跳转随手拍信息html
	public static final String SUISHOUPAI_HTML_URL = "/resource/msp/wx/html/suishou/suipai.html";
	
	
	//5学生缴费
	public static final String XSJF_HTML_URL = "/resource/msp/wx/html/xsjf/index.html";
	
	//6 图书借阅
	public static final String TSJY_HTML_URL = "/resource/msp/wx/html/tsjy/index.html";
	
	//7 课程安排
	public static final String KCAP_HTML_URL = "/resource/msp/wx/html/kcap/index.html";
	
	//8成绩查询
	public static final String CJCX_HTML_URL = "/resource/msp/wx/html/cjcx/index.html";
	
	
	// 0:秋季学期    判断截取时间点
	public static final String AUTUMN_DAY_TIME = "-08-15";
	
	// 1:春季学期    判断截取时间点
	public static final String SPRING_DAY_TIME = "-02-10";
	
	// 获取code Url 静默授权 最基本信息
	public static final String OAUTH2_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=CORPID&redirect_uri=REDIRECT_URL&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";

	// 获取code Url 静默授权 snsapi_userinfo
	public static final String OAUTH2_URL2 = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=CORPID&redirect_uri=REDIRECT_URL&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";

	// 获取code Url 手动授权 snsapi_privateinfo
	public static final String OAUTH2_URL3 = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=CORPID&redirect_uri=REDIRECT_URL&response_type=code&scope=snsapi_privateinfo&state=STATE#wechat_redirect";

	// 获取部门列表
	public static final String DEPARTMENT_LIST_URL = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=ACCESS_TOKEN";

	// 获取某个部门的信息
	public static final String DEPARTMENT_URL = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=ACCESS_TOKEN&id=ID";

	// 调用金智接口
	public static final String JINZHI_URL = "";

	// 获取部门成员详情(但无完整部门信息)
	public static final String GET_DEPMEMBER_LIST_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/list?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID&fetch_child=FETCH_CHILD";

	// 获取部门成员(简单信息,有部门信息)
	public static final String GET_SIMPLE_DEPMEMBER_LIST_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID&fetch_child=FETCH_CHILD";

	// 旅职新闻网站抓取页面
	public static final String LZNEWS_URL = "http://www.zztrc.edu.cn/col/col1397/index.html";

	// 旅职网站
	public static final String LZ_URL = "http://www.zztrc.edu.cn";

	// 旅职网站
	public static final String Calendar_Link_Id = "lvzhi2017";

	// 发送消息
	public static final String SEND_MESSAGE_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";

	// 获取标签列表
	public static final String GET_TAG_URL = "https://qyapi.weixin.qq.com/cgi-bin/tag/list?access_token=ACCESS_TOKEN";

	// 获取标签成员
	public static final String GET_MEMBERLIST_BYTAGID_URL = "https://qyapi.weixin.qq.com/cgi-bin/tag/get?access_token=ACCESS_TOKEN&tagid=TAGID";

	// 创建部门
	public static final String CREATE_DEPARTMENT_URL = "https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=ACCESS_TOKEN";

	// 更新部门 注意，部门的最大层级为15层；部门总数不能超过3万个；每个部门下的节点不能超过3万个。
	public static final String UPDATE_DEPARTMENT_URL = "https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token=ACCESS_TOKEN";

	// 删除部门 部门id。（注：不能删除根部门；不能删除含有子部门、成员的部门）
	public static final String DELETE_DEPARTMENT_URL = "https://qyapi.weixin.qq.com/cgi-bin/department/delete?access_token=ACCESS_TOKEN&id=ID";

	// 读取成员 get 获取用户头像等
	public static final String GET_USER_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid=USERID";

	// 创建成员 post
	public static final String CREATE_USER_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=ACCESS_TOKEN";

	// 更新成员 post
	public static final String UPDATE_USER_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token=ACCESS_TOKEN";

	// 删除成员 get
	public static final String DELETE_USER_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token=ACCESS_TOKEN&userid=USERID";

	// 删除成员 POST
	public static final String BATCH_DELETE_USERLIST_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/batchdelete?access_token=ACCESS_TOKEN";

}
