package com.example.managerapp.helper;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.UUID;

public class Utility {
    public static ZoneId zoneId = ZoneId.of("Asia/Ho_Chi_Minh");
    public static String randomString(){
        String randomCode = UUID.randomUUID().toString();
        String[] array = randomCode.split("-");
        return array[0];
    }

    public static String localDateTimeToString(LocalDateTime dateTime){
        return Long.toString(dateTime.toEpochSecond(ZoneOffset.UTC));
    }

    public static LocalDateTime secondsToLocalDateTime(long seconds){
        return Instant.ofEpochSecond(seconds).atZone(zoneId).toLocalDateTime();
    }

    public static LocalDateTime secondsToLocalDateTime(String seconds) {
        long second = Long.parseLong(seconds);
        return secondsToLocalDateTime(second);
    }


}
