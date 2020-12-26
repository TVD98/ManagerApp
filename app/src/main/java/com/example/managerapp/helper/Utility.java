package com.example.managerapp.helper;

import java.util.UUID;

public class Utility {
    public static String randomString(){
        String randomCode = UUID.randomUUID().toString();
        String[] array = randomCode.split("-");
        return array[0];
    }
}
