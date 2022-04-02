package com.fuswx.deformation_monitoring_system.Mapper;

import com.fuswx.deformation_monitoring_system.Bean.FixData;
import com.fuswx.deformation_monitoring_system.Bean.ReflectData;
import com.fuswx.deformation_monitoring_system.Bean.VariableData;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.Date;

public interface DeviceMapper {
    /*@Select("select * from realtimedata")
    ArrayList<RealTimeData> getAllRealTimeData();

    @Select("select * from statisticsdatabyday where time>=#{startTime} and time<= #{endTime}")
    ArrayList<StatisticsDataByDay> getDataByDay(Date startTime, Date endTime);

    @Select("select * from statisticsdatabyhour where time>=date_format(#{startTime},'%Y-%m-%d 00:00:00') and time< date_format(date_add(#{startTime},INTERVAL 1 hour),'%Y-%m-%d 00:00:00')")
    ArrayList<StatisticsDataByHour> getDataByHour(Date currentTime);

    @Insert("insert into savedparameter (userid,initDis,alDis,alDur,accuDis,time) values(#{userId},#{initDis},#{alDis},#{alDur},#{accuDis},#{time})")
    void setSavedParameter(SavedParameter savedParameter);*/

    @Select("select * from variableData")
    ArrayList<VariableData> getAllVariableData();

    @Select("select * from variableData where updateTime >= date_format(#{fromTime},'%Y-%m-%d %H:%i:%s') and updateTime <= date_format(#{totTime},'%Y-%m-%d %H:%i:%s')")
    ArrayList<VariableData> getVariableDataByDay(Date fromTime, Date toTime);

    @Insert("insert into variableData values(null,#{roadwayDepth},#{crossHeadingPress},#{workingFacePress},#{predictValue},#{stepPressure},#{updateTime})")
    void setVariableData(VariableData variableData);

    @Select("select * from reflectData")
    ArrayList<ReflectData> getAllReflectData();

    @Select("select * from reflectData where id=(select max(id) from reflectData)")
    ReflectData getLastReflectData();

    @Insert("insert into ReflectData values(null,#{roadwayDepthMeasure},#{roadwayDepthWeight},#{roadwayShapeMeasure},#{roadwayShapeWeight},#{pillarWidthMeasure},#{pillarWidthWeight},#{roadwaySizeTotalMeasure},#{roadwaySizeTotalWeight},#{crossHeadingPressMeasure},#{crossHeadingPressWeight},#{workingFacePressMeasure},#{workingFacePressWeight},#{stepPressureMeasure},#{stepPressureWeight},#{updateManagerId},#{updateTime})")
    void setReflectData(ReflectData reflectData);

    @Select("select * from FixData")
    ArrayList<FixData> getAllFixData();

    @Select("select * from FixData where id=(select max(id) from fixData)")
    FixData getLastFixData();

    @Insert("insert into FixData values(null,#{roadwayAngle},#{roadwayShape},#{roadwaySizeTotal},#{roadwaySize},#{pillarWidth},#{roadwayTotalTransform},#{FixTime},#{FixManagerId})")
    void setFixData(FixData fixData);
}
