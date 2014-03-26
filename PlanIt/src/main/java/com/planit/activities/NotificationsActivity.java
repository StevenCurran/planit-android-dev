package com.planit.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.planit.Event;
import com.planit.EventWithConflicts;
import com.planit.Notification;
import com.planit.R;
import com.planit.adapters.NotificationsArrayAdapter;
import com.planit.constants.GlobalUserStore;
import com.planit.constants.UrlServerConstants;
import com.planit.utils.WebClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Gareth on 17/03/2014.
 */
public class NotificationsActivity extends Activity {

    final Context context = this;
    NotificationsArrayAdapter adapter;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications);

        //set fonts
        Typeface uilFont = Typeface.createFromAsset(getAssets(), "fonts/segoeuisl.ttf");

        Button scheduleButton = (Button) findViewById(R.id.scheduleButton);
        scheduleButton.setTypeface(uilFont);
        TextView title = (TextView) findViewById(R.id.screenTitle);
        title.setTypeface(uilFont);

        //set schedule button date
        scheduleButton.setText(getDayString());

        ListView listview = (ListView) findViewById(R.id.notificationsList);
        adapter = new NotificationsArrayAdapter(context, getNotifications());
        listview.setAdapter(adapter);
    }

    public ArrayList<Notification> getNotifications() {

        ArrayList<Notification> notifications = new ArrayList<>();

        RequestParams params = new RequestParams();
        params.put("userid", GlobalUserStore.getUser().getId());

        WebClient.get(UrlServerConstants.PENDING_EVENTS, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONArray responseArr) {
                List<EventWithConflicts> events = new ArrayList<>();

                try {
                    for (int i = 0; i < responseArr.length(); i++) {
                        JSONObject o1 = responseArr.getJSONObject(i);
                        JSONObject o = o1.getJSONObject("planitEvent");

                        Event e = new Event();
                        e.setTitle(o.getString("name"));
                        e.setId(o.getString("eventId"));
                        e.setLocation(o.getString("location"));
                        e.setPriority(o.getInt("priority"));
                        e.setStartDate(new Date(o.getLong("startDate")));
                        e.setEndDate(new Date(o.getLong("endDate")));

                        EventWithConflicts ewc = new EventWithConflicts(e);

                        JSONArray conflicts = o1.getJSONArray("conflictingEvents");
                        if (conflicts != null && conflicts.length() > 0) {
                            for (int j = 0; j < conflicts.length(); j++) {
                                Event e1 = new Event();
                                JSONObject o2 = conflicts.getJSONObject(j);
                                e1.setTitle(o2.getString("name"));
                                e1.setId(o2.getString("eventId"));
                                e1.setLocation(o2.getString("location"));
                                e1.setPriority(o2.getInt("priority"));
                                e1.setStartDate(new Date(o2.getLong("startDate")));
                                e1.setEndDate(new Date(o2.getLong("endDate")));
                                ewc.addConflict(e1);
                            }
                        }
                        events.add(ewc);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                List<Notification> nots = buildNotifications(events);

                adapter.clear();
                adapter.addAll(nots);

                adapter.clear();
                for (Notification not : nots) {
                    adapter.add(not);
                }
                adapter.notifyDataSetChanged();

            }
        });


        return notifications;
    }

    private List<Notification> buildNotifications(List<EventWithConflicts> events) {
        List<Notification> nots = new ArrayList<>();

        for (EventWithConflicts event : events) {
            Notification n = new Notification();
            n.setTitle("Conflict");

            String s = event.getPlanitEvent().getTitle() + "\n" + event.getPlanitEvent().getStartDate() + "\n\nConflicts:\n";

            for(Event e : event.getConflictingEvents())
            {
                s += "\n" + e.getTitle() + " @ " + e.getStartDate();
            }

            n.setDetails(s);
            nots.add(n);
        }

        return nots;
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

    public void goToSchedule(View view) {
        Intent intent = new Intent(context, ScheduleActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_right);
    }

}
