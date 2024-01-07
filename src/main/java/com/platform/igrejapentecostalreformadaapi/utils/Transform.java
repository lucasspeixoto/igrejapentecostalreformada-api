package com.platform.igrejapentecostalreformadaapi.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

public class Transform {

    public static String getTransformedDate(Date date) {
        DateFormat dateFormatted = new SimpleDateFormat();

        return dateFormatted
                .format(date.getTime())
                .split("-")[0]
                .split(" ")[0];
    }

    public static String getFirstName(String name) {
        return name.split(" ")[0];
    }
}
