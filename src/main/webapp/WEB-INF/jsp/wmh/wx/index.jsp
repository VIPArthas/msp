<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8 ">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0, minimal-ui">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <title>首页</title>
    <script type="text/javascript"> var urlPix = "<%=request.getContextPath() %>";</script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/commons/css/font3/font_5y12bci07sbdquxr/iconfont.css?v=1"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/commons/css/font5/iconfont.css"/>    
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/commons/css/style.css?v=20"  />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/commons/css/iframe.css"/>  
    <script type="text/javascript" src="<%=request.getContextPath() %>/resource/wmh/wx/commons/js/jquery-3.1.1.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resource/wmh/wx/commons/js/index.js"></script>
    <script type="text/javascript">
	    var currentPage =${list[1]};
	    var moreData = true;
    </script>
</head>
<body style="background-color: #f3f3f3;padding-bottom:55px;">
<div class="header"></div>
<div class="content">
    <div class="banner">
    	<c:if test="${sign == 3 }">
	        <img src="<%=request.getContextPath() %>/resource/wmh/wx/commons/img/bj_home.jpg" />
    	</c:if>
    	
    	<c:if test="${not empty wxid }">
	       	<input id="wxid" type="hidden" value="${wxid}">
    	</c:if>
    	<c:if test="${not empty userid }">
	       	<input id="userid" type="hidden" value="${userid}">
    	</c:if>
    	<c:if test="${not empty myname }">
	       	<input id="myname" type="hidden" value="${myname}">
    	</c:if>
    </div>
    <div class="select-content">
        <div>
            <a href="<%=request.getContextPath() %>/wmh/wmhUser/wx/goDataServicePage.htm" class="xiaoli">
                <div class="icon iconfont icon-shuju"></div>
                <div>个人数据服务</div>
            </a>
            <a href="<%=request.getContextPath() %>/resource/msp/wx/html/xiaolinew/view.html" class="ditu">
                <div class="icon iconfont icon-qiuzhidaxuerili-"></div>
                <div>校历</div>
            </a>
            <c:if test="${type == 1 }">
             <a href="<%=request.getContextPath() %>/resource/msp/wx/html/tongxun/daiban.html" class="usercenter">
                <div class="icon iconfont icon-tongxunlu"></div>
                <div>班级通讯录</div>
            </a>
           </c:if>
        </div>
        
    </div>
    <%--
    <form>
        <input type="text" placeholder="请输入搜索内容" id="searchContent" value="${searchContent == null || searchContent  eq '' ?'':searchContent }">
        <button type="button" style="outline: none;" onclick="cancelSearch('${sign}')">取消</button>
        <button type="button" style="outline: none;"><img onclick="goSearch('${sign}')" src="<%=request.getContextPath() %>/resource/wmh/wx/commons/img/search.png" alt=""></button>
    </form>--%>
    <div class="list mt1">
    	<%-- <div  class="findNum"
    	<c:if test="${$('#name').data('signId') == 1 }">
	         name="findNum" count="${newsCount }">为您找到相关结果约<span>${newsCount }</span>条</div>
    	</c:if> 
    	<div  class="findNum" style="display:none" name="findNum" count="${newsCount }">为您找到相关结果约<span>${newsCount }</span>条</div> --%>
    	<div class="allnews">综合新闻<a href="<%=request.getContextPath()%>/wmh/news/web/goNewsList2.htm">查看全部<span class="icon iconfont icon-jiantou"></span></a></div>
    	<c:choose>
    		<c:when test="${not empty list[0] }">
    			<c:forEach items="${list[0] }" var="news">
    				<dl class="news"><dt><div><span>${news.day }</span><span>${news.yearMonth }</span></div></dt><dd><div data-id="${news.id }" onclick="newsInfo(this)" class="newstit">${news.news_title }</div><div class="t_gray">来源：${news.source_div == null or news.source_div eq '' ? '其他':news.source_div }</div> </dd></dl>
    			</c:forEach>
    		</c:when>
    		<c:otherwise>
    			暂无新闻信息
    		</c:otherwise>
    	</c:choose>
    </div>
    <div class="moreWrap" style="bottom: 50px;">
        <div class="btn">
            <a href="javascript:void(0);" class="more"><img id="loadingToast" src="<%=request.getContextPath()%>/resource/wmh/wx/commons/img/loading.gif" />正在加载...</a>
        </div>
    </div>
</div>
<nav>
    <a class="shouye select-actived" target="_self" href="<%=request.getContextPath()%>/wmh/wmhUser/wx/goIndex.htm">
        <span class="shou-ye"></span>
        <p>首页</p>
    </a>
    <a class="suishou" target="_self" href="<%=request.getContextPath() %>/resource/msp/wx/html/suishou/suipai.html">
        <span class="sui-shou"></span>
        <p>随手拍</p>
    </a>
    <a class="xiaoxi" target="_self" href="<%=request.getContextPath() %>/resource/msp/wx/html/message/message.html">
        <span class="xiao-xi"></span>
        <p>消息</p>
    </a>
    <a class="mymessage" target="_self" href="<%=request.getContextPath() %>/resource/msp/wx/html/my/mycenter.html">
        <span class="wo-de"></span>
        <p>我的</p>
    </a>
</nav>
<div class="footer"></div>
<script type="text/javascript">
$(function(){
	localStorage.removeItem('classna');
	localStorage.removeItem('classindex');
	localStorage.removeItem('top');
	localStorage.removeItem('spobj');
	localStorage.removeItem('sppage');
	localStorage.removeItem('sptype');
	localStorage.removeItem('sptop');
	localStorage.setItem('userid',$('#userid').val());
	localStorage.setItem('wxid',$('#wxid').val());
	localStorage.setItem('myname',$('#myname').val());
	console.log(localStorage.getItem('wxid'));
})

	

	function newsInfo(obj){
		var id = $(obj).data("id");
		window.location.href= "<%=request.getContextPath()%>/wmh/news/wx/newsInfo.htm?id="+id;
	}
	
	function goSearch(sign1){
		var searchContent = $.trim($("#searchContent").val());
		if(searchContent!=null &&searchContent!=""){
			 $("div[name='findNum']").css('display','block');
		}
		currentPage = 1;
		$.post("<%=request.getContextPath()%>/wmh/wmhUser/wx/goIndexTable.htm",
				{sign:sign1,searchContent:searchContent},
				function(response){
					$(".moreWrap").slideUp();
					$(".list").html(response);
					$("#searchContent").val(searchContent);
				});
	}
	
	function cancelSearch(sign1){
		currentPage = 1;
		$.post("<%=request.getContextPath()%>/wmh/wmhUser/wx/goIndexTable.htm",
				{sign:sign1},
				function(response){
					$(".moreWrap").slideUp();
					$(".list").html(response);
					$("#searchContent").val("");
				});
		
	}
</script>
</body>
</html>