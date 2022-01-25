package com.fuswx.deformation_monitoring_system.Service.impl;

import com.fuswx.deformation_monitoring_system.Bean.RealTimeData;
import com.fuswx.deformation_monitoring_system.Bean.SavedParameter;
import com.fuswx.deformation_monitoring_system.Bean.StatisticsDataByDay;
import com.fuswx.deformation_monitoring_system.Bean.StatisticsDataByHour;
import com.fuswx.deformation_monitoring_system.Mapper.DeviceMapper;
import com.fuswx.deformation_monitoring_system.Service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
@Transactional
public class DeviceServiceImpl implements IDeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化时间

    @Override
    public ArrayList<RealTimeData> getAllRealTimeData() {
        return deviceMapper.getAllRealTimeData();
    }

    @Override
    public ArrayList<StatisticsDataByDay> getDataByDay(String startTime, String endTime) {
        ArrayList<StatisticsDataByDay> statisticsDataByDays = new ArrayList<>();
        try {
            statisticsDataByDays=deviceMapper.getDataByDay(simpleDateFormat.parse(startTime),simpleDateFormat.parse(endTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return statisticsDataByDays;
    }

    @Override
    public ArrayList<StatisticsDataByHour> getDataByHour(String currentTime) {
        ArrayList<StatisticsDataByHour> statisticsDataByHour = new ArrayList<>();
        try {
            statisticsDataByHour=deviceMapper.getDataByHour(simpleDateFormat.parse(currentTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return statisticsDataByHour;
    }

    @Override
    public void setSavedParameter(SavedParameter savedParameter) {
        savedParameter.setTime(new Date());
        deviceMapper.setSavedParameter(savedParameter);
    }

    @Override
    public void setSavedParameters(String userId,Double initDis, Long alDis, Long alDur, Long accuDis) {
        deviceMapper.setSavedParameter(new SavedParameter(null,userId,initDis,alDis,alDur,accuDis,new Date()));
    }
}
