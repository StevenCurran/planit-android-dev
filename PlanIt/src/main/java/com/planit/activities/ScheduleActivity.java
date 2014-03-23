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
import java.util.Collections;
import java.util.Date;
import java.util.List;

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

    private List<Event> eventsMap = new ArrayList<>();

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
        calView.setWeekDayTextAppearance(R.style.planit_cal_header_text);
        calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i2, int i3) {
                Date selectedDate = new Date(i - 1900, i2, i3);
                //set title
                selectedDayTitle.setText(sdf.format(selectedDate));
                //update schedule list
                //adapter = new ScheduleArrayAdaptor(context, filterSchedule(selectedDate));
                adapter.clear();
                for (Event event : filterSchedule(selectedDate)) {
                    adapter.add(event);
                }
                adapter.notifyDataSetChanged();

            }
        });


    }

    public void doNewEvent(View view) {
        Intent intent = new Intent(context, AddEventActivity.class);
        startActivity(intent);
    }

    private synchronized ArrayList<Event> getSchedule(Date date) {

        final ArrayList<Event> events = new ArrayList<>();
        final Date localDate = date;


        WebClient.get(UrlServerConstants.GOOGLE_EVENTS, null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(JSONArray response) {


                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject o = response.getJSONObject(i);
                        Event e = new Event();
                        e.setLocation(o.getString("location"));
                        //add in desc
                        e.setPriority(o.getInt("priority"));
                        e.setStartDate(new Date(o.getLong("startDate")));
                        e.setEndDate(new Date(o.getLong("endDate")));
                        e.setTitle(o.getString("name"));

                        events.add(e);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                eventsMap.addAll(events);

                java.util.Calendar cal = java.util.Calendar.getInstance();
                cal.add(java.util.Calendar.DATE, 1);
                calView.setDate(cal.getTime().getTime());
                calView.setDate(new Date().getTime());


            }
        });

        return events;

    }

    private ArrayList<Event> filterSchedule(Date selectedDate) {
        ArrayList<Event> returnEvents = new ArrayList<>();
        for (Event event : eventsMap) {
            if (sdf.format(event.getStartDate()).equals(sdf.format(selectedDate))) {
                returnEvents.add(event);
            }
        }

        Collections.sort(returnEvents);
        return returnEvents;
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
