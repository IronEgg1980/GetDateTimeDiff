package com.example.mytestapp;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class GetDateTimeDiff {
    private static int mYear,mMonth,mDayOfMonth,mHour,mMinute;
    public static int[] getValues(long l1,long l2){
        Calendar c1 = new GregorianCalendar();
        Calendar c2 = new GregorianCalendar();
        c1.setTimeInMillis(l1);
        c2.setTimeInMillis(l2);
        return getValues(c1,c2);
    }
    public static int[] getValues(Date date1, Date date2){
        Calendar c1 = new GregorianCalendar();
        Calendar c2 = new GregorianCalendar();
        c1.setTime(date1);
        c2.setTime(date2);
        return getValues(c1,c2);
    }
    public static int[] getValues (Calendar calendar1,Calendar calendar2) {
        if(calendar1.getTimeInMillis()>calendar2.getTimeInMillis()){
            return null;
        }
        mYear = calendar2.get(Calendar.YEAR) - calendar1.get(Calendar.YEAR);
        mMonth = calendar2.get(Calendar.MONTH) -
                calendar1.get(Calendar.MONTH);
        mDayOfMonth = 0;
        mHour = calendar2.get(Calendar.HOUR_OF_DAY)-calendar1.get(Calendar.HOUR_OF_DAY);
        mMinute = calendar2.get(Calendar.MINUTE) - calendar1.get(Calendar.MINUTE);
        getMinute();
        getHour();
        getDayOfMonth(calendar1,calendar2);
        getMonth();
        return new int[]{mYear,mMonth,mDayOfMonth,mHour,mMinute};
    }

    private static void getMonth(){
        if(mMonth < 0){
            mMonth+=12;
            mYear--;
        }
    }
    private static void getDayOfMonth(Calendar calendar1,Calendar calendar2){
        int dayDiff = 0;
        int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
        int maxDay1 = calendar1.getActualMaximum(Calendar.DAY_OF_MONTH);
        int day2 = calendar2.get(Calendar.DAY_OF_MONTH);
        int maxDay2 =calendar2.getActualMaximum(Calendar.DAY_OF_MONTH);
        if(day1 == maxDay1){
            if(day2 == maxDay2){
                dayDiff =0+mDayOfMonth;
            }else{
                mMonth--;
                dayDiff = day2+mDayOfMonth;
            }
            if(dayDiff < 0){
                mMonth--;
                dayDiff = day2 - 1;
            }
        }else if(day2 == maxDay2){
            if(day1 >= day2){
                dayDiff = 0+mDayOfMonth;
                if(dayDiff < 0){
                    mMonth--;
                    dayDiff = day2 - 1;
                }
            }else{
                dayDiff = mDayOfMonth + maxDay2 - day1;
            }
        }else{
            if(day2 >= day1){
                dayDiff = day2 - day1+mDayOfMonth;
                if(dayDiff < 0){
                    mMonth --;
                    dayDiff = maxDay1 - day1 + day2 -1;
                }
            }else {
                mMonth--;
                dayDiff = maxDay1 - day1 + day2;
            }
        }
        mDayOfMonth = dayDiff;
    }
    private static void getHour(){
        if(mHour < 0){
            mDayOfMonth --;
            mHour+= 24;
        }
    }

    private static void getMinute(){
        if(mMinute<0) {
            mHour--;
            mMinute += 60;
        }
    }
}
