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
	$(".userUpdate").html("修改焦点图信息");

	var id = id;
	$.post("../../../admin/adminBanner/web/queryBannerImage.htm", {
		"id" : id
	}, function(msg) {
		var data = JSON.parse(msg);
		$("#id").val(data.bannerImages.id);
		$("#logoImg").attr("src","../../../"+data.bannerImages.bannerUrl);
		$("#link_url").val(data.bannerImages.linkUrl);
		var status = data.bannerImages.bannerStatus;
		$("input[name=bannerStatus]").each(function(){
			if($(this).val()==status)
			{
				$(this).attr("checked","checked");
			}
		});
		$("#init_link").val(data.bannerImages.initLink)
		$("#banner_remark").val(data.bannerImages.bannerRemark);
		$("#banner_sort").val(data.bannerImages.bannerSort);
		$("#userlayer").lightbox_me({
			centered : true
		});
	});
}
// 清空信息
function clearItem(type) {
	
		$("#id").val("");
		$("#logoImg").attr("src","");
		$("#link_url").val("");
		
		$("#init_link").val("")
		$("#banner_remark").val("");
		$("#banner_sort").val("");
	

}
function addBannerShow(){
	$(".userUpdate").html("新增焦点图");
	clearItem("2");
	$("#account").removeAttr("readOnly");
	$("#userlayer").lightbox_me({
		centered: true
	});
}

// 新增焦点图
function insertBannerImage() {
	
	var bannerForm = $("#banner_form");
	$(".full_mask").show();
	var logoImg = $("#logoImg");
	var link_url = $("#link_url").val();
	if( null == logoImg || null == logoImg[0].src || logoImg[0].src == '') {
		$.messager.alert("提示", "请上传图片！")
		return;
	}
	if(null == link_url || link_url.trim() == '') {
		$.messager.alert("提示", "请填写焦点图对应的链接！")
		return;
	}
	bannerForm.form("submit", {
		url:"../../../admin/adminBanner/web/updateBanner.htm",
		success: function(msg) {
			console.log(msg);
		
			var data = eval("("+msg+")");
			console.log(data);
			if (data.code == "1") {
				$(".full_mask").hide();
				$('#userlayer').trigger('close');
				$.messager.alert("提示", "修改焦点图成功！","info", function(data) {
					window.location.reload();

				});
			}else if(data.code == "2") {
				$(".full_mask").hide();
				$('#userlayer').trigger('close');
				$.messager.alert("提示", "新增焦点图成功！","info", function(data) {
					window.location.reload();
				});
			}else{
				$(".full_mask").hide();
				$.messager.alert("提示", data.msg);
			}
		}
	});






	$.post("../../../admin/system/userinfo/insertUserInfo.htm", {
		"data" : JSON.stringify(obj)
	}, function(msg) {
		var data = JSON.parse(msg);
		if (data.status == "1") {
			$(".full_mask").hide();
			$.messager.alert("提示", "添加焦点图成功！","info", function(data) {
				window.location.reload();

			});
			$("#userlayer").trigger('close');
		} else if (data.status == "2") {
			$(".full_mask").hide();
			$('#userlayer').trigger('close');
			$.messager.alert("提示", "修改焦点图成功！","info", function(data) {
				window.location.reload();

			});
		} else {
			$(".full_mask").hide();
			$.messager.alert("提示", data.msg);
		}
	});


}

// 删除banner图
function deleteBanner() {
	var banner_ids = "";
	$("input[name='userCheck']:checked").each(function() {
		banner_ids += $(this).val() + ",";
	});
	if(banner_ids==""){
		$.messager.alert("提示","请选择焦点图");
		return;
	}
	$.messager.confirm("操作提示","确定要删除焦点图么？",function(data) {
		if (data) {
			$(".full_mask").show();
			$.post("../../../admin/adminBanner/web/deleteBannerImages.htm",
				{
					"banner_ids" : banner_ids
				}, function(data) {
					$(".full_mask").hide();
					if (data == "1") {
						$.messager.alert("提示", "删除成功！","info", function(data) {
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
