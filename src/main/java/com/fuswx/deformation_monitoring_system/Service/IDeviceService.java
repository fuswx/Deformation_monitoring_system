package com.fuswx.deformation_monitoring_system.Service;

import com.fuswx.deformation_monitoring_system.Bean.FixData;
import com.fuswx.deformation_monitoring_system.Bean.ReflectData;
import com.fuswx.deformation_monitoring_system.Bean.VariableData;

import java.util.ArrayList;
import java.util.Date;

public interface IDeviceService {
    /*ArrayList<RealTimeData> getAllRealTimeData();

    ArrayList<StatisticsDataByDay> getDataByDay(String startTime, String endTime);

    ArrayList<StatisticsDataByHour> getDataByHour(String currentTime);

    void setSavedParameter(SavedParameter savedParameter);

    void setSavedParameters(String userId,Double initDis, Long alDis, Long alDur, Long accuDis);*/

    ArrayList<VariableData> getAllVariableData();

    ArrayList<VariableData> getVariableDataByDay(Date fromTime, Date toTime);

    void setVariableData(VariableData variableData);

    ArrayList<ReflectData> getAllReflectData();

    ReflectData getLastReflectData();

    void setReflectData(ReflectData reflectData);

    ArrayList<FixData> getAllFixData();

    FixData getLastFixData();

    void setFixData(FixData fixData);
}
