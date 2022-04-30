package com.fuswx.deformation_monitoring_system.Bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    @JsonProperty("userName")
    private String userName;
    @JsonProperty("password")
    private String password;
    private Date addTime;
    private Date updateTime;
    private String limits;

    public User(String userName, String password, String limits) {
        this.userName = userName;
        this.password = password;
        this.limits = limits;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(String userName, String password, Date addTime, String limits) {
        this.userName = userName;
        this.password = password;
        this.addTime = addTime;
        this.limits = limits;
    }

}
