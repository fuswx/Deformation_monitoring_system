package com.fuswx.deformation_monitoring_system.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VariableData {
    private Integer id;
    private Double roadwayDepth;
    private Double crossHeadingPress;
    private Double workingFacePress;
    private Double predictValue;
    private Double stepPressure;
    private Date updateTime;
}
