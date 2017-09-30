var index = true
var index1 = true;

$(".xialakuang>p:nth-child(1)").click(function(){
	if(index){
		index = false;
		$(".opents").css("display","block");
	}else{
		$(".opents").css("display","none");
		index = true;
	}
})
$(".opents").click(function(){
	$(".messagebian").text($(this).text());
	$(".opents").css("display","none");
})
$(".opents1").click(function(){
	$(".message1").css("display","block");
	$(".message2").css("display","none");
})
$(".opents2").click(function(){
	$(".message2").css("display","block");
	$(".message1").css("display","none");
})
$(".h_tianjiabiaoqian").blur(function(){
	var oa = true;
	var ob = false;
	var index11 = [];
	var biaoqian1 = true;
	console.log("aaa");
	$(".newdiv").each(function(i,v){
		index11 = $(v).text().split("(");
				if(($(".h_tianjiabiaoqian").val()) == index11[0]){
					$(".h_tianjiabiaoqian").val("");
					console.log("11");
					biaoqian1 = false;
					return;
				}
			});
	if($(".h_tianjiabiaoqian").val() != "" && biaoqian1){
	$("<p class='newdiv'></p>").text($(".h_tianjiabiaoqian").val()+"()").appendTo($(".tianjiabiaoqian>div:nth-child(1)")).append($("<span class='icon iconfont icon-houdongfangiconfont10'></span>").click(function(){
		if(ob){
		$(this).parent().remove();
	}
	})).click(function(){
		if(oa){
			$(this).css({"background":"#4bb1ff","color":"white"});
			oa = false;
			ob = true;
			
		}else{
			$(this).css({"background":"white","color":"#555555"});
			oa = true;
			ob = false;
		}
	});

		$(".h_tianjiabiaoqian").val("");
	}
});
$(".h_tianjiayonghu").blur(function(){
	var oa = true;
	var ob = false;
	var index11 = [];
	var biaoqian1 = true;
	console.log("aaa");
	$(".newdiv").each(function(i,v){
		index11 = $(v).text().split("(");
				if(($(".h_tianjiayonghu").val()) == index11[0]){
					$(".h_tianjiayonghu").val("");
					console.log("11");
					biaoqian1 = false;
					return;
				}
			})
	if($(".h_tianjiayonghu").val() != "" && biaoqian1){
		console.log("aaa");
//		$("<p class='newdiv'></p>").text($(".h_tianjiayonghu").val()).appendTo($(".tianjiabiaoqian>div:nth-child(1)"));
//		$(".h_tianjiayonghu").val("");
$("<p class='newdiv'></p>").text($(".h_tianjiayonghu").val()+"()").appendTo($(".tianjiabiaoqian>div:nth-child(1)")).append($("<span class='icon iconfont icon-houdongfangiconfont10'></span>").click(function(){
		if(ob){
		$(this).parent().remove();
	}
	})).click(function(){
		if(oa){
			$(this).css({"background":"#4bb1ff","color":"white"});
			oa = false;
			ob = true;
			
		}else{
			$(this).css({"background":"white","color":"#555555"});
			oa = true;
			ob = false;
		}
	});

		$(".h_tianjiayonghu").val("");
	}

})
//常用标签
$(".changyongbiaoqian").click(function(){
	var oa = true;
	var ob = false;
	var index11 = [];
	var biaoqian = true
			var inntext = this;
			$(".newdiv").each(function(i,v){
				 index11 = $(v).text().split("(");
				if($(inntext).text() == index11[0]){
					console.log("11");
					biaoqian = false;
					return;
				}
			})
            if(biaoqian){
//          	$("<p class='newdiv'></p>").text($(this).text()+"()").appendTo($(".tianjiabiaoqian>div:nth-child(1)"));
$("<p class='newdiv'></p>").text($(this).text()+"()").appendTo($(".tianjiabiaoqian>div:nth-child(1)")).append($("<span class='icon iconfont icon-houdongfangiconfont10'></span>").click(function(){
	if(ob){
		$(this).parent().remove();
	}
	})).click(function(){
		if(oa){
			$(this).css({"background":"#4bb1ff","color":"white"});
			oa = false;
			ob = true;
			
		}else{
			$(this).css({"background":"white","color":"#555555"});
			oa = true;
			ob = false;
		}
	});


            	
            }
            console.log($(".newdiv").text().split("("));
		
	});

//$(".newdiv").click(function(){
//	console.log("00");
//	$(this).css("display","none");
//	//$(this).parent().css("display","none");
//})
