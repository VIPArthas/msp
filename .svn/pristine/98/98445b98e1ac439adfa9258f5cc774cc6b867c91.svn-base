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
	$(".messagebian").attr("data-template-type","1");
})
$(".opents2").click(function(){
	
	$(".message2").css("display","block");
	$(".message1").css("display","none");
	$(".messagebian").attr("data-template-type","2");
})


//输入完标签，加载人数
$(".h_tianjiabiaoqian").blur(function(){
	console.log("aaa");
	var oa = true;
	var ob = false;
	var index11 = [];
	var biaoqian1 = true;
	
	$(".newdiv").each(function(i,v){
		index11 = $(v).text().split("(");
				if(($(".h_tianjiabiaoqian").val()) == index11[0]){
					$(".h_tianjiabiaoqian").val("");
					console.log("11");
					biaoqian1 = false;
					return;
				}
			})
	if($(".h_tianjiabiaoqian").val() != "" && biaoqian1){
		
		var tagName = $(".h_tianjiabiaoqian").val().trim();
		
		if(tagName == ""){
			return;
		}
		
		//根据标签名称查询标签ID和具有该标签的用户数量
		$.ajax({
	        url: urlPix+'/wmh/message/web/getTagIdAndUserCount.htm?tagName='+tagName,
	        async:false,
	        success: function (data) {
	            var data = JSON.parse(data);
	            if(data.code == 0){
	            	alert(data.msg);
	            	return;
	            }
	            if(data.code == -1){
	            	alert("该标签不存在！");
	            	$tag.val("");
	            	return;
	            }
	            var count = data.count;
	        	if(count != 0){
	        		
	        		$("<p class='newdiv' data-type='tag' data-tag-id='"+data.tag_id+"'></p>").text(tagName+"("+count+")").appendTo($(".tianjiabiaoqian>div:nth-child(1)")).append($("<span class='icon iconfont icon-houdongfangiconfont10'></span>").click(function(){
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
	        }
	    });
		
		
		
		
		
		
	}
})
$(".h_tianjiayonghu").blur(function(){
	console.log("aaa");
	var oa = true;
	var ob = false;
	var index11 = [];
	var biaoqian1 = true;
	
	
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
		
		
		
		var userName = $(".h_tianjiayonghu").val().trim();
	   	
	   	if(userName == ""){
	   		return;
	   	}
	   	
	   	//根据用户名称获取用户id和具有该用户名的用户数量
	   	$.ajax({
	           url: urlPix+'/wmh/message/web/getUserIdAndUserCount.htm?userName='+userName,
	           async:false,
	           success: function (data) {
	               var data = JSON.parse(data);
	               if(data.code == 0){
	               	alert(data.msg);
	               	return;
	               }
	               if(data.code == -1){
	               	alert("该用户不存在！");
	               	$tag.val("");
	               	return;
	               }
	               var count = data.count;
	           	if(count != 0){
	           		$("<p class='newdiv' data-type='user' data-user-name='"+userName+"'></p>").text(userName).appendTo($(".tianjiabiaoqian>div:nth-child(1)")).append($("<span class='icon iconfont icon-houdongfangiconfont10'></span>").click(function(){
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
	           }
	       });
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
    	
    	var tagName = $(this).text().trim();
    	
    	if(tagName == ""){
    		return;
    	}
    	
    	//根据标签名称查询标签ID和具有该标签的用户数量
    	$.ajax({
            url: urlPix+'/wmh/message/web/getTagIdAndUserCount.htm?tagName='+tagName,
            async:false,
            success: function (data) {
                var data = JSON.parse(data);
                if(data.code == 0){
                	alert(data.msg);
                	return;
                }
                if(data.code == -1){
                	alert("该标签不存在！");
                	$tag.val("");
                	return;
                }
                var count = data.count;
            	if(count != 0){
            		
            		$("<p class='newdiv' data-type='tag' data-tag-id='"+data.tag_id+"'></p>").text(tagName+"("+count+")").appendTo($(".tianjiabiaoqian>div:nth-child(1)")).append($("<span class='icon iconfont icon-houdongfangiconfont10'></span>").click(function(){
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
            }
        });
    }
    console.log($(".newdiv").text().split("("));
})
//会议通知
$("#pushMsg1").click(function(){
	
	var formdata={};
	var template_type = $(".messagebian").data("templateType");
	formdata.templateType = template_type;
	formdata.parm2 = $("#parm2_1").val();
	if(formdata.parm2.trim() == ""){
		alert("会议名称不能为空！");
		return;
	}
	
	formdata.parm3 = $("#parm3_1").val();
	if(formdata.parm3.trim() == ""){
		alert("会议时间不能为空！");
		return;
	}
	
	formdata.parm4 = $("#parm4_1").val();
	if(formdata.parm4.trim() == ""){
		alert("会议地点不能为空！");
		return;
	}
	
	formdata.parm5 = $("#parm5_1").val();
	if(formdata.parm5.trim() == ""){
		alert("会议介绍不能为空！");
		return;
	}
	
	//发送方式：
	var wx1 = $("#wx1").prop("checked");
	var dx1 = $("#dx1").prop("checked");
	var yj1 = $("#yj1").prop("checked");

	if(wx1 == false &&  dx1 == false && yj1== false){
		alert("至少选择一种发送方式！");
		return;
	}
	
	if(wx1 == true){
		formdata.wxSend = 1;
	}else{
		formdata.wxSend = 0;
	}
	if(dx1 == true){
		formdata.smsSend = 1;
	}else{
		formdata.smsSend = 0;
	}
	if(yj1 == true){
		formdata.mailSend = 1;
	}else{
		formdata.mailSend = 0;
	}
	
	//发送用户：
	var toUser = "";
	//发送用户名：
	var userNameArray = "";
	$('#labels p').each(function(){
		var type = $(this).data("type");
		if(type == 'tag'){
			toUser = toUser + $(this).data("tagId") + ",";
		}else{
			userNameArray = userNameArray + $(this).data("userName") + ",";
		}
		
	})
	formdata.toUser = toUser;
	formdata.userNameArray = userNameArray;
	
	if(formdata.toUser.trim() == "" && formdata.userNameArray.trim()==""){
		alert("发送用户不能为空！");
		return;
	}
	
	if(formdata.toUser == undefined &&formdata.userNameArray==undefined){
		return;
	}
	console.info(formdata);
//	showLoading();
	 $.ajax({
	        url: urlPix+'/wmh/pushMessage/web/pushMessage.htm',
	        type: "POST",
	        data: formdata,
	        success: function (data) {
//	        	hideLoading();
	        	var retJson = JSON.parse(data);
				if(null!=retJson){
					if(retJson.type==1){
						alert("信息发送成功，本次共发送人数"+retJson.number+"人，谢谢使用！");
					}else if(retJson.type==2){
						alert("信息发送成功，谢谢使用！");
					}

				}
	        },
	        error: function (data) {

	        }
	    });
	 
	
})



//薪资发放通知
$("#pushMsg2").click(function(){
	var formdata={};
	var template_type = $(".messagebian").data("templateType");
	formdata.templateType = template_type;
	var userName = $("#userName").val();
	if(userName.trim() == ""){
		alert("用户姓名不能为空！");
		return;
	}
	
	var phone = $("#phone").val();
	if(phone.trim() == ""){
		alert("手机号不能为空！");
		return;
	}
	
	var regPhone = /^1[3|4|5|7|8]\d{9}$/;
	if(!phone.match(regPhone)) {
		alert("手机号格式不正确！");
		return;
	}
	
	//根据手机号查询用户ID
	 $.ajax({
	        url: urlPix+'/wmh/message/web/getUserIdByPhone.htm?phone='+phone+'&realName='+userName,
	        type: "GET",
	        async:false,
	        success: function (data) {
	        	var data = JSON.parse(data);
                if(data.code == 0){
                	alert(data.msg);
                	return;
                }
                formdata.toUser = data.user_id;
                formdata.parm2 = $("#parm2_2").val();
        		if(formdata.parm2.trim() == ""){
        			alert("工资年月不能为空！");
        			return;
        		}
        		
        		var yfgz = $("#parm3_2").val();
        		if(yfgz.trim() == ""){
        			alert("应付工资不能为空！");
        			return;
        		}
        		
        		if(yfgz>2147483647){
    		        alert("应付工资格式不正确!");
    		        return;
    		    }
        		yfgz = yfgz * 100;
    		    if(/(?=[\x21-\x7e]+)[^A-Za-z0-9]/.test(yfgz)==true){
    		    	alert("应付工资格式不正确!");
    		        return;
    		    }
    		    yfgz = yfgz/100;
    		    if(yfgz!=null && yfgz!=""){
    		        check=/^[0-9]+(.[0-9]{1,2})?$/;
    		        if(!check.test(yfgz)){
    		            alert("应付工资格式不正确!");
    		            return;
    		        }
    		    }
        		    
    		    formdata.parm3 = yfgz;
    		    
        		var sfgz = $("#parm4_2").val();
        		if(sfgz.trim() == ""){
        			alert("实发工资不能为空！");
        			return;
        		}
        		
        		if(sfgz>2147483647){
    		        alert("实发工资格式不正确!");
    		        return;
    		    }
        		sfgz = sfgz * 100;
        		
    		    if(/(?=[\x21-\x7e]+)[^A-Za-z0-9]/.test(sfgz)==true){
    		    	alert("实发工资格式不正确!");
    		        return;
    		    }
    		    sfgz = sfgz / 100;
    		    if(sfgz!=null && sfgz!=""){
    		        check=/^[0-9]+(.[0-9]{1,2})?$/;
    		        if(!check.test(sfgz)){
    		            alert("实发工资格式不正确!");
    		            return;
    		        }
    		    }
    		    
    		    formdata.parm4 = sfgz;
        		
        		//发送方式：
        		var wx1 = $("#wx2").prop("checked");
        		var dx1 = $("#dx2").prop("checked");
        		var yj1 = $("#yj2").prop("checked");
        	
        		if(wx1 == false &&  dx1 == false && yj1== false){
        			alert("至少选择一种发送方式！");
        			return;
        		}
        		
        		if(wx1 == true){
        			formdata.wxSend = 1;
        		}else{
        			formdata.wxSend = 0;
        		}
        		if(dx1 == true){
        			formdata.smsSend = 1;
        		}else{
        			formdata.smsSend = 0;
        		}
        		if(yj1 == true){
        			formdata.mailSend = 1;
        		}else{
        			formdata.mailSend = 0;
        		}
        		
        		if(formdata.toUser == undefined &&formdata.userNameArray==undefined){
        			return;
        		}
        		console.info(formdata);
//        		showLoading();
        		 $.ajax({
        		        url: urlPix+'/wmh/pushMessage/web/pushMessage.htm',
        		        type: "POST",
        		        data: formdata,
        		        success: function (data) {
//        		        	hideLoading();
        		        	var retJson = JSON.parse(data);
        					if(null!=retJson){
        						if(retJson.type==1){
        							alert("信息发送成功，本次共发送人数"+retJson.number+"人，谢谢使用！");
        						}else if(retJson.type==2){
        							alert("信息发送成功，谢谢使用！");
        						}

        					}
        		        },
        		        error: function (data) {

        		        }
        		    });
        		 
	        },
	        error: function (data) {

	        }
	    });
	 
})
