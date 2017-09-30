<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传校历</title>
<link href="<%=request.getContextPath() %>/resource/wmh/web/commons/css/font/iconfont.css" rel="stylesheet">
<link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/resource/wmh/web/commons/css/style.css" rel="stylesheet" type="text/css" media="all"/>
<script type="text/javascript">var urlPix ="<%=request.getContextPath() %>"; </script>
<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath() %>/resource/wmh/web/commons/js/public.js" ></script>
<script src="<%=request.getContextPath() %>/resource/wmh/web/commons/js/organization.js" ></script>
</head>
<body>
<div class="tabs">
    <div class="row">
        <div class="col-md-2 col-sm-3 co-xs-6">校历2</div>
        <div class="col-md-10 col-sm-9 co-xs-6"></div>
    </div>
</div>
<div class="xiaoli-wrap">
<div class="upload-button">
<form id="imgData" method="post" enctype="multipart/form-data">
<canvas id="img-view"></canvas>
<div class="ul-text"><i class="fa">&#xe64b;</i> 上传校历</div>
<input id="XL" type="file">
</form>
</div>
</div>

<div class="row last-step"><a class="btn btn_blue" id="submit" href="javascript:void(0)">提交</a></button></div>

<script type="text/javascript">
var input1 = document.getElementById("XL"); 
if(typeof FileReader==='undefined'){ 
     input1.setAttribute('disabled','disabled'); 
}else{ 
     input1.addEventListener('change',readFile,false); 
}
function readFile(){ 
	var file = this.files[0];
	if(!/image\/\w+/.test(file.type)){
		alert("文件必须为图片！");
		return false; 
	} 
	var reader = new FileReader();
	reader.readAsDataURL(file);
	reader.onload = function(e){ 
		var image = new Image();
		image.src = e.target.result;
		var max=200;
		image.onload = function(){ 
			var canvas = document.getElementById("img-view"); 
			var ctx = canvas.getContext("2d"); 
			ctx.clearRect(0, 0, canvas.width, canvas.height); 
			ctx.drawImage(image,0,0,canvas.width,canvas.height);
		};  
	}
$(".ul-text").css({"background-color":"rgba(0,0,0,0.5)","color":"#fff"});
};
function dataURLtoBlob(dataurl) {
    var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
        bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
    while(n--){
        u8arr[n] = bstr.charCodeAt(n);
    }
    return new Blob([u8arr], {type:mime});
}
$("#submit").click(function(){
	var pic=document.getElementById("img-view").toDataURL();
	var images=dataURLtoBlob(pic);
	
	var formdata=new FormData($("#imgData")[0]);
	formdata.append('imgs',images);
 $.ajax({
		type:"post",
		dataType:"undefined",
		cache:false,
		url:"<%=request.getContextPath()%>/msp/mspPhoto/wx/saveCalendar.htm",
		async:false,
		data:formdata,
		processData:false,
	    success:function(data){
	    }
	})
})

</script>
</body>
</html>