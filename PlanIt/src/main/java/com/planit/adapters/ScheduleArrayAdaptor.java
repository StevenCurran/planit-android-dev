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
import com.planit.activities.EventDetailsActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Gareth on 17/03/2014.
 */
public class ScheduleArrayAdaptor extends ArrayAdapter<Event> {

    public final ArrayList<Event> events;
    private final Context context;

    public ScheduleArrayAdaptor(Context context, ArrayList<Event> events) {
        super(context, R.layout.schedule_list_item, events);
        this.context = context;
        this.events = events;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.schedule_list_item, parent, false);

        Typeface uilFont = Typeface.createFromAsset(context.getAssets(), "fonts/segoeuisl.ttf");

        //set text and font
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");

        TextView eventStartTime = (TextView) rowView.findViewById(R.id.eventStartTime);
        TextView eventEndTime = (TextView) rowView.findViewById(R.id.eventEndTime);
        TextView eventTitle = (TextView) rowView.findViewById(R.id.eventTitle);
        TextView eventLocation = (TextView) rowView.findViewById(R.id.eventLocation);

        Date startDate = events.get(position).getStartDate();
        eventStartTime.setText(sdf.format(startDate));
        eventStartTime.setTypeface(uilFont);

        Date endDate = events.get(position).getEndDate();
        eventEndTime.setText(sdf.format(endDate));
        eventEndTime.setTypeface(uilFont);

        eventTitle.setText(events.get(position).getTitle());
        eventTitle.setTypeface(uilFont);

        eventLocation.setText(events.get(position).getLocation());
        eventLocation.setTypeface(uilFont);

        //set priority indicator
        Button priorityIndicator = (Button) rowView.findViewById(R.id.priorityIndicator);
        switch (events.get(position).getPriority()) {
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

        return rowView;
    }

}
