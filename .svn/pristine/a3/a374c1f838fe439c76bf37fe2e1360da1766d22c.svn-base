<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en" style="background-color: #f3f3f3">
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0, minimal-ui">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <link href="//cdn.bootcss.com/weui/0.4.2/style/weui.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resource/wmh/wx/commons/css/font2/iconfont/iconfont.css" rel="stylesheet" />
    <script src="<%=request.getContextPath() %>/resource/wmh/wx/commons/js/jquery-3.1.1.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=request.getContextPath() %>/resource/wmh/wx/commons/js/rem.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resource/wmh/wx/commons/js/WapCircleImg.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resource/wmh/wx/commons/js/public.js"></script>
    <link href="<%=request.getContextPath() %>/resource/wmh/wx/commons/css/sendsuishoup.css" rel="stylesheet" />
    <title>发布随手拍</title>

</head>
<body style="background-color: #f3f3f3" >
<div class="content">
    <form class="form-inline" id="gzbx_form"  >
        <div class="box mt10">
            <input type="hidden" id="userid" name="userId"> 
            <textarea name="msg" id="faultInfo" class="textarea wrap" placeholder="说点什么吧"  ></textarea>
            <div class="imglist3" style="margin-top: -2vh;">
                <div class="aimg"><input type="file" accept="image/*" capture="camera" class="addimg" onchange="changeHandler(this)" accept="image/png,image/jpg,image/jpeg,image/gif" /> <span style="height:32vw"><i class="icon iconfont icon-shangchuantupian"></i></span></div>
            </div>
            <canvas id="mycanvas"></canvas>
            <div class="clearfix"></div>
        </div>
        <br>
        <div class="mlr20" id="hf">
            <a id="submit" class="btn_bg btn_green" >发&nbsp;布</a>
        </div>
    </form>
</div><br>


</body>
</html>
<script type="text/javascript">
$('#userid').val(localStorage.getItem('userid'));
$("#submit").on("click",function(){
	
		//显示进度条
    	//showLoading();
		var faultInfo = $("#faultInfo").val();
		if(faultInfo == "" || faultInfo == null){
			alert("写点随手拍分享吧~");
			return;
		}
    	var formdata=new FormData($("#gzbx_form")[0]);
    	for(var i=0;i<$(".imglist3 img").length;i++){
        	/* fromdata.append("imgs", convertdataURIToBlob_j($(".imglist3 img").get(i).src)); */
        	formdata.append("imgs", convertdataURIToBlob_j($(".imglist3 img").get(i).src),"img"+i+'.'+format.split('/')[1]);
        }
        console.log(formdata);
        	$.ajax({
    		url:'<%=request.getContextPath()%>/msp/mspPhoto/wx/savePhoto.htm',
    		type:'POST',
    		dataType:'json',
    		processData:false,
    		contentType:false,
    		data:formdata,
    		success:function(result){
    			if(result.success){
    				localStorage.removeItem('spobj');
    				localStorage.removeItem('sppage');
    				localStorage.setItem('sptype',1);
    				localStorage.removeItem('sptop');
    				window.location.href="<%=request.getContextPath() %>/resource/msp/wx/html/suishou/suipai.html";	
    		  	}
    		},
    		error:function(){
    			alert('发布失败咯。');
    			console.info('error');
    		} 
    	});
    });
	
	function changeHandler(obj){
        addImg(obj,9);
    };
</script>