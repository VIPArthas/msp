<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <script type="text/javascript">var urlPix ="<%=request.getContextPath() %>"; </script>
    <script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath() %>/resource/wmh/web/commons/js/public.js" ></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resource/wmh/web/user/addUser.js"></script>
</head>
<body>
<!--标签页开始-->
<div class="tabs">
    <div class="row">
        <div class="col-md-2 col-sm-3 co-xs-6">添加用户</div>
        <div class="col-md-10 col-sm-9 co-xs-6"></div>
    </div>
</div>
<!--标签页结束-->
<br>
<form class="form-horizontal" id="add_user" method="post">
    <div class="form-group">
        <span class="col-md-2 col-sm-2 control-label text-black bt">姓名：</span>
        <div class="col-md-6 col-sm-10">
            <input type="text" class="form-control" id="realName" name="realName"  placeholder="请输入姓名">
        </div>
    </div>
    <div class="form-group">
        <span  class="col-md-2 col-sm-2 control-label bt">手机号：</span>
        <div class="col-md-6 col-sm-10">
            <input type="text" class="form-control" id="phone" name="phone" placeholder="请输入手机号">
        </div>
    </div>
    <div class="form-group">
        <span class="col-md-2 col-sm-2 control-label text-black bt">邮箱：</span>
        <div class="col-md-6 col-sm-10">
            <input type="text" class="form-control" id="mail" name="mail" placeholder="请输入邮箱">
        </div>
    </div>
    <div class="form-group">
        <span  class="col-md-2 col-sm-2 control-label">QQ号：</span>
        <div class="col-md-6 col-sm-10">
            <input type="text" class="form-control" id="qq" name="qq"  placeholder="请输入QQ号">
        </div>
    </div>
    <div class="form-group">
        <span  class="col-md-2 col-sm-2 control-label">标签：</span>
        <div class="col-md-6 col-sm-10">
            <div class="well">
                <div class="labellist_gray" id="tags">
                    <input type="text" class="addinput" id="labels" placeholder="添加标签" maxlength="30"  />
                </div>
                <div class="tips">注：便签最多30个字</div>
            </div>
        </div>
    </div>
    <div class="form-group">
        <span  class="col-md-2 col-sm-2 control-label">常用标签：</span>
        <div class="col-md-6 col-sm-10">
             <div class="labellist_gray labels">
             <c:choose>
             	<c:when test="${not empty list }">
             		<c:forEach items="${list }" var="tag">
             			<mark class="cur">${tag.tagName }</mark>
             		</c:forEach>
             	</c:when>
             	<c:otherwise>
             		<div>暂无标签</div>
             	</c:otherwise>
             </c:choose>
             </div>
        </div>
    </div>
    <div class="form-group">
        <div class="col-md-offset-2 col-sm-offset-2 col-md-6 col-sm-10">
            <a href="javascript:void(0);" class="btn btn_blue btn_bg" id="add" onclick="addUser()">完成</a>
        </div>
    </div>
</form>
<script type="text/javascript">
    $("#tags").on("click","mark",function(){
        var _this=this;
        $("#tags mark").removeClass("del");
        $(_this).addClass("del");
        function handler(){
            event.stopImmediatePropagation();
            if(!!$(this).data("id")){
                $(".tj mark[data-id="+$(this).data("id")+"]").removeClass("cur");
            }else{
                allLables.splice($.inArray($(this).html().trim(),allLables),1);
            }
            $(this).remove();
        }
        $("#tags").on("click",".del",handler);
    });
    var allLables=$.map($("#tags mark"),function(e){return $(e).html()});
    $(".labellist_gray input").on("focusin",function(){
        $(".labellist_gray mark").removeClass("del");
        $(".labellist_gray input").on("focusout",function(){
            var val=$(this).val();
            if(!!val){
                if($.inArray(val,allLables)<0 ){
                    $(".labellist_gray input").before("<mark class='cur' >"+val+"</mark>").val('');
                    allLables.push(val.trim());
                }else{
                    $(".tips").html("该标签已经存在").show();
                    $(".labellist_gray input").focus();
                    setTimeout(function(){
                        $(".tips").hide();
                    },2000);
                }
            }
        });
    });
    
    function addLabel(text,flag){
        if(!!text){
            if($.inArray(text,allLables)<0){
                $("#tags input").before("<mark class='cur'>"+text+"</mark>");
                allLables.push(text.trim());
                $(".labellist_gray input").val('').blur();
            }else{
                $(".tips").html("该标签已经存在").show();
                $(".labellist_gray input").focus();
                setTimeout(function(){
                    $(".tips").html("标签最多30个字符");
                },2000);
            }
        }
    }
    $(".labels").on("click","mark",function(){
        addLabel($(this).html().split('(')[0]);
    });
</script>
</body>
</html>