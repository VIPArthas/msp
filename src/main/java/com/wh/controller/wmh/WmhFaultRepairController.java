package com.wh.controller.wmh;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wh.constants.Constants;
import com.wh.controller.common.BaseController;
import com.wh.entity.WmhFaultRepair;
import com.wh.entity.WmhUser;
import com.wh.framework.MethodLog;
import com.wh.service.wmh.WmhFaultRepairService;
import com.wh.util.DateUtil;
import com.wh.util.FileUtil;
import com.wh.util.WebUtil;

@Controller
@RequestMapping("/wmh/faultRepair")
public class WmhFaultRepairController extends BaseController {
	
	@Autowired
    private WmhFaultRepairService wmhFaultRepairService;
	
	
	@RequestMapping("/web/toFaultRepair.htm")
	public String toFaultRepair(HttpServletRequest request,ModelMap map){
		return "/wmh/wx/query/guzhang_baoxiu";
	}
	
	@RequestMapping("/wx/faultRepair.htm")
    @MethodLog(logKey = "故障报修", logTag = "故障报修处理", logRemark = "故障报修")
    public void evalua(HttpServletResponse response, HttpServletRequest request, WmhFaultRepair wmhFaultRepair) {
        JSONObject jso = new JSONObject();
        try {
            String msg = "";

            WmhUser user = (WmhUser) request.getSession().getAttribute("wmhUser");

            //图片上传
            String path = "/resource/upload/wmh/" + DateUtil.format(new Date(), "yyyyMM") + "/";
            List<String> fileList = FileUtil.uploadFiles4SpringMVC(request, path, Constants.img_allow_type, 5);
            wmhFaultRepair.setFileList(fileList);

            msg = wmhFaultRepairService.faultRepair(wmhFaultRepair,user);

            if ("".equals(msg)) {
                jso.put("msg", "");
                WebUtil.write(response, jso.toString());
                return;
            }
            jso.put("msg", msg);

        } catch (Exception e) {
            if ("com.wh.util.MyFileUploadException".equals(e.getClass().getName())) {
                jso.put("msg", e.getMessage());
            }
            log.error("异常日志：", e);
        }
        WebUtil.write(response, jso.toString());
    }
	
	
	

}
