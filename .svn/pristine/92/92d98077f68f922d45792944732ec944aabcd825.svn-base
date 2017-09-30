<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="page" uri="/WEB-INF/pageTag.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
  <script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/manager/comSug.js"></script>
  <link href="<%=request.getContextPath()%>/resource/css/admin.css" rel="stylesheet" type="text/css">
  <link href="<%=request.getContextPath()%>/resource/css/main.css" rel="stylesheet" type="text/css">
  <link href="<%=request.getContextPath()%>/resource/css/easyui/easyui.css"
  rel="stylesheet" type="text/css">
  <link href="<%=request.getContextPath()%>/resource/css/easyui/icon.css" rel="stylesheet"
        type="text/css">
  <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/common.css" />
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


</head>
<body>
<%
  /**获取用户登陆账户*/
  Integer userType = ((ManageUser)(request.getSession(false).getAttribute("manage_session_user_info"))).getUserType();
%>
<div class="main">
  <div class="search_box">
    <form action="<%=request.getContextPath()%>/admin/adminComplaintSuggestions/web/toIndex.htm" method="post" id="search_form">
      <table cellpadding="0" cellspacing="0">
        <tr>
          <td width="70" align="right"><span>投诉人姓名:</span></td>
          <td>
            <input class="cx_srk" name="realName" value="${queryParam.realName}" type="text">
          </td>
          <td width="70" align="right"><span>投诉人手机号:</span></td>
          <td>
            <input class="cx_srk" name="phone" value="${queryParam.phone}" type="text">
          </td>
          <td width="70" align="right"><span>投诉与建议:</span></td>
          <td>
            <input class="cx_srk" name="sugContent" value="${queryParam.sugContent}" type="text">
          </td>

          <td width="100" align="right"><span>投诉日期查询:</span></td>
          <td>
            <input class="bk_txt time" id="submitTimeStart" name="submitTimeStart" type="text"  value='<fmt:formatDate value="${queryParam.submitTimeStart}" pattern="yyyy-MM-dd" />'>
          </td>
          <td width="20" align="center">至</td>
          <td>
            <input class="bk_txt time" id="submitTimeEnd" name="submitTimeEnd" type="text" value='<fmt:formatDate value="${queryParam.submitTimeEnd}" pattern="yyyy-MM-dd" />'>
          </td>
          <td>
            <div class="chaxun"><input type="submit" class="btn" value="查询"></div>
          </td>
          <td>
            <div class="chaxun" style="background-image:none;"><input style="text-indent:0px;"   type="button" class="btn"  onClick="javascript:location.href='<%=request.getContextPath()%>/admin/adminComplaintSuggestions/web/toIndex.htm';" value="清空"></div>
          </td>
        </tr>
        </tr>
      </table>
    </form>
  </div>
  <div class="btn_box mar-t-10 clearfix">
    <input class="btn_sc" type="button" onclick="deleteComSug();" value="删除">
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
          投诉人姓名
          </th>
        <th width="7%" align="left" >
          投诉流水号
          </th>
        <th width="7%" align="left" >投诉人电话
        </th>
        <th width="15%" align="left" >投诉与建议
        </th>
        <th width="10%" align="center" > 投诉时间
        </th>
        <th width="10%" align="left" >投诉模块
        </th>

        <th width="10%" align="left" >投诉人ip
        </th>
        <th width="10%" align="left" >投诉的页面
        </th>
        <th width="10%" align="left" >操作
        </th>
      </tr>

      <c:choose>
        <c:when test="${empty comSugMapList[0]}">
          <tr>
            <td colspan="11" align="center">没有符合条件的记录</td>
          </tr>
        </c:when>
        <c:otherwise>
          <c:forEach items="${comSugMapList[0]}" var="comSug" varStatus="status">
            <tr align="center">
              <td class="qx_wzkd_z">
                <input type="checkbox" name="userCheck" id="userInfoId${status.index}"  value="${comSug.id}" >
					        <span class="qx_wzkd"></span></td>

              <td align="center">${comSug.real_name}</td>
              <td align="center">${comSug.sug_num}</td>
              <td align="center">${comSug.phone}</td>
              <td align="center">
                    ${fn:substring(comSug.sug_content,0,20)}...
              </td>
              <td align="center"><fmt:formatDate value="${comSug.submit_time}" pattern="yyyy-MM-dd " /></td>
              <td align="center">${comSug.modular_id}</td>
              <td align="center">${comSug.sug_ip}</td>
              <td align="center">${comSug.sug_url}</td>
              <td align="center">
                <a href="javascript:void(0);" onclick="getBannerImage('${comSug.id}');" class="bm-edit">回复</a>
              </td>
            </tr>
          </c:forEach>
        </c:otherwise>
      </c:choose>
    </table>
  </div>
  <page:page currentpage="${comSugMapList[1]}" rscount="${comSugMapList[2]}"
             className="adminPage" pagesize="5" action="/admin/adminComplaintSuggestions/web/toIndex.htm"/>
</div>
<!--弹出层-->

<!--新增用户弹窗-->
<div id="userlayer" class="gl_lby_gzt"   style="display:none;">
  <h3  class="tanchu_head"><span class="tanchu_title"><span class="tanchu_title_icon userUpdate">新增用户</span></span><span class="tanchu_close_button close">X</span></h3>
  <form action="<%=request.getContextPath()%>/admin/adminBanner/web/updateBanner.htm" method="post" id="reply_form">
  <div class="uslay_bd">
    <div class="xzyh">
      <div class="lb_xzyh">
        <div class="tous_detail">
          <div id="msglist" class="direct-chat-messages">

          </div>
          <div class="tous_huifu">
            <input class="tous_huifuk" type="text" name="replyContent" id="replyContent">
            <a class="tous_huifubtn" href="javascript:void(0)" onclick="replySugestions()">发送</a>
          </div>
        </div>
      </div>
    </div>

  </div>

  </form>

</div>
<!--新增用户弹窗 end-->
</body>
<script type="text/javascript">


</script>
</html>