package com.example.managerapp.models;

import androidx.annotation.NonNull;

import com.example.managerapp.helper.Constraints;
import com.example.managerapp.helper.Utility;

public class Code implements Comparable<Code> {
    private String id;
    private String userName;
    private String date;
    private String dateAdded;

    public Code(){

    }

    public Code(String id) {
        this.id = id;
        this.userName = "";
        this.dateAdded = "";
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

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public boolean isReady(){
        if(userName.isEmpty())
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
    public int compareTo(Code other) {
        if(isReady() == other.isReady()){
            if(Integer.parseInt(dateAdded) < Integer.parseInt(other.dateAdded))
                return 1;
            return -1;
        }
        else if(isReady() && !other.isReady())
            return -1;
        return 1;
    }

    @NonNull
    @Override
    public String toString() {
        String message = String.format("Code: '%s'\nDate created: %s\nUser: '%s'\nDate used:%s",
                getId(),
                Utility.secondsToLocalDateTimeFormat(getDateAdded()),
                getUserName(),
                Utility.secondsToLocalDateTimeFormat(getDate()));
        return  message;
    }
}
