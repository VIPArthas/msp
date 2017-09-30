package com.wh.controller.xlwapp;

import com.wh.controller.common.BaseController;
import com.wh.entity.SmsVerify;
import com.wh.service.xlwapp.SmsService;
import com.wh.util.MessageCode;
import com.wh.util.StringUtil;
import com.wh.util.WebUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2016-11-3.
 */
@Controller
@RequestMapping("/common/sendMsg")
public class SendMsgController extends BaseController {

    @Autowired
    private SmsService smsService;

    @RequestMapping("/andorid/sendNotic.htm")
    public void sendNotic(String phone, String content, HttpServletResponse response, HttpServletRequest request) {
        JSONObject object = new JSONObject();
        try {
            if (StringUtils.isEmpty(phone)) {
                object.put("code", -1);
                object.put("msg", "手机号不能为空(phone)");
                WebUtil.write(response, object.toString());
                return;
            }else if (!StringUtil.isMobile(phone)) {
                object.put("code", -1);
                object.put("msg", "手机号格式不正确(phone)");
                WebUtil.write(response, object.toString());
                return;
            }

            if (StringUtils.isEmpty(content)) {
                object.put("code", -1);
                object.put("msg", "内容不能为空(content)");
                WebUtil.write(response, object.toString());
                return;
            }
            String ip = WebUtil.getIp(request);
            SmsVerify smsVerify = new SmsVerify("", phone, content, "", null, ip, null);
            smsVerify.setOperateIp(ip);
            smsVerify = new MessageCode().sendTemplaMsg(smsVerify);
            if(smsVerify != null){//发送成功
                smsService.saveSms(smsVerify);
                object.put("code", 0);
                object.put("msg", smsVerify.getReturnStatus());
                WebUtil.write(response, object.toString());
            }

        }catch (Exception e) {
            log.error("异常日志：", e);
        }
    }
}
