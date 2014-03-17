package com.planit.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.BinaryHttpResponseHandler;
import com.planit.Participant;
import com.planit.R;
import com.planit.User;
import com.planit.adapters.ParticipantsArrayAdapter;
import com.planit.constants.GlobalUserStore;
import com.planit.utils.ImageTransformer;
import com.planit.utils.WebClient;

import java.util.ArrayList;

/**
 * Created by Gareth on 17/03/2014.
 */
public class AddParticipantActivity extends Activity {

    final Context context = this;
    private Bundle b = new Bundle();
    ParticipantsArrayAdapter adapter;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_participant);

        //set fonts
        Typeface uilFont = Typeface.createFromAsset(getAssets(), "fonts/segoeuisl.ttf");

        TextView title = (TextView) findViewById(R.id.screenTitle);
        title.setTypeface(uilFont);
        Button addParticipantsButton = (Button) findViewById(R.id.addParticipantsButton);
        addParticipantsButton.setTypeface(uilFont);

        ListView listview = (ListView) findViewById(R.id.participantsList);
        adapter = new ParticipantsArrayAdapter(context, getParticipants());
        listview.setAdapter(adapter);
    }

    private ArrayList<Participant> getParticipants(){

        //do server things here - get all available contacts and check whether they
        //have already been added to this event, need to include getting profile pics

        ArrayList<Participant> participants = new ArrayList<>();

        Participant testContact1 = new Participant();
        testContact1.setName("Steven Curran");
        testContact1.setEmail("scurran10@hotmail.com");
        testContact1.setAttending(false);

        Participant testContact2 = new Participant();
        testContact2.setName("Josh Lockhart");
        testContact2.setEmail("jlockhart92@qub.ac.uk");
        testContact2.setAttending(true);

        Participant testContact3 = new Participant();
        testContact3.setName("Stephen Madden");
        testContact3.setEmail("smadden_88@gmail.com");
        testContact3.setAttending(false);

        Participant testContact4 = new Participant();
        testContact4.setName("Jordan Tonni");
        testContact4.setEmail("jtonni27@qub.ac.uk");
        testContact4.setAttending(true);

        participants.add(testContact1);
        participants.add(testContact2);
        participants.add(testContact3);
        participants.add(testContact4);

        return participants;
    }


    public void doAddParticipants(View view) {
        //do fings
    }
}
