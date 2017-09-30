<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/pageTag.tld" prefix="page" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.wh.constants.Constants"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="校联网,微门户" />
     <script>
        var urlPix = "<%=request.getContextPath()%>";
    </script>
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function 

hideURLbar(){ window.scrollTo(0,1); } </script>
    <title>校联网微门户-新闻管理</title>
    <link href="<%=request.getContextPath() %>/resource/wmh/web/commons/css/font/iconfont.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resource/wmh/web/commons/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath() %>/resource/wmh/web/commons/js/public.js" ></script>
</head>
<body>
<!--标签页开始-->
<div class="tabs">
    <div class="row no-bbc">
        <div class="col-md-2 col-sm-3 cxs-6 mt10">新闻展示</div>
        <div class="col-md-10 col-sm-9 co-xs-6">
        	<c:choose>
	<c:when test="${syncTime == '' or  syncTime eq null}">
		<div class="col-md-10" style="text-align: right;line-height: 44px;"></div>
		<div class="col-md-2 mt10"><a href="javascript:void(0);" onclick="syncNews()" class="btn  btn_green" style="width: 

100%;line-height:30px;"><i class="fa">&#xe648;</i>　同步新闻</a></div>
	</c:when>
	<c:otherwise>
		<div class="col-md-10 mt10" style="text-align: right;line-height: 44px;font-size:12px;">上次同步时间：${syncTime }

</div>
		<div class="col-md-2 mt10"><a href="javascript:void(0);" onclick="syncNews()" class="btn btn_blue" style="width: 

100%;line-height:30px;"><i class="fa">&#xe648;</i>　同步新闻</a></div>
	</c:otherwise>
</c:choose>
        </div>
    </div>
</div>
<div class="row mt20">

<script>

//新闻同步
function syncNews(){
	//showLoading();
	$.ajax({
        url: '<%=request.getContextPath()%>/msp/mspNews/web/syncNews.htm',
        success: function (data) {
        	hideLoading();
            var data = JSON.parse(data);
            alert(data.msg);
            if (data.code == 1) {
                window.location.reload(true);
            }
        }
    });
}


</script>
</div>
<div class="table-responsive bor-solid">
    <table class="table table_black">
        <tbody>
        <c:choose>
        	<c:when test="${not empty newsList[0] }">
            	<c:forEach items="${newsList[0]}" var="news" varStatus="status">
                	<tr>
			            <td><a class="text-black" href="<%=request.getContextPath()%>/wmh/news/web/newsInfo.htm?id=${news.id}" title="${news.newsTitle }">${news.newsTitle }</a></td>
			            <td class="text-black_q">${news.sourceDiv }</td>
			            <td class="text-black_q">${news.createTime }</td>
			        </tr>	
                		
                </c:forEach>
             </c:when>
             <c:otherwise>
             <tr>
	            <td colspan="3" style="text-align:center">
	            	暂时没有数据~
	            </td>
	        </tr>
             	
             </c:otherwise>
         </c:choose>
        </tbody>
       <tfoot>
      
       
        
      </tfoot> 
    </table>
     <c:if test="${not empty newsList[0]}">
     <page:page currentpage="${newsList[1] }" rscount="${newsList[2] }" className="adminPage" pagesize="<%=Constants.wmhPageSize %>" action="wmh/news/web/goNewsList.htm"/>
       </c:if>
</div>

</body>
</html>