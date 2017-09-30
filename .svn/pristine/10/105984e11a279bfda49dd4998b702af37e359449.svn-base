$(function(){
	//右侧切换
	$(".switch").click(function(){
		if($(".left_menu").is(":hidden"))
		{
			$(".left_menu").show();
			$(".content").removeClass("fullsc");
		}
		else
		{
			$(".left_menu").hide();
			$(".content").addClass("fullsc");
			}
	});
	//禁止图片拖拽
	for(var i in document.images){
		var reg=/a/i;
		document.images[i].ondragstart=function(){return false;}
	}
	//兼容火狐
	if (navigator.userAgent.indexOf('Firefox') >= 0){
		for(var i in document.images){
			if(document.images[i].parentNode.tagName.toLowerCase()=="a")
			 {			
			document.images[i].parentNode.ondragstart=function(){return false;}
			 }	 
		} 
	}
	
	//登录
	$(".bgimg").height($(window).height());
});