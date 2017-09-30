/**
 * 微门户手机端后台管理首页js
 */
$("iframe").height($(".mui-inner-wrap").height()+$("html").height());

	$(".top>a:nth-child(1)").click(function(){
		$(".wrapcontent").css("left","0");
		$(".mui-inner-wrap").css("left","70%");
		$(".mengban").css("display","block");
	});
	$(".mengban").click(function(){
		$(".wrapcontent").css("left","-70%");
		$(".mui-inner-wrap").css("left","0");
		$(".mengban").css("display","none");
	});
	$(".yonghuguanli").click(function(){
		$(".wrapcontent").css("left","-70%");
		$(".mui-inner-wrap").css("left","0");
		$(".mengban").css("display","none");
	});
	touch.on("#wrap","swipeleft swiperight swipeup swipedown",swipeAction);
	function swipeAction(ev){
		console.log(ev);
		switch (ev.type){
			case "swipeleft":
				$(".wrapcontent").css("left","-70%");
				$(".mui-inner-wrap").css("left","0");
				$(".mengban").css("display","none");
				console.log("向左清扫了");
				break;
			case "swiperight":
				$(".wrapcontent").css("left","0");
				$(".mui-inner-wrap").css("left","70%");
				$(".mengban").css("display","block");
				console.log("向右清扫了");
				break;
			case "swipeup":
				console.log("向上清扫了");
				break;
			case "swipedown":
				console.log("向下清扫了");
				break;
		}
	}