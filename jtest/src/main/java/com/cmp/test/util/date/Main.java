package com.cmp.test.util.date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeField;
import org.junit.Test;

import java.util.Calendar;

/**
 * Created by YAN on 2015/10/13.
 */
public class Main {


    @Test
    public void test() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 1);
        System.out.println(calendar.getTimeInMillis());


        DateTime dateTime = new DateTime(2015, 10, 15, 0, 0, 0);
        System.out.println(dateTime.toString());

    }
}
