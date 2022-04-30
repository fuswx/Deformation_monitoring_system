package com.fuswx.deformation_monitoring_system.Service.impl;

import com.fuswx.deformation_monitoring_system.Bean.*;
import com.fuswx.deformation_monitoring_system.Mapper.DeviceMapper;
import com.fuswx.deformation_monitoring_system.Service.IDeviceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class DeviceServiceImpl implements IDeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public ArrayList<VariableData> getAllVariableData() {
        return deviceMapper.getAllVariableData();
    }

    @Override
    public ArrayList<VariableData> getVariableDataByDay(String fromTime, String toTime) throws ParseException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return deviceMapper.getVariableDataByDay(simpleDateFormat.parse(fromTime),simpleDateFormat.parse(toTime));
    }

    @Override
    public void setVariableData(VariableData variableData) {
        FixData fixData=deviceMapper.getLastFixData();
        ReflectData reflectData=deviceMapper.getLastReflectData();
        variableData.setRoadwayTotalTransformPre(
                variableData.getRoadwayDepth()*reflectData.getRoadwayDepthWeight()
                +fixData.getRoadwaySizeTotal()*reflectData.getRoadwaySizeTotalWeight()
                +fixData.getPillarWidth()*reflectData.getPillarWidthWeight()
                +variableData.getRoadwayTotalTransform()*reflectData.getRoadwaySizeTotalWeight()
                +variableData.getCrossHeadingPress()*reflectData.getCrossHeadingPressWeight()
                +variableData.getWorkingFacePress()*reflectData.getWorkingFacePressWeight()
                +fixData.getG()*reflectData.getGWeight()
        );
        deviceMapper.setVariableData(variableData);
    }

    @Override
    public ArrayList<ReflectData> getAllReflectData() {
        return deviceMapper.getAllReflectData();
    }

    @Override
    public ReflectData getLastReflectData() {
        return deviceMapper.getLastReflectData();
    }

    @Override
    public Boolean setReflectData(ReflectData reflectData) {
        return deviceMapper.setReflectData(reflectData) > 0;
    }

    @Override
    public ArrayList<FixData> getAllFixData() {
        return deviceMapper.getAllFixData();
    }

    @Override
    public FixData getLastFixData() {
        return deviceMapper.getLastFixData();
    }

    @Override
    public Boolean setFixData(FixData fixData) {
        return deviceMapper.setFixData(fixData) > 0;
    }


    @Override
    public LinkedHashMap<Map<String,String>, Map<String, Double>> getHashMapReflectData() throws IllegalAccessException {
        LinkedHashMap<Map<String,String>, Map<String, Double>> hashMapReflectData=new LinkedHashMap<>();
        ArrayList<ChineseEnglishMapping> reflectAllChinese=deviceMapper.getAllRelectChinese();
        ReflectData reflectData=getLastReflectData();
        Field[] fields=reflectData.getClass().getDeclaredFields();

        for (ChineseEnglishMapping chineseEnglishMapping: reflectAllChinese) {
            Map<String, Double> middleMap=new HashMap<>();
            String Chinese="";
            String English="";
            String measure="";
            Double weight=999999.0;
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getName().toLowerCase(Locale.ROOT).equals(chineseEnglishMapping.getEnglish() + "weight")) {
                    weight = Double.parseDouble(field.get(reflectData).toString());
                    Chinese = chineseEnglishMapping.getChinese();
                    English = field.getName().split("Weight")[0];
                } else if (field.getName().toLowerCase(Locale.ROOT).equals(chineseEnglishMapping.getEnglish() + "measure")) {
                    measure = field.get(reflectData).toString();
                    Chinese = chineseEnglishMapping.getChinese();
                    English = field.getName().split("Measure")[0];
                }
            }
            middleMap.put(measure,weight);
            Map<String,String> ChineseAndEnglish=new HashMap<>();
            ChineseAndEnglish.put(Chinese,English);
            hashMapReflectData.put(ChineseAndEnglish,middleMap);
        }
        return hashMapReflectData;
    }

    @Override
    public ArrayList<ChineseEnglishMapping> getAllRelectChinese() {
        return deviceMapper.getAllRelectChinese();
    }

    @Override
    public LinkedHashMap<String, String> getHashMapFixData() throws IllegalAccessException {
        LinkedHashMap<String, String> hashMapFixData=new LinkedHashMap<>();
        ArrayList<ChineseEnglishMapping> reflectAllChinese=deviceMapper.getAllRelectChinese();
        FixData fixData=deviceMapper.getLastFixData();
        Field[] fields=fixData.getClass().getDeclaredFields();
        for (ChineseEnglishMapping chineseEnglishMapping : reflectAllChinese) {
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getName().toLowerCase(Locale.ROOT).equals(chineseEnglishMapping.getEnglish())) {
                    hashMapFixData.put(chineseEnglishMapping.getChinese(), field.get(fixData).toString());
                }
            }
        }
        return hashMapFixData;
    }

    @Override
    public PageInfo<LinkedHashMap<String,String>> getAllHashMapReflectData(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        ArrayList<ReflectData> allReflectData=deviceMapper.getAllReflectData();
        ArrayList<LinkedHashMap<String,String>> allHashMapReflectData=new ArrayList<>();
        for (ReflectData reflectData: allReflectData) {
            LinkedHashMap<String,String> hashMap=new LinkedHashMap<>();
            Field[] fields=reflectData.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    String content="";
                    content=field.get(reflectData)==null?"":field.get(reflectData).toString();
                    hashMap.put(field.getName(),content);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            allHashMapReflectData.add(hashMap);
        }
        PageInfo<LinkedHashMap<String,String>> pageInfo=new PageInfo<LinkedHashMap<String,String>>(allHashMapReflectData);
        return pageInfo;
    }

    @Override
    public PageInfo<LinkedHashMap<String, String>> getAllHashMapReflectXXXXHistory(String xxxx,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        ArrayList<ReflectData> allReflectData=deviceMapper.getAllReflectData();
        Integer totalCount=deviceMapper.getAllReflectDataCount();
        ArrayList<LinkedHashMap<String,String>> allHashMapReflectData=new ArrayList<>();
        ArrayList<ChineseEnglishMapping> reflectAllChinese=deviceMapper.getAllRelectChinese();
        for (ReflectData reflectData: allReflectData) {
            LinkedHashMap<String,String> hashMap=new LinkedHashMap<>();
            Field[] fields=reflectData.getClass().getDeclaredFields();
            for (ChineseEnglishMapping chineseEnglishMapping : reflectAllChinese) {
                for (Field field : fields) {
                    field.setAccessible(true);
                    if (field.getName().toLowerCase(Locale.ROOT).startsWith(chineseEnglishMapping.getEnglish())&&field.getName().toLowerCase(Locale.ROOT).endsWith(xxxx)){
                        try {
                            String content="";
                            content=field.get(reflectData)==null?"":field.get(reflectData).toString();
                            hashMap.put(chineseEnglishMapping.getChinese(),content);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            allHashMapReflectData.add(hashMap);
        }
        PageInfo<LinkedHashMap<String, String>> pageInfo=new PageInfo<>(allHashMapReflectData);
        pageInfo.setTotal(totalCount);
        pageInfo.setPageSize(pageSize);
        pageInfo.setPages(totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1);
        return pageInfo;
    }

    @Override
    public PageInfo<LinkedHashMap<String, String>> getAllHashMapFixData(Integer pageNum,Integer pageSize) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        PageHelper.startPage(pageNum,pageSize);
        ArrayList<FixData> allFixData=deviceMapper.getAllFixData();
        ArrayList<LinkedHashMap<String,String>> allHashMapFixData=new ArrayList<>();
        for (FixData fixData: allFixData) {
            LinkedHashMap<String,String> hashMap=new LinkedHashMap<>();
            Field[] fields=fixData.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    String content="";
                    content=field.get(fixData)==null?"":field.get(fixData).toString();
                    if (field.get(fixData) instanceof Date){
                        content=simpleDateFormat.format(new Date(content));
                    }
                    hashMap.put(field.getName(),content);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            allHashMapFixData.add(hashMap);
        }
        PageInfo<LinkedHashMap<String, String>> pageInfo=new PageInfo<>(allHashMapFixData);
        return pageInfo;
    }

//    @Override
//    public void settingVariable() throws ParseException {
//        ArrayList<Double> transform=new ArrayList<>();
//        transform=deviceMapper.getTransform();
//        Double demoDepth=1602.458;
//        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date=simpleDateFormat.parse("2021-10-31 00:00:00");
//        DecimalFormat decimalFormat=new DecimalFormat("0.000");
//        Calendar calendar=new GregorianCalendar();
//        for (Double trans : transform) {
//            demoDepth+=Math.random()*19+Math.random();
//            demoDepth=Double.parseDouble(decimalFormat.format(demoDepth));
//            calendar.setTime(date);
//            calendar.add(calendar.DATE,1);
//            date=calendar.getTime();
//            VariableData variableData=new VariableData(demoDepth,trans,25.0,25.0,date);
//            deviceMapper.settingVariable(variableData);
//        }
//    }

    @Override
    public ArrayList<VariableData> getByDaysVariableData(Integer days) {
        return deviceMapper.getByDaysVariableData(days);
    }

    @Override
    public WorkingStatus getLastWorkingStatus() {
        return deviceMapper.getLastWorkingStatus();
    }

}
