package com.stony.sso.admin.monitor.controller;

import com.stony.sso.web.form.BaseForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/6/3 </p>
 * <p>Time: 10:35 </p>
 * <p>Version: 1.0 </p>
 */
@Controller
@RequestMapping("/statistics/driver")
public class StatisticsDriverController {

    @RequestMapping("/list")
    public String index(BaseForm baseForm){
        return "statistics/driver/list";
    }
}
