package com.wh.controller.wchat;

import com.wh.controller.rgpp.ControllerUtils;
import com.wh.entity.TbPlatform;
import com.wh.entity.TbWchatuser;
import com.wh.service.rgpp.PlatformService;
import com.wh.service.rgpp.WchatuserPlatService;
import com.wh.util.AddressUtil;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

/**
 * Created by danding on 2016/8/4.
 */
@Controller
@RequestMapping("/wchat")
public class WchatExtendsController {
    @Autowired
    private PlatformService platformService;
    
    @Autowired
    private WchatuserPlatService wchatuserPlatService;
    /**
     * 获取微信分享检验数据
     * @param request
     * @param response
     * @author 张红超
     * @date 2016年8月4日
     */
    @RequestMapping(value = "/jsSdkShare")
    public void wchatJsSdkShare(HttpServletRequest request, HttpServletResponse response){
        String platformId = request.getParameter("platformId");
        JSONObject result = new JSONObject();
        if(!StringUtils.isEmpty(platformId)){
            String url = request.getHeader("referer");
            TbPlatform tbPlatform = this.platformService.getPlatFromById(platformId);
            if(tbPlatform==null){
                result.put("code",404);
                result.put("msg","平台ID错误");
                ControllerUtils.outputJSON(response, result);
                return;
            }
            Map<String,Object> model = ControllerUtils.getWchatJsSdkModel(tbPlatform.getJsapiTicket(), tbPlatform.getAppid(), url);
            String createNonceStr = (String) model.get("createNonceStr");
            String createTimeStamp = (String) model.get("createTimeStamp");
            String signature = (String) model.get("signature");
            String appId = (String) model.get("appId");
            result.put("code",200);
            result.put("createNonceStr", createNonceStr);
            result.put("createTimeStamp", createTimeStamp);
            result.put("signature", signature);
            result.put("appId", appId);
            ControllerUtils.outputJSON(response,result);
        }
    }
    /**
     * 
     * @param request
     * @param response
     * @param platformId
     * @author 徐优优
     * @date 2016年8月11日
     */
    @RequestMapping(value="/wchatAccessToken.htm")
    public void wchatAccessToken(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = true)String platformId){
    	TbPlatform tbPlatform = this.platformService.getPlatFromById(platformId);
    	JSONObject result = new JSONObject();
    	result.put("accessToken", tbPlatform.getAccessToken());
    	ControllerUtils.outputJSON(response,result);
    }
    
    /**
     * 给微信用户表中插入用户关注时的地理位置信息
     * @param request
     * @param response
     * @param platformId
     * @author 徐优优
     * @date 2016年8月22日
     */
    @RequestMapping(value="/wchatUserAddress.htm")
    public void wchatUserAddress(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = true)String platformId){
    	List<Map<String, Object>> wchatUserAddressList = wchatuserPlatService.selectWchatUserAddress();
    	if(null != wchatUserAddressList && wchatUserAddressList.size() > 0) {
    		for(Map<String, Object> wchatUserAddress :wchatUserAddressList){
    			String doContent = (String)wchatUserAddress.get("doContent");
    			String addressDetailQ = (String)wchatUserAddress.get("address_detail");
    			if(!StringUtils.isEmpty(doContent) && StringUtils.isEmpty(addressDetailQ)) {
    				Double lng = Double.valueOf(doContent.split(",")[0]);
    				Double lat = Double.valueOf(doContent.split(",")[1]);
    				String result = AddressUtil.getAddressByXY(lng, lat);
    				JSONObject jsStr = JSONObject.fromObject(result);
					Integer status = (Integer) jsStr.get("status");
					if (status == 0){
						String addressDetail = jsStr.getJSONObject("result").getString("formatted_address");
						String addressProvince = jsStr.getJSONObject("result").getJSONObject("addressComponent").getString("province");
						String addressCity = jsStr.getJSONObject("result").getJSONObject("addressComponent").getString("city");
						String addressDistrict = jsStr.getJSONObject("result").getJSONObject("addressComponent").getString("district");
						
						String wchatUserId = (String)wchatUserAddress.get("id");
						TbWchatuser wu = new TbWchatuser();
						wu.setId(wchatUserId);
						wu.setAddressDetail(addressDetail);
						wu.setAddressProvince(addressProvince);
						wu.setAddressCity(addressCity);
						wu.setAddressArea(addressDistrict);
						wu.setAddressLng(lng);
						wu.setAddressLat(lat);
						wchatuserPlatService.update(wu);
					}
    			}
    		}
    	}
    }
}
