<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="page" uri="/WEB-INF/pageTag.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
  <script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/manager/banner.js"></script>
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

  </div>
  
  <div class="btn_box mar-t-10 clearfix">
    <input class="btn_tj user_addUser" onclick="addBannerShow();" type="button" value="添加">
    <input class="btn_sc" type="button" onclick="deleteBanner();" value="删除">
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
          焦点图的URL地址
          </th>
        <th width="7%" align="left" > 焦点图的链接地址
        </th>
        <th width="7%" align="left" > 状态
        </th>
        <th width="10%" align="center" > 预览
        </th>

        <th width="10%" align="left" > 备注信息
        </th>

        <th width="10%" align="left" >是否内连接
        </th>
        <th width="10%" align="left" >排序
        </th>
        <th width="10%" align="left" > 创建时间
        </th>
        <th width="10%" align="left" >操作
        </th>
      </tr>

      <c:choose>
        <c:when test="${empty bannersList[0]}">
          <tr>
            <td colspan="11" align="center">没有符合条件的记录</td>
          </tr>
        </c:when>
        <c:otherwise>
          <c:forEach items="${bannersList[0]}" var="banner" varStatus="status">
            <tr align="center">
              <td class="qx_wzkd_z">
                <input type="checkbox" name="userCheck" id="userInfoId${status.index}"  value="${banner.id}" >
					        <span class="qx_wzkd"></span></td>

              <td align="center">${banner.bannerUrl}</td>
              <td align="center">${banner.linkUrl}</td>
              <td align="center">
                <c:choose>
                  <c:when test="${banner.bannerStatus == 1}">
                  启用
                  </c:when>
                  <c:when test="${banner.bannerStatus == 3}">
                    撤下
                  </c:when>
                </c:choose>
              </td>
              <td align="center"><img style="height: 80px;width: 120px;" src="<%=request.getContextPath()%>${banner.bannerUrl}"></td>

              <td align="center">${banner.bannerRemark}</td>
              <td align="center">
                <c:choose>
                  <c:when test="${banner.initLink == 0}">
                    否
                  </c:when>
                  <c:when test="${banner.initLink == 1}">
                    是
                  </c:when>
                </c:choose>
              </td>
              <td align="center">${banner.bannerSort}</td>
              <td align="center"><fmt:formatDate value="${banner.createTime}" pattern="yyyy-MM-dd " /></td>
              <td align="center">
                <a href="javascript:void(0);" onclick="getBannerImage('${banner.id}');" class="bm-edit">编辑</a>
              </td>
            </tr>
          </c:forEach>
        </c:otherwise>
      </c:choose>
    </table>
  </div>
  <page:page currentpage="${bannersList[1]}" rscount="${bannersList[2]}"
             className="adminPage" pagesize="5" action="admin/adminBanner/web/bannerM.htm"/>
</div>
<!--弹出层-->

<!--新增用户弹窗-->
<div id="userlayer" class="gl_lby_gzt"   style="display:none;">
  <h3  class="tanchu_head"><span class="tanchu_title"><span class="tanchu_title_icon userUpdate">新增用户</span></span><span class="tanchu_close_button close">X</span></h3>
  <form action="<%=request.getContextPath()%>/admin/adminBanner/web/updateBanner.htm" method="post" enctype="multipart/form-data" id="banner_form">
  <div class="uslay_bd">
    <div class="xzyh">
      <div class="lb_xzyh">
        <ul>
          <li style="height:80px;"><span><i class="rq">*</i>焦点图上传：</span>
            <img style="width: 80px; height:80px;" id="logoImg" class="txsz_yh">
            <input class="upload" type="file" name="banner" id="banner" onChange="preivew(this);" />
            <input type="hidden" name="id" id="id">
          </li>
          <li>
            <span><i class="rq">*</i>焦点图对应的链接：</span>
            <input class="upload" type="text" name="linkUrl" id="link_url" />
          </li>
          <li>
            <span>状态：</span>

            <input class="upload" type="radio" name="bannerStatus" value="1" checked="checked"/>
            <span>启用</span><span class="w20"></span>

            <input class="upload" type="radio" name="bannerStatus" value="3"/>
            <span>撤下</span>
          </li>
          <li>
            <span>备注信息：</span>
            <input class="upload" type="text" name="bannerRemark" id="banner_remark"/>
          </li>
          <li>
            <span>是否内连接：</span>
            <select name="initLink" id="init_link" style="width:40px;">
              <option value="1">是</option>
              <option value="0">否</option>
            </select>
          </li>
          <li>
            <span>排序：</span>
            <input class="upload" type="text" name="bannerSort" id="banner_sort"/>
          </li>

        </ul>
      </div>
    </div>

  </div>

  </form>
  <div class="bottom">
    <label class="submit-border">

      <input type="button" onclick="insertBannerImage();" class="submit" value="保存">
    </label>
    <a class="ncbtn ml5 close" href="#">取消</a> </div>
</div>
<!--新增用户弹窗 end-->
</body>
<script type="text/javascript">


</script>
</html>