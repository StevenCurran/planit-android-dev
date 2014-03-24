package com.planit.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.planit.Event;
import com.planit.R;
import com.planit.adapters.AttendeesArrayAdapter;

import java.text.SimpleDateFormat;


/**
 * Created by Gareth on 24/03/2014.
 */
public class EventDetailsActivity extends Activity {

    final Context context = this;
    private Bundle extras;
    private TextView eventName;
    private TextView eventLocation;
    private TextView eventDate;
    private TextView eventTime;
    private Button priorityIndicator;
    private AttendeesArrayAdapter adapter;
    private it.sephiroth.android.library.widget.HListView listview;
    private SimpleDateFormat tf = new SimpleDateFormat("EEEE dd MMMM yyyy");
    private SimpleDateFormat df = new SimpleDateFormat("kk:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_details);

        //set fonts
        Typeface uilFont = Typeface.createFromAsset(getAssets(), "fonts/segoeuisl.ttf");

        TextView screenTitle = (TextView) findViewById(R.id.screenTitle);
        screenTitle.setTypeface(uilFont);
        eventName = (TextView) findViewById(R.id.details_eventName);
        eventName.setTypeface(uilFont);
        eventLocation = (TextView) findViewById(R.id.details_eventLocation);
        eventLocation.setTypeface(uilFont);
        eventDate = (TextView) findViewById(R.id.details_eventDate);
        eventDate.setTypeface(uilFont);
        eventTime = (TextView) findViewById(R.id.details_eventTime);
        eventTime.setTypeface(uilFont);
        TextView attendeesTitle = (TextView) findViewById(R.id.attendeesTitle);
        attendeesTitle.setTypeface(uilFont);

        priorityIndicator = (Button) findViewById(R.id.detail_priorityIndicator);

        extras = getIntent().getExtras();
        if (extras != null) {
            int eventId = extras.getInt("eventId");
            setDetails(eventId);
        }

        listview = (it.sephiroth.android.library.widget.HListView) findViewById(R.id.details_attendeesList);

    }

    public void setDetails(int eventId) {

        //do server stuff to get details of event with eventId;
        Event e = new Event(); //change this to server object

        eventName.setText(e.getTitle());
        eventLocation.setText(e.getLocation());
        eventDate.setText(df.format(e.getStartDate()));
        eventTime.setText(tf.format(e.getStartDate()));

        switch (e.getPriority()) {
            case 1:
                priorityIndicator.setBackgroundResource(R.drawable.priority_one_button);
                break;
            case 2:
                priorityIndicator.setBackgroundResource(R.drawable.priority_two_button);
                break;
            case 3:
                priorityIndicator.setBackgroundResource(R.drawable.priority_three_button);
                break;
            case 4:
                priorityIndicator.setBackgroundResource(R.drawable.priority_four_button);
                break;
            case 5:
                priorityIndicator.setBackgroundResource(R.drawable.priority_five_button);
                break;
        }

        adapter = new AttendeesArrayAdapter(context, e.getParticipants());
        listview.setAdapter(adapter);
    }

}