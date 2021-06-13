package com.SpringBootProject.UserApp.model;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Component

public class UserAppDTO
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long statusID;   // auto generated
    private String status;   // hardcoded
    private String userID;   // entered by user
    private String deviceID; // entered by user

    private Timestamp loginTime;
    private Long loginTimeInMilliSec;
    private String jwt_token;
    private Timestamp expiryTime;

    public UserAppDTO(Long statusID, String status, String userID, String deviceID, Timestamp loginTime, Long loginTimeInMilliSec, String jwt_token, Timestamp expiryTime)
    {
        this.statusID = statusID;
        this.status = status;
        this.userID = userID;
        this.deviceID = deviceID;
        this.loginTime = loginTime;
        this.loginTimeInMilliSec = loginTimeInMilliSec;
        this.jwt_token = jwt_token;
        this.expiryTime = expiryTime;
    }

    public UserAppDTO()
    {

    }


    public Long getStatusID() {
        return statusID;
    }

    public void setStatusID(Long statusID) {
        this.statusID = statusID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    public String getJwt_token() {
        return jwt_token;
    }

    public void setJwt_token(String jwt_token) {
        this.jwt_token = jwt_token;
    }

    public Timestamp getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Timestamp expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Long getLoginTimeInMilliSec() {
        return loginTimeInMilliSec;
    }

    public void setLoginTimeInMilliSec(Long loginTimeInMilliSec) {
        this.loginTimeInMilliSec = loginTimeInMilliSec;
    }
}
