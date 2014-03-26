package com.planit.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.planit.Event;
import com.planit.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Gareth on 26/03/2014.
 */
public class ConflictingEventsArrayAdaptor extends ArrayAdapter<Event> {

    public final ArrayList<Event> events;
    private final Context context;
    String eventId;

    public ConflictingEventsArrayAdaptor(Context context, ArrayList<Event> events) {
        super(context, R.layout.conflict_event_list_item, events);
        this.context = context;
        this.events = events;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.schedule_list_item, parent, false);

        Typeface uilFont = Typeface.createFromAsset(context.getAssets(), "fonts/segoeuisl.ttf");

        //set text and font
        SimpleDateFormat tf = new SimpleDateFormat("hh:mm a");

        TextView eventName = (TextView) rowView.findViewById(R.id.conflict_eventName);
        eventName.setTypeface(uilFont);
        TextView oldDate = (TextView) rowView.findViewById(R.id.oldEventDateAndTime);
        oldDate.setTypeface(uilFont);
        TextView newDate = (TextView) rowView.findViewById(R.id.newEventDateAndTime);
        newDate.setTypeface(uilFont);
        Button rejectButton = (Button) rowView.findViewById(R.id.rejectNewDateButton);
        rejectButton.setTypeface(uilFont);
        Button acceptButton = (Button) rowView.findViewById(R.id.acceptNewDateButton);
        acceptButton.setTypeface(uilFont);

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventId = events.get(position).getId();
                //accept the new time and update the server

                //remove the event from the list
            }
        });

        rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventId = events.get(position).getId();
                //reject the new time and update the server

                //remove the event from the list
            }
        });

        return rowView;
    }


}
