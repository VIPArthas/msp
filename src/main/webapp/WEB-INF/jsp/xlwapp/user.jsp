<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>会员</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/fonts/iconfont.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/css.css"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/style.css"/>
	 <script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/jquery-1.9.1.min.js"></script>
	 <script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/WapCircleImg.js"></script>
	  <script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/public.js"></script>
	<link rel="Shortcut Icon" href="<%=request.getContextPath()%>/resource/xlwapp/images/study.ico" />
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
			<div class="container">
				 <div class="user_top">
				     <dl>
					 	<dt><img onerror="showUserImg(this);" src="<%=request.getContextPath()%>${userMap.logo_url}"><em><img width="13" src="<%=request.getContextPath()%>/resource/xlwapp/images/vicon.png"></em></dt>
						<dd><h3>${userMap.nick_name != null? userMap.nick_name:"未设置昵称"}</h3><%--<p>写作界里最会设计的画家</p>--%><span>等级：${userMap.level}</span></dd>
					 </dl>
				 </div>
				 <div class="user_top_money">
				 <a href=""><dl>
				    <dt>校币</dt>
					<dd>${userMap.school_money != null ? userMap.school_money : 0 }</dd>
					</dl>
					</a>
					<a href=""><dl>
				    <dt>积分</dt>
					<dd>${userMap.score != null ? userMap.score : 0 }</dd>
					</dl>
					</a>
				 </div>
				  <div class="userinfo_box">
              
                <a href="<%=request.getContextPath()%>/common/userFont/wx/userInfo.htm"> <dl>
                	<dt><img width="24" src="<%=request.getContextPath()%>/resource/xlwapp/images/user_icon1.png">个人资料</dt>
                    <dd> </dd>
                </dl>
				</a>
				 <a href="<%=request.getContextPath()%>/common/attestation/wx/toAttestation.htm"> <dl>
                	<dt><img width="24" src="<%=request.getContextPath()%>/resource/xlwapp/images/user_icon2.png">实名认证</dt>
                    <dd><c:if test="${userMap.realname_status == 1}"><label>已基础认证</label></c:if></dd>
                </dl>
				</a>
				 <a href="<%=request.getContextPath()%>/common/userRecharge/wx/choiseRecharge.htm"> <dl>
                	<dt><img width="24" src="<%=request.getContextPath()%>/resource/xlwapp/images/user_icon3.png">充值</dt>
                    <dd> </dd>
                </dl>
				</a>
				<!-- 未实名认证，不能提现 -->
				<c:choose>
					<c:when test="${userMap.realname_status == 1 || userMap.realname_status == 2}">
						<a href="<%=request.getContextPath()%>/common/userCash/wx/viewCash.htm">
							<dl>
		                		<dt><img width="24" src="<%=request.getContextPath()%>/resource/xlwapp/images/user_icon4.png">提现</dt>
		                    	<dd> </dd>
		                	</dl>
						</a>
					</c:when>
					<c:otherwise>
						<a href="javascript:void(0);">
							<dl>
		                		<dt style="color:#aaa;"><img width="24" src="<%=request.getContextPath()%>/resource/xlwapp/images/user_icon4.png">未实名认证不能提现</dt>
		                    	<dd> </dd>
		                	</dl>
						</a>
					</c:otherwise>
				</c:choose>
				 <a href="javascript:void(0);"> <dl>
                	<dt><img width="24" src="<%=request.getContextPath()%>/resource/xlwapp/images/user_icon5.png">消费明细</dt>
                    <dd> </dd>
                </dl>
				</a>
            </div>
			 <div class="userinfo_box">
              	 <a href="<%=request.getContextPath()%>/common/userFont/wx/toChangeTel.htm"> <dl>
                	<dt><img width="24" src="<%=request.getContextPath()%>/resource/xlwapp/images/user_icon6.png">修改手机号</dt>
                    <dd> </dd>
                </dl>
				</a>
				 <a href="<%=request.getContextPath()%>/common/complaintSuggestions/wx/toList.htm"> <dl>
                	<dt><img width="24" src="<%=request.getContextPath()%>/resource/xlwapp/images/user_icon7.png">意见反馈</dt>
                    <dd> </dd>
                </dl>
				</a>
				 <a href="<%=request.getContextPath()%>/common/userFont/wx/toChangePassword.htm"> <dl>
                	<dt><img width="24" src="<%=request.getContextPath()%>/resource/xlwapp/images/user_icon8.png">修改密码</dt>
                    <dd> </dd>
                </dl>
				</a>
				 <a href="<%=request.getContextPath()%>/common/userLogin/wx/toLogin.htm"> <dl>
                	<dt><img width="24" src="<%=request.getContextPath()%>/resource/xlwapp/images/user_icon9.png">退出登录</dt>
                    <dd> </dd>
                </dl>
				</a>
				 
            </div>
					<div class="btmline"></div>
					</div>
				<jsp:include page="../frontCommon/foot.jsp"></jsp:include>
				</body>
				</html>
