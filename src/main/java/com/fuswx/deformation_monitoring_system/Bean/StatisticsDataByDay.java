package com.fuswx.deformation_monitoring_system.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StatisticsDataByDay {
    private Integer id;
    private Date time;
    private Double data;
    private String remain;
    private Date finishedBy;
}
