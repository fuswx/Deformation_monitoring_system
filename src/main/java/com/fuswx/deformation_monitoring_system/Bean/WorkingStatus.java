package com.fuswx.deformation_monitoring_system.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkingStatus {
    private Integer id;
    private String position;
    private Double buttery;
    private Double windSpeed;
    private Double temperature;
    private Double gasConcentration;
    private Date updateTime;
}
