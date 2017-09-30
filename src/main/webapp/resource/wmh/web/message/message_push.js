//选择发送范围操作
  $(document).ready(function(){
  	//设置弹出框
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
 	var sendrange=$(".sendrange");
  	//弹出操作
  	$("#mask").click(function(){
  		$("#selectList").html("");
  		sendrange.insertBefore(window.parent.document.body);//将子页面的选择项强制插入到父页面
  		window.parent.document.body.appendChild(mask);//将弹出层添加到父页面
  		sendrange.css({
  	  		left:(clientW-sendrange.outerWidth())/2,
  	  		top:parseInt((clientH-sendrange.outerHeight()))/2
  	  	}).fadeIn("fast");
  	})
   $(".closerange").click(function(){
	   sendrange.fadeOut("fast",function(){
		   $(mask).remove();
		   $(this).insertBefore(".tabs");
	   })
	  
	})

//    //添加操作
//    var ranges=$(".tab-content .result-list li");
//    var selects=$(".select-list .result-list");
//    var deleteIco="<label class='fa' onclick='deleteItem($(this))'>&#xe635;</label>"
//    ranges.each(function(){
//    	$("span",this).click(function(){
//    		var addItem="<li>"+$(this).parent().html()+deleteIco+"</li>";
//    		selects.append(addItem)
//    	})
//    })

  })

   