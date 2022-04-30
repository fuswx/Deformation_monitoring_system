package com.fuswx.deformation_monitoring_system.Controller;

import com.fuswx.deformation_monitoring_system.Bean.FixData;
import com.fuswx.deformation_monitoring_system.Bean.ReflectData;
import com.fuswx.deformation_monitoring_system.Bean.VariableData;
import com.fuswx.deformation_monitoring_system.Bean.WorkingStatus;
import com.fuswx.deformation_monitoring_system.Service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;

@Controller
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private IDeviceService deviceService;

    @RequestMapping("/getAllVariableData.do")
    public @ResponseBody ArrayList<VariableData> getAllVariableData(){
        return deviceService.getAllVariableData();
    }

    @RequestMapping("/getByDaysVariableData.do")
    public @ResponseBody ArrayList<VariableData> getByDaysVariableData(@RequestParam(defaultValue = "7",required = false,name = "days") Integer days){
        return deviceService.getByDaysVariableData(days);
    }

    @RequestMapping("/getVariableDataByDay.do")
    public @ResponseBody ArrayList<VariableData> getVariableDataByDay(String fromTime,String toTime) throws ParseException {
        return deviceService.getVariableDataByDay(fromTime,toTime);
    }

    @PostMapping("/setVariableData.do")
    @ResponseBody
    public void setVariableData(@RequestBody VariableData variableData){
        deviceService.setVariableData(variableData);
    }

    @RequestMapping("/getReflectData.do")
    public @ResponseBody ArrayList<ReflectData> getAllReflectData(){
        return deviceService.getAllReflectData();
    }

    @RequestMapping("/getLastReflectData.do")
    public @ResponseBody ReflectData getLastReflectData(){
        return deviceService.getLastReflectData();
    }

    @RequestMapping("/getAllFixData.do")
    public @ResponseBody ArrayList<FixData> getAllFixDataData(){
        return deviceService.getAllFixData();
    }

    @RequestMapping("/getLastFixData.do")
    public @ResponseBody FixData getLastFixDataData(){
        return deviceService.getLastFixData();
    }

    @RequestMapping("/getLastWorkingStatus.do")
    public @ResponseBody WorkingStatus getLastWorkingStatus(){
        return deviceService.getLastWorkingStatus();
    }
}
