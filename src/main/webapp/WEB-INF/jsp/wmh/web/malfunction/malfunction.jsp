<%@page import="com.wh.constants.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/pageTag.tld" prefix="page" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="校联网,微门户" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <title>校联网微门户-后台管理</title>
    <link href="<%=request.getContextPath() %>/resource/wmh/web/commons/css/font/iconfont.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resource/wmh/web/commons/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath() %>/resource/wmh/web/commons/js/public.js" ></script>
</head>
<body>
<!--标签页开始-->
<div class="tabs">
    <div class="row" style="    border-bottom: solid 2px gainsboro;">
    	<div class="col-md-10 col-sm-9 co-xs-6"></div>
   <!--      <div class="col-md-2 col-sm-3 co-xs-6" style="background-color: white;color:red;font-size: 15px;line-height: 40px;text-align: center;text-indent: 7px;">退出</div> -->
    </div>
</div>
<!--table start-->
<div class="col-md-12 mt20">
    <div class="table-responsive">
        <table class="table table_black table_blue">
            <thead>
            <tr>
                <th class="guzhang">什么故障</th>
                <th class="guzhang">在什么地方</th>
                <th class="guzhang">修报人</th>
                <th class="guzhang">报修时间</th>
                <th class="guzhang">状态</th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
        	<c:when test="${not empty list}">
            	<c:forEach items="${list}" var="mal" varStatus="status">
            <tr> 
            	<td class="guzhang">
            	<a href="<%=request.getContextPath() %>/wmh/malFunction/web/malById.htm?malId=${mal.id}&&place=${mal.place}&&faultInfo=${mal.fault_info}&&faultReason=${mal.fault_reason}">${mal.fault_info }</a>
            	</td>
            	
            	<td class="guzhang">${mal.place }</td>
            	<td class="guzhang">${mal.real_name }</td>
            	<td class="guzhang">${mal.create_time }</td>
            	<c:if test="${mal.STATUS==0}">
            	<td class="guzhang" style="color: red;">未解决</td>
            	</c:if>
         		<c:if test="${mal.STATUS==1}">
            	 <td class="guzhang">已解决</td>
            	</c:if>
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
            <tr>
            <!--     <td colspan="20">
                    <div class="float-right mt10" style="line-height: 23px;width: auto"><span>共</span><span class="ym text-blue">5</span><span>条记录，1/5页</span> <a class="btn btn_sm btn-default">1</a><a class="btn btn_sm btn_blue">2</a><a class="btn btn_sm btn-default">3</a><a class="btn btn_sm btn-default">4</a><a class="btn  btn_sm btn-default">5</a> 到第 <input type="text"  value="5" style="width:25px;text-align: center"/> 页 <a class="btn btn_sm btn_blue plr15">前往</a></div>
                
                    <tr> -->
        <td colspan="20">
            <%-- <div class="float-right mt10" style="line-height: 23px;width: auto"><span>共</span><span class="ym text-blue">${userList[2] }</span>条记录，${userList[1]}/页 <a class="btn btn_sm btn-default">1</a><a class="btn btn_sm btn_blue">2</a><a class="btn btn_sm btn-default">3</a><a class="btn btn_sm btn-default">4</a><a class="btn  btn_sm btn-default">5</a> 到第<input type="text"  value="5" style="width:25px;text-align: center"/>页 <a class="btn btn_sm btn_blue plr15">前往</a></div> --%>
      <%--    <page:page currentpage="${currentPage}" rscount="${count}" className="ym text-blue" pagesize="<%=Constants.wmhPageSize%>" action="wmh/malFunction/web/malFunctionList.htm" /> --%>
      
                
                
                
                
                
           <!-- 分页开始  -->  
            <div class="float-right mt10" style="line-height: 23px;width: auto">  
            
                <div class="page_list">  
               <span class="ym text-blue" style='position: relative;top:1px;'>共<span>${count}</span>条记录</span>
                  <a href="<%=request.getContextPath()%>/wmh/malFunction/web/malFunctionList.htm?start=${1}&&length=10">首页</a>  
                <c:choose>  
                    <c:when test="${pageNo eq '1'}">  
                        <a href="<%=request.getContextPath()%>/wmh/malFunction/web/malFunctionList.htm?start=${pageNo}&&length=10">上一页</a>  
                    </c:when>  
                    <c:otherwise>  
                        <a href="<%=request.getContextPath()%>/wmh/malFunction/web/malFunctionList.htm?start=${pageNo-1}&&length=10">上一页</a>  
                    </c:otherwise>  
                </c:choose>  
                  
                <c:choose>  
                    <c:when test="${aaa eq '123'}">  
                        <c:choose>  
                            <c:when test="${pageNo eq '1'}">  
                                <a href="<%=request.getContextPath()%>/wmh/malFunction/web/malFunctionList.htm?start=${1}&&length=10" style="background-color:#FA7100;color:#FFF;">1</a>  
                            </c:when>  
                            <c:otherwise>  
                                <a href="<%=request.getContextPath()%>/wmh/malFunction/web/malFunctionList.htm?start=${1}&&length=10">1</a>  
                            </c:otherwise>  
                        </c:choose>  
                    </c:when>  
                    <c:otherwise>  
                        <a href="<%=request.getContextPath()%>/wmh/malFunction/web/malFunctionList.htm?start=${1}&&length=10">1</a>  
                        <span class="pagebreak">...</span>  
                    </c:otherwise>  
                </c:choose>  
                  
                <c:forEach var="dataItem" items="${sss}">  
                        <c:choose>  
                            <c:when test="${dataItem eq pageNo}">  
                                <a href="<%=request.getContextPath()%>/wmh/malFunction/web/malFunctionList.htm?start=${dataItem}&&length=10" style="background-color:#FA7100;color:#FFF;"><span style="white-space:pre">${dataItem}</span></a>
                            </c:when>  
                            <c:otherwise>  
                                <a href="<%=request.getContextPath()%>/wmh/malFunction/web/malFunctionList.htm?start=${dataItem}&&length=10">${dataItem}</a>  
                            </c:otherwise>  
                        </c:choose>  
                </c:forEach>  
                <c:choose>  
                            <c:when test="${pageNo eq pages}">  
                                <a href="<%=request.getContextPath()%>/wmh/malFunction/web/malFunctionList.htm?start=${pageNo}&&length=10">下一页</a>  
                                <a href="javascript:void(0)">共${pages}页 </a>  
                            </c:when>  
                            <c:otherwise>  
                                <a href="<%=request.getContextPath()%>/wmh/malFunction/web/malFunctionList.htm?start=${pageNo+1}&&length=10 ">下一页</a>  
                                <a href="javascript:void(0)">共${pages}页 </a>  
                            </c:otherwise>  
                </c:choose>     
                        <a href="<%=request.getContextPath()%>/wmh/malFunction/web/malFunctionList.htm?start=${pages}&&length=10">末页</a>  
                </div>  
                
            </div>
             
            </span>  
                <!-- 分页结束    --> 
                
                
                
                
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
</div>



</body>
</html>