package com.dadagum.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertUtil {

    private ConvertUtil(){

    }

    /**
     * convert a formatted string like "yyyy:MM:dd hh:mm:ss" to Date type
     * @param formatStr
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String formatStr) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        simpleDateFormat.setLenient(false);
        return simpleDateFormat.parse(formatStr);
    }

    /**
     * convert a Date type date to formatted string like "yyyy:MM:dd hh:mm:ss"
     * @param date
     * @return
     */
    public static String DateToString(Date date){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        return sf.format(date);
    }
}
