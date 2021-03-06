package com.example.managerapp.helper;

import android.bluetooth.BluetoothAdapter;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.NetworkInterface;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Utility {
    public static ZoneId zoneId = ZoneId.of("Asia/Ho_Chi_Minh");

    public static String randomString() {
        String randomCode = UUID.randomUUID().toString();
        String[] array = randomCode.split("-");
        return array[0];
    }

    public static String localDateTimeToString(LocalDateTime dateTime) {
        if (dateTime == null)
            return "";
        return Long.toString(dateTime.toEpochSecond(ZoneOffset.UTC));
    }

    public static LocalDateTime secondsToLocalDateTime(long seconds) {
        return Instant.ofEpochSecond(seconds).atZone(zoneId).toLocalDateTime();
    }

    public static LocalDateTime secondsToLocalDateTime(String seconds) {
        if (seconds.isEmpty())
            return null;
        long second = Long.parseLong(seconds);
        return secondsToLocalDateTime(second);
    }

    public static String secondsToLocalDateTimeFormat(String seconds) {
        if (seconds.isEmpty())
            return "";
        else {
            long second = Long.parseLong(seconds);
            return secondsToLocalDateTimeFormat(second);
        }
    }

    public static String secondsToLocalDateTimeFormat(long seconds) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return secondsToLocalDateTime(seconds).format(format);
    }


}
