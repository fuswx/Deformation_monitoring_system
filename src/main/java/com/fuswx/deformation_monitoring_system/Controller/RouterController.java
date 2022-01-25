package com.fuswx.deformation_monitoring_system.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class RouterController {

    @RequestMapping("/account/{varName}")
    public String account(@PathVariable("varName")String varName){
        return "pages/account/"+varName;
    }

    @RequestMapping("/project/{varName}")
    public String project(@PathVariable("varName")String varName){
        return "pages/project/"+varName;
    }

    @RequestMapping("/monitor/{varName}")
    public String monitor(@PathVariable("varName")String varName){
        return "pages/monitor/"+varName;
    }

    @RequestMapping({"/","/index"})
    public String index(){
        return "index";
    }

    @RequestMapping({"/toLogin","/login"})
    public String login(){
        return "pages/login";
    }
}
