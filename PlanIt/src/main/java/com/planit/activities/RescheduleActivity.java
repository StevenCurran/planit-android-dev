package com.planit.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.planit.Event;
import com.planit.Notification;
import com.planit.R;
import com.planit.adapters.AttendeesArrayAdapter;
import com.planit.constants.UrlServerConstants;
import com.planit.utils.EventReader;
import com.planit.utils.WebClient;

import org.json.JSONObject;

import java.text.SimpleDateFormat;

/**
 * Created by Gareth on 26/03/2014.
 */
public class RescheduleActivity extends Activity {

    final Context context = this;
    private Bundle extras;
    private TextView eventName;
    private TextView eventLocation;
    private TextView eventDate;
    private TextView eventTime;
    private Button priorityIndicator;
    private TextView attendeesTitle;
    private TextView conflictsTitle;
    private ListView conflictingEventsList;
    private AttendeesArrayAdapter adapter;
    private it.sephiroth.android.library.widget.HListView attendeesListView;
    private SimpleDateFormat df = new SimpleDateFormat("EEEE dd MMMM yyyy");
    private SimpleDateFormat tf = new SimpleDateFormat("kk:mm");
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.reschedule);

        //set fonts
        Typeface uilFont = Typeface.createFromAsset(getAssets(), "fonts/segoeuisl.ttf");

        TextView screenTitle = (TextView) findViewById(R.id.screenTitle);
        screenTitle.setTypeface(uilFont);
        eventName = (TextView) findViewById(R.id.reschedule_eventName);
        eventName.setTypeface(uilFont);
        eventLocation = (TextView) findViewById(R.id.reschedule_eventLocation);
        eventLocation.setTypeface(uilFont);
        eventDate = (TextView) findViewById(R.id.reschedule_eventDate);
        eventDate.setTypeface(uilFont);
        eventTime = (TextView) findViewById(R.id.reschedule_eventTime);
        eventTime.setTypeface(uilFont);
        attendeesTitle = (TextView) findViewById(R.id.reschedule_attendeesTitle);
        attendeesTitle.setTypeface(uilFont);
        conflictsTitle = (TextView) findViewById(R.id.reschedule_conflictsTitle);
        conflictsTitle.setTypeface(uilFont);

        priorityIndicator = (Button) findViewById(R.id.reschedule_priorityIndicator);

        extras = getIntent().getExtras();
        if (extras != null) {
            String notificationId = extras.getString("notificationId");
            setDetails(notificationId);
        }

        attendeesListView = (it.sephiroth.android.library.widget.HListView) findViewById(R.id.details_attendeesList);
        conflictingEventsList = (ListView) findViewById(R.id.conflictingEventsList);
    }

    public void setDetails(String notificationId) {

        //get event info from server using eventId;
        final Event[] e = {new Event()}; //change this to server object and uncomment below

        RequestParams params = new RequestParams();
        params.put("notificationId", notificationId);

        WebClient.get(UrlServerConstants.GET_EVENT, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject response) {

                // need to use notification to get eventid??? and get conflicts array
                Notification n = new Notification();

                Event e = EventReader.read(response);

                eventName.setText(e.getTitle());
                eventLocation.setText(e.getLocation());
                eventDate.setText(df.format(e.getStartDate()));
                eventTime.setText(tf.format(e.getStartDate()) + " - " + tf.format(e.getEndDate()));

                switch (e.getPriority()) {
                    case 1:
                        priorityIndicator.setBackgroundResource(R.drawable.priority_one_button);
                        priorityIndicator.setText("1");
                        break;
                    case 2:
                        priorityIndicator.setBackgroundResource(R.drawable.priority_two_button);
                        priorityIndicator.setText("2");
                        break;
                    case 3:
                        priorityIndicator.setBackgroundResource(R.drawable.priority_three_button);
                        priorityIndicator.setText("3");
                        break;
                    case 4:
                        priorityIndicator.setBackgroundResource(R.drawable.priority_four_button);
                        priorityIndicator.setText("4");
                        break;
                    case 5:
                        priorityIndicator.setBackgroundResource(R.drawable.priority_five_button);
                        priorityIndicator.setText("5");
                        break;
                }

                adapter = new AttendeesArrayAdapter(context, e.getParticipants());
                attendeesListView.setAdapter(adapter);
                attendeesTitle.setText("Attendees");

                if (n.getConflicts().length > 0) {

                    //populate conflicts list with the conflicting events
                    //prob have to use server request to send each conflicitng event id, to get
                    //info of event

//                    adapter = new ConflictingEventsArrayAdapter(context, <list of conflicting event object>);
//                    conflictingEventsList.setAdapter(adapter);
//                    conflictsTitle.setText("Conflicting Events");

                }
            }

        });
    }



}
