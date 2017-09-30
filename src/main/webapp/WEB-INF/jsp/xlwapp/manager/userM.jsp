<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="page" uri="/WEB-INF/pageTag.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="com.wh.util.PrivilegeUtil"%>
<%@page import="com.wh.entity.UserInfo"%>
<%@ page import="com.wh.entity.ManageUser" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Insert title here</title>
  <script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/jquery.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/easyui/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/easyui/easyui-lang-zh_CN.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/lightbox_me/jquery.lightbox_me.js"></script>

  <script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/trexpand.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/formexpand.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/manager/user.js"></script>
  <link href="<%=request.getContextPath()%>/resource/css/admin.css" rel="stylesheet" type="text/css">
  <link href="<%=request.getContextPath()%>/resource/css/main.css" rel="stylesheet" type="text/css">
  <link href="<%=request.getContextPath()%>/resource/css/easyui/easyui.css"
  rel="stylesheet" type="text/css">
  <link href="<%=request.getContextPath()%>/resource/css/easyui/icon.css" rel="stylesheet"
        type="text/css">
  <script type="text/javascript">
    $(function(){
      /* 日期 */
      $("#search_form input.time").datebox({
        required : true,
        novalidate : true,
        editable : false
      });
      $("#user_info_form input.time").datebox({
        required : true,
        novalidate : true,
        editable : false
      });

    })
  </script>

  <script type="text/javascript">
    $(function(){
      $("#deleteBatchUser").click(function(){
        var is_checked = $(this).attr("checked");
        if(is_checked=="checked"){
          $("input[name=userCheck]").attr("checked",true);
        }
        else{
          $("input[name=userCheck]").attr("checked",false);
        }
      });
    });
  </script>

  <script type="text/javascript">
    function sortSubmit(sort) {
      var sortName = $("#sortName");
      var searchForm = $("#search_form");

        sortName.val(sort);
        searchForm.submit();


    }
  </script>

</head>
<body>
<%
  /**获取用户登陆账户*/
  Integer userType = ((ManageUser)(request.getSession(false).getAttribute("manage_session_user_info"))).getUserType();
%>
<div class="main">
  <div class="search_box">
    <form action="<%=request.getContextPath()%>/admin/adminUser/web/userM.htm" method="post" id="search_form">
      <table cellpadding="0" cellspacing="0">
        <tr>
          <td width="70" align="right"><span>用户名:</span></td>
          <td>
            <input class="cx_srk" name="realName" value="${queryParam.realName}" type="text">
            <input name="sortName" id="sortName" value="${queryParam.sortName}" type="hidden">
          </td>


          <td width="70" align="right"><span>手机号:</span></td>
          <td>
            <input class="cx_srk" name="phone" value="${queryParam.phone}" type="text">
          </td>
          <td width="70" align="right"><span>学校名称:</span></td>
          <td>
            <input class="cx_srk" name="schoolName" value="${queryParam.schoolName}" type="text">
          </td>
          <td width="70" align="right"><span>专业:</span></td>
          <td>
            <input class="cx_srk" name="majorName" value="${queryParam.majorName}" type="text">
          </td>
          <td width="100" align="right"><span>注册日期查询:</span></td>
          <td>
            <input class="bk_txt time" id="registerTimeStart" name="registerTimeStart" type="text"  value='<fmt:formatDate value="${queryParam.registerTimeStart}" pattern="yyyy-MM-dd" />'>
          </td>
          <td width="20" align="center">至</td>
          <td>
            <input class="bk_txt time" id="registerTimeEnd" name="registerTimeEnd" type="text" value='<fmt:formatDate value="${queryParam.registerTimeEnd}" pattern="yyyy-MM-dd" />'>
          </td>
          <td width="100" align="right"><span>最后登陆日期查询:</span></td>
          <td>
            <input class="bk_txt time" id="lastloginTimeStart" name="lastloginTimeStart" type="text"  value='<fmt:formatDate value="${queryParam.lastloginTimeStart}" pattern="yyyy-MM-dd" />'>
          </td>
          <td width="20" align="center">至</td>
          <td>
            <input class="bk_txt time" id="lastloginTimeEnd" name="lastloginTimeEnd" type="text" value='<fmt:formatDate value="${queryParam.lastloginTimeEnd}" pattern="yyyy-MM-dd" />'>
          </td>
          <td>
            <div class="chaxun"><input type="submit" class="btn" value="查询"></div>
          </td>
          <td>
            <div class="chaxun" style="background-image:none;"><input style="text-indent:0px;" type="button" class="btn"  onClick="javascript:location.href='<%=request.getContextPath()%>/admin/adminUser/web/userM.htm';" value="清空"></div>
          </td>
        </tr>
        </tr>
      </table>
    </form>
  </div>
  <div class="btn_box mar-t-10 clearfix">
    <input class="btn_sc" type="button" onclick="deleteUserOrDepartInfo();" value="删除">
    <input class="btn_cz" type="button"  onclick="resetPwd();" value="重置密码">
    <input class="btn_sc" type="button" onclick="disableUserOrDepartInfo();" value="禁用">
    <input class="btn_sc" type="button" onclick="ableUserOrDepartInfo();" value="启用">
    <input class="btn_sx" type="button"  onclick="refsh();" value="刷新">

  </div>
  <div class="lb_bd">
    <table width="100%"  border="0" class="table" align="center" cellpadding="0"
           cellspacing="0">
      <tr align="center">
        <th class="qx_wzkd_z"  width="5%">
          <input class="" type="checkbox"><span class="">全选</span>

        </th>
        <th width="7%" align="left" >
          会员真实姓名
          <a href="javascript:void(0);" onclick="sortSubmit('u.real_name asc')">▼</a>
          <a href="javascript:void(0);" onclick="sortSubmit('u.real_name desc')">▲</a>
          </th>
        <th width="7%" align="left" > 会员手机号
          <a href="javascript:void(0);" onclick="sortSubmit('phone asc')">▼</a>
          <a href="javascript:void(0);" onclick="sortSubmit('phone desc')">▲</a>
        </th>
        <th width="7%" align="left" > 实名状态
          <a href="javascript:void(0);" onclick="sortSubmit('realname_status asc')">▼</a>
          <a href="javascript:void(0);" onclick="sortSubmit('realname_status desc')">▲</a>
        </th>
        <th width="10%" align="center" > 注册日期
          <a href="javascript:void(0);" onclick="sortSubmit('register_time asc')">▼</a>
          <a href="javascript:void(0);" onclick="sortSubmit('register_time desc')">▲</a>
        </th>
        <th width="10%" align="left" > 最后登录日期
          <a href="javascript:void(0);" onclick="sortSubmit('lastlogin_time asc')">▼</a>
          <a href="javascript:void(0);" onclick="sortSubmit('lastlogin_time desc')">▲</a>
        </th>
        <th width="10%" align="left" > 最后登录IP
          <a href="javascript:void(0);" onclick="sortSubmit('lastlogin_ip asc')">▼</a>
          <a href="javascript:void(0);" onclick="sortSubmit('lastlogin_ip desc')">▲</a>
        </th>
        <th width="10%" align="left" >最后登录客户端
          <a href="javascript:void(0);" onclick="sortSubmit('lastlogin_terminal asc')">▼</a>
          <a href="javascript:void(0);" onclick="sortSubmit('lastlogin_terminal desc')">▲</a>
        </th>
        <th width="10%" align="left" >会员状态
          <a href="javascript:void(0);" onclick="sortSubmit('user_status asc')">▼</a>
          <a href="javascript:void(0);" onclick="sortSubmit('user_status desc')">▲</a>
        </th>
        <th width="7%" align="center"> 操作 </th>
      </tr>

      <c:choose>
        <c:when test="${empty userList[0]}">
          <tr>
            <td colspan="11" align="center">没有符合条件的记录</td>
          </tr>
        </c:when>
        <c:otherwise>
          <c:forEach items="${userList[0]}" var="userInfo" varStatus="status">
            <tr align="center">
              <td class="qx_wzkd_z">
                <input type="checkbox" name="userCheck" id="userInfoId${status.index}"  value="${userInfo.id}" >
					        <span class="qx_wzkd">
					        <input type="hidden" value="${userInfo.user_id}" id="userId"/></span></td>
              <td align="center">${userInfo.real_name}</td>
              <td align="center">${userInfo.phone}</td>
              <td>
                <c:choose>
                  <c:when test="${userInfo.realname_status == 0}">
                    未认证
                  </c:when>
                  <c:when test="${userInfo.realname_status == 1}">
                    基础认证
                  </c:when>
                  <c:when test="${userInfo.realname_status == 2}">
                    完整认证
                  </c:when>
                  <c:otherwise>
                    未知
                  </c:otherwise>
                </c:choose>
              </td>
              <td align="center"><fmt:formatDate value="${userInfo.create_time}" pattern="yyyy-MM-dd " /> </td>

              <td align="center"><fmt:formatDate value="${userInfo.lastlogin_time}" pattern="yyyy-MM-dd " /></td>
              <td align="center">${userInfo.lastlogin_ip}</td>
              <td align="center">
                <c:choose>
                  <c:when test="${userInfo.lastlogin_terminal == 0}">
                    web
                  </c:when>
                  <c:when test="${userInfo.lastlogin_terminal == 1}">
                    微信
                  </c:when>
                  <c:when test="${userInfo.lastlogin_terminal == 2}">
                    wap
                  </c:when>
                  <c:when test="${userInfo.lastlogin_terminal == 3}">
                    安卓
                  </c:when>
                  <c:when test="${userInfo.lastlogin_terminal == 4}">
                    IOS
                  </c:when>
                  <c:otherwise>
                    未知
                  </c:otherwise>
                </c:choose>
              </td>
              <td align="center">
                <c:choose>
                  <c:when test="${userInfo.user_status == 0}">
                    正常
                  </c:when>
                  <c:when test="${userInfo.user_status == 1}">
                    禁用
                  </c:when>
                  <c:otherwise>
                    未知
                  </c:otherwise>
                </c:choose>
              </td>
              <td align="center">
                <a href="javascript:void(0);" onclick="getUserInfo('${userInfo.user_id}');" class="bm-edit">编辑</a>
              </td>
            </tr>
          </c:forEach>
        </c:otherwise>
      </c:choose>
    </table>
  </div>
  <page:page currentpage="${userList[1]}" rscount="${userList[2]}"
             className="adminPage" pagesize="5" action="admin/adminUser/web/userM.htm"/>
</div>
<!--弹出层-->

<!--新增用户弹窗-->
<div id="userlayer" class="gl_lby_gzt"   style="display:none;">
  <h3  class="tanchu_head"><span class="tanchu_title"><span class="tanchu_title_icon userUpdate">新增用户</span></span><span class="tanchu_close_button close">X</span></h3>
  <form action="<%=request.getContextPath()%>/admin/adminUser/web/updateUser.htm" method="post" enctype="multipart/form-data" id="user_info_form">
  <div class="uslay_bd">
    <div class="xzyh">
      <h1>基本信息</h1>
      <input name="userId" id="user_id" type="hidden"/>
      <div class="lb_xzyh">
        <ul>
          <li><span><i class="rq">*</i>头像：</span>
            <img style="width: 80px; height:80px;" src="" id="logoImg" class="txsz_yh">
            <input class="upload" type="file" name="logo" id="logoUrl" onChange="preivew(this);" />
          </li>
          <li><span><i class="rq">*</i>昵称：</span>
            <input type="text" name="nickName" id="nick_name" class="cx_xzyh" maxlength="6">
          </li>

          <li><span><i class="rq">*</i>真实姓名：</span>
            <input type="text" name="realName" id="real_name" class="cx_xzyh" maxlength="6">
          </li>
          <li><span><i class="rq">*</i>手机号：</span>
            <input type="text" name="phone" id="Iphone" class="cx_xzyh" maxlength="11" onkeyup="this.value=this.value.replace(/\D{1,5}/g,'')">
          </li>

          <%--<li><span><i class="rq">*</i>登陆密码：</span>
            <input type="text" name="password" id="password" class="cx_xzyh">
          </li>--%>
          <li><span>性别：</span>


            <input type="radio" value="0" id="man"name="sex">
            <span>男</span><span class="w20"></span>

            <input type="radio" value="1" id="girl" name="sex" >
            <span>女</span>


          </li>
          <li><span><i class="rq">*</i>身份证号：</span>
            <input type="text" name="cardId" id="card_id"class="cx_xzyh" maxlength="18">
          </li>
          <li><span><i class="rq">*</i>微信号：</span>
            <input type="text" name="wechat" id="wechat"class="cx_xzyh" maxlength="20">
          </li>
          <li><span><i class="rq">*</i>qq号：</span>
            <input type="text" name="qq" id="qq"class="cx_xzyh" maxlength="11">
          </li>
          <li><span><i class="rq">*</i>支付宝号：</span>
            <input type="text" name="alipay" id="alipay"class="cx_xzyh" maxlength="15">
          </li>
          <%--<li><span><i class="rq">*</i>提现密码：</span>
            <input type="text" name="payPassword" id="pay_password"class="cx_xzyh">
          </li>--%>
          <li><span><i class="rq">*</i>会员状态：</span>
            <%--<input type="text" name="userStatus" id="user_status"class="cx_xzyh">--%>
            <select name="userStatus" id="user_status">
              <option value="0">正常</option>
              <option value="1">禁用</option>
            </select>
          </li>
          <li><span><i class="rq">*</i>实名认证状态：</span>
            <%--<input type="text" name="realnameStatus" id="realname_status"class="cx_xzyh">--%>
            <select name="realnameStatus" id="realname_status">
              <option value="0">未认证</option>
              <option value="1">基础认证</option>
              <option value="2">完整认证</option>
            </select>
          </li>
          <li><span><i class="rq">*</i>积分：</span>
            <input type="text" name="score" id="score"class="cx_xzyh" maxlength="10" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')">
          </li>
          <li><span><i class="rq">*</i>校币：</span>
            <input type="text" name="schoolMoney" id="school_money"class="cx_xzyh" maxlength="10" onkeyup="this.value=this.value.replace(/\D{1,5}/g,'')">
          </li>
        </ul>
      </div>
    </div>
    <div class="xzyh">
      <h1>真实信息</h1>
      <div class="lb_xzyh">
        <ul>
          <li><span><i class="rq">*</i>出生地：</span>
            <input type="text" name="birthPlace" id="birth_place"class="cx_xzyh">
          </li>
          <li style="height: 60px"><span><i class="rq">*</i>出生日期：</span>
            <input class="bk_txt time" name="birthDate" required="true" id="birth_date" type="text">
          </li>
        </ul>
      </div>
    </div>

    <div class="xzyh">
      <h1>学校信息</h1>
      <div class="lb_xzyh">
        <ul>
          <li><span><i class="rq">*</i>学校名称：</span>
            <input type="text" name="schoolName" id="school_name"class="cx_xzyh" maxlength="20">
          </li>
          <li><span><i class="rq">*</i>学号：</span>
            <input type="text" name="studentId" id="student_id"class="cx_xzyh" maxlength="12">
          </li>
          <li><span><i class="rq">*</i>宿舍号：</span>
            <input type="text" name="dormitoryNo" id="dormitory_no"class="cx_xzyh" maxlength="10">
          </li>
          <li  style="height: 60px"><span><i class="rq">*</i>入学年月：</span>
            <input type="date" name="startTime" id="start_time"class="cx_xzyh time">
          </li>
          <li><span><i class="rq">*</i>专业：</span>
            <input type="text" name="majorName" id="major_name"class="cx_xzyh" maxlength="15">
          </li>

          <li><span><i class="rq">*</i>教务系统账号：</span>
            <input type="text" name="edusysName" id="edusys_name"class="cx_xzyh" maxlength="18">
          </li>
          <li><span><i class="rq">*</i>教务系统密码：</span>
            <input type="text" name="edusysPwd" id="edusys_pwd"class="cx_xzyh" maxlength="18">
          </li>

        </ul>
      </div>
    </div>
  </div>

  </form>
  <div class="bottom">
    <label class="submit-border">

      <input type="button" onclick="updateUserInfo();" class="submit" value="保存">
    </label>
    <a class="ncbtn ml5 close" href="#">取消</a> </div>
</div>
<!--新增用户弹窗 end-->
</body>
<script type="text/javascript">


</script>
</html>