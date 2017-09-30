/**
 * Created by xxh on 2016-5-4.
 */
/* =========================================================
 *
 * check-type=
 *   required 不能为空，并在后面自动加*号
 *   url  表示 输入网址
 *   date 日期格式 xxxx-xx-xx
 *   mail 邮箱
 *   number 数字，可以整型，浮点型。
 *   char
 *   chinese 中文
 * mail-message="扩展提示内容" ， 可以扩展data-message,url-message
 * minlength="6" 表示长度大于等于6
 * range="2.1~3"   表示值在[2.1~3]之间，并check-type="number"
 * range="2.1,2,4,5"   表示值在只能填现数字，并check-type="number"
 *
 *
 * 例如:
 * $("form").validation(function(obj,params){
 *     if (obj.id=='mail'){
 *       $.post("/verifymail",{mail :$(obj).val()},function(data){
 *         params.err = !data.success;
 *         params.msg = data.msg;
 *       });
 *     }},
 *     {reqmark:false}
 *   );
 *
 *
 *  编号   版本号      作者     修改日期        修改内容
 *   1      1.0         xxh     16-5-4          增加对toast的支持，去掉对bootstrap3支持的冗余的代码
 * ========================================================= */

function alert_x(msg){
    if(!!!msg){
        return;
    }else{
        if($("body").find("#toast").length==0){
            $("body").append('<div id="toast" style="display: none;"><div class="weui_mask_transparent"></div><div class="weui_toast_x"><span class="weui_toast_content_x">提示</span></div></div>');
        }
        $('#toast').find(".weui_toast_content_x").html(msg);
            $('#toast').show();
            setTimeout(function () {
                $('#toast').hide();
            }, 2000);
    }
}