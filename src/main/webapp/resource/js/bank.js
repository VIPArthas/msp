
$(function() {
	/* 表格样式 */
	$(".table>tbody tr:odd").addClass("odd");
	$(".table>tbody tr:even").addClass("even");
	$(".table>tbody tr").mouseover(function() {
		$(this).addClass("hover");
	}).mouseout(function() {
		$(this).removeClass("hover");
	});
	/* 表格自适应高度 */
	$(".main_iframe").height($(window).height() - 170);
	$(window).resize(function() {
		$(".main_iframe").height($(window).height() - 170);
	});
	/* 部门编辑弹出层 */
	$(".bm-edit").click(function(e) {
		$('#edit_layer').lightbox_me({
			centered : true,
		}).draggable({
			handle : '.tanchu_head',
			cursor : 'move'
		});
		e.preventDefault();
	});
	/* 部门管理复选框 */
	$(".table>tbody>tr>th:eq(0)").find("input[type='checkbox']").click(
			function() {
				if ($(this).prop("checked")) {
					$(".table td").find("input[type='checkbox']").prop(
							"checked", true);// 全选

				} else {
					$(".table td").find("input[type='checkbox']").prop(
							"checked", false);// 全选
				}

			});
	/* 设备品牌管理弹出层 */
	$(".adda").click(function(e) {
		$('#adda_layer').lightbox_me({
			centered : true,
		}).draggable({
			handle : '.tanchu_head',
			cursor : 'move'
		});
		e.preventDefault();
	});
	/* 设备品牌管理弹出层 */
	$(".ht").click(function(e) {
		$('#ht_userlayer').lightbox_me({
			centered : true,
		}).draggable({
			handle : '.tanchu_head',
			cursor : 'move'
		});
		e.preventDefault();
	});
	/* 发起弹出层 */

	if ($("#sponsor_layer .c li").length == 1) {
		$("#sponsor_layer .c li").css({
			"margin" : "0 auto 10px",
			"float" : "none"
		});
	}
	$(".sponsor_btn").click(function(e) {
		$('#sponsor_layer .c >li:eq(6)').css({
			"width" : "100%"
		});
		$('#sponsor_layer').lightbox_me({
			centered : true,
		}).draggable({
			handle : '.tanchu_head',
			cursor : 'move'
		});
		e.preventDefault();
	});

	/* tab */
	$(".tabmenu li").click(function() {
		$(this).addClass("active").siblings().removeClass("active");
		$(".tabcontent .tablayer").hide().eq($(this).index()).show();
	});
	/* 增加设备 */
	$(".addequi .addbtn").click(
			function() {
				$(".eqbox").append(
						"<div class='eqinfo'>" + $(".eqinfo:eq(0)").html()
								+ "</div>");
			});
	/* 图片弹出层 */
	$(".preview_pic li img")
			.click(
					function() {
						$(
								"<div class='popimg'><img src='"
										+ $(this).attr("src")
										+ "'/><a href='javascript:;' class='close'></a></div>")
								.lightbox_me({
									centered : true
								});
					});
	/* 移除按钮 */
	$(".preview_pic li").mouseenter(function() {
		$(this).find(".remove").show().click(function() {
			$(this).parent("li").find("img").remove();
		});
	}).mouseleave(function() {
		$(this).find(".remove").hide();
	});
	/* 移除按钮 */
	$(".preview_pic_ht li").mouseenter(function() {
		$(this).find(".remove").show().click(function() {
			$(this).parent("li").find("img").remove();
		});
	}).mouseleave(function() {
		$(this).find(".remove").hide();
	});
	/* 日期 */
	$("input.time").datebox({
		required : true,
		novalidate : true,
		missingMessage: '请选择日期',
		editable : false
	});

	$("input.timeNoRequired").datebox({
		required : false,
		novalidate : true,
		editable : false
	});
	/*
	 * 日期范围限制 */
	$("input.time.gt_curr").each(function(){
		$(this).datebox('calendar').calendar({
			validator: function(date){
				var now = new Date();
				var d1 = new Date(now.getFullYear(), now.getMonth(), now.getDate());
				return d1<=date;
			}
		});
	});
	/*
	 * 日期范围限制 */
	$("input.time.gt_before").each(function(){
		$(this).datebox('calendar').calendar({
			validator: function(date){
				var now = new Date();
				var d1 = new Date(now.getFullYear(), now.getMonth(), now.getDate());
				return d1>=date;
			}
		});
	});
	$("input.time").next(".textbox").find(".textbox-text").attr("placeholder","请选择时间");
	$("input.timeNoRequired").next(".textbox").find(".textbox-text").attr("placeholder","请选择时间");
	$("input.time.gt_curr").next(".textbox").find(".textbox-text").attr("placeholder","请选择时间");
	$("input.time.gt_before").next(".textbox").find(".textbox-text").attr("placeholder","请选择时间");
	/* 新增用户 */
	$(".adduser").click(function() {
		$("#userlayer").lightbox_me({
			centered : true
		});
	})
	/* 新增部门 */
	$(".adddepart").click(function() {
		$("#edit_layer").lightbox_me({
			centered : true
		});
	});
	$(".add_depart").click(function() {
		$("#addbm_layer").lightbox_me({
			centered : true
		});
	});
	/* 角色和菜单 */
	$(".addrole").click(function(e) {
		$('#role_layer').lightbox_me({
			centered : true,
		}).draggable({
			handle : '.tanchu_head',
			cursor : 'move'
		});
		e.preventDefault();
	});
	$(".addmenu").click(function(e) {
		$('#menu_layer').lightbox_me({
			centered : true,
		}).draggable({
			handle : '.tanchu_head',
			cursor : 'move'
		});
		e.preventDefault();
	});

	$(".lianxirenadd").click(function(e) {
		$('#lianxir_layer').lightbox_me({
			centered : true,
		}).draggable({
			handle : '.tanchu_head',
			cursor : 'move'
		});
		e.preventDefault();
	});
	/* 删除部门 */
	$(".remove_wz").click(function() {
		$(this).parent("li").remove();
	});
});
function toggleCheck(obj){
	if(event.target.tagName=="INPUT" && $(event.target).attr("type")=="checkbox" ){
		return false;
	}
	var check=$(obj).find("td:first-child input[type='checkbox']");
	if(check){
		check.prop("checked",!(check.prop("checked")));
	}
}
