
$(document).ready(function(){
	$(".overflowy").mousedown(function(){
		$(".quickmenu").animate({
			 left:'-100%'
		  });
		$(this).hide();
	})

})


var aa = function(){  //显示
	$(".quickmenu").animate({ left:'0' });
	$(".overflowy").show();
}
var bb = function(){// 隐藏
	$(".quickmenu").animate({ left:'-100%' });
	$(".overflowy").hide();
}
	
var m = function(a){
	if(a==1){
		aa();
	}
	if(a==2){
		bb();
	}
	if(a==1){
		$(".header_licon a").attr("onclick","m(2)");
	}
	if(a==2){
		$(".header_licon a").attr("onclick","m(1)");
	}
}