package com.fuswx.deformation_monitoring_system.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FixData {

    private Integer id;
    private Double roadwayAngle;
    private String roadwayShape;
    private Double roadwaySizeTotal;
    private Double pillarWidth;
    private Double g;
    private Double stratumThickness;
    private Double stratumAngle;
    private Double topRockLayers;
    private Double topRockThickness;
    private Double rockFissures;
    @DateTimeFormat(pattern = "yyyy年MM月dd日 HH:mm:ss")
    private Date FixTime;
    private Integer FixManagerId;

}
