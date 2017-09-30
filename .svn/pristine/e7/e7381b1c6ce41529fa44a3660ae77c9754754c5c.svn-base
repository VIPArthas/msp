package com.wh.controller.xlwapp;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayOpenPublicTemplateMessageIndustryModifyRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayOpenPublicTemplateMessageIndustryModifyResponse;
import com.wh.constants.*;
import com.wh.constants.Constants;
import com.wh.dao.xlwapp.UserPayMapper;
import com.wh.entity.*;
import com.wh.service.xlwapp.UserPayService;
import com.wh.util.*;
import com.wh.util.alipay.AlipayNotify;
import com.wh.util.wxpay.*;
import com.wh.util.wxpay.protocol.unifiedOrderProtocol.OrderQueryReq;
import com.wh.util.wxpay.protocol.unifiedOrderProtocol.OrderQueryRes;
import org.apache.poi.util.SystemOutLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import com.wh.entity.RechargeParam;
import com.wh.framework.MethodLog;
import com.wh.service.xlwapp.UserRechargeService;
import com.wh.service.xlwapp.UserService;
import com.wh.util.alipay.AlipaySubmit;
import com.wh.util.alipay.config.AlipayConfig;
import com.wh.util.wxpay.protocol.JSAPIProtocol.JSAPIReqData;
import com.wh.util.wxpay.protocol.JSAPIProtocol.JSAPIResData;
import com.wh.util.wxpay.protocol.unifiedOrderProtocol.UnifiedOrderReqData;
import com.wh.util.wxpay.protocol.unifiedOrderProtocol.UnifiedOrderResData;

import net.sf.json.JSONObject;

/**
 * 用户充值controller
 *
 * @author 徐优优
 * @date 2016年4月25日
 */
@Controller
@RequestMapping("/common/userRecharge")
public class UserRechargeController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private UserRechargeService userRechargeService;
    @Autowired
    private UserPayService userPayService;
    /**
     * 充值选择充值平台
     *
     * @param modelMap
     * @param request
     * @param response
     * @param rechargeParam
     * @return String
     * @author 徐优优
     * @date 2016年4月28日
     */
    @RequestMapping("/wx/choiseRecharge.htm")
    @MethodLog(logRemark = "选择充值平台", logTag = "选择充值平台", logKey = "校联网平台前台财富系统", logType = 1)
    public String viewRechargeChoise(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, RechargeParam rechargeParam) {
        HttpSession session = request.getSession();
        session.setAttribute("rechargeParam", rechargeParam);//充值成功之后根据参数判断要跳转到哪个页面
        //用户当前校币余额
        Double schoolMoney = 0.00;
        String userId = WebUtil.getFrontUserId(request);
        User user = userService.selectUserById(userId);
        if (null != user) {
            schoolMoney = user.getSchoolMoney();
        }


        modelMap.put("schoolMoney", schoolMoney);
        modelMap.put("pageTitle", "选择充值平台");
        return "/xlwapp/chongzhi_choise";
    }

    /**
     * 充值页面
     *
     * @param modelMap
     * @param request
     * @param response
     * @return
     * @author 徐优优
     * @date 2016年4月25日
     */
    @RequestMapping("/wx/recharge.htm")
    @MethodLog(logRemark = "充值页面填写充值金额", logTag = "充值页面", logKey = "校联网平台前台财富系统", logType = 1)
    public String viewRecharge(Integer rechargeType, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
        //用户当前校币余额
        Double schoolMoney = 0.00;
        String userId = WebUtil.getFrontUserId(request);
        User user = userService.selectUserById(userId);
        if (null != user) {
            schoolMoney = user.getSchoolMoney();
        }
        modelMap.put("schoolMoney", schoolMoney);
        modelMap.put("rechargeId", UUIDUtil.getUUID());
        modelMap.put("rechargeType", rechargeType);
        modelMap.put("pageTitle", "充值");
        return "/xlwapp/chongzhi";
    }

    @RequestMapping("/wx/rechargeSuccess.htm")
    public String viewRechargeSuccess(String errMsg, String userRechargeId, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        if (!StringUtils.isEmpty(userRechargeId)) {
            UserRecharge userRecharge = userRechargeService.selectByPrimaryKey(userRechargeId);
            Double rechargeMoney = userRecharge.getRechargeMoney();
            modelMap.put("rechargeMoney", rechargeMoney * 100);
        } else {
            modelMap.put("rechargeMoney", 0);
        }
        String userId = WebUtil.getFrontUserId(request);
        User user = userService.selectUserById(userId);
        Double schoolMoney = 0.00;
        if (null != user && null != user.getSchoolMoney()) {
            schoolMoney = user.getSchoolMoney();
        }

        modelMap.put("schoolMoney", schoolMoney);
        modelMap.put("pageTitle", "支付结果页面");

        RechargeParam rechargeParam = (RechargeParam) session.getAttribute("rechargeParam");
        modelMap.put("rechargeParam", rechargeParam);
        if (!StringUtils.isEmpty(errMsg) && errMsg.equals("ok")) {
            return "/xlwapp/chongzhi-success";
        } else {
            return "/xlwapp/chongzhi-fail";
        }
    }


    @RequestMapping("/wx/viewSuccess.htm")
    public String viewSuccess(String errMsg, String userRechargeId, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        try {
            if (!StringUtils.isEmpty(userRechargeId)) {
                UserRecharge userRecharge = userRechargeService.selectByPrimaryKey(userRechargeId);
                Double rechargeMoney = userRecharge.getRechargeMoney();
                modelMap.put("rechargeMoney", rechargeMoney);
            } else {
                modelMap.put("rechargeMoney", 0);
            }
        } catch (Exception e) {
            log.error("异常：", e);
        }
        if (!StringUtils.isEmpty(errMsg) && errMsg.equals("ok")) {
            return "/xlwapp/chongzhi-success_test";
        } else {
            return "/xlwapp/chongzhi-fail";
        }
    }


    @RequestMapping("/wxc/unifiedOrderCB.htm")
    @MethodLog(logRemark = "微信支付支付结果通知是否支付成功及失败原因,并给微信发送回应", logTag = "微信支付支付结果通知回调方法", logKey = "校联网平台前台财富系统", logType = 4)
    public void unifiedOrderCallBack(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
        try {
            InputStream inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            outSteam.close();
            inStream.close();
            String resultStr = new String(outSteam.toByteArray(), "utf-8");
            JSAPIResData resData = (JSAPIResData) Util.getObjectFromXML(resultStr, JSAPIResData.class);

            //签名验证
            boolean signFlag = Signature.checkIsSignValidFromResponseString(resultStr);
            String xmlStr = "";
            if (signFlag) {
                if (resData.getReturn_code().equals("SUCCESS") && resData.getResult_code().equals("SUCCESS")) {
                    xmlStr = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
                } else {
                    xmlStr = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[" + resData.getReturn_msg() + "]]></return_msg></xml>";
                }
                log.info("微信支付支付结果通知是否支付成功:{}", resData.getReturn_msg());
                userRechargeService.updateJSAPIRechargeResult(resData);
                response.getWriter().write(xmlStr);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.error("异常日志：", e);
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            log.error("异常日志：", e);
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            log.error("异常日志：", e);
        }
    }

    @RequestMapping("/wxc/orderCallBack.htm")
    @MethodLog(logRemark = "微信支付支付结果通知是否支付成功及失败原因,并给微信发送回应", logTag = "微信支付支付结果通知回调方法", logKey = "校联网平台前台财富系统", logType = 4)
    public void orderCallBack(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
        try {
            InputStream inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            outSteam.close();
            inStream.close();
            String resultStr = new String(outSteam.toByteArray(), "utf-8");
            JSAPIResData resData = (JSAPIResData) Util.getObjectFromXML(resultStr, JSAPIResData.class);

            //签名验证
            boolean signFlag = Signature.checkIsSignValidFromResponseString(resultStr);
            String xmlStr = "";
            if (signFlag) {
                if (resData.getReturn_code().equals("SUCCESS") && resData.getResult_code().equals("SUCCESS")) {
                    xmlStr = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
                } else {
                    xmlStr = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[" + resData.getReturn_msg() + "]]></return_msg></xml>";
                }
                log.info("微信支付支付结果通知是否支付成功:{}", resData.getReturn_msg());
                userRechargeService.updateWxZhiFuResult(resData);
                response.getWriter().write(xmlStr);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.error("异常日志：", e);
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            log.error("异常日志：", e);
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            log.error("异常日志：", e);
        }
    }
    @RequestMapping("/wxc/unifiedOrderCBHJG.htm")
    @MethodLog(logRemark = "微信支付支付结果通知是否支付成功及失败原因,并给微信发送回应", logTag = "微信支付支付结果通知回调方法", logKey = "校联网平台前台财富系统", logType = 4)
    public void unifiedOrderHJGCallBack(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
        try {
            InputStream inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            outSteam.close();
            inStream.close();
            String resultStr = new String(outSteam.toByteArray(), "utf-8");
            JSAPIResData resData = (JSAPIResData) Util.getObjectFromXML(resultStr, JSAPIResData.class);

            //签名验证
            boolean signFlag = Signature.checkIsSignValidFromResponseString(resultStr);
            String xmlStr = "";
            if (signFlag) {
                if (resData.getReturn_code().equals("SUCCESS") && resData.getResult_code().equals("SUCCESS")) {
                    xmlStr = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
                } else {
                    xmlStr = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[" + resData.getReturn_msg() + "]]></return_msg></xml>";
                }
                log.info("微信支付支付结果通知是否支付成功:{}", resData.getReturn_msg());
                log.info("支付成功后，开始处理业务逻辑....");
                try {
                    userRechargeService.updateCashJSAPIRechargeResult(resData);
                } catch (Exception e) {
                    log.error("更新支付异常：", e);
                }
                // TODO: 2016-11-16
                //支付成功后，开始处理业务逻辑....
                response.getWriter().write(xmlStr);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.error("异常日志：", e);
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            log.error("异常日志：", e);
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            log.error("异常日志：", e);
        }
    }

    /**
     * 充值动作
     *
     * @param modelMap
     * @param request
     * @param response
     * @author 徐优优
     * @date 2016年4月25日
     */
    @RequestMapping("/wx/toRecharge.htm")
    @MethodLog(logRemark = "根据充值平台给对应的平台发送请求", logTag = "充值动作", logKey = "校联网平台前台财富系统", logType = 1)
    public ModelAndView toWxRecharge(Integer rechargeType, Double rechargeMoney, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
        String userId = WebUtil.getFrontUserId(request);
        String errormsg = "";
        JSONObject resObj = new JSONObject();
        String resultUrl = request.getParameter("resultUrl");
        String unifiedOrderCB = request.getParameter("unifiedOrderCB");
        String WXbody = "xiaolianwang";
        if (!StringUtils.isEmpty(unifiedOrderCB) && "HJGPAY".equals(unifiedOrderCB)) {
            unifiedOrderCB = "/common/userRecharge/wxc/unifiedOrderCBHJG.htm";
            WXbody = "寒假工报名费";
        } else {
            unifiedOrderCB = "/common/userRecharge/wxc/unifiedOrderCB.htm";
        }

        try {
            if (null != rechargeType && rechargeType == 1) {
                //商户订单号，商户网站订单系统中唯一订单号，必填
                String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"), "UTF-8");

                //订单名称，必填
                String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"), "UTF-8");

                //付款金额，必填
                String total_fee = new String(request.getParameter("WIDtotal_fee").getBytes("ISO-8859-1"), "UTF-8");

                //收银台页面上，商品展示的超链接，必填
                String show_url = new String(request.getParameter("WIDshow_url").getBytes("ISO-8859-1"), "UTF-8");

                //商品描述，可空
                String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"), "UTF-8");

                //////////////////////////////////////////////////////////////////////////////////

                //把请求参数打包成数组
                Map<String, String> sParaTemp = new HashMap<String, String>();
                sParaTemp.put("service", AlipayConfig.service);
                sParaTemp.put("partner", AlipayConfig.partner);
                sParaTemp.put("seller_id", AlipayConfig.seller_id);
                sParaTemp.put("_input_charset", AlipayConfig.input_charset);
                sParaTemp.put("payment_type", AlipayConfig.payment_type);
                sParaTemp.put("notify_url", AlipayConfig.notify_url);
                sParaTemp.put("return_url", AlipayConfig.return_url);
                sParaTemp.put("out_trade_no", out_trade_no);
                sParaTemp.put("subject", subject);
                sParaTemp.put("total_fee", total_fee);
                sParaTemp.put("show_url", show_url);
                sParaTemp.put("body", body);
                //其他业务参数根据在线开发文档，添加参数.文档地址:https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.2Z6TSk&treeId=60&articleId=103693&docType=1
                //如sParaTemp.put("参数名","参数值");

                //建立请求
                String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
                //out.println(sHtmlText);
                WebUtil.write(response, sHtmlText);
            } else if (null != rechargeType && rechargeType == 2) {
                try {
                    if (null == rechargeMoney) {
                        resObj.put("returnCode", -1);
                        resObj.put("errormsg", "充值金额不能为空");
                        errormsg = "充值金额不能为空";
                        return new ModelAndView("redirect:/common/userRecharge/wx/wxChongzhiFail.htm?errormsg=" + errormsg);
                    }
                    if (rechargeMoney < 0.01) {
                        resObj.put("returnCode", -1);
                        resObj.put("errormsg", "最少充值0.01元");
                        errormsg = "最少充值0.01元";
                        return new ModelAndView("redirect:/common/userRecharge/wx/wxChongzhiFail.htm?errormsg=" + errormsg);
                    }
                    String openId = (String) request.getSession().getAttribute("openId");
                    UserRecharge userRecharge = new UserRecharge();
                    HttpSession session = request.getSession();
                    userRecharge.setOpenId((String) session.getAttribute("openId"));
                    userRecharge.setId(UUIDUtil.getUUID());
                    userRecharge.setRechargeNum(new Long(new Date().getTime()).intValue());
                    userRecharge.setUserId(userId);
                    userRecharge.setRechargeType(rechargeType);
                    userRecharge.setRechargeMoney(rechargeMoney);
                    userRecharge.setRechargeTime(new Date());
                    userRecharge.setRechargeStatus(0);
                    Integer rechargeM = new Double(rechargeMoney * 100).intValue();

                    UnifiedOrderReqData unifiedOrderReqData = new UnifiedOrderReqData(WXbody, userRecharge.getId(), rechargeM, WebUtil.getIp(request), PropertiesUtil.getPropertie("WEB-INF/classes/config.properties", "base_server") + unifiedOrderCB, "JSAPI", openId);
                    UnifiedOrderResData resData = userRechargeService.saveRecharge(userRecharge, unifiedOrderReqData);
                    if (null != resData) {
                        String returnCode = resData.getReturn_code();
                        String resultCode = resData.getResult_code();
                        if ((!StringUtils.isEmpty(returnCode) && returnCode.equals("SUCCESS")) && (!StringUtils.isEmpty(resultCode) && resultCode.equals("SUCCESS"))) {
                            String prepayId = resData.getPrepay_id();
                            String signType = "MD5";
                            /*JSAPIReqData jsApiReqData = new JSAPIReqData(packageStr, signType);
                            modelMap.put("jsApiReqData", jsApiReqData);
							modelMap.put("userRechargeId", userRecharge.getId());*/
                            //return "/xlwapp/wxPayJSAPI";
                            return new ModelAndView("redirect:/common/userRecharge/wx/wxPayJSAPI.htm?resultUrl=" + resultUrl + "&prepayId=" + prepayId + "&signType=" + signType + "&userRechargeId=" + userRecharge.getId());
                            /*resObj.put("returnCode", 0);
                            resObj.put("prepayId", prepayId);
							resObj.put("signType", signType);
							resObj.put("userRechargeId", userRecharge.getId());*/

                        } else {
                            resObj.put("returnCode", -1);
                            String returnMsg = resData.getReturn_msg();
                            resObj.put("errormsg", returnMsg);
                            errormsg = returnMsg;
                        }
                    } else {
                        resObj.put("returnCode", -1);
                        resObj.put("errormsg", "处理失败，请联系管理员!");
                        errormsg = "处理失败，请联系管理员!";
                    }
                } catch (Exception e) {
                    resObj.put("returnCode", -1);
                    resObj.put("errormsg", "处理失败，请联系管理员!");
                    errormsg = "处理失败，请联系管理员!";
                    log.error("异常日志：", e);
                }
            }
            errormsg = URLEncoder.encode(errormsg, "utf-8");
        } catch (Exception e) {
            resObj.put("returnCode", -1);
            resObj.put("errormsg", "处理失败，请联系管理员!");
            errormsg = "处理失败，请联系管理员!";
            log.error("异常日志：", e);
        }

//		WebUtil.write(response, resObj.toString());

        return new ModelAndView("redirect:/common/userRecharge/wx/wxChongzhiFail.htm?errormsg=" + errormsg);
    }

    /**
     * @return ${return_type}    返回类型
     * @Description: ${todo}(微信充值页面)
     * @author lp
     * @date 2016年12月29日09:56:11
     */
    @RequestMapping("/wx/cz.htm")
    @MethodLog(logRemark = "根据充值平台给对应的平台发送请求", logTag = "充值动作", logKey = "校联网平台前台财富系统", logType = 1)
    public ModelAndView cz(Integer rechargeType, Double rechargeMoney, Integer type, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
        //rechargeType 3：校币充值
        User user = WebUtil.getUser(request);
        String userId = user.getId();
        JSONObject resObj = new JSONObject();
        String msg = "";
        String successUrl = request.getParameter("successUrl");
        String failedUrl = request.getParameter("failedUrl");
        failedUrl = failedUrl.replaceAll("WOW", "&");
        if (type != null) {
            if (type == 1) {
                failedUrl = failedUrl + "?&msg=";
            } else if (type == 2 || type == 3) {
                failedUrl = failedUrl + "&msg=";
            }
        }
        String WXbody = "xiaolianwang";
        String unifiedOrderCB = "/common/userRecharge/wxc/orderCallBack.htm";

        try {
            if (null != rechargeType && rechargeType == 3) {
                try {
                    if (null == rechargeMoney) {
                        msg = "充值金额不能为空";
                        resObj.put("returnCode", -1);
                        resObj.put("errormsg", "充值金额不能为空");
                        WebUtil.write(response, resObj.toString());
                        return new ModelAndView("redirect:" + failedUrl + msg);
                    }
                    if (rechargeMoney < 0.01) {
                        msg = "最少充值0.01元";
                        resObj.put("returnCode", -1);
                        resObj.put("errormsg", "最少充值0.01元");
                        WebUtil.write(response, resObj.toString());
                        return new ModelAndView("redirect:" + failedUrl + msg);
                    }
                    String openId = (String) request.getSession().getAttribute("openId");
                    UserRecharge userRecharge = new UserRecharge();
                    HttpSession session = request.getSession();
                    userRecharge.setOpenId((String) session.getAttribute("openId"));
                    userRecharge.setId(UUIDUtil.getUUID());
                    userRecharge.setRechargeNum(new Long(new Date().getTime()).intValue());
                    userRecharge.setUserId(userId);
                    userRecharge.setRechargeType(rechargeType);
                    userRecharge.setRechargeMoney(rechargeMoney);
                    userRecharge.setRechargeTime(new Date());
                    userRecharge.setRechargeStatus(0);
                    Integer rechargeM = new Double(rechargeMoney * 100).intValue();
                    UserPay userPay = new UserPay();
                    userPay.setId(userRecharge.getId());
                    userPay.setUserId(userId);
                    userPay.setPayStatus(Constants.PAY_STATUS_FAIL);
                    userPay.setPayType(Constants.PAY_TYPE_CZ);
                    userPay.setPayTime(new Date());
                    userPay.setPaySchoolMoney((double) rechargeMoney * 100);
                    userPay.setSchoolMoney(0.0);
                    userPay.setPayNote("微信支付");
                    userPayService.insertSelective(userPay);
                    UnifiedOrderReqData unifiedOrderReqData = new UnifiedOrderReqData(WXbody, userRecharge.getId(), rechargeM, WebUtil.getIp(request), PropertiesUtil.getPropertie("WEB-INF/classes/config.properties", "base_server") + unifiedOrderCB, "JSAPI", openId);
                    UnifiedOrderResData resData = userRechargeService.saveRecharge(userRecharge, unifiedOrderReqData);
                    if (null != resData) {
                        String returnCode = resData.getReturn_code();
                        String resultCode = resData.getResult_code();
                        if ((!StringUtils.isEmpty(returnCode) && returnCode.equals("SUCCESS")) && (!StringUtils.isEmpty(resultCode) && resultCode.equals("SUCCESS"))) {
                            String prepayId = resData.getPrepay_id();
                            String signType = "MD5";
                            return new ModelAndView("redirect:/common/userRecharge/wx/wxPayJSAPI.htm?resultUrl=" + successUrl + "&prepayId=" + prepayId + "&signType=" + signType + "&userRechargeId=" + userRecharge.getId());
                        } else {
                            String returnMsg = resData.getReturn_msg();
                            resObj.put("returnCode", -1);
                            resObj.put("errormsg", "支付异常");
                        }
                    } else {
                        msg = "处理失败，请联系管理员!";
                        resObj.put("returnCode", -1);
                        resObj.put("errormsg", "处理失败，请联系管理员!");
                    }
                } catch (Exception e) {
                    msg = "处理失败，请联系管理员!";
                    log.error("异常日志：", e);
                    resObj.put("returnCode", -1);
                    resObj.put("errormsg", "处理失败，请联系管理员!");
                }
            }
            msg = URLEncoder.encode(msg, "utf-8");
        } catch (Exception e) {
            msg = "处理失败，请联系管理员!";
            log.error("异常日志：", e);
            resObj.put("returnCode", -1);
            resObj.put("errormsg", "处理失败，请联系管理员!");
        }
//        		WebUtil.write(response, resObj.toString());
        return new ModelAndView("redirect:" + failedUrl + msg);
    }

    /**
     * @return ${return_type}    返回类型
     * payType:支付类型
     * rechargeMoney：支付的金额
     * @Description: ${todo}(用于微门户支付)
     * @author lp
     * @date 2017年3月7日13:22:16
     */
    @RequestMapping("/wx/zfForWmh.htm")
    @MethodLog(logRemark = "根据充值平台给对应的平台发送请求", logTag = "充值动作", logKey = "校联网平台前台财富系统", logType = 1)
    public ModelAndView zfForWmh(HttpServletRequest request, HttpServletResponse response) {
    	log.info("/wx/zfForWmh.htm--------------------1");
        WmhUser user = WebUtil.getWmhUser(request);
        String userId = user.getId();
        JSONObject resObj = new JSONObject();
        String msg = "";
        String userPayId = request.getParameter("orderId");
        String successUrl = request.getParameter("successUrl");
        String failedUrl = request.getParameter("failedUrl");
        String remark = request.getParameter("remark");

        try {
            if (StringUtils.isEmpty(userPayId)) {
                msg = "系统出错";
                resObj.put("returnCode", -1);
                resObj.put("errormsg", "系统出错");
                return new ModelAndView("redirect:" + failedUrl + msg);
            }
            UserPay userPay = userPayService.searchById(userPayId);
            if (null == userPay) {
                msg = "系统出错";
                resObj.put("returnCode", -1);
                resObj.put("errormsg", "系统出错");
                return new ModelAndView("redirect:" + failedUrl + msg);
            }
            Double payMoney = 0.0;
            if (userPay.getPayType() == 15) { //其它
                String rechargeMoney = request.getParameter("rechargeMoney");
                if (!StringUtils.isEmpty(rechargeMoney)) {
                    payMoney = Double.parseDouble(rechargeMoney);
                }

            } else {
                payMoney = userPay.getPaySchoolMoney() / 100;
            }
            
            String WXbody = "xiaolianwang";
            String unifiedOrderCB = "/common/userRecharge/wxc/orderCallBack.htm";
            log.info("/wx/zfForWmh.htm--------------------2");
            if (null == payMoney) {
                msg = "充值金额不能为空";
                resObj.put("returnCode", -1);
                resObj.put("errormsg", "充值金额不能为空");
                return new ModelAndView("redirect:" + failedUrl + msg);
            }
            if (payMoney < 0.01) {
                msg = "最少充值0.01元";
                resObj.put("returnCode", -1);
                resObj.put("errormsg", "最少充值0.01元");
                response.setContentType("text/html;charset=utf-8");
                return new ModelAndView("redirect:" + failedUrl + msg);
            }

            log.info("/wx/zfForWmh.htm--------------------3");
            String openId = (String) request.getSession().getAttribute("openId");
            UserRecharge userRecharge = new UserRecharge();
            HttpSession session = request.getSession();
            userRecharge.setOpenId((String) session.getAttribute("openId"));
            userRecharge.setId(userPayId);
            userRecharge.setRechargeNum(new Long(new Date().getTime()).intValue());
            userRecharge.setUserId(userId);
            userRecharge.setRechargeType(2);//微信充值
            userRecharge.setRechargeMoney(payMoney);
            userRecharge.setRechargeTime(new Date());
            userRecharge.setRechargeStatus(0);
            Integer rechargeM = new Double(payMoney * 100).intValue();
            UnifiedOrderReqData unifiedOrderReqData = new UnifiedOrderReqData(WXbody, userRecharge.getId(), rechargeM, WebUtil.getIp(request), PropertiesUtil.getPropertie("WEB-INF/classes/config.properties", "base_server") + unifiedOrderCB, "JSAPI", openId);
            UnifiedOrderResData resData = userRechargeService.saveRecharge(userRecharge, unifiedOrderReqData);
            if(userPay.getPayType()==15){
                userPay.setPaySchoolMoney((double) payMoney * 100);
            }
            log.info("/wx/zfForWmh.htm--------------------4");
            if (StringUtil.isNotEmpty(remark)) {
				userPay.setRemark(remark);
			}
            userPayService.update(userPay);
            if (null != resData) {
                String returnCode = resData.getReturn_code();
                String resultCode = resData.getResult_code();
                if ((!StringUtils.isEmpty(returnCode) && returnCode.equals("SUCCESS")) && (!StringUtils.isEmpty(resultCode) && resultCode.equals("SUCCESS"))) {
                    String prepayId = resData.getPrepay_id();
                    String signType = "MD5";
//                    OrderQueryReq orderQueryReq=new OrderQueryReq(unifiedOrderReqData);
//                    OrderQueryRes orderQueryRes=userRechargeService.orderQuery(orderQueryReq);
//                    String tradeState=orderQueryRes.getTrade_state();
//                    String jiaoYiReturnCode=orderQueryRes.getReturn_code();
//                    log.info("*************微信支付交易状态1*****************" + jiaoYiReturnCode);
//                    log.info("*************微信支付交易状态*****************" + tradeState);
//                    && tradeState.equals("SUCCESS")
                    log.info("/wx/zfForWmh.htm--------------------5");
                        return new ModelAndView("redirect:/common/userRecharge/wx/wxPayJSAPI.htm?resultUrl=" + successUrl + "&prepayId=" + prepayId + "&signType=" + signType + "&userRechargeId=" + userRecharge.getId());

                } else {
                    String returnMsg = resData.getReturn_msg();
                    resObj.put("returnCode", -1);
                    resObj.put("errormsg", "支付异常");
                }
            } else {
                msg = "处理失败，请联系管理员!";
                resObj.put("returnCode", -1);
                resObj.put("errormsg", "处理失败，请联系管理员!");
            }
            msg = URLEncoder.encode(msg, "utf-8");
        } catch (Exception e) {
            msg = "处理失败，请联系管理员!";
            log.error("异常日志：", e);
            resObj.put("returnCode", -1);
            resObj.put("errormsg", "处理失败，请联系管理员!");
        }

        return new ModelAndView("redirect:" + failedUrl + msg);
    }

    /**
     * @return ${return_type}    返回类型
     * @Description: ${todo}(在非微信内置浏览器中进行微信支付)  商户没有H5支付权限不能用
     * @author lp
     * @date 2017年2月21日17:22:50
     */
    @RequestMapping("/wx/cz2.htm")
    @MethodLog(logRemark = "根据充值平台给对应的平台发送请求", logTag = "充值动作", logKey = "校联网平台前台财富系统", logType = 1)
    public ModelAndView cz2(Integer rechargeType, Double rechargeMoney, Integer type, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
        //rechargeType 3：校币充值
        User user = WebUtil.getUser(request);
        String userId = user.getId();
        JSONObject resObj = new JSONObject();
        String msg = "";
        String successUrl = request.getParameter("successUrl");
        String failedUrl = request.getParameter("failedUrl");
        failedUrl = failedUrl.replaceAll("WOW", "&");
        if (type != null) {
            if (type == 1) {
                failedUrl = failedUrl + "?&msg=";
            } else if (type == 2 || type == 3) {
                failedUrl = failedUrl + "&msg=";
            }
        }
        String WXbody = "xiaolianwang";
        String unifiedOrderCB = "/common/userRecharge/wxc/unifiedOrderCB.htm";
        try {
            if (null != rechargeType && rechargeType == 3) {
                try {
                    if (null == rechargeMoney) {
                        msg = "充值金额不能为空";
                        resObj.put("returnCode", -1);
                        resObj.put("errormsg", "充值金额不能为空");
                        WebUtil.write(response, resObj.toString());
                        return new ModelAndView("redirect:" + failedUrl + msg);
                    }
                    if (rechargeMoney < 0.01) {
                        msg = "最少充值0.01元";
                        resObj.put("returnCode", -1);
                        resObj.put("errormsg", "最少充值0.01元");
                        WebUtil.write(response, resObj.toString());
                        return new ModelAndView("redirect:" + failedUrl + msg);
                    }
                    String openId = (String) request.getSession().getAttribute("openId");
                    UserRecharge userRecharge = new UserRecharge();
                    HttpSession session = request.getSession();
                    userRecharge.setOpenId((String) session.getAttribute("openId"));
                    userRecharge.setId(UUIDUtil.getUUID());
                    userRecharge.setRechargeNum(new Long(new Date().getTime()).intValue());
                    userRecharge.setUserId(userId);
                    userRecharge.setRechargeType(rechargeType);
                    userRecharge.setRechargeMoney(rechargeMoney);
                    userRecharge.setRechargeTime(new Date());
                    userRecharge.setRechargeStatus(0);
                    Integer rechargeM = new Double(rechargeMoney * 100).intValue();

                    UnifiedOrderReqData unifiedOrderReqData = new UnifiedOrderReqData(WXbody, userRecharge.getId(), rechargeM, WebUtil.getIp(request), PropertiesUtil.getPropertie("WEB-INF/classes/config.properties", "base_server") + unifiedOrderCB, "MWEB", openId);
                    UnifiedOrderResData resData = userRechargeService.saveRecharge(userRecharge, unifiedOrderReqData);
                    if (null != resData) {
                        String returnCode = resData.getReturn_code();
                        String resultCode = resData.getResult_code();
                        if ((!StringUtils.isEmpty(returnCode) && returnCode.equals("SUCCESS")) && (!StringUtils.isEmpty(resultCode) && resultCode.equals("SUCCESS"))) {
                            String prepayId = resData.getPrepay_id();
                            String signType = "MD5";
                            JSAPIReqData jsApiReqData = new JSAPIReqData("prepay_id=" + prepayId, signType);
                            return new ModelAndView("redirect:weixin://wap/pay?appid=" + jsApiReqData.getAppId() + "&package=" + jsApiReqData.getPackageStr() + "&prepayid=" + prepayId + "&timestamp=" + jsApiReqData.getTimeStamp() + "&sign=" + jsApiReqData.getPaySign());
                        } else {
                            String returnMsg = resData.getReturn_msg();
                            resObj.put("returnCode", -1);
                            resObj.put("errormsg", "支付异常");
                        }
                    } else {
                        msg = "处理失败，请联系管理员!";
                        resObj.put("returnCode", -1);
                        resObj.put("errormsg", "处理失败，请联系管理员!");
                    }
                } catch (Exception e) {
                    msg = "处理失败，请联系管理员!";
                    log.error("异常日志：", e);
                    resObj.put("returnCode", -1);
                    resObj.put("errormsg", "处理失败，请联系管理员!");
                }
            }
            msg = URLEncoder.encode(msg, "utf-8");
        } catch (Exception e) {
            msg = "处理失败，请联系管理员!";
            log.error("异常日志：", e);
            resObj.put("returnCode", -1);
            resObj.put("errormsg", "处理失败，请联系管理员!");
        }
//        		WebUtil.write(response, resObj.toString());
        return new ModelAndView("redirect:" + failedUrl + msg);
    }

    @RequestMapping("/wx/wxChongzhiFail.htm")
    public String chongzhiFail(String errormsg, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
        try {
            String isOnline = ConfigUtil.getProperty("isOnline");
            if (!StringUtils.isEmpty(isOnline) && isOnline.equals("true")) {
                errormsg = new String(errormsg.getBytes("ISO-8859-1"), "UTF-8");
            }
            modelMap.put("errormsg", errormsg);
            modelMap.put("pageTitle", "支付结果页面");
        } catch (Exception e) {
            log.error("异常日志：", e);
        }

        return "/xlwapp/chongzhi-fail";
    }

    /**
     * @return ${return_type}    返回类型
     * @Description: ${todo}(手机网页上支付宝支付)
     * @author lp
     * @date 2017年2月23日15:58:50
     */
    @RequestMapping("/wx/payByZfb.htm")
    public void payByZFB(HttpServletRequest httpServletRequest, HttpServletResponse httpResponse, Double rechargeMoney, String url, String userId) throws Exception {

        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", com.wh.util.Constants.PID, com.wh.util.Constants.APP_PRIVATE_KEY, "json", "UTF-8", com.wh.util.Constants.ALIPAY_PUBLIC_KEY, "RSA");
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.open.public.template.message.industry.modify
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request

        //在公共参数中设置回跳和通知地址
        String returnUrl = url.replaceAll("WOW", "&");
        returnUrl = PropertiesUtil.getPropertie("WEB-INF/classes/config.properties", "base_server") + returnUrl;
//        ?userId=" + userId
        String notifyUrl = PropertiesUtil.getPropertie("WEB-INF/classes/config.properties", "base_server") + "/common/userRecharge/wx/zfbReturnUrl.htm";
        //SDK已经封装掉了公共参数，这里只需要传入业务参数
        alipayRequest.setReturnUrl(returnUrl);
        alipayRequest.setNotifyUrl(notifyUrl);
        String tradeNo = getOrderNum(); //订单号
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\"" + tradeNo + "\"," +
                "    \"total_amount\":\"" + rechargeMoney + "\"," +
                "    \"subject\":\"校园任务充值\"," +
                "    \"seller_id\":\"2088221506980843\"," +
                "    \"product_code\":\"QUICK_WAP_PAY\"" +
                "  }");
        String form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        httpResponse.setContentType("text/html;charset=UTF-8");
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        //生成订单信息
        User user = WebUtil.getUser(httpServletRequest);
        UserPay userPay = new UserPay();
        userPay.setId(tradeNo);
        userPay.setPayNum(Long.getLong(tradeNo));
        userPay.setUserId(userId);
        userPay.setReceiverId("admin");
        userPay.setPaySchoolMoney(rechargeMoney * 100);
        userPay.setSchoolMoney(user.getSchoolMoney() + rechargeMoney * 100);
        userPay.setPayTime(new Date());
        userPay.setPayStatus(Constants.PAY_STATUS_FAIL);
        userPay.setPayType(Constants.PAY_TYPE_CZ);
        userPay.setPayNote("支付宝支付");
        userPayService.insertSelective(userPay);
    }

    //支付的响应页面
    @RequestMapping("/wx/zfbReturnUrl.htm")
    public void zfbReturnUrl(HttpServletRequest request, HttpServletResponse response, String userId) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            System.out.println(">>>>>参数" + name + ":" + valueStr);
            params.put(name, valueStr);
        }
        String tradeNo = request.getParameter("out_trade_no"); //订单号
        String tradeStatus = request.getParameter("trade_status"); //交易状态
        Double money = Double.valueOf(request.getParameter("total_amount"));//	订单金额
        boolean signVerified = AlipaySignature.rsaCheckV1(params, com.wh.util.Constants.ALIPAY_PUBLIC_KEY, "UTF-8", "RSA"); //调用SDK验证签名
        if (signVerified) {
            System.out.println(">>>>>下单成功" + tradeNo);
            if (tradeStatus.equals("TRADE_FINISHED") || tradeStatus.equals("TRADE_SUCCESS")) {
                WebUtil.write(response, "success");
                if (!StringUtils.isEmpty(tradeNo)) {
                    UserPay userPay = new UserPay();
                    userPay.setId(tradeNo);
                    userPayService.updateUserPayByZFB(userPay);
                }
            }
        } else {//验证失败
            System.out.println(">>>>>验签失败" + tradeNo);
            System.out.println(">>>>>交易被关闭了");
            WebUtil.write(response, "failure");
        }
    }

    /**
     * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
     *
     * @return 以yyyyMMddHHmmss为格式的当前系统时间
     */
    public static String getOrderNum() {
        /** 年月日时分秒(无下划线) yyyyMMddHHmmss */
        String dtLong = "yyyyMMddHHmmss";
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(dtLong);
        return df.format(date);
    }

    /**
     * @param signType
     * @param userRechargeId
     * @param modelMap
     * @param request
     * @param response
     * @return
     * @author 徐优优
     * @date 2016年5月13日
     */
    @RequestMapping("/wx/wxPayJSAPI.htm")
    public String wxPayJSAPI(String prepayId, String signType, String userRechargeId, ModelMap modelMap, HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        String packageStr = "prepay_id=" + prepayId;
        String resultUrl = request.getParameter("resultUrl");
        resultUrl = resultUrl.replaceAll("WOW", "&");
        JSAPIReqData jsApiReqData = new JSAPIReqData(packageStr, signType);
        modelMap.put("jsApiReqData", jsApiReqData);
        modelMap.put("userRechargeId", userRechargeId);
        modelMap.put("pageTitle", "支付");
        modelMap.put("resultUrl", resultUrl);

        return "/xlwapp/wxPayJSAPI";
    }

    public void toWapRecharge(Integer rechargeType, String rechargeId, Double money, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (null != rechargeType && rechargeType == 1) {
                //商户订单号，商户网站订单系统中唯一订单号，必填
                String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"), "UTF-8");

                //订单名称，必填
                String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"), "UTF-8");

                //付款金额，必填
                String total_fee = new String(request.getParameter("WIDtotal_fee").getBytes("ISO-8859-1"), "UTF-8");

                //收银台页面上，商品展示的超链接，必填
                String show_url = new String(request.getParameter("WIDshow_url").getBytes("ISO-8859-1"), "UTF-8");

                //商品描述，可空
                String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"), "UTF-8");

                //////////////////////////////////////////////////////////////////////////////////

                //把请求参数打包成数组
                Map<String, String> sParaTemp = new HashMap<String, String>();
                sParaTemp.put("service", AlipayConfig.service);
                sParaTemp.put("partner", AlipayConfig.partner);
                sParaTemp.put("seller_id", AlipayConfig.seller_id);
                sParaTemp.put("_input_charset", AlipayConfig.input_charset);
                sParaTemp.put("payment_type", AlipayConfig.payment_type);
                sParaTemp.put("notify_url", AlipayConfig.notify_url);
                sParaTemp.put("return_url", AlipayConfig.return_url);
                sParaTemp.put("out_trade_no", out_trade_no);
                sParaTemp.put("subject", subject);
                sParaTemp.put("total_fee", total_fee);
                sParaTemp.put("show_url", show_url);
                sParaTemp.put("body", body);
                //其他业务参数根据在线开发文档，添加参数.文档地址:https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.2Z6TSk&treeId=60&articleId=103693&docType=1
                //如sParaTemp.put("参数名","参数值");

                //建立请求
                String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
                //out.println(sHtmlText);
                WebUtil.write(response, sHtmlText);
            } else if (null != rechargeType && rechargeType == 2) {
                try {
                    /*String openId = (String) request.getSession().getAttribute("openId");
                    //===========请求生成统一订单号==============
					H5PayReqData h5PayReqData = new H5PayReqData("testorder",UUIDUtil.getUUID(),1,WebUtil.getIp(request), PropertiesUtil.getPropertie("WEB-INF/classes/config.properties","base_server")+"/common/userRecharge/wx/rechargeSuccess.htm","JSAPI",openId);
					HttpsRequest hr = new HttpsRequest();
					String xmlStr = hr.sendPost(Configure.DO_ORDER, h5PayReqData);
					H5PayResData h5PayResData = (H5PayResData) Util.getObjectFromXML(xmlStr, H5PayResData.class);
					if(null != h5PayResData && (!StringUtils.isEmpty(h5PayResData.getReturn_code()) && h5PayResData.getReturn_code().equals("SUCCESS")) && (!StringUtils.isEmpty(h5PayResData.getResult_code()) && h5PayResData.getResult_code().equals("SUCCESS"))){
						//将从API返回的XML数据映射到Java对象
				        //===========请求生成js调用微信客户端的url==============
				        String noncestr = new RandomStringGenerator().getRandomStringByLength(32);
				        String timestamp = new Date().getTime() + "";
				        Map<String,	Object> paramMap = new HashMap<String,	Object>();
				        paramMap.put("appid", "wx16c61d1d10c64ceb");
				        paramMap.put("noncestr", noncestr);
				        paramMap.put("package", "WAP");
				        paramMap.put("prepayid", h5PayResData.getPrepay_id());
				        paramMap.put("timestamp", timestamp);
				        String signUrl = Signature.getSign(paramMap);
				        wxH5PayUrl = "weixin://wap/pay?appid%3Dwx16c61d1d10c64ceb%26noncestr%3D" + noncestr + "%26package%3DWAP%26prepayid%3D" + h5PayResData.getPrepay_id() + "%26timestamp%3D" + timestamp + "%26sign%3D" + signUrl;
					}else{
						modelMap.put("errormsg", "处理失败，请联系管理员!");
					}
					modelMap.put("prepayid", h5PayResData.getPrepay_id());
			        modelMap.put("wxH5PayUrl", wxH5PayUrl);*/
                } catch (Exception e) {
                    modelMap.put("errormsg", "处理失败，请联系管理员!");
                    log.error("异常日志：", e);
                }
            }
        } catch (Exception e) {
            log.error("异常日志：", e);
        }
    }

    /**
     * 发送请求.
     *
     * @param httpsUrl 请求的地址
     * @param xmlStr   请求的数据
     */
    public static String post(String httpsUrl, String xmlStr) {
        String res = "";
        HttpsURLConnection urlCon = null;
        try {
            urlCon = (HttpsURLConnection) (new URL(httpsUrl)).openConnection();
            urlCon.setDoInput(true);
            urlCon.setDoOutput(true);
            urlCon.setRequestMethod("POST");
            urlCon.setRequestProperty("Content-Length",
                    String.valueOf(xmlStr.getBytes().length));
            urlCon.setUseCaches(false);
            //设置为gbk可以解决服务器接收时读取的数据中文乱码问题
            urlCon.getOutputStream().write(xmlStr.getBytes("gbk"));
            urlCon.getOutputStream().flush();
            urlCon.getOutputStream().close();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    urlCon.getInputStream()));

            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                res += line;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
