package com.fuswx.deformation_monitoring_system.Mapper;

import com.fuswx.deformation_monitoring_system.Bean.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.Date;

public interface DeviceMapper {

    @Select("select * from variabledata")
    ArrayList<VariableData> getAllVariableData();

    @Select("select * from variabledata where updatetime >= date_format(#{fromTime},'%Y-%m-%d %H:%i:%s') and updatetime <= date_format(#{toTime},'%Y-%m-%d %H:%i:%s')")
    ArrayList<VariableData> getVariableDataByDay(Date fromTime, Date toTime);

    @Insert("insert into variabledata values(null,#{roadwayDepth},#{crossHeadingPress},#{workingFacePress},#{predictValue},#{stepPressure},#{updateTime})")
    void setVariableData(VariableData variableData);

    @Select("select * from reflectdata")
    ArrayList<ReflectData> getAllReflectData();

    @Select("select * from reflectdata where id=(select max(id) from reflectdata)")
    ReflectData getLastReflectData();

    @Insert("insert into reflectdata values(null,#{roadwayDepthMeasure},#{roadwayDepthWeight},#{roadwayDepthMeasure},#{roadwayShapeMeasure},#{roadwayShapeWeight},#{pillarWidthMeasure},#{pillarWidthWeight},#{roadwaySizeTotalMeasure},#{roadwaySizeTotalWeight},#{crossHeadingPressMeasure},#{crossHeadingPressWeight},#{workingFacePressMeasure},#{workingFacePressWeight},#{stepPressureMeasure},#{stepPressureWeight},#{gMeasure},#{gWeight},#{updateManagerId},#{updateTime})")
    Integer setReflectData(ReflectData reflectData);

    @Select("select * from fixdata")
    ArrayList<FixData> getAllFixData();

    @Select("select * from fixdata where id=(select max(id) from fixdata)")
    FixData getLastFixData();

    @Insert("insert into fixdata values(null,#{roadwayAngle},#{roadwayShape},#{roadwaySizeTotal},#{pillarWidth},#{g},#{FixTime},#{FixManagerId})")
    Integer setFixData(FixData fixData);

    @Select("select chinese,english from reflectchinese")
    ArrayList<ChineseEnglishMapping> getAllRelectChinese();

//    @Insert("insert into variabledata(roadwaydepth,roadwaytotaltransform,roadwaytotaltransformpre,steppressure,updatetime) values(#{roadwayDepth},#{roadwayTotalTransform},#{roadwayTotalTransformPre},#{stepPressure},#{updateTime})")
//    void settingVariable(VariableData variableData);

    @Select("select transform from datas")
    ArrayList<Double> getTransform();

    @Select("select * from variabledata where id<=(select max(id) from variabledata) and id>((select max(id) from variabledata)-#{days})")
    ArrayList<VariableData> getByDaysVariableData(Integer days);

    @Select("select * from workingstatus where id=(select max(id) from workingstatus)")
    WorkingStatus getLastWorkingStatus();

    @Select("select count(id) from reflectdata")
    Integer getAllReflectDataCount();
}
