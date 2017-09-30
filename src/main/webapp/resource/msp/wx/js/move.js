//begin Mr.夏目
(function($) {
	$.fn.move = function(setti) {
		var defaultSetti = {
			width: 200, //实际显示区域
			height: 200, //实际显示区域
			x: true, // 横向移动
			y: true, //纵向移动
			chil: ".Slide", //控制移动元素
			chilWidth:0,    //必要时候手动添加移动元素宽高
			chilHeight:0    //必要时候手动添加移动元素宽高
		}
		setti = $.extend(true, {}, defaultSetti, setti);
		return this.each(function() {
			var _this = $(this),
				s = setti;
			var chil = $(s.chil, _this); //触控元素
			var c_li = chil.children(); //所有列表
			var startX = 0,
				startY = 0; //触摸开始时手势横纵坐标 
			var temPos = {}; //元素当前位置
			var oPosition = {}; //触点位置
			var wh = {}; //元素宽高
			wh.w=s.chilWidth;
			wh.h=s.chilHeight;
			var z = 0; //手指滑动时间
			var d = {}; //总过滑动距离
			var m = 0; //每毫秒滚动距离
			//页面加载或发生改变
			$(window).bind('resize load', function() {
				zong_fun();
			});
			zong_fun();
			function zong_fun() {
				b();
				if(!s.chilWidth){
					wh.w = chil.width();
				}
				if(!s.chilHeight){
					wh.h = chil.height();
				}
				if(wh.w < s.width) { //防止输入的值不符合实际
					s.width = wh.w
				}
				if(wh.h < s.height) {
					s.height = wh.h
				}
				loc();
			}
			if(_this.css("position") == "static") { //检测是否定位
				_this.css("position", "relative")
			}
			//获取滑动时间
			function autoMo() {
				z++;
			}
			
			//停止计算
			function b() { //绑定三个事件
				chil.get(0).addEventListener("touchstart", c, false)
				chil.get(0).addEventListener('touchmove', y, false);
				chil.get(0).addEventListener('touchend', t, false);
			}

			function h(e) { //获取触摸点
				var touches = e.changedTouches,l = touches.length,touch, tagX, tagY;
				for(var i = 0; i < l; i++) {
					touch = touches[i];
					tagX = touch.clientX;
					tagY = touch.clientY;
				}
				oPosition.x = tagX;
				oPosition.y = tagY;
				if(!z) {
					d.x = tagX;
					d.y = tagY;
				}
			}

			function c(e) { //触摸开始
				h(e);
				startX = oPosition.x;
				startY = oPosition.y;
				temPos.x = chil.position(_this).left;
				temPos.y = chil.position(_this).top;
				animate(chil, 0);
			}

			function y(e) { //触摸移动
				e.preventDefault();
				h(e);
				autoMo();
				var moveX = oPosition.x - startX;
				var moveY = oPosition.y - startY;
				if(s.x) {
					chil.css({
						left: temPos.x + moveX
					});
				}
				if(s.y) {
					chil.css({
						top: temPos.y + moveY
					});
				}
			}

			function t(e) { //触摸结束
				temPos.x = chil.position().left;
				temPos.y = chil.position().top;
				if(s.x) {
					var wwl = (d.x - oPosition.x) / z * 20;
					_left(chil, wwl);
				}
				if(s.y) {
					var wwt = (d.y - oPosition.y) / z * 20;
					_top(chil, wwt);
				}
				z = 0; //初始化触控时间
			}

			function loc() { //页面加载完成定位到选中位置
				oPosition.x = 0; //触点位置
				oPosition.y = 0;
				temPos.x = 0; //元素当前位置
				temPos.y = 0;
				var c_li_num=0;
				for(var i = 0; i < c_li.length; i++){
					if(c_li.eq(i).hasClass("on") || c_li.eq(i).hasClass("cur")) {
						c_li_num++;
					}
				}
				if(c_li_num==0){
					return;
				}
				for(var i = 0; i < c_li.length; i++) {
					if(c_li.eq(i).hasClass("on") || c_li.eq(i).hasClass("cur")) {
						break;
					}
					oPosition.x -= c_li.eq(i).width();
					oPosition.y -= c_li.eq(i).height();
				}
				var moveX = oPosition.x - startX;
				var moveY = oPosition.y - startY;
				if(s.x) {
					chil.css({
						left: temPos.x + moveX
					});
				}
				if(s.y) {
					chil.css({
						top: temPos.y + moveY
					});
				}
			}

			function animate(obj, num) {
				clearInterval(obj.timer);
				clearInterval(obj.timers);
				if(!num) {
					return;
				}
			}
			function _top(obj, num) {
				clearInterval(obj.timer);
				if(!num) {
					return;
				}
				var ti = 0;
				if(num > 0) {
					obj.timer = setInterval(function() {
						var flag = true; //清楚定时器的标识
						var step = num / 20;
						step = step > 0 ? Math.ceil(step) : Math.floor(step);
						obj.css("top", temPos.y - step - ti);
						ti = ti + step;
						num = num - step;
						if(num < 0 || -temPos.y + step + ti > wh.h - s.height) {
							clearInterval(obj.timer);
						}
						if(-temPos.y - step - ti > wh.h - s.height) {
							clearInterval(obj.timer);
							doAnimt(-wh.h + s.height)
						}
					}, 10)
				} else {
					obj.timer = setInterval(function() {
						var step = num / 20;
						step = step > 0 ? Math.ceil(step) : Math.floor(step);
						obj.css("top", temPos.y - step - ti);
						ti = ti + step;
						num = num - step;
						if(num > 0 || temPos.y - step - ti > 0) {
							clearInterval(obj.timer);
						}
						if(temPos.y - step - ti > 0) {
							clearInterval(obj.timer);
							doAnimt(0)
						}
					}, 10)
				}
			}
			function _left(obj, num) {
				clearInterval(obj.timers);
				if(!num) {
					return;
				}
				var til = 0;
				if(num > 0) {
					obj.timers = setInterval(function() {
						var stepl = num / 20;
						stepl = stepl > 0 ? Math.ceil(stepl) : Math.floor(stepl);
						obj.css("left", temPos.x - stepl - til);
						til = til + stepl;
						num = num - stepl;
						if(num < 0 || -temPos.x + stepl + til > wh.w - s.width) {
							clearInterval(obj.timers);
						}
						if(-temPos.x - stepl - til > wh.w - s.width) {
							doAniml(-wh.w + s.width)
						}
					}, 10)
				} else {

					obj.timers = setInterval(function() {
						var stepl = num / 20;
						stepl = stepl > 0 ? Math.ceil(stepl) : Math.floor(stepl);
						obj.css("left", temPos.x - stepl - til);
						til = til + stepl;
						num = num - stepl;
						if(num > 0 || temPos.x - stepl - til > 0) {
							clearInterval(obj.timers);
						}
						if(temPos.x - stepl - til > 0) {
							doAniml(0)
						}
					}, 10)
				}
			}

			function doAnimt(iTarget) {
				chil.animate({
					top: iTarget
				}, {
					duration: 300,
					queue: false
				});
			}

			function doAniml(iTarget) {
				chil.animate({
					left: iTarget
				}, {
					duration: 300,
					queue: false
				});
			}
			try {
				if(typeof(eval(_data)) == "function") {
					_data(); //可用于传输内部数据
				}
			} catch(e) {}
		});
	}
})(jQuery);