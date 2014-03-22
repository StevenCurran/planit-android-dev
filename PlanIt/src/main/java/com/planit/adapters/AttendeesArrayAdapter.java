package com.planit.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.planit.Participant;
import com.planit.R;

import java.util.ArrayList;

/**
 * Created by Gareth on 22/03/2014.
 */
public class AttendeesArrayAdapter extends ArrayAdapter<Participant>{

    private final Context context;
    public final ArrayList<Participant> attendees;

    public AttendeesArrayAdapter(Context context, ArrayList<Participant> attendees) {
        super(context, R.layout.attendee_list_item, attendees);
        this.context = context;
        this.attendees = attendees;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.attendee_list_item, parent, false);

        Typeface uiFont = Typeface.createFromAsset(context.getAssets(), "fonts/segoeui.ttf");

        //set text and font
        TextView attendeeName = (TextView) rowView.findViewById(R.id.attendeeName);

        attendeeName.setText(attendees.get(position).getName());
        attendeeName.setTypeface(uiFont);

        //proper way to get user image when server stuff is in
//        ImageView attendeeImage = (ImageView) rowView.findViewById(R.id.attendeePicture);
//        attendeetImage.setImageDrawable(attendees.get(position).getImage());

        return rowView;
    }
}
