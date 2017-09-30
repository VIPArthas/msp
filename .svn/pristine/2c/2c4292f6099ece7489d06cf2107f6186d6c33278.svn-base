$(function(){
	//表格样式
	$(".table>tbody tr:odd").addClass("odd");
	$(".table>tbody tr:even").addClass("even");
	$(".table>tbody tr").mouseover(function() {
		$(this).addClass("hover");
	}).mouseout(function() {
		$(this).removeClass("hover");
	});
	$(".table>tbody td").each(function(){
		$(this).wrapInner("<div style='width:"+$(this).width()+"px;text-overflow:ellipsis;overflow:hidden;white-space:nowrap;'></div>");
	});
	//复选框
	$(".table>tbody>tr>th:eq(0)").find("input[type='checkbox']").click(function() {
		if ($(this).prop("checked")) {
			$(".table td").find("input[type='checkbox']").prop("checked", true); // 全选
		} else {
			$(".table td").find("input[type='checkbox']").prop("checked", false); // 全选
		}
	});
	
});