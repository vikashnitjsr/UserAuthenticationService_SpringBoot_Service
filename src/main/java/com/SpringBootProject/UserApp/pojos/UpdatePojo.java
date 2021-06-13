package com.SpringBootProject.UserApp.pojos;

public class UpdatePojo
{

    private String oldUserID;
    private String newUserID;
    private String deviceID;

    public UpdatePojo(String oldUserID, String newUserID, String deviceID) {
        this.oldUserID = oldUserID;
        this.newUserID = newUserID;
        this.deviceID = deviceID;
    }

    public UpdatePojo() {
    }

    public String getOldUserID() {
        return oldUserID;
    }

    public void setOldUserID(String oldUserID) {
        this.oldUserID = oldUserID;
    }

    public String getNewUserID() {
        return newUserID;
    }

    public void setNewUserID(String newUserID) {
        this.newUserID = newUserID;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }
}
