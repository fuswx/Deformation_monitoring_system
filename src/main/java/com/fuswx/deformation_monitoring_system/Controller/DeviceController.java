package com.fuswx.deformation_monitoring_system.Controller;

import com.fuswx.deformation_monitoring_system.Bean.FixData;
import com.fuswx.deformation_monitoring_system.Bean.ReflectData;
import com.fuswx.deformation_monitoring_system.Bean.VariableData;
import com.fuswx.deformation_monitoring_system.Service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;

@Controller
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private IDeviceService deviceService;

    @RequestMapping("/getAllVariableData.do")
    public @ResponseBody ArrayList<VariableData> getAllVariableData(){
        return deviceService.getAllVariableData();
    }

    @RequestMapping("/getVariableDataByDay.do")
    public @ResponseBody ArrayList<VariableData> getVariableDataByDay(Date fromTime,Date toTime){
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

    @PostMapping("/setReflectData.do")
    @ResponseBody
    public void setReflectData(@RequestBody ReflectData reflectData){
        deviceService.setReflectData(reflectData);
    }

    @RequestMapping("/getAllFixData.do")
    public @ResponseBody ArrayList<FixData> getAllFixDataData(){
        return deviceService.getAllFixData();
    }

    @RequestMapping("/getLastFixData.do")
    public @ResponseBody FixData getLastFixDataData(){
        return deviceService.getLastFixData();
    }

    @PostMapping("/setFixData.do")
    @ResponseBody
    public void setFixData(@RequestBody FixData fixData){
        deviceService.setFixData(fixData);
    }

    /*@RequestMapping("/getAllRealTimeData.do")
    public @ResponseBody ArrayList<RealTimeData> getAllRealTimeData(){
        return deviceService.getAllRealTimeData();
    }

    @PostMapping("/getDataByDay.do")
    public @ResponseBody ArrayList<StatisticsDataByDay> getDataByDay(String startTime,String endTime){
        return deviceService.getDataByDay(startTime,endTime);
    }

    @PostMapping("/getDataByHour.do")
    public @ResponseBody ArrayList<StatisticsDataByHour> getDataByHour(String currentTime){
        return deviceService.getDataByHour(currentTime);
    }

    @PostMapping("/setSavedParameter.do")
    @ResponseBody
    public void getSavedParameter(@RequestBody SavedParameter savedParameter){
        deviceService.setSavedParameter(savedParameter);
    }

    @ResponseBody
    @PostMapping("/setSavedParameters.do")
    public void getSavedParameter(@RequestParam("userId") String userId,@RequestParam("initDis") Double initDis, @RequestParam("alDis") Long alDis, @RequestParam("alDur") Long alDur, @RequestParam("accuDis") Long accuDis){
        deviceService.setSavedParameters(userId,initDis,alDis,alDur,accuDis);
    }*/
}
