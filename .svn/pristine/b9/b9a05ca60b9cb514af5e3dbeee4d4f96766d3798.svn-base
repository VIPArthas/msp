package com.wh.controller.wmh;

import com.taobao.api.internal.util.StringUtils;
import com.wh.controller.common.BaseController;
import com.wh.entity.SmsVerify;
import com.wh.entity.TbWchatuser;
import com.wh.entity.WmhMessage;
import com.wh.service.rgpp.WchatuserPlatService;
import com.wh.service.wmh.WmhMessageService;
import com.wh.service.xlwapp.SmsService;
import com.wh.util.*;
import org.apache.poi.util.SystemOutLogger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author lp
 * @Description: ${todo}(推送消息)
 * @date 2017/3/6 13:50
 * @return ${return_type}    返回类型
 */
@Controller
@RequestMapping("/wmh/pushMessage")
public class PushMessageController extends BaseController {
    @Autowired
    WmhMessageService wmhMessageService;

    /**
     * @return ${return_type}    返回类型
     * @Description: ${todo}(推送消息调用的方法)
     * @author lp
     * @date 2017年3月7日10:11:41
     */
    @RequestMapping("/web/pushMessage.htm")
    public void pushMessage(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            //测试用
            WmhMessage wmhMessage = new WmhMessage();
//            wmhMessage.setTemplateType(1);
//            wmhMessage.setToUser("4b66b7cb02fa11e79928000c297c52e2");
//            wmhMessage.setWxSend(1);
//            wmhMessage.setMailSend(0);
//            wmhMessage.setSmsSend(0);
//            wmhMessage.setParm2("讨论微门户需求");
//            wmhMessage.setParm3("周3下午");
//            wmhMessage.setParm4("公司会议室");
//            wmhMessage.setParm5("需求");
            JSONObject jsonObject=new JSONObject();
            String toUser;
            Integer templateType;
            String parm1;
            String parm2;
            String parm3;
            String parm4;
            String parm5;
            String parm6;
            Integer wxSend;
            Integer smsSend;
            Integer mailSend;
            String t = httpServletRequest.getParameter("templateType");
            templateType = Integer.parseInt(t);
            toUser = httpServletRequest.getParameter("toUser");
            //获取添加的用户名
            String userNameArray = httpServletRequest.getParameter("userNameArray");
            //System.out.println(userNameArray);
      
            String w = httpServletRequest.getParameter("wxSend");
            String s = httpServletRequest.getParameter("smsSend");
            String m = httpServletRequest.getParameter("mailSend");
            parm1 = httpServletRequest.getParameter("parm1");
            parm2 = httpServletRequest.getParameter("parm2");
            parm3 = httpServletRequest.getParameter("parm3");
            parm4 = httpServletRequest.getParameter("parm4");
            parm5 = httpServletRequest.getParameter("parm5");
            parm6 = httpServletRequest.getParameter("parm6");
            if (null == w) {
                wxSend = 0;
            } else {
                wxSend = Integer.parseInt(w);
            }
            if (null == s) {
                smsSend = 0;
            } else {
                smsSend = Integer.parseInt(s);
            }
            if (null == m) {
                mailSend = 0;
            } else {
                mailSend = Integer.parseInt(m);
            }

            wmhMessage.setId(UUIDUtil.getUUID());
            wmhMessage.setTemplateType(templateType);
            wmhMessage.setToUser(toUser);
            wmhMessage.setWxSend(wxSend);
            wmhMessage.setMailSend(mailSend);
            wmhMessage.setSmsSend(smsSend);
            wmhMessage.setParm1(parm1);
            wmhMessage.setParm2(parm2);
            wmhMessage.setParm3(parm3);
            wmhMessage.setParm4(parm4);
            wmhMessage.setParm5(parm5);
            wmhMessage.setParm6(parm6);
            if (StringUtil.isNotEmpty(userNameArray)) {
            	 wmhMessage.setUserNameArray(userNameArray);
			}
           Integer number= wmhMessageService.pushMessageMethod(httpServletRequest, wmhMessage);
            if(templateType==1){ //会议通知
                jsonObject.put("number",number);
                jsonObject.put("type",1);
            }else if(templateType==2){//工资发放
            	jsonObject.put("number",number);
                jsonObject.put("type",2);
            }else if(templateType==3){
            	jsonObject.put("number",number);
            	jsonObject.put("type",3);//周师修改日志通知
            }
            WebUtil.write(httpServletResponse,jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
