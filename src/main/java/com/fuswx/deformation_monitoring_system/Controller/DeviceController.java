package com.fuswx.deformation_monitoring_system.Controller;

import com.fuswx.deformation_monitoring_system.Bean.RealTimeData;
import com.fuswx.deformation_monitoring_system.Bean.SavedParameter;
import com.fuswx.deformation_monitoring_system.Bean.StatisticsDataByDay;
import com.fuswx.deformation_monitoring_system.Bean.StatisticsDataByHour;
import com.fuswx.deformation_monitoring_system.Service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

@Controller
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private IDeviceService deviceService;

    @RequestMapping("/getAllRealTimeData.do")
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
    }
}
