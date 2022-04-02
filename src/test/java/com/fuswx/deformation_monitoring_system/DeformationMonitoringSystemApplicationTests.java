package com.fuswx.deformation_monitoring_system;

import com.fuswx.deformation_monitoring_system.Bean.ReflectData;
import com.fuswx.deformation_monitoring_system.Bean.VariableData;
import com.fuswx.deformation_monitoring_system.Service.IDeviceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class DeformationMonitoringSystemApplicationTests {

    @Autowired
    private IDeviceService deviceService;

    @Test
    void contextLoads() {

        ArrayList<VariableData> variableData=deviceService.getAllVariableData();
        for (VariableData var : variableData) {
            System.out.println(var);
        }

        ArrayList<ReflectData> reflectData=deviceService.getAllReflectData();
        for (ReflectData reflectData1 : reflectData) {
            System.out.println(reflectData1);
        }

        System.out.println(deviceService.getLastReflectData());
    }

}
