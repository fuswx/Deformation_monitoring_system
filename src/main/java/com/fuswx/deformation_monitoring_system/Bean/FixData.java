package com.fuswx.deformation_monitoring_system.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FixData {

    private Integer id;
    private Double roadwayAngle;
    private String roadwayShape;
    private Double roadwaySizeTotal;
    private Double roadwaySize;
    private Double pillarWidth;
    private Double roadwayTotalTransform;
    private Date FixTime;
    private Integer FixManagerId;

}
