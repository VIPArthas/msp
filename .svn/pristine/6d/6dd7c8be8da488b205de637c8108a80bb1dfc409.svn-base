<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/zepto.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/validation_a.js"></script>
<script></script>
<script>
var userRechargeId = '${userRechargeId}';
var resultUrl = '${resultUrl}';
function onBridgeReady(){
   WeixinJSBridge.invoke(
       'getBrandWCPayRequest', {
           "appId":'${jsApiReqData.appId}',     //公众号名称，由商户传入     
           "timeStamp":"${jsApiReqData.timeStamp}",         //时间戳，自1970年以来的秒数     
           "nonceStr" : "${jsApiReqData.nonceStr}", //随机串     
           "package" :"${jsApiReqData.packageStr}",     
           "signType" : "${jsApiReqData.signType}",         //微信签名方式：     
           "paySign" : "${jsApiReqData.paySign}" //微信签名 
       },
       function(res){
    	   // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
    	   //get_brand_wcpay_request：cancel或者get_brand_wcpay_request：fail可以统一处理为用户遇到错误或者主动放弃，不必细化区分。
           if (undefined == resultUrl || null == resultUrl || "" == resultUrl) {
               window.location.href = "<%=request.getContextPath()%>/common/userRecharge/wx/rechargeSuccess.htm?errMsg=ok&userRechargeId=${userRechargeId}";
           }else {
               window.location.href = "<%=request.getContextPath()%>"+resultUrl+"&errMsg=ok&userRechargeId=${userRechargeId}";
           }
    	}
    ); 
}
if (typeof WeixinJSBridge == "undefined"){
   if( document.addEventListener ){
       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
   }else if (document.attachEvent){
       document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
   }
}else{
   onBridgeReady();
}
</script>