package com.wh.controller.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class BaseController {

    protected String errorPage = "/errorpage/errorPage";

    public Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * form表单提交 Date类型数据绑定 <功能详细描述>
     * springMVC  对Date,Double等类型数据不会自动绑定
     * @param binder
     * @see [类、类#方法、类#成员]
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);	//严格解析,如果格式不对,或数据问题,会报错
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
