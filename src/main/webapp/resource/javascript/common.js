
$(function(){
	// 全选 start
	$('.checkall').click(function(){
		$('.checkall').attr('checked',$(this).attr('checked') == 'checked');
		$('.checkitem').each(function(){
			$(this).attr('checked',$('.checkall').attr('checked') == 'checked');
		});
	});
	
});