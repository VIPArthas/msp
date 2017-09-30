
 mui('body').on('tap', '.editor1', function () {$(this).click(deleteuser($(this))); });


 mui('body').on('tap', '.adduser', function () {$(this).click(adduser($(this))); });
 
//编辑前 
function edituser(aa) {
	
	var userId = aa.data('userId');

	window.location.href = urlPix
			+ "/wmh/userManage/web/goEditUserPageWx.htm?userId=" + userId;

}
// 查询
function userSearch() {
	
	var searchContent = $("#vagueSearch").val();

	window.location.href = urlPix+ "/wmh/userManage/web/goUserManageListWx1.htm?searchContent="+ searchContent;
	pullupRefresh();
	

	/*
	 $.post(urlPix+"/wmh/userManage/web/goUserManageListWx1.htm",{
		 searchContent:searchContent
				},function(response){
					var data = JSON.parse(response);
					window.location.href = urlPix+"/wmh/userManage/web/getUserCountPageWx.htm?searchContent="+searchContent;
				});*/

}
//跳转到添加用户页面
function adduser() {
	window.location.href = urlPix+ "/wmh/userManage/web/goAddUserPageWx.htm?";
}


function deleteuser(cc){
	
	var userId = cc.data('userId');
	console.log(userId);
	var con = confirm("确定删除该用户？");
	if(con){
		$.post(urlPix+"/wmh/userManage/web/deleteUserByUserId.htm",
				{id:userId},
				function(response){
					var data = JSON.parse(response);
					if(data.code == 0){
						window.location.reload(true);
					}
			
		});
	}
}
