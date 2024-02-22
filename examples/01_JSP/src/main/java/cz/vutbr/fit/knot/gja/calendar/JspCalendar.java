/*
 * Project: Examples for GJA course
 * Author: Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * Refactored: Bc. Vít Barták xbarta47@stud.fit.vutbr.cz
 * File: JspCalendar.java
 * Description: Calendar backbean
 */
/**
 * @file JspCalendar.java
 *
 * Calendar backbean
 */
package cz.vutbr.fit.knot.gja.calendar;

import java.util.Calendar;
import java.util.Date;

/**
 * Calendar backbean
 */
public class JspCalendar {

    /**
     * Calendar object
     */
    private Calendar calendar = null;

    /**
     * Constructor
     */
    public JspCalendar() {
        calendar = Calendar.getInstance();
        Date currentTime = new Date();
        calendar.setTime(currentTime);
    }

    /**
     * Get year
     *
     * @return Year
     */
    public int getYear() {
        return calendar.get(Calendar.YEAR);
    }

    /**
     * Get month
     *
     * @return Month (word)
     */
    public String getMonth() {
        int m = getMonthInt();
        String[] months = new String[]{"January", "February", "March",
            "April", "May", "June",
            "July", "August", "September",
            "October", "November", "December"};
        if (m > 12) {  // wrong month
            return "Unknown to Man";
        }
        return months[m - 1];
    }

    /**
     * Get day
     *
     * @return Day (as a word)
     */
    public String getDay() {
        int x = getDayOfWeek();
        String[] days = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday", "Saturday"};
        if (x > 7) {  // wrong day
            return "Unknown to Man";
        }
        return days[x - 1];
    }

    /**
     * Get month number
     *
     * @return Month number
     */
    public int getMonthInt() {
        return 1 + calendar.get(Calendar.MONTH);
    }

    /**
     * Get formatted date
     *
     * @return Formatted date
     */
    public String getDate() {
        return getMonthInt() + "/" + getDayOfMonth() + "/" + getYear();
    }

    /**
     * Get current date
     *
     * @return Current formatted date
     */
    public String getCurrentDate() {
        Date dt = new Date();
        calendar.setTime(dt);
        return getMonthInt() + "/" + getDayOfMonth() + "/" + getYear();

    }

    /**
     * Set calendar to next date and returns it
     *
     * @return Next date
     */
    public String getNextDate() {
        calendar.set(Calendar.DAY_OF_MONTH, getDayOfMonth() + 1);
        return getDate();
    }

    /**
     * Set calendar to previous date and returns it
     *
     * @return Previous date
     */
    public String getPrevDate() {
        calendar.set(Calendar.DAY_OF_MONTH, getDayOfMonth() - 1);
        return getDate();
    }

    /**
     * Get formatted time
     *
     * @return Formatted time
     */
    public String getTime() {
        return getHour() + ":" + getMinute() + ":" + getSecond();
    }

    /**
     * Get number of day of month
     *
     * @return Number of day of month
     */
    public int getDayOfMonth() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Get number of day in year
     *
     * @return Number of day in a year
     */
    public int getDayOfYear() {
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * Get number of week in year
     *
     * @return Number of week in year
     */
    public int getWeekOfYear() {
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * Get number of week in month
     *
     * @return Number of week in month
     */
    public int getWeekOfMonth() {
        return calendar.get(Calendar.WEEK_OF_MONTH);
    }

    /**
     * Get number of day in week
     *
     * @return Number of day in week
     */
    public int getDayOfWeek() {
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Get hour
     *
     * @return Hour
     */
    public int getHour() {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * Get minute
     *
     * @return Minute
     */
    public int getMinute() {
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * Get second
     *
     * @return Second
     */
    public int getSecond() {
        return calendar.get(Calendar.SECOND);
    }

    /**
     * Get era
     *
     * @return Era
     */
    public int getEra() {
        return calendar.get(Calendar.ERA);
    }

    /**
     * Get US time zone
     *
     * @return US time zone
     */
    public String getUSTimeZone() {
        String[] zones = new String[]{"Hawaii", "Alaskan", "Pacific",
            "Mountain", "Central", "Eastern"};

        return zones[10 + getZoneOffset()];
    }

    /**
     * Get time zone offset
     *
     * @return Time zone offset in hours
     */
    public int getZoneOffset() {
        return calendar.get(Calendar.ZONE_OFFSET) / (60 * 60 * 1000);
    }

    /**
     * Get daylight saving time offset
     *
     * @return Daylight saving time offset
     */
    public int getDSTOffset() {
        return calendar.get(Calendar.DST_OFFSET) / (60 * 60 * 1000);
    }

    /**
     * Get AM or PM
     *
     * @return AM or PM
     */
    public int getAMPM() {
        return calendar.get(Calendar.AM_PM);
    }
}  // public class JspCalendar
