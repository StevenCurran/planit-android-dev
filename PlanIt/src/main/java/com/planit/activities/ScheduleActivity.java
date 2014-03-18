package com.planit.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.planit.Event;
import com.planit.R;
import com.planit.adapters.ScheduleArrayAdaptor;
import com.planit.constants.UrlServerConstants;
import com.planit.utils.WebClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

/**
 * Created by Gareth on 17/03/2014.
 */
public class ScheduleActivity extends Activity {

    final Context context = this;
    CalendarView calView;
    TextView selectedDayTitle;
    ScheduleArrayAdaptor adapter;
    ListView listview;
    SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd MMMM yyyy");

    private ConcurrentHashMap<Date, ArrayList<Event>> eventsMap = new ConcurrentHashMap<>();
    private Semaphore lock = new Semaphore(1);

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

        //set initial value of selected date title
        Date todaysDate = new Date();
        selectedDayTitle.setText(sdf.format(todaysDate));

        //get schedule for today
        listview = (ListView) findViewById(R.id.selectedDaySchedule);
        adapter = new ScheduleArrayAdaptor(context, getSchedule(todaysDate));
        listview.setAdapter(adapter);

        //do calendar view stuff and set onClick
        calView = (CalendarView) findViewById(R.id.scheduleCal);
        calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i2, int i3) {
                Date selectedDate = new Date(i - 1900, i2, i3);
                //set title
                selectedDayTitle.setText(sdf.format(selectedDate));
                //update schedule list
                adapter = new ScheduleArrayAdaptor(context, filterSchedule(selectedDate));
                listview.setAdapter(adapter);
            }
        });


    }

    public void doNewEvent(View view) {
        //do new event
    }

    private ArrayList<Event> getSchedule(Date date) {

        final ArrayList<Event> events = new ArrayList<>();

        WebClient.get(UrlServerConstants.GOOGLE_EVENTS, null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(JSONArray response) {


                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject o = response.getJSONObject(i);
                        Event e = new Event();
                        e.setId(o.getInt("id"));
                        events.add(e);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

        //do server things here - get all available events for selected day


        Event testEvent = new Event();
        testEvent.setStartDate(new Date(114, 2, 28, 9, 0));
        testEvent.setEndDate(new Date(114, 2, 28, 10, 30));
        testEvent.setTitle("Meeting with Barry");
        testEvent.setLocation("BCB Room 04/001");
        testEvent.setPriority(1);

        Event testEvent2 = new Event();
        testEvent2.setStartDate(new Date(114, 2, 28, 13, 0));
        testEvent2.setEndDate(new Date(114, 2, 28, 16, 00));
        testEvent2.setTitle("Group Work");
        testEvent2.setLocation("16 Malone Road");
        testEvent2.setPriority(3);

        Event testEvent3 = new Event();
        testEvent3.setStartDate(new Date(114, 2, 28, 17, 0));
        testEvent3.setEndDate(new Date(114, 2, 28, 18, 00));
        testEvent3.setTitle("HPC Assignment");
        testEvent3.setLocation("Home");
        testEvent3.setPriority(2);

        Event testEvent4 = new Event();
        testEvent4.setStartDate(new Date(114, 2, 28, 18, 30));
        testEvent4.setEndDate(new Date(114, 2, 28, 21, 00));
        testEvent4.setTitle("Tennis");
        testEvent4.setLocation("Newry Tennis Club");
        testEvent4.setPriority(4);

        Event testEvent5 = new Event();
        testEvent5.setStartDate(new Date(114, 2, 28, 22, 0));
        testEvent5.setEndDate(new Date(114, 2, 28, 23, 00));
        testEvent5.setTitle("Reading Work");
        testEvent5.setLocation("Home");
        testEvent5.setPriority(5);

        events.add(testEvent);
        events.add(testEvent2);
        events.add(testEvent3);
        events.add(testEvent4);
        events.add(testEvent5);

        return events;
    }

    private ArrayList<Event> filterSchedule(Date selectedDate) {
        return eventsMap.get(selectedDate);
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
