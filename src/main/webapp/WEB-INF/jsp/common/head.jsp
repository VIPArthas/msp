<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- index begin -->
<link rel="Shortcut Icon" href="<%=basePath%>resource/images/favicon.ico" />
<link href="<%=basePath%>resource/css/style.css" type="text/css" rel="stylesheet">
<link href="<%=basePath%>resource/css/fonts/iconfont.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=basePath%>resource/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resource/js/index.js"></script>
<script>
	var status = 1;
	var flag=false;
	function switchSysBar() {
		if (1 == window.status) {
			window.status = 0;
			switchPoint.innerHTML = '<img src="<%=basePath%>resource/images/shousuo_right.png">';
			document.all("frmtitle").style.display = "none"
		} else {
			window.status = 1;
			switchPoint.innerHTML = '<img src="<%=basePath%>resource/images/right.gif">';
			document.all("frmtitle").style.display = ""
		}
	}
	
	 function clickMenu(menu_id){
		 var flag=true;
		window.parent.$("#frmleft").attr("src","<%=request.getContextPath() %>/admin/system/menuinfo/menuLeft.htm?menu_f_id="+menu_id).ready();
		window.parent.$("#frmright").attr("src","");//先清除当前页面
		if ($("#frmleft")[0].attachEvent){  
			$("#frmleft")[0].attachEvent("onload", function(){ // IE 
				
				if(flag){
					openRight();
					flag=false;
				}
				
		    });  
		} else {  
			$("#frmleft")[0].onload = function(){ // 非IE  
				if(flag){
					openRight();
					flag=false;
				} 
		    };  
		}  
	}
	
	function openRight(){
		 var doc;
		 if (document.all){ // IE 
	 	 doc = window.parent.document.frames["frmleft"].document; 
		 }else{ // 标准
		  doc = window.parent.document.getElementById("frmleft").contentDocument; 
		 }
		 var r_url=$(doc.body).find("#menubar li:eq(0)").children("a").attr("datahref");
		 $("#frmright").attr("src",r_url).ready();	
	}

</script>
<!-- index end -->
<!--导航部分-->
 <div class="top_table">
  <div class="top_table_leftbg">
    <div class="system_logo"><img src="<%=basePath%>resource/images/logo.png"></div>
    <div class="top_table_rightgr">
    <dl>
    <dt>欢迎您：</dt>
    <c:if test="${user!=null}">
    <dd>${user.depart_name }</dd>
    	<c:forEach var="post" items="${user.postList }">
	    <dd class="rightgr_hong">${post.post_name }</dd>
	    </c:forEach>
	    <dd class="rightgr_ddjl">${user.userInfo.name}</dd>
	    <dd><a href="<%=request.getContextPath()%>/admin/admin/loginOut.htm">退出</a></dd>
    </c:if>
    
    </dl>
    
    
    </div>
  </div>
  
</div>
<div class="menu">
   <ul>
    <li><a href="<%=request.getContextPath() %>/admin/workOrder/work/index.htm"  target="frmright"><span class="iconfont icon-home"></span>首页</a> </li>
    <c:if test="${menuList.size()!=0}">
  	<c:forEach items="${menuList}" var="menu">
  	<li><a style="cursor: pointer;" id="${menu.id}" onclick="clickMenu('${menu.id}');"><span class="iconfont icon-set2"></span>${menu.menu_name}</a> </li>
  </c:forEach>
	</c:if>
   <!--  <li><a href="#"><i class="abq_tp4"></i>统计分析</a> </li>
    <li><a href="#"><i class="abq_tp5"></i>系统管理</a> </li>
    <li><a href="#"><i class="abq_tp6"></i>知识库</a> </li> -->
  </ul> 
</div>
<!--导航部分结束-->