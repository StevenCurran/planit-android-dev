package com.planit.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.planit.Participant;
import com.planit.R;
import com.planit.utils.ImageTransformer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Gareth on 22/03/2014.
 */
public class AttendeesArrayAdapter extends ArrayAdapter<Participant> {

    public final ArrayList<Participant> attendees;
    private final Context context;
    private ImageTransformer imageTransformer = new ImageTransformer();

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

        if (attendees != null && attendees.size() > 0) {

            try {
                attendeeName.setText(attendees.get(position).getFirstName() + " " + attendees.get(position).getLastName().substring(0, 1) + ".");
                attendeeName.setTypeface(uiFont);

                //proper way to get user image when server stuff is in
                ImageView attendeeImage = (ImageView) rowView.findViewById(R.id.attendeePicture);
                Picasso.with(context).load(attendees.get(position).getImageUrl()).transform(imageTransformer).error(R.drawable.default_user_photo).into(attendeeImage);


            } catch (Exception e) {
                //unhandled
            }


        }
        return rowView;
    }
}
