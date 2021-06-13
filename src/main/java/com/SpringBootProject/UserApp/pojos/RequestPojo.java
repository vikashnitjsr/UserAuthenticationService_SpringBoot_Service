package com.SpringBootProject.UserApp.pojos;

public class RequestPojo
{
    private String userID;
    private String deviceID;

    public RequestPojo(String userID, String deviceID) {
        this.userID = userID;
        this.deviceID = deviceID;
    }

    public RequestPojo() {
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
}
