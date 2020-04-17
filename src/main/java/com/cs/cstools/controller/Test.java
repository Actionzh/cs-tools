package com.cs.cstools.controller;

import java.util.Calendar;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();

        Date d = new Date();
        int week = 0;
        int day = 0;
        c.set(2000, 0, 3);

        System.out.println(c.get(Calendar.DAY_OF_WEEK_IN_MONTH));
        System.out.println(c.get(Calendar.DAY_OF_WEEK));
        int i = Calendar.DAY_OF_WEEK;
        if (i - 1 == 0) {
            i = 7;
        } else {
            i = i - 1;
        }
        int finalday = 0;
        if (week == 1) {
            if (day < i || day > 7) {
                System.out.println("error");
            } else {
                finalday = day - i + 1;
            }

        } else if (week > 5) {
            System.out.println("error");
        } else {
            int days = 7 - i + 1;
            finalday = (week - 2) * 7 + days + day;
        }


        System.out.println(c.getTime().toString());
        c.getMinimalDaysInFirstWeek();

    }
}
