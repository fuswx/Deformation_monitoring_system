package com.fuswx.deformation_monitoring_system.Controller;

import com.alibaba.fastjson.JSONObject;
import com.fuswx.deformation_monitoring_system.Bean.FixData;
import com.fuswx.deformation_monitoring_system.Bean.ReflectData;
import com.fuswx.deformation_monitoring_system.Service.IDeviceService;
import com.fuswx.deformation_monitoring_system.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private IDeviceService deviceService;

    @Autowired
    private IUserService userService;

    @PostMapping("/setFixData.do")
    @ResponseBody
    public void setFixData(@RequestBody FixData fixData){
        deviceService.setFixData(fixData);
    }

    @PostMapping(value = "/setReflectData.do")
    @ResponseBody
    public Integer setReflectData(@RequestBody String reflectDataStr){
        reflectDataStr= URLDecoder.decode(reflectDataStr, StandardCharsets.UTF_8);
        reflectDataStr=reflectDataStr.split("=")[1];
        FixData fixData=JSONObject.parseObject(reflectDataStr,FixData.class);
        ReflectData reflectData=JSONObject.parseObject(reflectDataStr,ReflectData.class);
        Date dateNow=new Date();
        Integer updateUserId=userService.getUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName().split("_roo")[0]).getId();
        fixData.setFixTime(dateNow);
        reflectData.setUpdateTime(dateNow);
        fixData.setFixManagerId(updateUserId);
        reflectData.setUpdateManagerId(updateUserId);
        Boolean successFlag= deviceService.setReflectData(reflectData) && deviceService.setFixData(fixData);
        return successFlag?200:400;
    }

//    @RequestMapping("/getRelectChinese.do")
//    @ResponseBody
//    public ArrayList<String> getRelectChinese(){
//        return deviceService.getAllRelectChinese();
//    }
}
