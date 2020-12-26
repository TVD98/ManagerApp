package com.example.managerapp.models;

public class Code {
    private String id;
    private String userName;
    private int usedCount;

    public Code() {

    }

    public Code(String id) {
        this.id = id;
        this.userName = "";
        this.usedCount = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(int usedCount) {
        this.usedCount = usedCount;
    }
}
