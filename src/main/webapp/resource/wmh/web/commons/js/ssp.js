$(document).ready(function(){
	var totalCount;
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
	var tr=""
	var loadData=function(n){
		$.ajax({
			type:"get",
			dataType:"json",
			cache:false,
			url: urlPix +"/msp/mspPhoto/web/sspList.htm",
			async:false,
			data:{
				start:n,
				length:10
			},
		    success:function(data){
		     var Numbers=0;
		     for(var i=0;i<data.obj.length;i++){
		    	 var Name=data.obj[i].name;
		    	 if(Name==undefined){
		    		 Name="-"
		    	 }
		    	 Numbers++
		    	tr+='<tr><td>'+(Numbers+10*(n-1))+'</td><td>'+data.obj[i].name+'</td><td>'+data.obj[i].msg+'</td><td><a href="javascript:;" class="ssp" data-id="'+data.obj[i].id+'"><img src="'+data.obj[i].file_path+'" /></a></td><td>'+format(data.obj[i].create_time)+'</td><td><a href="javascript:void(0)" data-id="'+data.obj[i].id+'" class="removeBand">删除</a></td><tr>'
		     }
		     $("#thistable tbody").html("");
		     $(tr).appendTo("#thistable tbody");
		     tr="";
		    }
		});
	}
	
	$.ajax({
		type:"get",
		dataType:"json",
		cache:false,
		url: urlPix +"/msp/mspPhoto/web/sspList.htm",
		async:false,
		data:{
			start:1,
			length:10
		},
	    success:function(data){
	    	totalCount=data.attributes.count;
	     var Numbers=0;
	     for(var i=0;i<data.obj.length;i++){
	    	 var Name=data.obj[i].name;
	    	 if(Name==undefined){
	    		 Name="-"
	    	 }
	    	 Numbers++
	    	tr+='<tr><td>'+Numbers+'</td><td>'+data.obj[i].name+'</td><td>'+data.obj[i].msg+'</td><td><a href="javascript:;" class="ssp" data-id="'+data.obj[i].id+'"><img src="'+data.obj[i].file_path+'" /></a></td><td>'+format(data.obj[i].create_time)+'</td><td><a href="javascript:void(0)" data-id="'+data.obj[i].id+'" class="removeBand">删除</a></td><tr>'
	     }
	     $("#thistable tbody").html("");
	     $(tr).appendTo("#thistable tbody");
	     tr="";
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
	            	loadData(index+1);
	            }
	        });
	    }
	});
	
	$("#thistable tr td").on("click","a.ssp",function(){
		var img={}
		var pid=$(this).data("id");
		$.ajax({
			type:"get",
			dataType:"json",
			cache:false,
			url:urlPix+"/msp/mspPhoto/web/getPhotoListById.htm",
			async:false,
			data:{
				photoId:pid
			},
		    success:function(data){
		     img=data;
		    }
		});
		var docEl=window.parent.document.documentElement;//在子页面获取父页面的主框架
	      var clientW=docEl.clientWidth;
	      var clientH=docEl.clientHeight;
	      var thisWindow=document.documentElement;
	      var mask=document.createElement("div");
	      mask.id="mask";
	      $(mask).css({
	        "width":clientW,
		   	"height":clientH,
		  	"position":"absolute",
		  	"top":0,
		  	"left":0,
		  	"z-index":"2",
		  	"background-color":"rgba(0,0,0,0.2)"
		  	});
	      
	        var i=0,//大图编号
			len=img.obj.length,//img数组的长度
			cur=0;//当前图片编号
			j=9,//默认显示小图个数
			page=0,//小图的页码
			$s_next=$('#smallImg-next'),//小图下一页
			$s_pre=$('#smallImg-pre'),//小图上一页
			box=$('#smallImg-box').width(),//显示的长度
			$ul=$('#smallImg-ul'),//小图外层
			$imgLi=$ul.find('li'),//小图li
			html=_html='';//存放载入的代码		
			
		$('#detailImg-box').append('<a href="javascript:void(0)" class="detailImg_1"><img src="'+img.obj[i].file_path+'"></a>');
		//alert($('#detailImg-box').html())
		//大图	
		$('#detailImg-next').click(function(){
			++i;
			if(i>=len){
				i=len-1;
               return false
			}else{
				detailImg_click($s_next,i,len);
			}
			
		})
		$('#detailImg-pre').click(function(){
			--i;
			if(i<0){
				i=0;
				return false
			}else{
				detailImg_click($s_pre,i,len);
			}
			
		})
		//小图
		for(var k=0;k<len;k++){
			html+='<li class=\"smallImg_'+(k+1)+'\"><a><img src=\"'+img.obj[k].file_path+'\"></a></li>';
		}
		$ul.append(html);
		$('.smallImg_1').addClass('cur');	
		//小图下一页
		$('#smallImg-next').click(function(){
			if(!$ul.is(':animated')){
				page++;
				var a=page*j,_a,c;
				for(var k=0;k<j;k++,a++){
					smallImg_click(a,_a,len,i);
					_html+=h;
				}
				$ul.append(_html);
				$ul.css({'left':0,'right':'auto'});
				$ul.animate({left:-box},1600,function(){
					$ul.find('li:lt('+j+')').detach();
					$ul.css('left',0);
					_html='';
				});//动画执行后,再删除前9个li,将left设回0
				$('#smallImg-ul li',window.parent.document).click(function(){//三处一样，不知道这个要怎么优化？？？
					var _this=$(this);
					i=_this.attr('class').replace(/[^0-9]/ig,'')-1;
					img_info(i);
					s_a_r(_this,'cur');
					cur=i;
				})
			}
		})
		//小图上一页
		$('#smallImg-pre').click(function(){
			if(!$ul.is(':animated')){
				page--;
				var a=(page-1)*j,_a,c;
				for(var k=0;k<j;k++,a--){
					smallImg_click(a,_a,len,i);
					_html=h+_html;
				}
				$ul.prepend(_html).css({'right':box,'left':'auto'});
				$ul.animate({right:0},1600,function(){
					$ul.find('li:gt('+(j-1)+')').detach();//删除后9个li,从8开始
					_html='';
				});
				$('#smallImg-ul li',window.parent.document).click(function(){
					var _this=$(this);
					i=_this.attr('class').replace(/[^0-9]/ig,'')-1;
					img_info(i);
					s_a_r(_this,'cur');
					cur=i;
				})
			}
				
		})
		//点击小图
		$('#smallImg-ul li').click(function(){
			var _this=$(this);
			i=_this.attr('class').replace(/[^0-9]/ig,'')-1;
			img_info(i);
			s_a_r(_this,'cur');
			cur=i;
		})
		function s_a_r(o,c){
			o.addClass(c).siblings().removeClass(c);	
		}
		//大图左右点击
		function i_cur(i,len){
			i=i%len;
			if(i<0){
				i=len+i;
			}
			return i;	
		}
		function detailImg_click($pn,i,len){
			i_cur(i,len);
			img_info(i);
			var imgCur=$('.smallImg_'+(i+1),window.parent.document);
			if(!imgCur.html()){
				$pn.click();
			} 
			s_a_r($('.smallImg_'+(i+1),window.parent.document),'cur');//小图选中
		}
		//小图左右点击
		function smallImg_click(a,_a,len,i){
			_a=a;
			_a=a%len;
			if(_a<0){
				_a+=len;
			}
			c=_a==i?'cur':'';
			s_html(_a,c);
		}
		function s_html(_a,c){
			return h='<li class=\"smallImg_'+(_a+1)+'\"><a><img src=\"'+img.obj[_a].file_path+'\"></a></li>';
		}
		function img_info(i){
			var	src=img.obj[i].file_path,
				$main=$('#detailImg-box',window.parent.document);
			$main.find('img').attr({'src':src});
		}
	    var photoWrap=$(".photoWrap");
	    photoWrap.css({left:(clientW-photoWrap.outerWidth())/2,top:(clientH-photoWrap.outerHeight())/2}).fadeIn("fast");
	    $(mask).append(photoWrap);
	    window.parent.document.body.appendChild(mask);	
	    $(".close",window.parent.document).click(function(){
	    	$ul.html("");
	    	$('#detailImg-box',window.parent.document).html("");
	    	photoWrap.fadeOut("fast",function(){
	  		   $(mask).remove();
	  		   $(this).insertBefore(".right-view");
	  	   })
	    })
	})
$(".removeBand").click(function(){
	 var _this=$(this)
	 var pid=$(this).data("id");
	 var r=confirm("确定删除这条数据吗?");
	 if(r){
	 $.ajax({
			type:"get",
			dataType:"json",
			cache:false,
			url:urlPix+"/msp/mspPhoto/web/deleteSSPById.htm",
			async:false,
			data:{
				photoId:pid
			},
		    success:function(data){
		      alert(data.msg);
		      _this.parents("tr").remove();
		    }
		});
	 }else{
		 return false;
	 }
})
	
})