/*史长顺*/
package com.example.farming.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {

    private static Calendar calendar = Calendar.getInstance();;

    private TimeUtils() {};

    public static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String dateString = dateFormat.format(date);
        return dateString == null ? "" : dateString;
    }
    public static Date formatString(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        Date dateString = dateFormat.parse(date);
        return dateString;
    }

    public static int getYear() {
        return calendar.get(Calendar.YEAR);
    }

    public static int getMonth(){
        return calendar.get(Calendar.MONTH);
    }

    public static int getDayOfMonth(){
        Date date = new Date();
        return Calendar.DAY_OF_MONTH;
    }

    public static int getHour() {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute() {
        return calendar.get(Calendar.MINUTE);
    }

    public static int setHour(int time) {
        return time / 60;
    }

    public static int setMinute(int time) {
        return time % 60;
    }

}
