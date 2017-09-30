/** 预览模板文件 */
function previewTemplet(templetId){
	$.post(
		"/tcm/wh/templet/previewTemplet.htm",
		{"templetId":templetId},
		function(data){
			$('#edit_layer').html("");
			$('#edit_layer').html(data);
			//初始化弹出层
			$('#edit_layer').dialog({
				width : 800,//弹出层宽度
				height: 318,
				modal : true,//是否有遮罩层
				iconCls : 'icon-edit',//标题前图标
				title: "预览模板",//弹出层标题
				closed:true//弹出层默认关闭
			}).dialog('open');
			}
		);
}