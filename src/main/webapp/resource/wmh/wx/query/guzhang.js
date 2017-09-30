
    function saveFault(){
 
/*		var place=$('.write-where').val();
		
		if(place == "" || place == null){
			alert("故障地点不能为空！");
			return;
		}
		var faultInfo = $("#faultInfo").val();
		if(faultInfo == "" || faultInfo == null){
			alert("故障名称不能为空！");
			return;
		}
		
		
		var faultReason=$('.write-reson').val();
	
    	var formdata={};

            var urls = [];
             $('#imgs img').each(function () {
                 urls.push($(this).attr('src'));
             })
   
     	formdata.faultInfo=faultInfo;
        formdata.place=place;  
        formdata.faultReason=faultReason;
        formdata.imgs = urls;
    	$.ajax({
    		url:urlPix+'/wmh/faultRepair/wx/faultRepair.htm',
    		type:'POST',
    		dataType:'json',
    		processData:false,
    		contentType:false,
    		data:formdata,
    		success:function(response){
    			//隐藏进度条
    			//hideLoading();
    			var msg = response.msg;
    			if(msg == "" || msg == null){
    				window.location.href=urlPix+"/resource/wmh/wx/query/appcenter.html";	
    		  	}else{
    		  		alert(msg);
    		  	}
    		},
    		error:function(){
    			alert('error');
    			console.info('error');
    		} 
    	});*/
    	

    		var faultInfo = $("#faultInfo").val();
    		if(faultInfo == "" || faultInfo == null){
    			alert("故障名称不能为空！");
    			return;
    		}
        	var formdata=new FormData($("#gzbx_form")[0]);
        	for(var i=0;i<$("#imgs img").length;i++){
            	/* fromdata.append("imgs", convertdataURIToBlob_j($(".imglist3 img").get(i).src)); */
            	formdata.append("imgs", $("#imgs img").get(i).src);
            }
        	$.ajax({
        		url:urlPix+'/wmh/faultRepair/wx/faultRepair.htm',
        		type:'POST',
        		dataType:'json',
        		processData:false,
        		contentType:false,
        		data:formdata,
        		success:function(response){
        			//隐藏进度条
        			//hideLoading();
        			var msg = response.msg;
        			if(msg == "" || msg == null){
        				window.location.href=urlPix+"/resource/wmh/wx/query/appcenter.html";	
        		  	}else{
        		  		alert(msg);
        		  	}
        		},
        		error:function(){
        			alert('error');
        			console.info('error');
        		} 
        	});
     
    	
    }
    
    