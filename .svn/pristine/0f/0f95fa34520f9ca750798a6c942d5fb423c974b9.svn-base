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
// 编辑获取用户信息
function getBannerImage(id) {
	$(".userUpdate").html("投诉与建议");
	var id = id;
	$.post("../../../admin/adminComplaintSuggestions/web/selectAdminBySugId.htm", {
		"sugId" : id
	}, function(msg) {
		$("#userlayer").lightbox_me({
			centered : true
		});
		$("#msglist").html("");
		$("#msglist").html(msg);
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
function addBannerShow(){
	$(".userUpdate").html("新增焦点图");
	clearItem("2");
	$("#account").removeAttr("readOnly");
	$("#userlayer").lightbox_me({
		centered: true
	});
}

// 回复
function replySugestions() {
	console.log("回复");
	var reply_form = $("#reply_form");
	$(".full_mask").show();
	var replyContent = $("#replyContent").val();
	if( null == replyContent || replyContent.trim() == '') {
		$.messager.alert("提示", "回复内容不能为空");
		return;
	}
	reply_form.form("submit", {
		url:"../../../admin/adminComplaintSuggestions/web/saveAdminReply.htm",
		success: function(msg) {
			console.log(msg);
			var data = eval("("+msg+")");
			console.log(data);
			if (data.code == "1") {
				$(".full_mask").hide();
				$('#userlayer').trigger('close');
				$.messager.alert("提示", "回复成功！","info", function(data) {
					window.location.reload();
				});
			}else{
				$(".full_mask").hide();
				$.messager.alert("提示", data.msg);
			}
		}
	});


}

// 删除banner图
function deleteComSug() {
	var ids = "";
	$("input[name='userCheck']:checked").each(function() {
		ids += $(this).val() + ",";
	});
	if(ids==""){
		$.messager.alert("提示","请选择投诉与建议");
		return;
	}
	$.messager.confirm("操作提示","确定要删除这条投诉与建议吗？",function(data) {
		if (data) {
			$(".full_mask").show();
			$.post("../../../admin/adminComplaintSuggestions/web/deleteComplaintSuggestions.htm",
				{
					"ids" : ids
				}, function(data) {
					$(".full_mask").hide();
					var returnJson = JSON.parse(data);
					if (returnJson.code == 1) {
						$.messager.alert("提示", "删除成功！","info", function(data) {
							window.location.reload();
						});
					}else{
						$.messager.alert("提示",returnJson.msg,"info", function(data) {
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
