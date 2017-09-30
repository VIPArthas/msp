 function addUser(){
	 	showLoading();
	 	$("#add").attr("onclick","");
    	//验证手机号和邮箱、qq号
    	var realName = $("#realName").val();
    	if(realName == null || realName== ''){
    		alert("请填写姓名！");
    		$("#add").attr("onclick","addUser()");
    		hideLoading();
    		return;
    	}
    	var phone = $("#phone").val();
    	var regPhone = /^1[3|4|5|7|8]\d{9}$/;
    	if(phone == null || phone.trim() == ''){
    		alert("请填写手机号！");
    		$("#add").attr("onclick","addUser()");
    		hideLoading();
    		return;
    	}
    	if(!phone.match(regPhone)){
    		alert("手机号码格式不正确！");
    		$("#add").attr("onclick","addUser()");
    		hideLoading();
    		return;
    	}
    	var mail = $("#mail").val();
    	if(mail == null || mail.trim() == ''){
    		alert("请填写邮箱！");
    		$("#add").attr("onclick","addUser()");
    		hideLoading();
    		return;
    	}
    	var qq = $("#qq").val();
    	/*if(qq == null || qq.trim() == ''){
    		alert("请填写qq！");
    		$("#add").attr("onclick","addUser()");
    		hideLoading();
    		return;
    	}*/
    	var tagStr = "";
    	var allLables=$.map($("#tags mark"),function(e){return $(e).html()});
    	tagStr = allLables.join(" ");
    	
    	$.post(urlPix+"/wmh/userManage/web/addUser.htm",
    			{realName:realName,phone:phone,mail:mail,qq:qq,tagStr:tagStr},
    			function(response){
    				$("#add").attr("onclick","addUser()");
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