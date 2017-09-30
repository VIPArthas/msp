function insertJZ(){

	var form = $("#add_JZ_form");
	form.form('submit',{
			url : urlPix + "/admin/JzManage/web/AddjzManage.htm",
			onSubmit : function(){
				var flag = $(this).form('enableValidation').form('validate');
				return flag;
			},
			success : function(data){
				var dataMsg = JSON.parse(data);
				if(dataMsg.code == '1'){
					$.messager.alert("提示","保存成功","info",function(){
						window.location.reload();
					});
				}else{
					$.messager.alert("提示", dataMsg.msg);
				}
			}
		});
}



function delPartTime(){
	var source_id = "";
	$("input[name='Jz_check']:checked").each(function () {
		source_id += $(this).val() + ",";
	})
	var ids = source_id.split(",");
	if (ids.length <= 1) {
		$.messager.alert("提示", "请至少选择一条数据!");
		return;
	}
	$.messager.confirm("操作提示","确定要删除该条数据吗?",function(data){
		if(data){
			$.post(
				urlPix + "/admin/JzManage/web/deljzManageList.htm",
				{
					id:source_id
				},
				function(response){
					var data = JSON.parse(response);
					if(data.code == '1'){
						$.messager.alert("提示","删除成功!","info",function(){
							window.location.reload();
						});
					}else{
						$.messager.alert("提示",data.msg)
					}
				}
			)}
	})
}

function updatePartTimeTopCanle(){
	var source_id = "";
	$("input[name='Jz_check']:checked").each(function () {
		source_id += $(this).val() + ",";
	})
	var ids = source_id.split(",");
	if (ids.length <= 1) {
		$.messager.alert("提示", "请至少选择一条数据!");
		return;
	}
	$.messager.confirm("操作提示","确定要取消置顶该条数据吗?",function(data){
		if(data){
			$.post(
				urlPix + "/admin/JzManage/web/updatejzManageList.htm",
				{
					id:source_id,
					orderNum:0
				},
				function(response){
					var data = JSON.parse(response);
					if(data.code == '1'){
						$.messager.alert("提示","取消置顶成功!","info",function(){
							window.location.reload();
						});
					}else{
						$.messager.alert("提示",data.msg)
					}
				}
			)}
	})
}

function updatePartTimeTop(){
	var source_id = "";
	$("input[name='Jz_check']:checked").each(function () {
		source_id += $(this).val() + ",";
	})
	var ids = source_id.split(",");
	if (ids.length <= 1) {
		$.messager.alert("提示", "请至少选择一条数据!");
		return;
	}
	$.messager.confirm("操作提示","确定要置顶该条数据吗?",function(data){
		if(data){
			$.post(
				urlPix + "/admin/JzManage/web/updatejzManageList.htm",
				{
					id:source_id,
					orderNum:1
				},
				function(response){
					var data = JSON.parse(response);
					if(data.code == '1'){
						$.messager.alert("提示","置顶成功!","info",function(){
							window.location.reload();
						});
					}else{
						$.messager.alert("提示",data.msg)
					}
				}
			)}
	})
}