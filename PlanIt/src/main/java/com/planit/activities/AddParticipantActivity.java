package com.planit.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.planit.Participant;
import com.planit.R;
import com.planit.adapters.ParticipantsArrayAdapter;
import com.planit.constants.UrlServerConstants;
import com.planit.utils.WebClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gareth on 17/03/2014.
 */
public class AddParticipantActivity extends Activity {

    final Context context = this;
    ParticipantsArrayAdapter adapter;
    private Bundle b = new Bundle();
    private Gson gson = new Gson();
    ArrayList<Participant> participants = new ArrayList<>();


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

    private ArrayList<Participant> getParticipants() {

        final ArrayList<Participant> p = new ArrayList<>();

        //do server things here - get all available contacts and check whether they
        //have already been added to this event, need to include getting profile pics
        WebClient.get(UrlServerConstants.ATTENDEES, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONArray response) {

                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        Participant user = gson.fromJson(jsonObject.toString(), Participant.class);
                        user.setAttending(false);
                        p.add(user);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                participants.clear();
                participants.addAll(p);

                adapter.clear();
                for (Participant participant : p) {
                    adapter.add(participant);
                }
                adapter.notifyDataSetChanged();
            }


        });

        return participants;

    }


    public void doAddParticipants(View view) {

        //get all attending participants, update the event object(?)

    }
}
