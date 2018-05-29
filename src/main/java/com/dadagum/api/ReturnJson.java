package com.dadagum.api;

public class ReturnJson<T> {
    private T data;
    private String info;
    private boolean status;

    public ReturnJson() {
    }

    public ReturnJson(T data, String info, boolean status) {
        this.data = data;
        this.info = info;
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
