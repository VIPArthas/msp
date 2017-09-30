
$(function(){	
	$(".h_tianjiabiaoqian").blur(function(){
        var oa = true;
        var ob = false;
        var index11 = [];
        var biaoqian1 = true;
        console.log("aaa");
        $(".newdiv").each(function(i,v){
            index11 = $(v).text().split(" ");
            if(($(".h_tianjiabiaoqian").val()) == index11[0]){
                $(".h_tianjiabiaoqian").val("");
                console.log("11");
                biaoqian1 = false;
                return;
            }
        })
        if($(".h_tianjiabiaoqian").val() != "" && biaoqian1){
            console.log("aaa");

            $("<p class='newdiv'></p>").text($(".h_tianjiabiaoqian").val()+" ").appendTo($(".tianjiabiaoqian>div:nth-child(1)")).append($("<span class='icon iconfont icon-houdongfangiconfont10'></span>").click(function(){
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

    })
    //常用标签
    $(".changyongbiaoqian").click(function(){
        var oa = true;
        var ob = false;
        var index11 = [];
        var biaoqian = true
        var inntext = this;
        $(".newdiv").each(function(i,v){
            index11 = $(v).text().split(" ");
            if($(inntext).text() == index11[0]){
                console.log("11");
                biaoqian = false;
                return;
            }
        })
        if(biaoqian){
//          	$("<p class='newdiv'></p>").text($(this).text()+"()").appendTo($(".tianjiabiaoqian>div:nth-child(1)"));
            $("<p class='newdiv'></p>").text($(this).text()+" ").appendTo($(".tianjiabiaoqian>div:nth-child(1)")).append($("<span class='icon iconfont icon-houdongfangiconfont10'></span>").click(function(){
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
        console.log($(".newdiv").text().split(" "));

    });
    
});     
	//编辑后保存
    function updateUser(){
   
    		$("#update").attr("onclick","");
    		var id = $("#id").val();
    		var realName = $("#realName").val();
        	if(realName == null || realName== ''){
        		alert("请填写姓名！");
        		$("#update").attr("onclick","updateUser()");
        		
        		return;
        	}
        	var phone = $("#phone").val();
        	var regPhone = /^1[3|4|5|7|8]\d{9}$/;
        	if(phone == null || phone.trim() == ''){
        		alert("请填写手机号！");
        		$("#update").attr("onclick","updateUser()");
        	
        		return;
        	}
        	if(!phone.match(regPhone)){
        		alert("手机号码格式不正确！");
        		$("#add").attr("onclick","updateUser()");
        		
        		return;
        	}
        	var mail = $("#mail").val();
        	if(mail == null || mail.trim() == ''){
        		alert("请填写邮箱！");
        		$("#update").attr("onclick","updateUser()");
        	
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
        	
        	tagStr = $(".newdiv").text().split(" ");
        	tagStr=tagStr.join(",");
        	console.log(tagStr);
        	$.post(urlPix+"/wmh/userManage/we/editUserInfoWx.htm",
        			{id:id,realName:realName,phone:phone,mail:mail,qq:qq,tagStr:tagStr},
        			function(response){
        				var data = JSON.parse(response);
        				if(data.code == 0){
        					alert(data.msg);
        					window.location.href= urlPix+"/wmh/userManage/web/getUserCountPageWx.htm";
        				}else{
        					alert(data.msg);
        				}
        	});
        	
    	}    
    
    
    
    
    //新增后保存      
	 function addUser(){
		 	debugger;
		 	$("#add").attr("onclick","");
	    	//验证手机号和邮箱、qq号
	    	var realName = $("#realName").val();
	    	if(realName == null || realName== ''){
	    		alert("请填写姓名！");
	    		$("#add").attr("onclick","addUser()");
	    		
	    		return;
	    	}
	    	var phone = $("#phone").val();
	    	var regPhone = /^1[3|4|5|7|8]\d{9}$/;
	    	if(phone == null || phone.trim() == ''){
	    		alert("请填写手机号！");
	    		$("#add").attr("onclick","addUser()");
	    		
	    		return;
	    	}
	    	if(!phone.match(regPhone)){
	    		alert("手机号码格式不正确！");
	    		$("#add").attr("onclick","addUser()");
	    		
	    		return;
	    	}
	    	var mail = $("#mail").val();
	    	if(mail == null || mail.trim() == ''){
	    		alert("请填写邮箱！");
	    		$("#add").attr("onclick","addUser()");
	    	
	    		return;
	    	}
	    	var qq = $("#qq").val();
	    	/*if(qq == null || qq.trim() == ''){
	    		alert("请填写qq！");
	    		$("#add").attr("onclick","addUser()");
	    	
	    		return;
	    	}*/
	    	var tagStr = "";
	    	tagStr = $(".newdiv").text().split(" ");
	    	tagStr = tagStr.join(",");
	    	
	    	$.post(urlPix+"/wmh/userManage/web/addUserWx.htm",
	    			{realName:realName,phone:phone,mail:mail,qq:qq,tagStr:tagStr},
	    			function(response){
	    				$("#add").attr("onclick","addUser()");
	    			
	    				var data = JSON.parse(response);
	    				if(data.code == 0){
	    					alert(data.msg);
	    					window.location.href= urlPix+"/wmh/userManage/web/getUserCountPageWx.htm";
	    				}else{
	    					alert(data.msg);
	    				}
	    	});
	    }      
               
    
    
    
    
    
    
    
    
    
    