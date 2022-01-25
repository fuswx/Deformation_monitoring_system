package com.fuswx.deformation_monitoring_system.Service;

import com.fuswx.deformation_monitoring_system.Bean.RealTimeData;
import com.fuswx.deformation_monitoring_system.Bean.SavedParameter;
import com.fuswx.deformation_monitoring_system.Bean.StatisticsDataByDay;
import com.fuswx.deformation_monitoring_system.Bean.StatisticsDataByHour;

import java.util.ArrayList;
import java.util.Date;

public interface IDeviceService {
    ArrayList<RealTimeData> getAllRealTimeData();

    ArrayList<StatisticsDataByDay> getDataByDay(String startTime, String endTime);

    ArrayList<StatisticsDataByHour> getDataByHour(String currentTime);

    void setSavedParameter(SavedParameter savedParameter);

    void setSavedParameters(String userId,Double initDis, Long alDis, Long alDur, Long accuDis);
}
