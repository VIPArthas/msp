
function myformatter(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}
function formatDate(time)
{
	var year=time.getYear() +1900;
	var month=time.getMonth()+1;
	var date=time.getDate();
	var hour=time.getHours();
	var minute=time.getMinutes();
	var second=time.getSeconds();
	return year+"-"+month+"-"+date;
}

function myparser(s){
	if (!s) return new Date();
	var ss = (s.split('-'));
	var y = parseInt(ss[0],10);
	var m = parseInt(ss[1],10);
	var d = parseInt(ss[2],10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
		return new Date(y,m-1,d);
	} else {
		return new Date();
	}
}
//var curr_time = new Date();
//$("#user_info_form input.time").datebox("setValue",myformatter(curr_time));
////      $("#user_info_form input.time").datebox("setValue",myformatter(curr_time));
////获取时间
//var beginTime=$("#user_info_form input.time").datebox("getValue");
////      var endTime=$("#user_info_form input.time").datebox("getValue");
//console.log(beginTime);



// 编辑获取用户信息
function getUserInfo(id) {
	$(".userUpdate").html("修改用户");
	var user_id = id;
	$.post("../../../admin/adminUser/web/queryUserRealStudent.htm", {
		"user_id" : user_id
	}, function(msg) {
		var data = JSON.parse(msg);
		console.log(data);
		if(null != data.userMap.birth_date && data.userMap.birth_date != "") {
			var birthDate = formatDate(new Date(data.userMap.birth_date.time));
			$("#birth_date").datebox("setValue", birthDate);
		}
		if (null != data.userMap.start_time && data.userMap.start_time != "") {
			var startTime = formatDate(new Date(data.userMap.start_time.time));
			$("#start_time").datebox("setValue", startTime);
		}

		console.log("设置完成")
		$("#user_id").val(data.userMap.user_id);
		$("#nick_name").val(data.userMap.nick_name);
		$("#real_name").val(data.userMap.real_name);
		$("#Iphone").val(data.userMap.phone);
		var sex = data.userMap.sex;
		$("input[name=sex]").get(sex).checked = true;
		//$('input[name=items]').get(1).checked = true;
		/*$("input[type=radio]").each(function(){
			if($(this).val()==sex)
			{
				$(this).attr("checked","checked");
			}
		});*/
		$("#logoImg").attr("src", data.userMap.logo_url);
		//$("#logoUrl").val(data.userMap.logo_url);
		$("#card_id").val(data.userMap.card_id);
		$("#wechat").val(data.userMap.wechat);
		$("#qq").val(data.userMap.qq);
		$("#alipay").val(data.userMap.alipay);
		$("#birth_place").val(data.userMap.birth_place);
		$("#school_name").val(data.userMap.school_name);
		$("#student_id").val(data.userMap.student_id);
		$("#dormitory_no").val(data.userMap.dormitory_no);

		$("#major_name").val(data.userMap.major_name);
		$("#pay_password").val(data.userMap.pay_password);
		$("#user_status").val(data.userMap.user_status);
		$("#realname_status").val(data.userMap.realname_status);
		$("#score").val(data.userMap.score);
		$("#school_money").val(data.userMap.school_money);
		$("#edusys_name").val(data.userMap.edusys_name);
		$("#edusys_pwd").val(data.userMap.edusys_pwd);
		$("#password").val(data.userMap.password);

		$("#userlayer").lightbox_me({
			centered : true
		});
	});
}
// 清空信息
function clearItem(type) {
	// 清空用户信息
	if (type == "2") {
		$("#user_id").val("");
		$("#account").val("");
		$("#name").val("");
		$("#id_card").val("");
		$("#mobile").val("");
		$("#phone").val("");
		$("#mail").val("");
		$("#address").val("");
		$(".sex").val("");
	}

}
function addUserShow(){
	$(".userUpdate").html("新增用户");
	clearItem("2");
	$("#account").removeAttr("readOnly");
	$("#userlayer").lightbox_me({
		centered: true
	});
}

function preivew(file) {
	var imgType = ["jpg", "gif", "bmp", "png", "jpeg"];
	var isVaild = true;
	if (file) {
		if (file.files && file.files.length > 0) {
			var fileList = file.files;
			for (var i = 0; i < fileList.length; i++) {
				var suffix = fileList[i].name.substr(fileList[i].name.lastIndexOf(".") + 1, fileList[i].name.length);
				if (imgType.indexOf(suffix) == -1) {
					isVaild = false;
				}
			}
		}
	} else {
		isVaild = false;
	}
	if (isVaild == false) {
		$.messager.alert("提示", "请上传正确格式的文件！", "error");
		return;
	} else {
		$(".txsz_yh").attr("src", window.URL.createObjectURL(file.files[0]));
	}
}

// 编辑/展示用户信息
function updateUserInfo() {
	var real_name = $("#real_name").val();
	var phone = $("#Iphone").val();
	var card_id = $("#card_id").val();

	var regPhone = /^1[3|4|5|7|8]\d{9}$/;
	if(phone == null || phone.trim() == '') {
		$.messager.alert("提示", '手机号不能为空');
		return;
	}else if(!phone.match(regPhone)) {
		$.messager.alert("提示", '手机号格式不正确 ');
		return;
	}

	var cardReg = /^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/
	if(card_id != null && card_id != '' && !card_id.match(cardReg)) {
		$.messager.alert("提示", '身份证格式不正确 ');
		return;
	}


	var userInfoForm = $("#user_info_form");
	$(".full_mask").show();
	userInfoForm.form("submit", {
		url:"../../../admin/adminUser/web/updateUser.htm",
		success: function(msg) {
			console.log(msg);
			var data = eval("("+msg+")");
			console.log(data);
			if (data.code == "1") {
				$(".full_mask").hide();
				$('#userlayer').trigger('close');
				$.messager.alert("提示", "修改用户成功！","info", function(data) {
					window.location.reload();

				});
			}else {
				$(".full_mask").hide();
				$.messager.alert("提示", data.msg);
			}
		}
	});



		

}

// 删除用户或者部门信息
function deleteUserOrDepartInfo() {
	var user_ids = "";
	$("input[name='userCheck']:checked").each(function() {
		user_ids += $(this).val() + ",";
	});
	if(user_ids==""){
		$.messager.alert("提示","请选择用户");
		return;
	}
	$.messager.confirm("操作提示","确定要删除用户么？",function(data) {
						if (data) {
							$(".full_mask").show();
							$.post("../../../admin/adminUser/web/deleteUser.htm",
								{
									"user_ids" : user_ids
								}, function(data) {
									var returnJson = JSON.parse(data);
									$(".full_mask").hide();
									if (returnJson.code == "1") {
										$.messager.alert("提示", "删除成功！","info", function(data) {
											window.location.reload();
										});
									}
						});
						}
					});
}
//禁用用户
function disableUserOrDepartInfo() {
	var user_ids = "";
	$("input[name='userCheck']:checked").each(function() {
		user_ids += $(this).val() + ",";
	});
	if(user_ids==""){
		$.messager.alert("提示","请选择用户");
		return;
	}
	$.messager.confirm("操作提示","确定要禁用此用户么？",function(data) {
						if (data) {
							$(".full_mask").show();
							$.post("../../../admin/adminUser/web/disableUser.htm",
											{
												"user_ids" : user_ids,
												"user_status" : 1
											}, function(data) {
												$(".full_mask").hide();
												if (data == "1") {
													$.messager.alert("提示", "禁用成功！","info", function(data) {
														window.location.reload();
													});
												}
											});
						}
					});
}



//启用用户
function ableUserOrDepartInfo() {
	var user_ids = "";
	$("input[name='userCheck']:checked").each(function() {
		user_ids += $(this).val() + ",";
	});
	if(user_ids==""){
		$.messager.alert("提示","请选择用户");
		return;
	}
	$.messager.confirm("操作提示","确定要启用此用户么？",function(data) {
		if (data) {
			$(".full_mask").show();
			$.post("../../../admin/adminUser/web/disableUser.htm",
				{
					"user_ids" : user_ids,
					"user_status" : 0
				}, function(data) {
					$(".full_mask").hide();
					if (data == "1") {
						$.messager.alert("提示", "启用成功！","info", function(data) {
							window.location.reload();
						});
					}
				});
		}
	});
}
function resetPwd() {
	// 读取所有的选中的记录
	var userInfoId = new Array();
	var checkedArr = $("input[name='userCheck']:checked");
	if (checkedArr != null && checkedArr != undefined) {
		for (var i = 0; i < checkedArr.length; i++) {
			var temp = checkedArr[i];
			userInfoId.push(temp.value);
		}
	}
	if(userInfoId.length==0){
		$.messager.alert("提示","请选择用户");
		return;
	}
	$.messager.confirm("操作提示", "确定要重置密码么？", function(data) {
		if (data) {

			$.post("../../../admin/adminUser/web/updateUserPassword.htm", {
				"user_ids" : userInfoId.toString()
			}, function(data) {
				if (data == "1") {
					$.messager.alert("提示", "重置密码成功！","info", function(data) {
						window.location.reload();

					});
				}
			});
		} 
	});

}

// 刷新
function refsh() {
	window.location.reload();
}
