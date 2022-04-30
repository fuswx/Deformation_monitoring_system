package com.fuswx.deformation_monitoring_system.Controller;

import com.fuswx.deformation_monitoring_system.Service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RouterController {

    @Autowired
    private IDeviceService deviceService;

    @RequestMapping("/account/{varName}")
    public String account(@PathVariable("varName")String varName){
        return "pages/account/"+varName;
    }

    @RequestMapping("/account/configuration")
    public ModelAndView configuration() throws IllegalAccessException {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("pages/account/configuration");

        modelAndView.addObject("allHashMapReflectData",deviceService.getHashMapReflectData());
        modelAndView.addObject("allHashMapFixData",deviceService.getHashMapFixData());
        return modelAndView;
    }

    @RequestMapping("/account/reflectHistory")
    public ModelAndView reflectHistory(@RequestParam(name = "pageNum",defaultValue = "1")Integer pageNum,
                                       @RequestParam(name = "pageSize",defaultValue = "10")Integer pageSize) throws IllegalAccessException {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("pages/account/user");
        modelAndView.addObject("pageInfo",deviceService.getAllHashMapReflectData(pageNum,pageSize));
        return modelAndView;
    }

    @RequestMapping("/account/reflectMeasureHistory")
    public ModelAndView reflectMethodHistory(@RequestParam(name = "pageNum",defaultValue = "1")Integer pageNum,
                                             @RequestParam(name = "pageSize",defaultValue = "10")Integer pageSize) throws IllegalAccessException {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("pages/account/history");
        modelAndView.addObject("pageInfo",deviceService.getAllHashMapReflectXXXXHistory("measure",pageNum,pageSize));
        return modelAndView;
    }
    @RequestMapping("/account/reflectWeightHistory")
    public ModelAndView reflectWeightHistory(@RequestParam(name = "pageNum",defaultValue = "1")Integer pageNum,
                                             @RequestParam(name = "pageSize",defaultValue = "10")Integer pageSize) throws IllegalAccessException {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("pages/account/history");
        modelAndView.addObject("pageInfo",deviceService.getAllHashMapReflectXXXXHistory("weight",pageNum,pageSize));
        return modelAndView;
    }
    @RequestMapping("/account/fixHistory")
    public ModelAndView fixHistory(@RequestParam(name = "pageNum",defaultValue = "1")Integer pageNum,
                                   @RequestParam(name = "pageSize",defaultValue = "10")Integer pageSize) throws IllegalAccessException {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("pages/account/history");

        modelAndView.addObject("pageInfo",deviceService.getAllHashMapFixData(pageNum,pageSize));
        return modelAndView;
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
    public ModelAndView index(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("pages/monitor/manage");
//        modelAndView.addObject("workingStatus",deviceService.getLastWorkingStatus());
        return modelAndView;
    }

    @RequestMapping({"/toLogin","/login"})
    public String login(){
        return "pages/login";
    }
}
