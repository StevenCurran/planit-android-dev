package com.planit.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.planit.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Gareth on 17/03/2014.
 */
public class ScheduleActivity extends Activity {

    final Context context = this;
    CalendarView calView;
    TextView selectedDayTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule);

        //set fonts
        Typeface uilFont = Typeface.createFromAsset(getAssets(), "fonts/segoeuisl.ttf");

        Button scheduleButton = (Button) findViewById(R.id.scheduleButton);
        scheduleButton.setTypeface(uilFont);
        Button goToTodayButton = (Button) findViewById(R.id.goToTodayButton);
        goToTodayButton.setTypeface(uilFont);
        TextView title = (TextView) findViewById(R.id.screenTitle);
        title.setTypeface(uilFont);
        selectedDayTitle = (TextView) findViewById(R.id.selectedDayTitle);
        selectedDayTitle.setTypeface(uilFont);
        Button newEventButton = (Button) findViewById(R.id.newEventButton);
        newEventButton.setTypeface(uilFont);

        //set schedule button date
        scheduleButton.setText(getDayString());

        //set initial value of selectedDate
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd MMMM yyyy");
        String dateString = sdf.format(date);
        selectedDayTitle.setText(dateString);

        //set cal view
        calView = (CalendarView) findViewById(R.id.scheduleCal);
        calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i2, int i3) {
                Date selecteDate = new Date(i-1900,i2,i3);
                SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd MMMM yyyy");
                String dateString = sdf.format(selecteDate);
                selectedDayTitle.setText(dateString);
            }
        });

    }

    public void doNewEvent(View view) {
        //do new event
    }

    public void goToToday(View view) {
        long date = System.currentTimeMillis();
        calView.setDate(date);
    }

    public String getDayString() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        return Integer.toString(day);
    }

    public void goToProfile(View view) {
        Intent intent = new Intent(context, ProfileActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_right);
    }

    public void goToNotifications(View view) {
        Intent intent = new Intent(context, NotificationsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    //disabling back button on profile activity to prevent going back to login loading screen
    @Override
    public void onBackPressed() {
    }

}
