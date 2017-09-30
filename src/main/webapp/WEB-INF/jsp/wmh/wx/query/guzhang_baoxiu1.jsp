<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" style="background-color: #f3f3f3">
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0, minimal-ui">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <title>故障报修</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/commons/css/font3/font_5y12bci07sbdquxr/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/commons/css/index.css"/>
    <script src="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/js/jquery-3.1.1.js"></script> 
	<script type="text/javascript">var urlPix = "<%=request.getContextPath()%>";</script>
    <script type="text/javascript"
		src="<%=request.getContextPath()%>/resource/wmh/wx/query/guzhang.js"  charset="UTF-8">
	</script>
</head>
<body>
<section class="guzhang">
<form class="form-inline" id="gzbx_form"  >
    <div class="where">在什么地方</div>
    <input class="write-where" type="text">
    <div class="what" >什么故障</div>
    <div class="write-what">
        <textarea name="faultInfo" id="faultInfo" cols="30" rows="10"></textarea>
        <section id="imgs">

        </section>
        <input type="file" id="file" accept="image/png,image/jpg,image/jpeg,image/gif">
        <div class="icon iconfont icon-paizhao"></div>
    </div>
    <div class="reson">可能故障的原因</div>
    <textarea class="write-reson" name="faultReason" cols="30" rows="10" placeholder="可能引起故障的原因是什么，不知道可不填"></textarea>
    <button id="submit" class="guzhang-submit"  onclick='saveFault()'>提交</button>
</form>
</section>
</body>
</html>
<script src="<%=request.getContextPath() %>/resource/wmh/wx/commons/js/rem.js"></script>
<script src="<%=request.getContextPath() %>/resource/wmh/wx/commons/js/jquery-3.1.1.js"></script>
<script>
    //创建图片对象
    var img = new Image();
    var canvas = document.createElement("canvas");
    var ctx = canvas.getContext("2d");
    $("#file").on("change", function() {
        //获取input标签选择的图片对象
        var file = document.getElementById("file").files[0];
        //读取选取的文件对象
        var reader = new FileReader();
        reader.onload = function() {
            var url = this.result; //获取读取的网址链接
            img.src = url;
        };
        reader.readAsDataURL(file);

        //当图片完全加载成功的时候开始绘制图片
        img.onload = function() {
            //获取图片的原始尺寸
            var w = img.naturalWidth;
            var h = img.naturalHeight;
            canvas.width = 100;
            canvas.height = 100*h/w;

            ctx.drawImage(img, 0, 0, w, h, 0, 0, canvas.width, canvas.height);
            //将canvas上面的图片转换成网址链接
            var dataUrl = canvas.toDataURL("image/jpeg", 1);
                if(document.querySelectorAll('#imgs img').length < 9){
                    $('<div><img onclick="del($(this))" src="'+dataUrl+'"/><span onclick="dell($(this))" class="icon iconfont icon-houdongfangiconfont10"></span></div>').appendTo($("#imgs"));
                }

        }
    });
    function del(th) {
        console.log(th.siblings('span'));
        th.siblings('span').click();

    }
    function dell(thi) {
        console.log('666');
        console.log(thi);
        thi.parent().remove();

    }


</script>