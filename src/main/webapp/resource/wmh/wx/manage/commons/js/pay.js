
mui('body').on('tap', '#kaoshi', function () {$(this).click(kaoshi($(this))); });

mui('body').on('tap', '#xuefei', function () {$(this).click(kaoshi($(this))); });

mui('body').on('tap', '#ziliao', function () {$(this).click(kaoshi($(this))); });

mui('body').on('tap', '#aixin', function () {$(this).click(kaoshi($(this))); });

mui('body').on('tap', '#quanbu', function () {$(this).click(kaoshi($(this))); });

//考试
function kaoshi(kaoshi) {

	var payType=kaoshi.data('id');
	window.location.href = urlPix+ "/wmh/payManage/web/goPayRecordPageWx1.htm?payType="+payType;
	pullupRefresh();

}

//学费
function xuefei(kaoshi) {

	var payType=kaoshi.data('id');
	window.location.href = urlPix+ "/wmh/payManage/web/goPayRecordPageWx1.htm?payType="+payType;
	pullupRefresh();

}

//资料
function ziliao(kaoshi) {

	var payType=kaoshi.data('id');
	window.location.href = urlPix+ "/wmh/payManage/web/goPayRecordPageWx1.htm?payType="+payType;
	pullupRefresh();

}

//爱心
function aixin(kaoshi) {

	var payType=kaoshi.data('id');
	window.location.href = urlPix+ "/wmh/payManage/web/goPayRecordPageWx1.htm?payType="+payType;
	pullupRefresh();

}

function quanbu(kaoshi) {

	var payType=kaoshi.data('id');
	window.location.href = urlPix+ "/wmh/payManage/web/getPayCountPageWx.htm";
	pullupRefresh();

}
