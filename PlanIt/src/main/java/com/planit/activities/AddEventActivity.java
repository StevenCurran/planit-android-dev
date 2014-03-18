package com.planit.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.doomonafireball.betterpickers.datepicker.DatePickerBuilder;
import com.doomonafireball.betterpickers.datepicker.DatePickerDialogFragment;
import com.google.gson.Gson;
import com.planit.R;
import com.planit.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Gareth on 18/03/2014.
 */
public class AddEventActivity extends FragmentActivity {

    final Context context = this;
    private Bundle b = new Bundle();
    private Gson gson = new Gson();
    private List<User> attendees = new ArrayList<>();
    private DatePickerBuilder startDpb;
    private DatePickerBuilder endDpb;

    private Date startWindow;
    private Date endWindow;

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

        int year = Calendar.getInstance().get(Calendar.YEAR);
        startDpb = new DatePickerBuilder().setStyleResId(R.style.BetterPickersDialogFragment_Light).setYear(year).setFragmentManager(getSupportFragmentManager()).addDatePickerDialogHandler(START_WINDOW_HANDLER);
        endDpb = new DatePickerBuilder().setStyleResId(R.style.BetterPickersDialogFragment_Light).setYear(year).setFragmentManager(getSupportFragmentManager()).addDatePickerDialogHandler(END_WINDOW_HANDLER);

    }


    public void openStartWindowPicker(View view) {
        startDpb.show();
    }

    public void openEndWindowPicker(View view) {
        endDpb.show();
    }

    public void addAttendees(View view) {
        Intent intent = new Intent(context, AddParticipantActivity.class);
        //startActivity(intent);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            //ArrayList<Participant> attendees = (ArrayList<Participant>) data.getParcelableArrayListExtra("attendees");

        }
        ///get the result of the selection of the contacts

    }

    public void doCreateEvent(View view) {
        //create the event and scheduling stuff - might want to go to view showing
        //free slots? or are we doing this automated way, it reschedules were nessecary?
    }

    private DatePickerDialogFragment.DatePickerDialogHandler START_WINDOW_HANDLER = new DatePickerDialogFragment.DatePickerDialogHandler() {

        final Calendar c = Calendar.getInstance();

        @Override
        public void onDialogDateSet(int i, int i2, int i3, int i4) {
            c.set(i2, i3, i4);
            startWindow = c.getTime();
        }
    };

    private DatePickerDialogFragment.DatePickerDialogHandler END_WINDOW_HANDLER = new DatePickerDialogFragment.DatePickerDialogHandler() {

        final Calendar c = Calendar.getInstance();

        @Override
        public void onDialogDateSet(int i, int i2, int i3, int i4) {
            c.set(i2, i3, i4);
            endWindow = c.getTime();
        }
    };


}
