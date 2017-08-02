package com.nowui.chuangshi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat cnDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
    public static final SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

    public static String getDateString(Date dateTime) {
        return dateFormat.format(dateTime);
    }

    public static String getCNDateString(Date dateTime) {
        return dateFormat.format(cnDateFormat);
    }

    public static String getDateTimeString(Date dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTimeFormat.format(dateTime);
    }

    public static Date getDateTime(String dateTime) {
        try {
            return dateTimeFormat.parse(dateTime);
        } catch (ParseException e) {
            throw new RuntimeException("时间格式不正确");
        }
    }

    public static Date getStartDateTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    public static Date getEndDateTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }

    public static Date getNowYearLastDay() {
        Calendar cal = Calendar.getInstance();

        // 设置年份
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
        // 设置月份
        cal.set(Calendar.MONTH, 12 - 1);
        // 获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        // 设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        System.out.println("获取当前月的最后一天：" + cal.getTime());

        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // String lastDayOfMonth = sdf.format(cal.getTime());
        // System.out.println("获取当前月的最后一天：" + lastDayOfMonth);

        return cal.getTime();
    }

}
