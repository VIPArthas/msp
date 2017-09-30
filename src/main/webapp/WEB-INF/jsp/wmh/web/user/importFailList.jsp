<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <div class="row plr15">
        <div class="col-md-12 col-sm-12">
            <label class="text-blue">本次数据导入已完成</label><br/>
            <span class="text-muted">本次提供数据 <big class="text-blue">${failNum + successNum }</big> 条，导入成功 <big class="text-green">${successNum }</big> 条，导入失败 <big class="text-red">${failNum }</big> 条，失败信息如下：</span>
        </div>
    </div>

    <div class="col-md-12 mt20">
        <div class="table-responsive">
            <table class="table table_black table_blue">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>姓名</th>
                    <th>手机号</th>
                    <th>邮箱</th>
                    <th>QQ号</th>
                    <th>失败原因</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                	<c:when test="${not empty userFailList }">
	                	<c:forEach items="${userFailList}" var="user" varStatus="status">
							<tr align="center">
								<td>${status.index + 1}</td>
								<td>${user.real_name }</td>
								<td>${user.phone }</td>
								<td>${user.mail }</td>
								<td>${user.qq }</td>
								<td>${errorMessageList[status.index] }</td>
							</tr>
						</c:forEach>
                	</c:when>
                	<c:otherwise>
                		<tr align="center"><td colspan="6">无失败数据</td></tr>
                	</c:otherwise>
                </c:choose>
                </tbody>
                <tfoot>
                <!-- <tr>
                    <td colspan="20">
                        <div class="float-right mt10" style="line-height: 23px;width: auto"><span>共</span><span class="ym text-blue">5</span><span>条记录，1/5页</span> <a class="btn btn_sm btn-default">1</a><a class="btn btn_sm btn_blue">2</a><a class="btn btn_sm btn-default">3</a><a class="btn btn_sm btn-default">4</a><a class="btn  btn_sm btn-default">5</a> 到第 <input type="text"  value="5" style="width:25px;text-align: center"/> 页 <a class="btn btn_sm btn_blue plr15">前往</a></div>
                    </td>
                </tr> -->
                </tfoot>
            </table>
        </div>
    </div>
    <div class="clearfix"> </div>
</body>
</html>