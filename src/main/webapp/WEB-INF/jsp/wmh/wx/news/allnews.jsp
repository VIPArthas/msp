<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <title>全部新闻</title>
    <script type="text/javascript"> var urlPix = "<%=request.getContextPath() %>";</script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/commons/css/style.css"  />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/commons/css/font3/font_5y12bci07sbdquxr/iconfont.css"/>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resource/wmh/wx/commons/js/jquery-3.1.1.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resource/wmh/wx/commons/js/index.js"></script>
    <script type="text/javascript">
	    var currentPage =${list[1]};//获取的是当前页码
	    var moreData = true;
    </script>
</head>
<body style="background-color: #f3f3f3;">
<div class="header"></div>
<div class="form">
    <div>
    <input id="suo-content" type="text" placeholder="标题模糊搜索" value="${searchContent == null || searchContent  eq '' ?'':searchContent }">
    <button class="quxiao">取消</button>
    <button class="sou-suo" onclick="goSearch('${sign}')"><span style='display: inline-block' class="icon iconfont icon-iconfontsousuo1" ></span></button>
    </div>

</div>
    <div class="list mt1">
        <div class="findNum" style="display:block" name="findNum">本次共搜到<span>${newsCount }</span>条新闻</div>
        
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
<div class="footer"></div>
<script>
    $(function(){

        $('#suo-content').bind('input onpropertychange', function() {
            if($(this).val()){
                $('.quxiao').css('display','block');
            }else {
                $('.quxiao').css('display','none');
            }
        });
        $('.quxiao').click(function () {
            $('#suo-content').val('');
            $(this).css('display','none');
        })

    })
    
    //新闻详情
   function newsInfo(obj){
		var id = $(obj).data("id");
		window.location.href= "<%=request.getContextPath()%>/wmh/news/wx/newsInfo.htm?id="+id;
	}
	
    //搜索
	function goSearch(sign1){	
		var searchContent = $.trim($("#suo-content").val());
		console.log(searchContent);
		currentPage = 1;
		window.location.href= "<%=request.getContextPath()%>/wmh/news/web/goNewsList2.htm?sign="+sign1+"&&searchContent="+searchContent;
	<%-- 	$.post("<%=request.getContextPath()%>/wmh/news/web/goNewsList2.htm",
				{sign:sign1,searchContent:searchContent},
				function(response){
					
					$("#searchContent").val(searchContent);
				}); --%>
	}

</script>
</body>
</html>