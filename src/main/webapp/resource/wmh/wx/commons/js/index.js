$(function(){
	var isNextPage = true;
    $(window).on("scroll",function(){
    	var searchContent = $.trim($("#searchContent").val());
    	if (searchContent==""|| searchContent==null) {
    		 $("div[name='findNum']").hide();
		}
        var scrollTop = $(window).scrollTop(),scrollHeight = $(document).height(),windowHeight = $(window).height();
        if(scrollTop + windowHeight >= scrollHeight-1 && isNextPage && moreData){
        	currentPage ++;
        	isNextPage = false;
            $(".moreWrap").slideDown();
            $.post(
        			urlPix + "/wmh/wmhUser/wx/goIndexTable.htm",
        			{
        				currentpage:currentPage,
        				searchContent:searchContent
        			},
        			function(response){
        				$(".moreWrap").slideUp();
            			$(".list").append(response);
            			isNextPage = true;
            			showDiv();   //微门户首页显示结果条数处理
        	});
        }
    });
});

function showDiv(){
	var countList = $("div[name='findNum']");
	var nowCount = "";
	countList.each(function(){
		var count = $(this).prop("count");
		if(count != nowCount){
			nowCount = count;
		}else{
			$(this).hide();
		}
	});
}