package com.example.managerapp.models;

import com.example.managerapp.helper.Constraints;
import com.example.managerapp.helper.Utility;

import java.time.LocalDateTime;

public class Code implements Comparable<Code> {
    private String id;
    private String userName;
    private int usedCount;
    private String date;

    public Code(){

    }

    public Code(String id) {
        this.id = id;
        this.userName = "";
        this.usedCount = 0;
        this.date = Utility.localDateTimeToString(LocalDateTime.now().plusHours(-7));
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getState(){
        if(usedCount == 0){
            return "Ready";
        }
        return "Used";
    }

    public boolean isReady(){
        if(usedCount == 0)
            return true;
        return false;
    }

    public boolean isCorrectOnType(int type){
        if(type == Constraints.ALL_CODE)
            return true;
        if(type == Constraints.READY_CODE)
            return isReady();
        else return !isReady();
    }

    @Override
    public int compareTo(Code o) {
        if(usedCount == o.getUsedCount()){
            if(Integer.parseInt(date) < Integer.parseInt(o.date))
                return 1;
            return -1;
        }
        else if(usedCount < o.getUsedCount())
            return -1;
        return 1;
    }
}
