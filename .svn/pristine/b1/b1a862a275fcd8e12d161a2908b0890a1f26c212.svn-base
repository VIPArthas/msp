package com.wh.controller.msp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wh.base.AjaxJson;
import com.wh.framework.OAuthRequired;  
/** 
 * 需要验证OAuth2控制器 
 * @author Sunlight 
 * 
 */  
@Controller
@RequestMapping("/msp/load")
public class UserController {  
	private Logger log = LoggerFactory.getLogger(this.getClass());
    /** 
     * 加载个人信息，此处添加了@OAuthRequired注解 
     * @param model 
     * @return 
     */  
    @RequestMapping(value="/userInfo.do")  
    @ResponseBody
    @OAuthRequired
    public AjaxJson load(HttpServletRequest request,HttpServletResponse resp,Model model){  
    	AjaxJson json=new AjaxJson();
        System.out.println("Load a User!");  
        HttpSession session = request.getSession();  
        model.addAttribute("Userid", session.getAttribute("Userid")); 
        json.setObj(session.getAttribute("Userid"));
        log.info("Userid:"+session.getAttribute("Userid"));
        return json;  
    }  
} 