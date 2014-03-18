package com.planit.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.planit.R;

/**
 * Created by Gareth on 18/03/2014.
 */
public class AddEventActivity extends Activity {

    final Context context = this;
    private Bundle b = new Bundle();

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);

        //set fonts
        Typeface uilFont = Typeface.createFromAsset(getAssets(), "fonts/segoeuisl.ttf");

        TextView title = (TextView) findViewById(R.id.screenTitle);
        title.setTypeface(uilFont);
        TextView priorityTitle = (TextView) findViewById(R.id.priorityTitle);
        priorityTitle.setTypeface(uilFont);
        TextView attendeesTitle = (TextView) findViewById(R.id.attendeesTitle);
        attendeesTitle.setTypeface(uilFont);
        TextView tagsTitle = (TextView) findViewById(R.id.tagsTitle);
        tagsTitle.setTypeface(uilFont);
        EditText eventNameBox = (EditText) findViewById(R.id.eventNameBox);
        eventNameBox.setTypeface(uilFont);
        EditText eventLocationBox = (EditText) findViewById(R.id.eventLocationBox);
        eventLocationBox.setTypeface(uilFont);
        Button createEventButton = (Button) findViewById(R.id.createEventButton);
        createEventButton.setTypeface(uilFont);

    }

    public void addAttendees(View view) {
        Intent intent = new Intent(context, AddParticipantActivity.class);
        startActivity(intent);
    }

    public void doCreateEvent(View view) {
        //create the event and scheduling stuff - might want to go to view showing
        //free slots? or are we doing this automated way, it reschedules were nessecary?
    }
}
