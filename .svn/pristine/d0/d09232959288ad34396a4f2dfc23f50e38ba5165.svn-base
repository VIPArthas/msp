package com.wh.controller.wchat;

import com.wh.entity.TbLog;
import com.wh.entity.TbWchatuser;
import com.wh.entity.WxChatEntity;
import com.wh.message.custom.Customservice;
import com.wh.message.custom.Text;
import com.wh.message.resp.Article;
import com.wh.message.resp.NewsMessage;
import com.wh.message.resp.TextMessage;
import com.wh.service.rgpp.LogService;
import com.wh.service.rgpp.WchatuserPlatService;
import com.wh.service.wchat.ReplyMessageService;
import com.wh.util.*;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 供微信服务器调用相关接口
 * Created by danding on 2015/8/13.
 */
@Controller()
@RequestMapping(value = "/wchatService")
public class WchatController {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private WchatuserPlatService wchatuserPlatService;
    @Autowired
    private ReplyMessageService replyMessageService;
    @Autowired
    private LogService logService;
    private String userOpenId = "";//客户的OpenId
    private boolean jieDai = false;
    private List<WxChatEntity> listData = new ArrayList<>();//保存需要服务的人员信息，和他们发的消息
    private Timer timer;//3分钟的计时器
    //正式服务器
//    private String fwOpenId="okLTXt5NYfjvb26m1baZdeKfn6kU";//服务人员的OpenId  郭强
//    private String defaultKf="kf2006@jhkj_xlw";//设定的默认的平台客服，负责转发消息

    private String jzxxKf="kf2002@gh_33c835383975";//全网兼职优惠信息客服
    //测试服务器
//    private String fwOpenId = "of9gswMhy-T9R0N4f3Y49CAMHlew";//服务人员的OpenId
//    private String defaultKf = "kf2001@gh_33c835383975";//设定的默认的平台客服，负责转发消息
    private boolean huiFued = false;

    @RequestMapping(value = "/wx/wchatInterface.htm", method = RequestMethod.GET)
    public void wchatInterfaceGet(HttpServletRequest req, HttpServletResponse resp, String signature, String timestamp, String nonce, String echostr) throws IOException {
        //signature 微信加密签名
        //timestamp 时间戳
        //noce 随机数
        //echostr 随机字符串
        if ("".equals(signature) || "".equals(timestamp) || "".equals(nonce)) {
            log.error("验证signature请求参数出错");
            return;
        }
        log.info("正在进行验证signature...");
        PrintWriter out = resp.getWriter();
        //通过验证signature对请求进行校验，若校验成功则原样返回echostr,表示成功接入，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
            log.info("验证signature成功");
        } else {
            log.info("验证signature失败");
        }
        out.close();
        out = null;
    }

    @RequestMapping(value = "/wx/wchatInterface.htm", method = RequestMethod.POST)
    public void wchatInterfacePost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //设置编码格式，防止出现乱码
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String jmStr = processRequest(req);//调用核心服务类，执行相应逻辑
        PrintWriter out = resp.getWriter();
        //向微信服务器返回执行结果
        out.print(jmStr);
        out.close();
        out = null;
    }

    /**
     * 处理微信发来的请求
     *
     * @param request
     * @return
     */
    private String processRequest(HttpServletRequest request) {
        String responseMessage = "";
        try {
            //默认返回的文本消息内容
            String respContent = "请求处理异常，请稍候尝试!";
            //xml请求解析
            Map<String, String> requestMap = null;
            requestMap = MessageUtil.parseXml(request);
            //发送方账号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            //公众账号
            String toUserName = requestMap.get("ToUserName");
            //消息类型
            String msgType = requestMap.get("MsgType");
            log.info("接收到用户{}发来的消息，消息类型为{}", fromUserName, msgType);
            log.info("接收到的完整信息：{}", requestMap);
            //事件推送
            if (MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgType)) {
                //事件类型
                String eventType = requestMap.get("Event");

                //订阅
                if (MessageUtil.EVENT_TYPE_SUBSCRIBE.equals(eventType)) {
                    String eventKey = requestMap.get("EventKey");
                    String ticket = requestMap.get("Ticket");
                    updateWchatUserInfo(fromUserName, eventKey, toUserName);//重新关注后更新用户资料
//                    responseMessage = MessageUtil.textMessageToXml(MessageTemplate.getWelcomeInfo(toUserName, fromUserName));
                    responseMessage = this.replyMessageService.getWelcomeInfo(fromUserName, toUserName);
                    TbLog tbLog = new TbLog();
                    tbLog.setId(UUIDUtil.getUUID());
                    tbLog.setUserId(fromUserName);
                    tbLog.setDotype("subscribe");
                    tbLog.setDocontent("关注");
                    tbLog.setProject(1);
                    if (StringUtils.isEmpty(eventKey)) {
                        tbLog.setSource("0");
                    }else {
                        tbLog.setSource(eventKey);
                    }
                    tbLog.setTicket(ticket);
                    this.logService.insertSelectiveLog(tbLog);
                    return responseMessage;
                }
                //取消订阅
                else if (MessageUtil.EVENT_TYPE_UNSUBSCRIBE.equals(eventType)) {
                    TbLog tbLog = new TbLog();
                    tbLog.setId(UUIDUtil.getUUID());
                    tbLog.setUserId(fromUserName);
                    tbLog.setDotype("unsubscribe");
                    tbLog.setDocontent("取消关注");
                    tbLog.setProject(1);
                    this.logService.insertSelectiveLog(tbLog);
                    TbWchatuser wchatuser = wchatuserPlatService.findWchatUserByOpenId(fromUserName);
                    if (null != wchatuser) {
                        wchatuser.setStatus(0);
                        this.wchatuserPlatService.update(wchatuser);
                    }
                    return "";
                } else if (MessageUtil.EVENT_TYPE_CLICK.equals(eventType)) {
                    if ("welcome".equals(requestMap.get("EventKey"))) {
                        TextMessage textMessage = new TextMessage();
                        textMessage.setToUserName(fromUserName);
                        textMessage.setFromUserName(toUserName);
                        textMessage.setMsgType(MessageUtil.RESQ_MESSAGE_TYPE_TEXT);
                        textMessage.setContent("欢迎来到\"职业测评公众平台\"，我们将致力于为你提供最优质的服务！\n\n系统正在努力为你加载属于你的菜单......\n\n稍等片刻,精彩立马展现！");
                        responseMessage = MessageUtil.textMessageToXml(textMessage);
                        return responseMessage;
                    }
                }
                //位置信息
                else if (MessageUtil.EVENT_TYPE_LOCATION.equals(eventType)) {
                    TbLog log = new TbLog();
                    log.setId(UUIDUtil.getUUID());
                    log.setUserId(fromUserName);
                    log.setDotype("location");
                    log.setDocontent(requestMap.get("Longitude") + "," + requestMap.get("Latitude"));
                    log.setProject(1);
                    //TODO 第一次记录位置信息(判断标准tb_log中userId=fromUserName and Dotype=location，如果list.getSize()==0)时
                    //TODO 获取位置 String result = AddressUtil.getAddressByXY(lng, lat); 具体参看WchatExtendsController.wchatUserAddress
                    //TODO 将位置信息记录到tb_wchatuser.openId=fromUserName -- update(address_province,address_city,address_area,address_detail,address_lng,address_lat)
                    TbLog tbLog = new TbLog();
                    tbLog.setUserId(fromUserName);
                    List<TbLog> log2 = this.logService.queryTbLogByParam(tbLog);
                    if (log2.size() == 0) {
                        String result = AddressUtil.getAddressByXY(Double.valueOf(requestMap.get("Longitude")), Double.valueOf(requestMap.get("Latitude")));
                        JSONObject jsStr = JSONObject.fromObject(result);
                        Integer status = (Integer) jsStr.get("status");
                        if (status == 0) {
                            String addressDetail = jsStr.getJSONObject("result").getString("formatted_address");
                            String addressProvince = jsStr.getJSONObject("result").getJSONObject("addressComponent").getString("province");
                            String addressCity = jsStr.getJSONObject("result").getJSONObject("addressComponent").getString("city");
                            String addressDistrict = jsStr.getJSONObject("result").getJSONObject("addressComponent").getString("district");

                            //根据openId=fromUserName去wChatUser表中查询数据，放入位置信息
                            TbWchatuser tbWchatuser = this.wchatuserPlatService.findWchatUserByOpenId(fromUserName);
                            if (null != tbWchatuser) {
                                tbWchatuser.setAddressDetail(addressDetail);
                                tbWchatuser.setAddressProvince(addressProvince);
                                tbWchatuser.setAddressCity(addressCity);
                                tbWchatuser.setAddressArea(addressDistrict);
                                tbWchatuser.setAddressLng(Double.valueOf(requestMap.get("Longitude")));
                                tbWchatuser.setAddressLat(Double.valueOf(requestMap.get("Latitude")));
                                wchatuserPlatService.update(tbWchatuser);
                            }
                        }
                    }
                    this.logService.insertSelectiveLog(log);
                }//菜单点击事件
                else if (MessageUtil.EVENT_TYPE_VIEW.equals(eventType)) {
                    TbLog log = new TbLog();
                    log.setId(UUIDUtil.getUUID());
                    log.setUserId(fromUserName);
                    log.setDotype("VIEW");
                    log.setDocontent(requestMap.get("EventKey"));
                    log.setProject(0);
                    this.logService.insertSelectiveLog(log);
                }
            } else if (MessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(msgType)) {
//              if (isAllowAutoReplyAccout(toUserName)) {
//              responseMessage = this.replyMessageService.getAutoReplyMessageByCode(requestMap.get("Content"), fromUserName, toUserName);
//          }
//                List<Map> servicesList= (List<Map>) request.getServletContext().getAttribute("constom");
//                for(int i=0;i<servicesList.size();i++){
//                    Map map=servicesList.get(i);
//                    if((Integer)map.get("status")==1){
//                        fwOpenId= (String)map.get("openId");
//                        break;
//                    }
//                }
                Constants.FW_OPEN_Id = getConstom(request);
                if (requestMap.get("FromUserName").equals(Constants.FW_OPEN_Id)) {//服务人员对当前客户的问题进行回复
                    com.wh.message.custom.TextMessage textMessage = new com.wh.message.custom.TextMessage();
                    if (listData.size() > 0) {
                        textMessage.setTouser(listData.get(0).getFromUserName());
                    }
                    textMessage.setMsgtype(MessageUtil.RESQ_MESSAGE_TYPE_TEXT);
                    Customservice service = new Customservice();
                    service.setKf_account(Constants.DEFAULT_Kf);
                    textMessage.setCustomservice(service);
                    Text t = new Text();
                    String msg = requestMap.get("Content");
                    if (msg.equals("007")) {
                        msg = "本次服务结束,祝您生活愉快！^_^";
                        userOpenId = "";
                        if (listData.size() > 0) {
                            t.setContent(msg);
                            textMessage.setText(t);
                            WeiXinPlatformUtil.sendCustomMsg(textMessage, "1");
                            listData.remove(0);
                            jieDai = false;
                        }
                        huiFued = false;
                        if (listData.size() > 0 && jieDai == false) { //结束当前会话，提示排队的人可以进行服务了
                            com.wh.message.custom.TextMessage textMessage2 = new com.wh.message.custom.TextMessage();
                            textMessage2.setTouser(listData.get(0).getFromUserName());
                            textMessage2.setMsgtype(MessageUtil.RESQ_MESSAGE_TYPE_TEXT);
                            service = new Customservice();
                            service.setKf_account(Constants.DEFAULT_Kf);
                            textMessage2.setCustomservice(service);
                            t = new Text();
                            msg = "您好，有什么小美能帮到您的吗？ ^_^ ";
                            t.setContent(msg);
                            textMessage2.setText(t);
                            WeiXinPlatformUtil.sendCustomMsg(textMessage2, "1");
                            huiFued = true;
                            jieDai = true;
                            if (!StringUtils.isEmpty(listData.get(0).getContent())) {
                                com.wh.message.custom.TextMessage textMessage3 = new com.wh.message.custom.TextMessage();
                                textMessage3.setTouser(Constants.FW_OPEN_Id);
                                textMessage3.setMsgtype(MessageUtil.RESQ_MESSAGE_TYPE_TEXT);
                                service = new Customservice();
                                service.setKf_account(Constants.DEFAULT_Kf);
                                textMessage3.setCustomservice(service);
                                t = new Text();
                                t.setContent(listData.get(0).getContent());
                                textMessage3.setText(t);
                                WeiXinPlatformUtil.sendCustomMsg(textMessage3, "1");
                            }
                        }
                    } else {
                        t.setContent(msg);
                        textMessage.setText(t);
                        WeiXinPlatformUtil.sendCustomMsg(textMessage, "1");
                    }

                } else { //接收客户的信息
                    String id = requestMap.get("FromUserName");
                    if (StringUtils.isEmpty(id)) {
                        return null;
                    }
                    if(!StringUtils.isEmpty(requestMap.get("Content"))){
                        if(requestMap.get("Content").equals("打字员")){
                            com.wh.message.custom.TextMessage textMessage3 = new com.wh.message.custom.TextMessage();
                            textMessage3.setTouser(id);
                            textMessage3.setMsgtype(MessageUtil.RESQ_MESSAGE_TYPE_TEXT);
                            Customservice service = new Customservice();
                            service.setKf_account(Constants.DEFAULT_Kf);
                            textMessage3.setCustomservice(service);
                            Text t = new Text();
                            t.setContent("打字员的岗位其实是网上欺骗的常用手段,建议您谨慎选择......");
                            textMessage3.setText(t);
                            WeiXinPlatformUtil.sendCustomMsg(textMessage3, "1");
                            return responseMessage;
                        }
                    }
                    //该客户是否在服务队列里
                    boolean isExist = false;
                    if (listData.size() > 0) {
                        for (int i = 0; i < listData.size(); i++) {
                            WxChatEntity e = listData.get(i);
                            if (e.getFromUserName().equals(id)) { //说明已经添加到服务队列里了,把他发的消息存起来
                                String m = e.getContent();
                                m = m + "  " + requestMap.get("Content");
                                e.setContent(m);
                                isExist = true;
                                break;
                            }
                        }
                        //没有的话添加到服务队列里
                        if (!isExist) {
                            WxChatEntity entity = new WxChatEntity();
                            entity.setMsgType("text");
                            entity.setFromUserName(id);
                            entity.setToUserName(Constants.FW_OPEN_Id);
                            entity.setContent(requestMap.get("Content"));
                            entity.setCreateTime(Integer.parseInt(requestMap.get("CreateTime")));
                            entity.setMsgId(requestMap.get("MsgId"));
                            listData.add(entity);
                            //如果有新的客户进来 给客服发个消息
                            com.wh.message.custom.TextMessage textMessage = new com.wh.message.custom.TextMessage();
                            textMessage.setTouser(Constants.FW_OPEN_Id);
                            textMessage.setMsgtype(MessageUtil.RESQ_MESSAGE_TYPE_TEXT);
                            Customservice service = new Customservice();
                            service.setKf_account(Constants.DEFAULT_Kf);
                            textMessage.setCustomservice(service);
                            Text t = new Text();
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
                            Date curDate = new Date(System.currentTimeMillis());
                            String str = formatter.format(curDate);
                            String msg = "“" + str + "有1位新用户加入队列，当前队列已经有" + (listData.size() - 1) + "人在等候。”";
                            t.setContent(msg);
                            textMessage.setText(t);
                            WeiXinPlatformUtil.sendCustomMsg(textMessage, "1");
                        }
                    } else {
                        WxChatEntity entity = new WxChatEntity();
                        entity.setMsgType("text");
                        entity.setFromUserName(id);
                        entity.setToUserName(Constants.FW_OPEN_Id);
                        entity.setContent(requestMap.get("Content"));
                        entity.setCreateTime(Integer.parseInt(requestMap.get("CreateTime")));
                        entity.setMsgId(requestMap.get("MsgId"));
                        listData.add(entity);
                    }
                    if (getPosition(id) == 0) { //当前服务人员没有接待客户或者正在接待，可以进行服务
                        userOpenId = requestMap.get("FromUserName");
                        com.wh.message.custom.TextMessage textMessage = new com.wh.message.custom.TextMessage();
                        textMessage.setTouser(Constants.FW_OPEN_Id);
                        textMessage.setMsgtype(MessageUtil.RESQ_MESSAGE_TYPE_TEXT);
                        Customservice service = new Customservice();
                        service.setKf_account(Constants.DEFAULT_Kf);
                        textMessage.setCustomservice(service);
                        Text t = new Text();
                        if (!jieDai) { //发送之前等待的时候客户说的话
                            t.setContent(listData.get(0).getContent());
                        } else {
                            t.setContent(requestMap.get("Content"));
                        }
                        textMessage.setText(t);
                        WeiXinPlatformUtil.sendCustomMsg(textMessage, "1");
                        if (!jieDai && huiFued == false) {//和客户第一次会话并且没问候过，自动回复问候语
                            jieDai = true;
                            textMessage = new com.wh.message.custom.TextMessage();
                            textMessage.setTouser(userOpenId);
                            textMessage.setMsgtype(MessageUtil.RESQ_MESSAGE_TYPE_TEXT);
                            service = new Customservice();
                            service.setKf_account(Constants.DEFAULT_Kf);
                            textMessage.setCustomservice(service);
                            t = new Text();
                            String msg = "您好，有什么小美能帮到您的吗？ ^_^ ";
                            t.setContent(msg);
                            textMessage.setText(t);
                            WeiXinPlatformUtil.sendCustomMsg(textMessage, "1");
                        }
                        //客户说话了 开启计时器
                        if (timer == null) {
                            timer = new Timer();
                        } else { //重新开启新的计时器
                            timer.cancel();
                            timer = null;
                            timer = new Timer();
                        }
                        MyTimerTask myTimerTask = new MyTimerTask();
                        timer.schedule(myTimerTask, 180000, 180000);  //3分钟之后执行关闭当前会话

                    } else { //后面的客户
                        //有排队人员给客户发消息
                        com.wh.message.custom.TextMessage textMessage = new com.wh.message.custom.TextMessage();
                        textMessage.setTouser(requestMap.get("FromUserName"));
                        textMessage.setMsgtype(MessageUtil.RESQ_MESSAGE_TYPE_TEXT);
                        Customservice service = new Customservice();
                        service.setKf_account(Constants.DEFAULT_Kf);
                        textMessage.setCustomservice(service);
                        Text t = new Text();
                        int position = getPosition(id);
                        String msg = "客服在忙，您前面还有" + position + "位人员，请稍等...";
                        t.setContent(msg);
                        textMessage.setText(t);
                        WeiXinPlatformUtil.sendCustomMsg(textMessage, "1");
                    }
                }
            }
        } catch (Exception e) {
            log.error("异常日志：", e);
        }
        return responseMessage;
    }

    //获取该用户在队列中的位置
    private int getPosition(String openId) {
        int position = -1;
        if (listData.size() > 0) {
            for (int i = 0; i < listData.size(); i++) {
                WxChatEntity e = listData.get(i);
                if (e.getFromUserName().equals(openId)) {
                    position = i;
                    break;
                }
            }
        }
        return position;
    }

    //计时器
    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            if (listData.size() > 1) {//说明有新的客户排队,断开当前会话
                com.wh.message.custom.TextMessage textMessage = new com.wh.message.custom.TextMessage();
                textMessage.setTouser(listData.get(0).getFromUserName());
                textMessage.setMsgtype(MessageUtil.RESQ_MESSAGE_TYPE_TEXT);
                Customservice service = new Customservice();
                service.setKf_account(Constants.DEFAULT_Kf);
                textMessage.setCustomservice(service);
                Text t = new Text();
                String msg = "抱歉，由于您长时间未回复，我将关闭会话，如有其他疑问，请再咨询，谢谢！祝您生活愉快！^_^";
                t.setContent(msg);
                textMessage.setText(t);
                WeiXinPlatformUtil.sendCustomMsg(textMessage, "1");
                listData.remove(0);
                //对新的接待人对话
                jieDai = true;
                textMessage = new com.wh.message.custom.TextMessage();
                textMessage.setTouser(listData.get(0).getFromUserName());
                textMessage.setMsgtype(MessageUtil.RESQ_MESSAGE_TYPE_TEXT);
                service = new Customservice();
                service.setKf_account(Constants.DEFAULT_Kf);
                textMessage.setCustomservice(service);
                t = new Text();
                msg = "您好，有什么小美能帮到您的吗？ ^_^ ";
                t.setContent(msg);
                textMessage.setText(t);
                WeiXinPlatformUtil.sendCustomMsg(textMessage, "1");

                huiFued = true;
                jieDai = true;
                if (!StringUtils.isEmpty(listData.get(0).getContent())) {
                    com.wh.message.custom.TextMessage textMessage3 = new com.wh.message.custom.TextMessage();
                    textMessage3.setTouser(Constants.FW_OPEN_Id);
                    textMessage3.setMsgtype(MessageUtil.RESQ_MESSAGE_TYPE_TEXT);
                    service = new Customservice();
                    service.setKf_account(Constants.DEFAULT_Kf);
                    textMessage3.setCustomservice(service);
                    t = new Text();
                    t.setContent(listData.get(0).getContent());
                    textMessage3.setText(t);
                    WeiXinPlatformUtil.sendCustomMsg(textMessage3, "1");
                }
            } else {
                timer.cancel();   //没有人排队，重新开始这个计时器
                timer = null;
                timer = new Timer();
                MyTimerTask myTimerTask = new MyTimerTask();
                timer.schedule(myTimerTask, 10000, 10000); //这时开启个5秒钟的计时器，时刻检测是否有新客户来
            }
        }

    }

    /**
     * 保存从微信获取的用户信息
     *
     * @param openId   用户Id
     * @param eventKey 用户身份（场景Id,1为学生，2为企业）
     */
    private void updateWchatUserInfo(String openId, String eventKey, String toUserName) {
        TbWchatuser wchatuser = wchatuserPlatService.findWchatUserByOpenId(openId);
        if (wchatuser == null) {
            wchatuser = new TbWchatuser();
            if ("".equals(eventKey)) {
                wchatuser.setSource("0");
            } else {
                wchatuser.setSource(eventKey);
            }
            wchatuser.setOpenid(openId);

            wchatuser.setStatus(1);
//            wchatuserPlatService.add(wchatuser, toUserName);
            wchatuserPlatService.saveOrUpdate(wchatuser,toUserName);
        } else {
            TbWchatuser tu = new TbWchatuser();
            tu.setId(wchatuser.getId());
            tu.setStatus(1);
            wchatuserPlatService.update(tu);
        }
    }

    /**
     * 判断指定微信账号是否支持自动回复功能
     *
     * @param accout
     * @return
     */
    private boolean isAllowAutoReplyAccout(String accout) {
        String[] accouts = com.wh.constants.Constants.AUTOREPLY_APPCOUNTS.split("|");
        for (String acc : accouts) {
            if (acc.equals(accout)) {
                return true;
            }
        }
        return false;
    }

    private String getConstom(HttpServletRequest request) {
        String constom = (String) request.getServletContext().getAttribute("constom");
        if (StringUtils.isEmpty(constom)) {
            List<Map> list = new ArrayList<>();
            Map map = new HashMap();
            map.put("openId", "okLTXt-IVwbko8Us3piN3Y78Wt3g");
            map.put("name", "陈昊东");
            map.put("status", 1);
            list.add(map);

            map = new HashMap();
            map.put("openId", "okLTXt5NYfjvb26m1baZdeKfn6kU");
            map.put("name", "郭强");
            map.put("status", 0);
            list.add(map);

            request.getServletContext().setAttribute("constoms", list);
            constom = "okLTXt5NYfjvb26m1baZdeKfn6kU";
            request.getServletContext().setAttribute("constom", constom);
        }
        return constom;
    }





    /**
     * 优惠活动 默认客服通过微信公众号给商家发送消息
     * @param toOpenId
     * @param msg
     * @autor lyk
     * @date 2016年10月11日
     */
    public void sendMsg(String toOpenId, String msg) {
        try{

            com.wh.message.custom.TextMessage textMessage = new com.wh.message.custom.TextMessage();
            com.wh.message.custom.Text t = new com.wh.message.custom.Text();
            Customservice customservice = new Customservice();
            customservice.setKf_account(jzxxKf);
            textMessage.setTouser(toOpenId);
            textMessage.setMsgtype(MessageUtil.RESQ_MESSAGE_TYPE_TEXT);
            t.setContent(msg);
            textMessage.setText(t);
            WeiXinPlatformUtil.sendCustomMsg(textMessage, "1");

        }catch (Exception e) {
            log.error("异常日志：", e);
        }

    }
}
