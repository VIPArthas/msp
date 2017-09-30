package com.wh.controller.rgpp;

import com.wh.constants.Constants;
import com.wh.controller.common.BaseController;
import com.wh.util.WeiXinPlatformUtil;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 控制器辅助操作
 * Created by danding on 2016/3/10.
 */
public class ControllerUtils {
    /**
     * 以输出流的方式回复指定内容
     *
     * @param resp
     * @param content
     */
    public static void outputContent(HttpServletResponse resp, String content) {
        PrintWriter printWriter = null;
        try {
            printWriter = resp.getWriter();
            printWriter.write(content);
            printWriter.flush();
        } catch (IOException e) {
//            e.error("异常日志：", e);
            e.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }

    /**
     * 返回json数据
     *
     * @param resp
     * @param json
     */
    public static void outputJSON(HttpServletResponse resp, JSONObject json) {
        PrintWriter printWriter = null;
        try {
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("application/json; charset=utf-8");
            printWriter = resp.getWriter();
            printWriter.write(json.toString());
            printWriter.flush();
        } catch (IOException e) {
//            log.error("异常日志：", e);
            e.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }

    /**
     * 获得指定长度随机字符串
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 获得微信JS-SDK签名验证数据
     *
     * @param request
     * @return
     */
    public static Map getWchatJsSdkModel(HttpServletRequest request, String ticket, String appId) {
        Map<String, Object> model = new HashMap<String, Object>();
        String noncestr = getRandomString(16);
        String timestamp = System.currentTimeMillis() + "";
        model.put("createNonceStr", noncestr);
        model.put("createTimeStamp", timestamp);
        String url = Constants.BASE_SERVER + request.getRequestURI();
        if (null != request.getQueryString() && !"".equals(request.getQueryString())) {
            url += "?" + request.getQueryString();
        }
        model.put("signature", WeiXinPlatformUtil.getJS_signature(noncestr, timestamp, url, ticket));
        model.put("appId", appId);
        return model;
    }

    /**
     * 获得微信JS-SDK签名验证数据
     * @param ticket
     * @param appId
     * @param url
     * @return
     */
    public static Map getWchatJsSdkModel( String ticket, String appId, String url) {
        Map<String, Object> model = new HashMap<>();
        String noncestr = getRandomString(16);
        String timestamp = System.currentTimeMillis() + "";
        model.put("createNonceStr", noncestr);
        model.put("createTimeStamp", timestamp);
        model.put("signature", WeiXinPlatformUtil.getJS_signature(noncestr, timestamp, url, ticket));
        model.put("appId", appId);
        return model;
    }
}
