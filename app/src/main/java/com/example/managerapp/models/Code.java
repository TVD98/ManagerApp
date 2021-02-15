package com.example.managerapp.models;

import androidx.annotation.NonNull;

import com.example.managerapp.helper.Constraints;
import com.example.managerapp.helper.Utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Code implements Comparable<Code> {
    private String id;
    private String userName;
    private int usedCount;
    private String date;
    private String dateCreated;

    public Code(){

    }

    public Code(String id) {
        this.id = id;
        this.userName = "";
        this.usedCount = 0;
        this.dateCreated = Utility.localDateTimeToString(LocalDateTime.now().plusHours(-7));
        this.date = "";
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

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
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
            if(Integer.parseInt(dateCreated) < Integer.parseInt(o.dateCreated))
                return 1;
            return -1;
        }
        else if(usedCount < o.getUsedCount())
            return -1;
        return 1;
    }

    @NonNull
    @Override
    public String toString() {
        String message = String.format("Code: '%s'\nDate created: %s\nUser: '%s'\nDate used:%s",
                getId(),
                Utility.secondsToLocalDateTimeFormat(getDateCreated()),
                getUserName(),
                Utility.secondsToLocalDateTimeFormat(getDate()));
        return  message;
    }
}
