/**
 * Created by Administrator on 2017-3-10.
 */
//功能：统计包含汉字的字符个数
//说明：汉字占2个字符，非汉字占1个字符
function checksum(chars)
{
    var sum = 0;
    for (var i=0; i<chars.length; i++)
    {
        var c = chars.charCodeAt(i);
        if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f))
        {
            sum++;
        }
        else
        {
            sum+=2;
        }
    }
    return sum;
}
function showLoading() {
        $("body").append('<div class="loading" >' +
            '<div class="alert_green" style="text-align: center">' +
            '<img src='+urlPix+'"/resource/wmh/web/commons/img/loading_1.gif" width="10%" style="border-radius: 5px">' +
            '</div>' +
            '</div>');

        $(".loading").show();
}
function hideLoading() {
    $(".loading").hide();
}

$(function () {
    $("#qx").on("click",function(){
        if($(this).prop("checked")){
            $(".table_black input[type=checkbox]").prop("checked",true);
            $("#tk").show();
        }else{
            $(".table_black input[type=checkbox]").prop("checked",false);
            $("#tk").hide();
        }
    });
    function toTop(){
        window.top.scrollTo(0,1);
    }
    toTop();
    setFooter();
    function setFooter(){
        $(".copyrights ").removeClass("fixed");
        if(!window.top){
            var ih=$("#myiframe").height(),wh=$(window).height(),it=$("#myiframe").offset().top,ch=$(".copyrights ").height();
            if(wh-ih-it-ch<0){
                $(".copyrights ").removeClass("fixed");
            }
        }


    }

    $("#menu a").on("click",function(){
        setFooter();
    })

});

//发送消息页面选择部门及人员

function selectSend(obj){
	  var dataId=obj.parent().data("id");
	  var selectItem="<li data-id='"+dataId+"'>";
	  selectItem+="<span name='"+obj.attr("name")+"' data-is='on'>"+obj.html()+"</span><label class='fa' onclick='removeSelect($(this))'>&#xe635;</label>";
	  selectItem+="</li>";
	  if(obj.attr("data-is")=="on"){
		  if($("#selectList li").html()==undefined){
			  $("#selectList").append(selectItem);
			  obj.attr("data-is","off"); 
		  }else{
			   $("#selectList li").each(function(){
				   if($(this).data("id")==dataId){
					   $(this).remove();
					   $("#normal li").add("#searchList li").each(function(){
							if($(this).data("id")==dataId){
								$("span",this).attr("data-is","on")
							}
						})					   
				   }else{
					$("#selectList").append(selectItem);
					obj.attr("data-is","off");  
					selectItem="";
				   }
			   })
		  }
	  }else{
		  alert("您已选择该发送对象!")
	  }
}

//发送消息页面移除所选部门及标签方法
function removeSelect(obj){
	var selectId=obj.parent().data("id");
	$("#normal li").add("#searchList li").each(function(){
		if($(this).data("id")==selectId){
			$("span",this).attr("data-is","on")
		}
	})
	obj.parent().remove();
}
//移除已提交的发送范围
function removeThis(obj){
	    var thisData=$(this).data("id");
	    $("#normal li span[data-is]").each(function(){
	    	if($(this).data("id")==thisData){
	    		$(this).attr("data-is","on") 
	    	}
	    })
  		obj.parent().remove();
  	}
 