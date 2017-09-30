$(document).ready(function(){
	function format(timestamp) {
		var time = new Date(timestamp);
		var year = time.getFullYear();
		var month = (time.getMonth() + 1) > 9 && (time.getMonth() + 1) || ('0' + (time.getMonth() + 1))
		var date = time.getDate() > 9 && time.getDate() || ('0' + time.getDate())
		var hour = time.getHours() > 9 && time.getHours() || ('0' + time.getHours())
		var minute = time.getMinutes() > 9 && time.getMinutes() || ('0' + time.getMinutes())
		var second = time.getSeconds() > 9 && time.getSeconds() || ('0' + time.getSeconds())
		var YmdHis = year + '-' + month + '-' + date
			+ ' ' + hour + ':' + minute + ':' + second;
		return YmdHis;
	}
	
	var totalCount;
	$.ajax({
		type:"get",
		dataType:"json",
		cache:false,
		url:urlPix+"/msp/mspMessage/web/messageList.htm",
		async:false,
		data:{
			start:1,
			length:12
		},
	    success:function(data){
	    	totalCount=data.attributes.count;
	    	$(".thistable tbody").html("");
			for(var i=0;i<data.obj.length;i++){
			$("<tr><td>"+(i+1)+"</td><td>"+format(data.obj[i].createTime)+"</td><td>"+data.obj[i].content+"</td><td>"+"人员:"+data.obj[i].toUserName+"<br>"+"部门:"+data.obj[i].toPartyName+"</td></tr>").appendTo($(".thistable tbody"));
			}
	    	$("#Pagination").pagination(parseInt(totalCount), {
	            items_per_page: 12,
	            current_page: 0,//当前选中的页面默认是0，表示第1页
	            num_display_entries:15,//连续分页主体部分显示的分页条目数,默认是10
	            link_to: "javascript:void(0)",//分页的链接
	            prev_text: "上一页",
	            next_text: "下一页",
	            prev_show_always: true, 
	            next_show_always: true,
	            callback:function(index){
	            	loadData(index+1);
	            }
	        });
	    }
	});
	function loadData(st){
		
		$.ajax({
			type:"get",
			dataType:"json",
			cache:false,
			url:urlPix+"/msp/mspMessage/web/messageList.htm",
			async:false,
			data:{
				start:st,
				length:12
			},
			success:function(data){
				$(".thistable tbody").html("");
				for(var i=0;i<data.obj.length;i++){
				$("<tr><td>"+((i+1)+12*(st-1))+"</td><td>"+format(data.obj[i].createTime)+"</td><td>"+data.obj[i].content+"</td><td>"+"人员:"+data.obj[i].toUserName+"<br>"+"部门:"+data.obj[i].toPartyName+"</td></tr>").appendTo($(".thistable tbody"));
				}
				}
		})
	}
});