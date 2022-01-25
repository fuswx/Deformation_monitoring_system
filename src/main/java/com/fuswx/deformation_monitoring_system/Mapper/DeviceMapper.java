package com.fuswx.deformation_monitoring_system.Mapper;

import com.fuswx.deformation_monitoring_system.Bean.RealTimeData;
import com.fuswx.deformation_monitoring_system.Bean.SavedParameter;
import com.fuswx.deformation_monitoring_system.Bean.StatisticsDataByDay;
import com.fuswx.deformation_monitoring_system.Bean.StatisticsDataByHour;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.Date;

public interface DeviceMapper {
    @Select("select * from realtimedata")
    ArrayList<RealTimeData> getAllRealTimeData();

    @Select("select * from statisticsdatabyday where time>=#{startTime} and time<= #{endTime}")
    ArrayList<StatisticsDataByDay> getDataByDay(Date startTime, Date endTime);

    @Select("select * from statisticsdatabyhour where time>=date_format(#{startTime},'%Y-%m-%d 00:00:00') and time< date_format(date_add(#{startTime},INTERVAL 1 hour),'%Y-%m-%d 00:00:00')")
    ArrayList<StatisticsDataByHour> getDataByHour(Date currentTime);

    @Insert("insert into savedparameter (userid,initDis,alDis,alDur,accuDis,time) values(#{userId},#{initDis},#{alDis},#{alDur},#{accuDis},#{time})")
    void setSavedParameter(SavedParameter savedParameter);
}
