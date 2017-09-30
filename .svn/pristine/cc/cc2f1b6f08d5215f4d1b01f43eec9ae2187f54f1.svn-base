<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en" style="background-color: #f3f3f3;height: 100%">
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0, minimal-ui">
    <meta http-equiv="Access-Control-Allow-Origin" content="*"> 
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <title>在线支付</title>
	<style>
	.move{
		width:100vw;
		height:100vh;
		background-color:rgba(00,00,00,0.5);
		z-index:1000;
		position: fixed;
		top:0;
		display:none;
	}
	.move div{
		width:70vw;
		height:20vh;
		background-color:white;
		margin-left:15vw;
		margin-top:30vh;
		padding:3vh 3vh 3vh 3vh;
		border-radius: 0.1rem;
	}
	</style>
        <link rel="stylesheet" href="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/css/mui.min.css">
    
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/css/font/iconfont.css"/>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/js/jquery-3.1.1.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/css/bootstrap.min.css"/>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/js/rem.js"></script>
    <script type="text/javascript">var urlPix = "<%=request.getContextPath()%>";</script>
   
</head>
<body style="background-color: #f3f3f3;" id="pay">
<div class="header"></div>

<div id="pullrefresh" class="mui-content mui-scroll-wrapper" style="background-color: #f3f3f3;padding-bottom: 0.8rem">
    <div class="mui-scroll">
        <!--数据列表-->
        <div class="pay-message mui-table-view mui-table-view-chevron">
        <section class="pay-title">
    
    <div data-id='12' id='kaoshi' class="pay-active" >考试费</div>
    <div data-id='13' id='xuefei' >学费</div>
    <div data-id='14' id='ziliao' >资料费</div>
    <div data-id='15' id='aixin' >爱心捐助</div>
    <c:if test="${not empty payType}">
     <input hidden="hidden" id="paymoney"   value="${payType }"></input>
    </c:if>
</section>
            <div class="row mui-table-view-cell colorblue">
                <div class="col-xs-2" style="padding:0;">付款人</div>
                <div class="col-xs-3" style="padding:0;">付款金额</div>
				
				
                <div class="col-xs-5" style="padding:0;">时间</div>
				<div class="col-xs-2" style="padding:0;">备注</div>
				
            </div>
        	 <%--    <c:choose>
                <c:when test="${not empty payRecordList[0]}">
                    <c:forEach items="${payRecordList[0]}" var="pay">
                        <div class="row mui-table-view-cell">
                			<div class="col-xs-2">${pay.real_name}</div>
                			<div class="col-xs-3">${pay.pay_school_money/100}元</div>
                			<div class="col-xs-4">${pay.pay_time}</div>
							<div class="col-xs-3">1</div>
           				 </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr><td colspan="4" style="text-align: center">暂无数据</td></tr>
                </c:otherwise>

            </c:choose> --%>
        </div>
    </div>

</div>
<div class="footer"></div>

<div class="move">
       <div>
	   
       </div>
</div>
<script src="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/js/mui.min.js"></script>
<script>
mui('body').on('tap', '.pay-title div', function () { 
	$(this).addClass('pay-active').siblings().removeClass('pay-active') ;
	
	});

    mui.init({
    	 gestureConfig:{
             doubletap:true
         },
        pullRefresh: {
            container: '#pullrefresh',
            up: {
                contentrefresh: '正在加载...',
                callback: pullupRefresh
            }
        }
    });
    var currentPage = 0;
	var totalPageSize = ${totalPage};
	function formatDate(now) { 
		var year=now.getFullYear(); 
		var month=now.getMonth()+1; 
		var date=now.getDate(); 
		var hour=now.getHours(); 
		var minute=now.getMinutes(); 
		var second=now.getSeconds(); 
		return year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second; 
		} 

    function pullupRefresh() {
        setTimeout(function () {
            mui('#pullrefresh').pullRefresh().endPullupToRefresh((++currentPage > totalPageSize)); //参数为true代表没有更多数据了  初始加载的不计入count  5代表可上拉的次数，即总数据/10向上取整。
            var table = document.body.querySelector('.mui-table-view');
            
    	    var payType=$('#paymoney').val();
    		$('.pay-title div').each(function(){
				if($(this).data('id') == payType){
					$(this).addClass('pay-active').siblings().removeClass('pay-active') ;
				}
			})
				
    	    console.log(payType);
        	$.post(
        			"<%=request.getContextPath() %>/wmh/payManage/web/goPayRecordPageWx.htm",
        	   		{
        				currentpage:currentPage,payType:payType
        			},
        	   		function(response){
        			

        				
        				var res = JSON.parse(response);
        				var list = res.payRecordList[0];
        				for (var i = 0;  i < list.length; i++){
        					var pay = list[i];
        					var payTime=new Date(pay.pay_time.time);        					
        		            var sec = document.createElement('div');
        	                sec.className = 'row mui-table-view-cell';   
                            if(pay.remark){
								 sec.innerHTML = ' <div class="col-xs-2" style="padding:0;">'+pay.real_name+'</div><div class="col-xs-3" style="padding:0;">'+pay.pay_school_money/100+'元</div><div class="col-xs-5" style="padding:0;">'+formatDate(payTime)+'</div><div class="col-xs-2 xq" style="padding:0;text-decoration:underline;"data-id="'+pay.remark+'">详情</div>';
							}else{
								sec.innerHTML = ' <div class="col-xs-2" style="padding:0;">'+pay.real_name+'</div><div class="col-xs-3" style="padding:0;">'+pay.pay_school_money/100+'元</div><div class="col-xs-5" style="padding:0;">'+formatDate(payTime)+'</div><div class="col-xs-2 xq" style="padding:0;text-decoration:underline;"data-id="'+pay.remark+'"></div>';
							}							
        	               
        	                table.appendChild(sec);
                          mui('body').on('tap', '.xq', function () { 
	                      $(".move").css("display","block");
	                           $(".move>div").html($(this).data("id"));
	                      });
						  mui('body').on('tap', '.move', function () { 
	                      $(".move").css("display","none");
	
	                      });
        				}
        	   		}	
        	    );
  
        }, 10);
    }

    if (mui.os.plus) {
        mui.plusReady(function () {
            setTimeout(function () {
                mui('#pullrefresh').pullRefresh().pullupLoading();
            }, 10);

        });
    } else {
        mui.ready(function () {
            mui('#pullrefresh').pullRefresh().pullupLoading();
        });
    }
    



</script>

 <script type="text/javascript" src="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/js/pay.js"></script>
</body>
</html>
