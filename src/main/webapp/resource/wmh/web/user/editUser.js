$(function(){	
$("#model").on("change",function(){
        $("#tab"+$(this).val()).show().siblings(".tab").hide();
    });
    /*
     text:标签内容
     num：标签上的数字
     */
    function addLabel(text,flag){
        if(!!text){
            if($.inArray(text,allLables)<0){
                $("#labels input").before("<mark class='cur'>"+text+"</mark>");
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
    $("#labels").on("click","mark",function(){
        var _this=this;
        $("#labels mark").removeClass("del");
        $(_this).addClass("del");
        function handler(e){
            e.stopImmediatePropagation();
            if(!!$(this).data("id")){
                $(".tj mark[data-id="+$(this).data("id")+"]").removeClass("cur");
            }else{
                allLables.splice($.inArray($(this).html().trim(),allLables),1);
            }
            $(this).remove();
        }
        $("#labels").on("click",".del",handler);
    });
    var allLables=$.map($("#labels mark"),function(e){return $(e).html().split("(")[0]});
    $(".labellist_gray input").on("focusin keyup",function(e){
        if(e.keyCode=="13"){
            $(this).trigger("blur");
        }else{
            var len=checksum($(this).val()),val=$(this).val();
            if(len>30){
                $(this).val(val.substr(0,val.length-1));
            }
        }
        $(".labellist_gray mark").removeClass("del");

    });
    $(".labellist_gray input").on("focusout",function(){
        addLabel($(this).val().trim().split("(")[0],1)
    });
    $(".labels").on("click","mark",function(){
        addLabel($(this).html().split('(')[0]);
    });
    
});   


function updateUser(){
		showLoading();
		$("#update").attr("onclick","");
		var id = $("#id").val();
		var realName = $("#realName").val();
    	if(realName == null || realName== ''){
    		alert("请填写姓名！");
    		$("#update").attr("onclick","updateUser()");
    		hideLoading();
    		return;
    	}
    	var phone = $("#phone").val();
    	var regPhone = /^1[3|4|5|7|8]\d{9}$/;
    	if(phone == null || phone.trim() == ''){
    		alert("请填写手机号！");
    		$("#update").attr("onclick","updateUser()");
    		hideLoading();
    		return;
    	}
    	if(!phone.match(regPhone)){
    		alert("手机号码格式不正确！");
    		$("#add").attr("onclick","updateUser()");
    		hideLoading();
    		return;
    	}
    	var mail = $("#mail").val();
    	if(mail == null || mail.trim() == ''){
    		alert("请填写邮箱！");
    		$("#update").attr("onclick","updateUser()");
    		hideLoading();
    		return;
    	}
    	var qq = $("#qq").val();
    	/*if(qq == null || qq.trim() == ''){
    		alert("请填写qq！");
    		$("#update").attr("onclick","updateUser()");
    		hideLoading();
    		return;
    	}*/
    	var tagStr = "";
    	var allLables=$.map($("#labels mark"),function(e){return $(e).html()});
    	tagStr = allLables.join(" ");
    	
    	$.post(urlPix+"/wmh/userManage/we/editUserInfo.htm",
    			{id:id,realName:realName,phone:phone,mail:mail,qq:qq,tagStr:tagStr},
    			function(response){
    				$("#update").attr("onclick","updateUser()");
    				hideLoading();
    				var data = JSON.parse(response);
    				if(data.code == 0){
    					alert(data.msg);
    					window.location.href= urlPix+"/wmh/userManage/web/goUserManageList.htm";
    				}else{
    					alert(data.msg);
    				}
    	});
    	
	}