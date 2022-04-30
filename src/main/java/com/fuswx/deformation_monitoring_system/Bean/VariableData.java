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
    private Double roadwayTotalTransform;
    private Double roadwayTotalTransformPre;
    private Double stepPressure;
    private Date updateTime;

    public VariableData(Double roadwayDepth, Double roadwayTotalTransform, Double roadwayTotalTransformPre, Double stepPressure, Date updateTime) {
        this.roadwayDepth = roadwayDepth;
        this.roadwayTotalTransform = roadwayTotalTransform;
        this.roadwayTotalTransformPre = roadwayTotalTransformPre;
        this.stepPressure = stepPressure;
        this.updateTime = updateTime;
    }
}
