package com.fuswx.deformation_monitoring_system.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReflectData {

    private Integer id;
    private String roadwayDepthMeasure;
    private Double roadwayDepthWeight;
    private String roadwayAngleMeasure;
    private String roadwayShapeMeasure;
    private Double roadwayShapeWeight;
    private String pillarWidthMeasure;
    private Double pillarWidthWeight;
    private String roadwaySizeTotalMeasure;
    private Double roadwaySizeTotalWeight;
    private String crossHeadingPressMeasure;
    private Double crossHeadingPressWeight;
    private String workingFacePressMeasure;
    private Double workingFacePressWeight;
    private String stepPressureMeasure;
    private Double stepPressureWeight;
    private String gMeasure;
    private Double gWeight;
    private String stratumThicknessMeasure;
    private Double stratumThicknessWeight;
    private String stratumAngleMeasure;
    private Double stratumAngleWeight;
    private String topRockLayersMeasure;
    private Double topRockLayersWeight;
    private String topRockThicknessMeasure;
    private Double topRockThicknessWeight;
    private String rockFissuresMeasure;
    private Double rockFissuresWeight;
    private Integer updateManagerId;
    private Date updateTime;
}
