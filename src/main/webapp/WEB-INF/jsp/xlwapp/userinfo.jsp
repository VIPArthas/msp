<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>我的个人资料</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/fonts/iconfont.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/css.css"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/style.css"/>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/easyui/jquery.easyui.min.js"></script>
	 <script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/WapCircleImg.js"></script>
	  <script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/public.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/zepto.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/validation_a.js"></script>
	<link rel="Shortcut Icon" href="<%=request.getContextPath()%>/resource/xlwapp/images/study.ico" />
	<script>
		$(document).ready(function () {
			$(".mal").find("a").click(function () {
				$(this).addClass("cur");
				$(this).siblings().removeClass("cur");
				if($(this).html == "男") {
					$("#sexInput").val(0);
				} else{
					$("#sexInput").val(1);
				}
			});
		});
	</script>
	<script>
		function showUserImg(e) {
			$(e).attr('src',"<%=request.getContextPath()%>/resource/xlwapp/images/user_icon1.png")
		}

	</script>
	<script>
		$(document).ready(function(){
			$("#footC").find("a").each(function() {
				if($(this).text() == "我的") {
					$(this).parent().addClass("cur");
				}else{
					$(this).parent().removeClass();
				}
			})
		})

	</script>
</head>
<body class="combg">
<%@include file="../frontCommon/head.jsp" %>
	<form method="POST" data-options="novalidate: true" enctype="multipart/form-data" id="userInfo"
		  action="<%=request.getContextPath()%>/common/userFont/wx/updateUser.htm">
			 <div class="container">


				 <div class="user_info">
				     <div class="user_infobox">
					     <dl>
						 	<dt style="line-height:50px">头像</dt>
							<dd>
								<a href="javascript:void(0);"><img width="45" onerror="showUserImg(this);" src="<%=request.getContextPath()%>${userMap.logo_url}" class="txsz_yh"></a>
								<input class="upload" type="file" name="logo" id="logoUrl" onChange="preivew(this);" />
							</dd>
						 </dl>
						  <dl>
						 	<dt>昵称</dt>
							<dd><input type="text" placeholder="${userMap.nick_name }" value="${userMap.nick_name }" id="nickName" name="nickName" maxlength="6" ></dd>
							  <input type="hidden" value="${userMap.id}" id="userId" name="userId">
						 </dl>
						  <dl>
						 	<dt>性别</dt>
							<dd><div class="mal" id="sex">
								<a class="${userMap.sex == 0 ? 'cur':'' }" href="javascript:void(0);" >男</a>
								<a class="${userMap.sex == 1 ? 'cur':'' }" href="javascript:void(0);">女</a>
								<input type="hidden" id="sexInput" name="sex" value="${userMap.sex}">
							</div></dd>
						 </dl>
						  <dl>
						 	<dt>出生地</dt>
							<dd><input type="text" placeholder="${userMap.birth_place}" value="${userMap.birth_place}" id="birth_place" name="birthPlace" maxlength="20"></dd>
						 </dl>
						  <dl>
						 	<dt>出生日期</dt>
							<dd><input type="date" value="<fmt:formatDate value="${userMap.birth_date}" pattern="yyyy-MM-dd"/>" id="birth_date" name="birthDate"></dd>
						 </dl>
						  <dl>
						 	<dt>身份证号</dt>
							<dd><input type="text" placeholder="${userMap.card_id}" ${userMap.realname_status == 1|| userMap.realname_status == 2 ? 'readonly':''}  value="${userMap.card_id}" id="card_id" name="cardId" maxlength="18"></dd>
						 </dl>
				 
					 </div>
					 <div class="user_infobox">
						  <dl>
						 	<dt>学校名称</dt>
							<dd><input type="text" placeholder="${userMap.school_name}" value="${userMap.school_name}" id="school_name" name="schoolName" maxlength="20"></dd>
						 </dl>
						  <dl>
						 	<dt>入学年月</dt>
							<dd><input type="date" value="<fmt:formatDate value="${userMap.start_time}" pattern="yyyy-MM-dd"/>" id="start_time" name="startTime"></dd>
						 </dl>
						  <dl>
						 	<dt>学号</dt>
							<dd><input type="text" placeholder="${userMap.student_id}" value="${userMap.student_id}" id="student_id" name="studentId" maxlength="12"></dd>
						 </dl>
						  <dl>
						 	<dt>专业</dt>
							<dd><input type="text" placeholder="${userMap.major_name}" value="${userMap.major_name}" id="major_name" name="majorName" maxlength="15"></dd>
						 </dl>
						  <dl>
						 	<dt>宿舍号</dt>
							<dd><input type="text" placeholder="${userMap.dormitory_no}" value="${userMap.dormitory_no}" id="dormitory_no" name="dormitoryNo" maxlength="10"></dd>
						 </dl>
				 
					 </div>
					 <div class="user_infobox">
						  <dl>
						 	<dt>微信号</dt>
							<dd><input type="text" placeholder="${userMap.wechat}" value="${userMap.wechat}" id="wechat" name="wechat" maxlength="15"></dd>
						 </dl>
						  <dl>
						 	<dt>QQ号</dt>
							<dd><input type="text" placeholder="${userMap.qq}" value="${userMap.qq}" id="qq" name="qq" maxlength="15"></dd>
						 </dl>
						  <dl>
						 	<dt>支付宝帐号</dt>
							<dd><input type="text" placeholder="${userMap.alipay}" value="${userMap.alipay}" id="alipay" name="alipay" maxlength="20"></dd>
						 </dl>
				 
					 </div>
				 </div>

			  </div>
			 <div class="m10">
			 <a class="combtn" href="javascript:void(0);" onClick="update();">编辑资料</a>
			</div>
	</form>
			 <div class="btmline"></div>
					<jsp:include page="../frontCommon/foot.jsp"></jsp:include>

				</body>
<script>
	function update() {
		var cardId = $("#card_id").val();
		var sex;
		$("#sex").find("a").each(function(index,element) {
			if($(element).hasClass("cur")) {
				sex = index;
			}
		});
		var cardReg = /^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/
		if(cardId != null && cardId != '' && !cardId.match(cardReg)) {
			alert_t("身份证格式不正确");
			return;
		}


		var userInfoForm = $("#userInfo");

		userInfoForm.form("submit", {
			url:"<%=request.getContextPath()%>/common/userFont/wx/updateUser.htm",

			success: function(data) {
				if(data.indexOf("<html") > -1){
					window.location.reload();
					return;
				}
				var returnJson = JSON.parse(data);
				if(returnJson.code == 1){
					alert_t(returnJson.msg)
					window.location.href = '<%=request.getContextPath()%>/common/userFont/wx/user.htm';
				}else{
					alert_t(returnJson.msg);
				}
			}
		});

	}


	function preivew(file) {
		var imgType = ["jpg", "gif", "bmp", "png", "jpeg"];
		var isVaild = true;
		if (file) {
			if (file.files && file.files.length > 0) {
				var fileList = file.files;
				for (var i = 0; i < fileList.length; i++) {
					var suffix = fileList[i].name.substr(fileList[i].name.lastIndexOf(".") + 1, fileList[i].name.length);
					if (imgType.indexOf(suffix) == -1) {
						isVaild = false;
					}
				}
			}
		} else {
			isVaild = false;
		}
		if (isVaild == false) {
			$.messager.alert("提示", "请上传正确格式的文件！", "error");
			return;
		} else {
			$(".txsz_yh").attr("src", window.URL.createObjectURL(file.files[0]));
		}
	}
</script>
</html>
