package com.fuswx.deformation_monitoring_system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fuswx.deformation_monitoring_system.Mapper")
public class DeformationMonitoringSystemApplication  {

    public static void main(String[] args) {
        SpringApplication.run(DeformationMonitoringSystemApplication.class, args);
    }
}
