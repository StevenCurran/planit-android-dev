package com.planit.utils;

import android.view.ViewDebug;

import com.planit.EventDuration;
import com.planit.Participant;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Steven Curran on 23/03/2014.
 */
public class UrlParamUtils {


    public static String addAttendees(ArrayList<Participant> attendees) {

        if (attendees == null)
            return "null";
        int iMax = attendees.size() - 1;
        if (iMax == -1)
            return "";

        StringBuilder b = new StringBuilder();
        for (int i = 0; ; i++) {
            b.append(attendees.get(i).getUserId());
            if (i == iMax)
                return b.toString();
            b.append(",");
        }

    }

    public static String addDate(Date startWindow) {
        //this needs to be done properly.
        Calendar c = Calendar.getInstance();
        c.setTime(startWindow);
        StringBuilder date = new StringBuilder();
        date.append(c.get(Calendar.YEAR));
        date.append(',');
        date.append(c.get(Calendar.MONTH) + 1);
        date.append(',');
        date.append(c.get(Calendar.DAY_OF_MONTH));
        date.append(',');
        date.append(c.get(Calendar.HOUR_OF_DAY));
        date.append(',');
        date.append(c.get(Calendar.MINUTE));

        return date.toString();
    }

    public static String addDuration(EventDuration eventDuration) {
        return "1";
    }
}
