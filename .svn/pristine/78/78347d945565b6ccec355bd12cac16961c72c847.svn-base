$(document).ready(function(){
   var DepId="01";
	//页面装载初始数据
	$.ajax({
		type:"get",
		dataType:"json",
		cache:false,
		url:urlPix+"/msp/mspUser/web/showAddressBook.htm",
		async:false,
		data:{
	    depID:"01",
		start:1,
		length:10
		},
	    success:function(data){
	    	var totalCount=data.attributes.count;
	    $("<li data-id="+data.obj.id+"><span data-switch='on' name='child'><i class='fa'>&#xe60e;</i>"+data.obj.name+"</span></li>").appendTo($(".result-list").eq(0));
	    innerUserData(data);
	    $("#Pagination").pagination(parseInt(totalCount), {
            items_per_page: 10,
            current_page: 0,//当前选中的页面默认是0，表示第1页
            num_display_entries:15,//连续分页主体部分显示的分页条目数,默认是10
            link_to: "javascript:void(0)",//分页的链接
            prev_text: "上一页",
            next_text: "下一页",
            prev_show_always: true, 
            next_show_always: true,
            callback:function(index){
            	loadUserData(index+1);
            }
        });
	    }
	});
	
	var loadUserData=function(s){
		var dataModel={};
		$.ajax({
			 type:"get",
			 dataType:"json",
			 cache:false,
			 url:urlPix+"/msp/mspUser/web/showAddressBook.htm",
			 async:false,
			 data:{
			 depID:DepId,
			 start:s,
			 length:10
			 },
			 success:function(data){
				 dataModel=data;
				 totalCount=data.attributes.count;
				 if(dataModel.attributes!=null){
					 innerUserData(dataModel);
				 }else{
					 $(".thistable tbody").html("");
					 $("<tr><td colspan='6'>暂无数据</td></tr>").appendTo($(".thistable tbody"));
				 }
			 }
		 });
	}
	

	
	//请求子频道信息将所请求频道人员信息置入客户端
	$(".result-list li").on("click","span[data-switch]",function(index){
		$(".searchbox input[type=text]").val("");
		$(this).addClass("active");
		$(this).parents(".result-list").find("span").not($(this)).removeClass("active");
		DepId=$(this).parent().data("id");
		var _this=$(this)
	    var _index=_this.index();
		if(_this.data("switch")=="on"){
			$.ajax({
				type:"get",
				dataType:"json",
				cache:false,
				url:urlPix+"/msp/mspUser/web/showAddressBook.htm",
				async:false,
				data:{
			    depID:DepId,
				start:1,
				length:10
				},
			    success:function(data){
			    var Parthtml="<ul>";
			    if(data.obj.childDepList!=null){
			    	for(var i=0;i<data.obj.childDepList.length;i++){
				    	Parthtml+="<li data-id="+data.obj.childDepList[i].id+"><span data-switch='on' name='child'><i class='fa'>&#xe60e;</i>"+data.obj.childDepList[i].name+"</span></li>";
				    }
			    }else{
			    	 $(".thistable tbody").html("");
					 $("<tr><td colspan='6'>暂无数据</td></tr>").appendTo($(".thistable tbody"));
			    }
			    Parthtml+="</ul>";
			    _this.parent().find("ul").remove();
			   _this.parent().append(Parthtml);
			   if(data.attributes!=null){
				     var totalCount=data.attributes.count;
				     innerUserData(data);
					 $("#Pagination").pagination(parseInt(totalCount), {
				            items_per_page: 10,
				            current_page: 0,//当前选中的页面默认是0，表示第1页
				            num_display_entries:15,//连续分页主体部分显示的分页条目数,默认是10
				            link_to: "javascript:void(0)",//分页的链接
				            prev_text: "上一页",
				            next_text: "下一页",
				            prev_show_always: true, 
				            next_show_always: true,
				            callback:function(index){
				            	reloadUserData(_this,index+1);
				            }
				        });
				 }else{
					 $(".thistable tbody").html("");
					 $("<tr><td colspan='6'>暂无数据</td></tr>").appendTo($(".thistable tbody"));
					 $("#Pagination").pagination(-1, {
				            items_per_page: 10,
				            current_page: 0,//当前选中的页面默认是0，表示第1页
				            num_display_entries:15,//连续分页主体部分显示的分页条目数,默认是10
				            link_to: "javascript:void(0)",//分页的链接
				            prev_text: "上一页",
				            next_text: "下一页",
				            prev_show_always: true, 
				            next_show_always: true,
				            callback:function(index){
				            	reloadUserData(_this,index+1);
				            }
				        });
				 }
			    
			    }
			});
			_this.data("switch","off")
		}else{
			_this.data("switch","on");
			_this.next("ul").remove();
		}
		
		
	});
	
	var reloadUserData=function(p,s){
		p.addClass("active");
		p.parents(".result-list").find("span").not($(this)).removeClass("active");
		var parentLi=p.parent();
		var childId=parentLi.data("id");
		DepId=childId;
		//DepId=childId;
		var dataModel={};
		$.ajax({
			 type:"get",
			 dataType:"json",
			 cache:false,
			 url:urlPix+"/msp/mspUser/web/showAddressBook.htm",
			 async:false,
			 data:{
			 depID:childId,
			 start:s,
			 length:10
			 },
			 success:function(data){
				 dataModel=data;
				 if(dataModel.attributes!=null){
					 innerUserData(dataModel);
				 }else{
					 $(".thistable tbody").html("");
					 $("<tr><td colspan='6'>暂无数据</td></tr>").appendTo($(".thistable tbody"));
				 }
			 }
		 })
 
	}
	 
	//解绑
 $(".thistable td").on("click",".removeBand",function(){
	 var thisTD=$(this).parent();
	 var uid=thisTD.parent().find("td").first().data("id");
	 $.ajax({
			type:"get",
			dataType:"json",
			cache:false,
			url:urlPix+"/msp/mspUser/web/unBindUser.htm",
			async:false,
			data:{
				id:uid,
			},
			success:function(data){
				alert(data.msg);
				thisTD.text("未绑定");
			}
		})	 
 })
 //搜索
 $("#search").click(function(){
	 var keyWord=$.trim($(this).prev().val());
	 if(keyWord!=""){
		 $.ajax({
				type:"get",
				dataType:"json",
				cache:false,
				url:urlPix+"/msp/mspUser/web/GetDepMemberList.htm",
				async:false,
				data:{
					depID:DepId,
					start:1,
					length:10,
					search:keyWord
				},
		       success:function(data){
		    	   if(data.attributes!=null){
		    	   innerUserData(data)
		    	   $("#Pagination").pagination(parseInt(data.attributes.count), {
			            items_per_page: 10,
			            current_page: 0,//当前选中的页面默认是0，表示第1页
			            num_display_entries:15,//连续分页主体部分显示的分页条目数,默认是10
			            link_to: "javascript:void(0)",//分页的链接
			            prev_text: "上一页",
			            next_text: "下一页",
			            prev_show_always: true, 
			            next_show_always: true,
			            callback:function(index){
			            	SearchloadUserData(index+1,keyWord);
			            }
			        });
		    	   }else{
		    		   $(".thistable tbody").html("");
						 $("<tr><td colspan='5'>没有找到姓名或手机号码为　"+keyWord+"　的人员请确认</td></tr>").appendTo($(".thistable tbody"));
						 $("#Pagination").pagination(-1, {
					            items_per_page: 10,
					            current_page: 0,//当前选中的页面默认是0，表示第1页
					            num_display_entries:15,//连续分页主体部分显示的分页条目数,默认是10
					            link_to: "javascript:void(0)",//分页的链接
					            prev_text: "上一页",
					            next_text: "下一页",
					            prev_show_always: true, 
					            next_show_always: true,
					            callback:function(index){
					            	SearchloadUserData(index,keyWord);
					            }
					        });
						 
		    	   }
		       }
			})
	 }else{
		 alert("请输入搜索内容");
	 }
	 })
	 var SearchloadUserData=function(s,kw){
		var dataModel={};
		$.ajax({
			 type:"get",
			 dataType:"json",
			 cache:false,
			 url:urlPix+"/msp/mspUser/web/GetDepMemberList.htm",
			 async:false,
			 data:{
			 depID:DepId,
			 start:s,
			 length:10,
			 search:kw
			 },
			 success:function(data){
				 dataModel=data;
				 if(dataModel.attributes!=null){
					 innerUserData(dataModel);
				 }else{
					 $(".thistable tbody").html("");
					 $("<tr><td colspan='6'>暂无数据</td></tr>").appendTo($(".thistable tbody"));
				 }
			 }
		 })

	}
})