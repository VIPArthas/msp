<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0, minimal-ui">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <title>新闻列表</title>
     <link rel="stylesheet" href="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/css/mui.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/css/style.css"  />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/css/font/iconfont.css"  />
    <script type="text/javascript" src="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/js/jquery-3.1.1.js"></script>
</head>
<body style="background-color: white;height: 100%;" id="news">
<div class="header"></div>

<div class="content">
	
	
	<div id="pullrefresh" class="mui-content mui-scroll-wrapper" style="padding-bottom: 0.8rem">
        <div class="mui-scroll">
            <div class="list mt1 mui-table-view mui-table-view-chevron">
                <div class="tongbu">
	
					<c:choose>
						<c:when test="${syncTime == '' or  syncTime eq null}">
							<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
							<button id="syncNews"><i class="icon iconfont icon-tongbu"></i>同步新闻</button>
						</c:when>
						<c:otherwise>
							<span>上次同步时间：${syncTime }</span>
							<button id="syncNews"><i class="icon iconfont icon-tongbu"></i>同步新闻</button>
						</c:otherwise>
					</c:choose>
					
					
				</div>
            </div>
        </div>
    </div>
    
    
    <%-- <div class="list mt1">
    
    	<c:choose>
        	<c:when test="${not empty list[0] }">
            	<c:forEach items="${list[0]}" var="news" varStatus="status">
			        <a href="<%=request.getContextPath()%>/wmh/news/wx/newsInfo.htm?id=${news.id }">
			        <dl class="news"><dt><div><span>${news.day }</span>
			        <span>${news.yearMonth }</span></div></dt><dd>
			        <div class="newstit">${news.news_title }</div>
			        <div class="t_gray">来源：${news.source_div == null or news.source_div eq '' ? '其他':news.source_div }</div> </dd></dl></a>
                		
                </c:forEach>
             </c:when>
             <c:otherwise>
	            	暂时没有数据~
             </c:otherwise>
        </c:choose>
    </div> --%>
</div>
<div class="footer"></div>
<script src="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/js/mui.min.js"></script>
<script>

mui.init({
	gestureConfig:{
        doubletap:true
    },
    pullRefresh: {
        container: '#pullrefresh',
        up: {
            contentrefresh: '正在加载...',
            callback: pullupRefresh
        }
    }
});
var currentPage = 0;
var totalPageSize = ${totalPage};
function pullupRefresh() {
    setTimeout(function () {
    	
    	
        mui('#pullrefresh').pullRefresh().endPullupToRefresh((++currentPage > totalPageSize)); //参数为true代表没有更多数据了  初始加载的不计入currentPage  5代表可上拉的次数，即总数据/10向上取整。
        var table = document.body.querySelector('.mui-table-view');

		$.post(
			"<%=request.getContextPath() %>/wmh/news/web/goNewsListData.htm",
	   		{
				currentpage:currentPage
			},
	   		function(response){
				var res = JSON.parse(response);
				var list = res.list;
				
				for (var i = 0;  i < list.length; i++){
					var news = list[i];
					var source_div = news.source_div;
					if(news.source_div == null || news.source_div == ""){
						source_div = '其他';
					}
					var sec = document.createElement('a');
		            sec.className = 'mui-table-view-cell';
		            sec.href = '<%=request.getContextPath()%>/wmh/news/wx/newsInfoWx.htm?id='+news.id;
		            sec.innerHTML ='<dl class="news"><dt><div><span>'+news.day+'</span><span>'+news.yearMonth+'</span></div></dt><dd><div class="newstit">'+news.news_title+'</div><div class="t_gray">来源：'+source_div+'</div> </dd></dl>';
		            table.appendChild(sec);
				}
	   		}	
	    );
    }, 10);
}
if (mui.os.plus) {
    mui.plusReady(function () {
        setTimeout(function () {
            mui('#pullrefresh').pullRefresh().pullupLoading();
        }, 10);

    });
} else {
    mui.ready(function () {
        mui('#pullrefresh').pullRefresh().pullupLoading();
    });
}
mui('body').on('tap', 'a', function () { document.location.href = this.href; });

//新闻同步
$("#syncNews").on("click",function(){
	//showLoading();
	$.ajax({
      url: '<%=request.getContextPath()%>/wmh/news/web/syncNews.htm',
      success: function (data) {
      	//hideLoading();
          var data = JSON.parse(data);
          alert(data.msg);
          if (data.code == 1) {
              window.location.reload(true);
          }
      }
  });
});

</script>
</body>
</html>