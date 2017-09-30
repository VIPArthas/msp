<%@ page import="com.wh.constants.Constants" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/pageTag.tld" prefix="page" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="校联网,微门户"/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);
    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <title>校联网微门户-后台管理</title>
    <link href="<%=request.getContextPath() %>/resource/wmh/web/commons/css/font/iconfont.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resource/wmh/web/commons/css/style.css" rel="stylesheet" type="text/css"
          media="all"/>
    <script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resource/wmh/web/commons/js/public.js"></script>
</head>
<body>
<!--标签页开始-->
<div class="tabs">
    <div class="row">
        <div class="col-md-2 col-sm-3 co-xs-6">在线支付</div>
        <div class="col-md-10 col-sm-9 co-xs-6"></div>
    </div>
</div>
<!--标签页结束-->
<div class="row mt20">
    <form class="form-inline" action="<%=request.getContextPath()%>/wmh/payManage/web/goPayRecordPage.htm"
          method="post" id="search_form">
        <div class="col-md-1" style="text-align: right"><span style="line-height: 36px;">付款项:</span></div>
        <div class="form-group col-md-2">
            <%--<input type="text" class="form-control" name="payTypeName" style="width: 100%">--%>
            <%--</input>--%>
            <select class="form-control input-md" style="width: 100%" name="payType">
                <option value="">全部</option>
                <option value="12" ${payType==12?'selected':''}>考试费</option>
                <option value="13" ${payType==13?'selected':''}>学费</option>
                <option value="14" ${payType==14?'selected':''}>资料费</option>
                <option value="15" ${payType==15?'selected':''}>爱心捐助</option>
            </select>

        </div>
        <div class="col-md-1" style="text-align: right"><span style="line-height: 36px;">时间:</span></div>
        <div class="form-group col-md-2">
            <input type="date" class="form-control" name="payTime"   style="width: 100%"   value="${payData}" >
        </div>
        <div class="col-md-1" style="text-align: right"><span style="line-height: 36px;">付款人:</span></div>
        <div class="form-group col-md-2">
            <input type="text" class="form-control" name="realName" value="${realName}" placeholder="付款人" style="width: 100%">
        </div>
        <div class="col-md-3">
            <a class="btn btn_blue" href="javascript:document:search_form.submit();"> 搜索 </a>
        </div>
    </form>
</div>
<!--table start-->
<div class="col-md-12 mt20">
    <div class="table-responsive">
        <table class="table table_black table_blue">
            <thead>
            <tr>
                <th>付款人</th>
                <th>付款金额</th>
                <th>付款项</th>
                <th>时间</th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${not empty payRecordList[0]}">
                    <c:forEach items="${payRecordList[0]}" var="pay">
                        <tr>
                            <td>${pay.real_name}</td>
                            <td>${pay.pay_school_money/100}元</td>
                            <td>${pay.pay_type}</td>
                            <td>${pay.pay_time}</td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr><td colspan="4" style="text-align: center">暂无数据</td></tr>
                </c:otherwise>

            </c:choose>
            <%--<tr>                <td>Malorum</td><td>12345678901</td><td>该手机号已存在</td><td>abc@123.com</td>            </tr>--%>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="20">
                    <page:page currentpage="${payRecordList[1]}" rscount="${payRecordList[2]}" className="adminPage"
                               pagesize="<%=Constants.wmhPageSize%>"
                               action="wmh/payManage/web/goPayRecordPage.htm"/>
                    <%--<div class="float-right mt10" style="line-height: 23px;width: auto"><span>共</span><span--%>
                    <%--class="ym text-blue">5</span><span>条记录，1/5页</span> <a class="btn btn_sm btn-default">1</a><a--%>
                    <%--class="btn btn_sm btn_blue">2</a><a class="btn btn_sm btn-default">3</a><a--%>
                    <%--class="btn btn_sm btn-default">4</a><a class="btn  btn_sm btn-default">5</a> 到第 <input--%>
                    <%--type="text" value="5" style="width:25px;text-align: center"/> 页 <a--%>
                    <%--class="btn btn_sm btn_blue plr15">前往</a></div>--%>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
</div>
<div class="clearfix"></div>
<!--table end-->


</body>
</html>