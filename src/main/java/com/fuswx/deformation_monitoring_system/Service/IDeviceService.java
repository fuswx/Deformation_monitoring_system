package com.fuswx.deformation_monitoring_system.Service;

import com.fuswx.deformation_monitoring_system.Bean.*;
import com.github.pagehelper.PageInfo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public interface IDeviceService {

    ArrayList<VariableData> getAllVariableData();

    ArrayList<VariableData> getVariableDataByDay(String fromTime, String toTime) throws ParseException;

    void setVariableData(VariableData variableData);

    ArrayList<ReflectData> getAllReflectData();

    ReflectData getLastReflectData();

    Boolean setReflectData(ReflectData reflectData);

    ArrayList<FixData> getAllFixData();

    FixData getLastFixData();

    Boolean setFixData(FixData fixData);

    //ArrayList<String> getAllRelectChinese();

    LinkedHashMap<Map<String, String>, Map<String, Double>> getHashMapReflectData() throws IllegalAccessException;

    ArrayList<ChineseEnglishMapping> getAllRelectChinese();

    LinkedHashMap<String,String> getHashMapFixData() throws IllegalAccessException;

    PageInfo<LinkedHashMap<String,String>> getAllHashMapReflectData(Integer pageNum, Integer pageSize);

    PageInfo<LinkedHashMap<String,String>> getAllHashMapReflectXXXXHistory(String xxxx,Integer pageNum,Integer pageSize);

    PageInfo<LinkedHashMap<String, String>> getAllHashMapFixData(Integer pageNum,Integer pageSize);

    //void settingVariable() throws ParseException;

    ArrayList<VariableData> getByDaysVariableData(Integer days);

    WorkingStatus getLastWorkingStatus();
}
