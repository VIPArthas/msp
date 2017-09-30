<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html style='height:auto;overflow-y:auto;'>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
		<meta name="renderer" content="webkit">
		<meta http-equiv="Access-Control-Allow-Origin" content="*">
		<title>微门户管理后台</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/css/mui.min.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/css/font/iconfont.css"/>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/css/demo.css"/>
		<script type="text/javascript" src="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/js/jquery-3.1.1.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/js/rem.js"></script>

	</head>
	<body>
		<div id="offCanvasWrapper" class="mui-off-canvas-wrap mui-draggable">
			<!--侧滑菜单部分-->
			<aside id="offCanvasSide" class="mui-off-canvas-left">
				<div id="offCanvasSideScroll" class="mui-scroll-wrapper">
					<div class="mui-scroll">
						<div class="scroll-right"><img src="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/img/bj.jpg" alt=""></div>
						<div class="menu">
							<ul id="menu">
								<li class="oneli"><a href="<%=request.getContextPath() %>/wmh/userManage/web/getUserCountPageWx.htm" target="myiframe">
									<div>
										<span class="icon iconfont icon-yonghuguanli"></span>
										<p class="yonghuguanli">用户管理</p>
									</div>
								</a></li>
								<li class="two">
									<a href="<%=request.getContextPath() %>/wmh/message/web/toMessagePush1.htm" target="myiframe">
										<div>
											<span class="icon iconfont icon-xiaoxi"></span>
											<p class="messagepush">消息推送</p>
										</div>
									</a>
									<div class="message">
										<a href="<%=request.getContextPath() %>/wmh/message/web/toMessagePush1.htm" target="myiframe">
											<div>
												<span class="icon iconfont icon-fasong"></span>
												<p class="yonghuguanli">发送消息</p>
											</div>
										</a>
										<a href="<%=request.getContextPath() %>/wmh/message/web/pushMessageHistory1.htm" target="myiframe">
											<div>
												<span class="icon iconfont icon-lishirenwu"></span>
												<p class="yonghuguanli">查看历史消息</p>
											</div>

										</a>
									</div>
								</li>
								<li class="threeli"><a href="<%=request.getContextPath() %>/wmh/news/web/goNewsList1.htm" target="myiframe">
									<div>
										<span class="icon iconfont icon-xinwen"></span>
										<p class="yonghuguanli">新闻展示</p>
									</div>
								</a></li>
								<li class="fourli"><a href="<%=request.getContextPath() %>/wmh/payManage/web/getPayCountPageWx.htm" target="myiframe">
									<div>
										<span class="icon iconfont icon-icon108"></span>
										<p class="yonghuguanli">在线支付</p>
									</div>
								</a></li>
							</ul>
						</div>


					</div>
				</div>
			</aside>
			<!--主界面部分-->
			<div class="mui-inner-wrap">
				<header class="mui-bar mui-bar-nav top">
					<a href="#offCanvasSide" class="mui-icon mui-action-menu icon iconfont icon-liebiao mui-pull-left"></a>
					<a class="zhuxiao mui-pull-right icon iconfont icon-zhuxiao"></a>
					<h1 class="mui-title">${title}</h1>
				</header>
				<div id="offCanvasContentScroll" class="mui-content mui-scroll-wrapper">
					<iframe name="myiframe" id="myiframe" src="<%=request.getContextPath() %>/wmh/userManage/web/getUserCountPageWx.htm" frameborder="0" style="width: 100%;overflow-y: auto;height:100vh"></iframe>
				</div>
				<div class="mui-off-canvas-backdrop"></div>
			</div>
		</div>
		<script src="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/js/mui.min.js"></script>
		<script>
	       //$("iframe").attr('style','height:auto');
		   //console.log($("html").height());

			mui('#offCanvasSide').on('tap', 'a', function () {
//				console.log($(this).attr('href'))
				$('iframe').attr('src',$(this).attr('href'));
				offCanvasWrapper.offCanvas('close');
			});

			mui.init();
			//侧滑容器父节点
			var offCanvasWrapper = mui('#offCanvasWrapper');
			//主界面容器
			var offCanvasInner = offCanvasWrapper[0].querySelector('.mui-inner-wrap');
			//菜单容器
			var offCanvasSide = document.getElementById("offCanvasSide");
			if (!mui.os.android) {
				document.getElementById("move-togger").classList.remove('mui-hidden');
				var spans = document.querySelectorAll('.android-only');
				for (var i = 0, len = spans.length; i < len; i++) {
					spans[i].style.display = "none";
				}
			}
			//主界面‘显示侧滑菜单’按钮的点击事件
			document.getElementById('offCanvasShow').addEventListener('tap', function() {
				offCanvasWrapper.offCanvas('show');
			});
			//菜单界面，‘关闭侧滑菜单’按钮的点击事件
			document.getElementById('offCanvasHide').addEventListener('tap', function() {
				offCanvasWrapper.offCanvas('close');
			});
//
			//主界面和侧滑菜单界面均支持区域滚动；
			mui('#offCanvasSideScroll').scroll();
			mui('#offCanvasContentScroll').scroll();
			//实现ios平台原生侧滑关闭页面；
			if (mui.os.plus && mui.os.ios) {
				mui.plusReady(function() { //5+ iOS暂时无法屏蔽popGesture时传递touch事件，故该demo直接屏蔽popGesture功能
					plus.webview.currentWebview().setStyle({
						'popGesture': 'none'
					});
				});
			}
		</script>
	</body>

</html>