package com.SpringBootProject.UserApp.pojos;

public class BaseResponse<T>
{
   private String Status;
   private T data;

    public BaseResponse(String status, T data) {
        Status = status;
        this.data = data;
    }

    public BaseResponse()
    {

    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
