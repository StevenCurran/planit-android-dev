package com.planit.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.planit.Participant;
import com.planit.R;

import java.util.ArrayList;

/**
 * Created by Gareth on 17/03/2014.
 */
public class ParticipantsArrayAdapter extends ArrayAdapter<Participant> {

    public final ArrayList<Participant> participants;
    private final Context context;

    public ParticipantsArrayAdapter(Context context, ArrayList<Participant> participants) {
        super(context, R.layout.participant_list_item, participants);
        this.context = context;
        this.participants = participants;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.participant_list_item, parent, false);

        Typeface uilFont = Typeface.createFromAsset(context.getAssets(), "fonts/segoeuisl.ttf");

        //set text and font
        TextView participantName = (TextView) rowView.findViewById(R.id.participantName);
        TextView participantEmail = (TextView) rowView.findViewById(R.id.participantEmail);

        participantName.setText(participants.get(position).getName());
        participantName.setTypeface(uilFont);
        participantEmail.setText(participants.get(position).getEmail());
        participantEmail.setTypeface(uilFont);

        //set participant image - TEMPORARY - this need to be done in AddParticipantsActivity
        //- in getParticipants - just doing this to sort layout - remove this later and chnage
        //to commented out code below
        ImageView participantImage = (ImageView) rowView.findViewById(R.id.participantImage);
        participantImage.setImageResource(R.drawable.default_user_photo);

        //proper way to get user image when server stuff is in
//        ImageView participantImage = (ImageView) rowView.findViewById(R.id.participantImage);
//        participantImage.setImageDrawable(participants.get(position).getImage());

        //set attending button status
        ToggleButton attendingToggleButton = (ToggleButton) rowView.findViewById(R.id.attendingToggleButton);
        attendingToggleButton.setChecked(participants.get(position).getAttending());
        attendingToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doChangeAttendingStatus(view);
            }
        });

        return rowView;
    }

    public void doChangeAttendingStatus(View view) {
        //change status
    }
}

