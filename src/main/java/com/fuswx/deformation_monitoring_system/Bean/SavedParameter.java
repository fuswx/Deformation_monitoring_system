package com.fuswx.deformation_monitoring_system.Bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavedParameter {
    private Integer id;
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("initDis")
    private Double initDis;
    @JsonProperty("alDis")
    private Long alDis;
    @JsonProperty("alDur")
    private Long alDur;
    @JsonProperty("accuDis")
    private Long accuDis;
    private Date time;
}
