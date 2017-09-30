 
function reset(){
	this.dataLength=10;//每页条数
	this.nowPage=1;//加载默认页
	this.url="";//ajax请求地址
	this.nowShow=10;//当前展示页数
	this.parentElement=".EX-pager";//父元素
	this.setdataLength=function(num){
		dataLength=num;
		return dataLength;
	}
	this.setnowPage=function(np){
		nowPage=np;
		return nowPage;
	}
	this.seturl=function(src){
		url=src;
		return url;
	}
	this.setnowShow=function(ns){
		nowShow=ns;
		return nowShow;
	}
	this.setparentElement=function(pe){
		parentElement=pe;
		return parentElement;
	}
	
}
var EX=new reset();
function PageData(st,dl,src,did){
	var pageDatas={};
		$.ajax({
			type:"get",
			dataType:"json",
			cache:false,
			url:src,
			async:false,
			data:{
			depID:did,
			start:st,
			length:dl
			},
			success:function(data){
				pageDatas=data;
				}
			});
		return pageDatas;
}

function setPage(st,dl,src,did){
	var datas=new PageData(st,dl,src,did);
	var pageNum=datas.attributes.count/dl;
	pageNum=Math.ceil(pageNum);
	 var pagerContentInfo='<div class="msg-pager">共<i>'+pageNum+'</i>页　　<s>'+datas.attributes.count+'</s>条数据</div>';
	 var pagerContent='<div class="page-pager"><span><a href="javascript:void(0)" class="PagePrev">&lt;</a>';
	 var pagin=""
	 if(pageNum<=10){
		 for(var i=1;i<=pageNum;i++){
			 pagin+='<a href="javascript:void(0)" class="pageItem" data-page="'+i+'">'+i+'</a>'
		 } 
	 }else{
			// pagerContent+='<a href="javascript:void(0)" class="pageItem" data-page="'+i+'">'+i+'</a>'
		 for(var j=1;j<11;j++){
			 pagin+='<a href="javascript:void(0)" class="pageItem" data-page="'+j+'">'+j+'</a>'
		 }
		 pagin+='<font>...</font><a href="javascript:void(0)" class="pageItem" data-page="'+pageNum+'">'+pageNum+'</a>';
		 }
	 var pagerEnd='<a href="javascript:void(0)" class="PageNext">&gt;</a></span><label>跳到<input type="text" id="pageNum" />页<input type="button" value="GO" id="goPage" /></label></div>';
	 $(EX.parentElement).html("");
	 $(pagerContentInfo+pagerContent+pagin+pagerEnd).appendTo(EX.parentElement);
	 $(EX.parentElement).find("a").eq(EX.nowPage).addClass("cur");
	 var pageList=function(p){
		     pagin="";
			 var dp=parseInt($(p).data("page"));
			 pagin='<font>...</font><a href="javascript:void(0)" class="pageItem" data-page="'+(dp-1)+'">'+(dp-1)+'</a>';
			 if(dp+10<=pageNum){
				 for(var i=dp;i<dp+10;i++){
					 pagin+='<a href="javascript:void(0)" class="pageItem" data-page="'+i+'">'+i+'</a>';
				 }
				 pagin+='<font>...</font><a href="javascript:void(0)" class="pageItem" data-page="'+pageNum+'">'+pageNum+'</a>';
			 }else{
				 for(var i=dp-10;i<pageNum;i++){
					 pagin+='<a href="javascript:void(0)" class="pageItem" data-page="'+i+'">'+i+'</a>'
				 }
				 pagin+='<a href="javascript:void(0)" class="pageItem" data-page="'+pageNum+'">'+pageNum+'</a>';
			 }
			 $(EX.parentElement).html("");
			 $(pagerContentInfo+pagerContent+pagin+pagerEnd).appendTo(EX.parentElement);
			 $(EX.parentElement).find("a").each(function(){
				 if($(this).data("page")==dp){
					 $(this).addClass("cur");
				 }
			 })
	 }
	 
	 var prevList=function(o){
		 var dp=$(o).data("page");
		  pagin="";
		  if(dp-10<5){
			for(var i=1;i<=10;i++){
			 pagin+='<a href="javascript:void(0)" class="pageItem" data-page="'+i+'">'+i+'</a>';
			}
			pagin+="<font>...</font>";
		  }else{
			  pagin+="<font>...</font>";
			for(var i=dp-10;i<dp;i++){
				 pagin+='<a href="javascript:void(0)" class="pageItem" data-page="'+i+'">'+i+'</a>';
			}
			pagin+='<a href="javascript:void(0)" class="pageItem cur" data-page="'+dp+'">'+dp+'</a><a href="javascript:void(0)" class="pageItem" data-page="'+(dp+1)+'">'+(dp+1)+'</a><font>...</font>';
		  }
		  $(EX.parentElement).html("");
		  $(pagerContentInfo+pagerContent+pagin+pagerEnd).appendTo(EX.parentElement);
		  $(EX.parentElement).find("a").each(function(){
				 if($(this).data("page")==dp){
					 $(this).addClass("cur");
				 }
			 })
	 }
	 //点击页数
	 $(EX.parentElement).on("click","a.pageItem",function(){
		 if($(this).next().prop("nodeName")=="FONT"){
			 pageList(this);
		 }
		 if($(this).prev().prop("nodeName")=="FONT"){
			 prevList(this);
		 }
		 $(this).addClass("cur").siblings().removeClass("cur");
		 var thisPageData=new PageData(EX.setnowPage($(this).data("page")),dl,src,did);
		 innerUserData(thisPageData,$(this).data("page"));
	 });
	 //点击上一页
	 $(EX.parentElement).on("click","a.PagePrev",function(){
		 var prevPage=$(this).siblings("a.cur").data("page")-1;
		 if(prevPage<=0){
			 alert("已经在第一页!");
		 }else{
			 $(this).attr("data-page",$(EX.parentElement).find("a.cur").data("page")-1);
			 if($(EX.parentElement).find("a.cur").prev().prop("nodeName")=="FONT"){
				 prevList(this);
			 }
			 $(this).siblings("a.cur").prev().addClass("cur").siblings().removeClass("cur")
			 var thisPageData=new PageData(prevPage,dl,src,did);
			 innerUserData(thisPageData,$(this).data("page"));
		 }

	 })
	 //点击下一页
	 $(EX.parentElement).on("click","a.PageNext",function(){
		 $(this).attr("data-page",$(EX.parentElement).find("a.cur").data("page")+1);
		 if($(EX.parentElement).find("a.cur").next().prop("nodeName")=="FONT"){
			 pageList(this);
		 };
		 var nextPage=$(this).siblings(".cur").data("page")+1;
		 if(nextPage>pageNum){
			 alert("已经在最后一页!");
		 }else{
			 $(this).siblings("a.cur").next().addClass("cur").siblings().removeClass("cur")
			 var thisPageData=new PageData(nextPage,dl,src,did);
			 innerUserData(thisPageData,$(this).data("page"));
 
		 }
	 })
	 //跳转页面
	  $(EX.parentElement).on("click","#goPage",function(){
		 var pageNums=$("#pageNum").val();
		 if(pageNums<=0){
			 alert("我不知道你到底要去哪里,但是你选的这个页数,我实在找不到!")
		 }else if(pageNums>pageNum){
			 alert("当前数据共有"+pageNum+"页，您输入的数字过大!")
		 }else{
			 $(this).attr("data-page",pageNums);
			 pageList(this);
			 var goPageData=new PageData(pageNums,dl,src);
			 innerUserData(goPageData,$(this).data("page"));
 
		 }
	 })
 
}