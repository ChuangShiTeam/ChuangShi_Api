package com.nowui.chuangshi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

public class DateUtil {

    public static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat cnDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
    public static final SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final String[] weekDaysName = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };

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
    
    public static Calendar getCal() {
        return Calendar.getInstance();
    }

    public static Calendar getCal(Date date) {
        return DateUtils.toCalendar(date);
    }

    /**
     * 获取当前年份
     * @return
     */
    public static String getYear() {
        return String.valueOf(getCal().get(Calendar.YEAR));
    }

    /**
     * 获取日期对应的年份
     * 
     * @param date
     * @return
     */
    public static String getYeay(Date date) {
        return String.valueOf(getCal(date).get(Calendar.YEAR));
    }
    
    /**
     * 获取当前季度
     * @return
     */
    public static String getQuarter() {
        int currentMonth = Integer.valueOf(getMonth());
        if (currentMonth >= 1 && currentMonth <= 3) {
            return "1"; 
        } else if (currentMonth >= 4 && currentMonth <= 6) {
            return "2";
        } else if (currentMonth >= 7 && currentMonth <= 9) {
            return "3"; 
        } else {
            return "4";
        }
    }

    /**
     * 获取日期对应的季度
     * 
     * @param date
     * @return
     */
    public static String getQuarter(Date date) {
        int currentMonth = Integer.valueOf(getMonth(date));
        if (currentMonth >= 1 && currentMonth <= 3) {
            return "1"; 
        } else if (currentMonth >= 4 && currentMonth <= 6) {
            return "2";
        } else if (currentMonth >= 7 && currentMonth <= 9) {
            return "3"; 
        } else {
            return "4";
        }
    }

    /**
     * 获取当前月份
     * @return
     */
    public static String getMonth() {
        return String.valueOf(getCal().get(Calendar.MONTH) + 1);
    }

    /**
     * 获取日期对应月份
     * 
     * @param date
     * @return
     */
    public static String getMonth(Date date) {
        return String.valueOf(getCal(date).get(Calendar.MONTH) + 1);
    }
    
    /**
     * 获取当前星期
     * @return
     */
    public static String getWeekofDate() {
        return weekDaysName[getCal().get(Calendar.DAY_OF_WEEK) - 1]; 
    }
    
    /**
     * 获取日期对应星期
     * @return
     */
    public static String getWeekofDate(Date date) {
        return weekDaysName[getCal(date).get(Calendar.DAY_OF_WEEK) - 1]; 
    }
    
    /**
     * 获取当前月对应天数
     * @return
     */
    public static String getDay() {
        return String.valueOf(getCal().get(Calendar.DAY_OF_MONTH));
    }
    
    /**
     * 获取日期对应天数
     * @param date
     * @return
     */
    public static String getDay(Date date) {
        return String.valueOf(getCal(date).get(Calendar.DAY_OF_MONTH));
    }
    
    /**
     * 获取当前时分
     * @return
     */
    public static String getHourMinute() {
        return DateFormatUtils.format(getCal(), "HH:mm");
    }

    /**
     * 获取两个日期相差的天数
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getNumberOfDays(Date startDate, Date endDate) {
        return (int) ChronoUnit.DAYS.between(startDate.toInstant(), endDate.toInstant());
    }
    
}
