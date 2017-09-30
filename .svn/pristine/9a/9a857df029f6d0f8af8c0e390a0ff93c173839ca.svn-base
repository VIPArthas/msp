/**
 * 文件上传
 */
var preivew = function(file, formId) {
	try {
		var pic = new Picture(file, formId);
	} catch (e) {
		console.log(e);
	}
}

// 缩略图类定义
var Picture = function(file, formId) {
	var form = $("#"+formId);
	form.form('submit', {
		url: prefix + "/wh/templet/fileUpload.htm",
		onSubmit: function() {
			var isVaild = true;
			if (file) {
				if (file.files && file.files.length > 0) {
					var fileList = file.files;
					for (var i = 0; i < fileList.length; i++) {
						var suffix = fileList[i].name.substr(fileList[i].name.lastIndexOf(".") + 1,
							fileList[i].name.length);
						if (allowExt.indexOf(suffix) == -1) {
							isVaild = false;
						}
					}
				}
			} else {
				isVaild = false;
			}
			if (isVaild == false) {
				$.messager.alert("提示", "请上传正确格式的文件！", "error");
				// for IE, Opera, Safari, Chrome
				if (file.outerHTML) {
					file.outerHTML = file.outerHTML;
				} else { // FF(包括3.5)
					file.value = "";
				}
			}
			return isVaild;
		},
		success: function(response) {
			var reqmsg = JSON.parse(response);
			if (reqmsg != null && reqmsg instanceof Array && reqmsg.length > 0) {
				reqmsg = reqmsg[0];
				if (reqmsg.success == true) {
					var data = reqmsg.data;
					var num = $(".preview_pic ul li").length;
					if (data != null && data instanceof Array && data.length > 0) {
						for (var i = 0; i < data.length; i++) {
							var fileobj = data[i];
							var suffix = fileobj.path.substr(fileobj.path.lastIndexOf(".") + 1, fileobj.path.length);
							var name = fileobj.path.substr(fileobj.path.lastIndexOf("/") + 1, fileobj.path.length);
							var index = num + i;
							//1,增加预览块
							$(".preview_pic ul").append(
								'<li>' +
								'<a href="' + prefix + fileobj.path + '" target="_self">' +
								'<img id="img_' + name + '" src="">' +
								'</a>' +
								'<a href="javascript:;" class="remove">删除</a>' +
								'</li>');
							//2,预览块图片
							if (docStr.indexOf(suffix) != -1) {
								var container = document.getElementById('img_' + name);
								if (docStr.indexOf(suffix) == 0 || docStr.indexOf(suffix) == 4) {
									container.src = prefix + "/resource/images/upload/word.png";
								} else if (docStr.indexOf(suffix) == 9 || docStr.indexOf(suffix) == 13) {
									container.src = prefix + "/resource/images/upload/excel.png";
								} else if (docStr.indexOf(suffix) == 18 || docStr.indexOf(suffix) == 22) {
									container.src = prefix + "/resource/images/upload/ppt.png";
								}
								container.alt = name;
							}else if (vodStr.indexOf(suffix) != -1) {
								var container = document.getElementById('img_' + name);
								container.src = prefix + "/resource/images/upload/video.png";
								container.alt = name;
							}else{
								var container = document.getElementById('img_' + name);
								container.src = prefix + fileobj.path;
								container.alt = name;
							}
							
							//成功后调用
							fileUploadSucc(fileobj.path);
						}
					}
					$(".preview_pic li").mouseenter(function() {
						$(this).find(".remove").show().click(function() {
							$(this).parent().remove();
						});
					}).mouseleave(function() {
						$(this).find(".remove").hide();
					});
					/* 图片弹出层 */
					$(".preview_pic li img").click(function() {
						if (this.parentNode.tagName == "LI") {
							$("<div class='popimg'><img src='" + $(this).attr("src") + "'/><a href='javascript:;' class='close'></a></div>").lightbox_me({
								centered: true
							});
						}
					});
					// for IE, Opera, Safari, Chrome
					if (file.outerHTML) {
						file.outerHTML = file.outerHTML;
					} else { // FF(包括3.5)
						file.value = "";
					}
				} else {
					$.messager.alert("提示", "请上传文件失败！", "error");
				}
			}
		}
	});
}