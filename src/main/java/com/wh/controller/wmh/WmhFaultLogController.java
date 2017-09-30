package com.wh.controller.wmh;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.wh.controller.common.BaseController;
import com.wh.service.wmh.WmhFaultLogService;


@Controller
@RequestMapping("/wmh/faultLog")
public class WmhFaultLogController extends BaseController {
	
	@Autowired
    private WmhFaultLogService wmhFaultLogService;

	
}
