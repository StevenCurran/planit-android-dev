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
import com.doomonafireball.betterpickers.hmspicker.HmsPicker;
import com.doomonafireball.betterpickers.hmspicker.HmsPickerBuilder;
import com.doomonafireball.betterpickers.hmspicker.HmsPickerDialogFragment;
import com.doomonafireball.betterpickers.radialtimepicker.RadialPickerLayout;
import com.doomonafireball.betterpickers.radialtimepicker.RadialTimePickerDialog;
import com.doomonafireball.betterpickers.timepicker.TimePickerBuilder;
import com.google.gson.Gson;
import com.planit.EventDuration;
import com.planit.R;
import com.planit.User;

import java.text.SimpleDateFormat;
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
    private RadialTimePickerDialog timePicker;
    private HmsPickerBuilder durationPicker;
    private TextView startDateTextView;
    private TextView endDateTextView;
    private TextView durationTextView;
    private TextView preferredTextView;
    private Date startWindow;
    private Date endWindow;
    private Date preferredTime;
    private EventDuration eventDuration;
    private SimpleDateFormat df = new SimpleDateFormat("E d MMM yyy");
    private SimpleDateFormat tf = new SimpleDateFormat("kk:mm");
    private int eventPriority;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);

        //set fonts
        Typeface uilFont = Typeface.createFromAsset(getAssets(), "fonts/segoeuisl.ttf");

        TextView title = (TextView) findViewById(R.id.screenTitle);
        title.setTypeface(uilFont);
        TextView dateRangeTitle = (TextView) findViewById(R.id.dateRangeTitle);
        dateRangeTitle.setTypeface(uilFont);
        TextView startHeaderText = (TextView) findViewById(R.id.startHeaderText);
        startHeaderText.setTypeface(uilFont);
        TextView endHeaderText = (TextView) findViewById(R.id.endHeaderText);
        endHeaderText.setTypeface(uilFont);
        TextView durationTimeHeader = (TextView) findViewById(R.id.durationTextheader);
        durationTimeHeader.setTypeface(uilFont);
        TextView preferredTimeHeader = (TextView) findViewById(R.id.preferredTimeHeader);
        preferredTimeHeader.setTypeface(uilFont);
        startDateTextView = (TextView) findViewById(R.id.startDateText);
        startDateTextView.setTypeface(uilFont);
        endDateTextView = (TextView) findViewById(R.id.endDateText);
        endDateTextView.setTypeface(uilFont);
        durationTextView = (TextView) findViewById(R.id.durationText);
        durationTextView.setTypeface(uilFont);
        preferredTextView = (TextView) findViewById(R.id.preferredTimeText);
        preferredTextView.setTypeface(uilFont);
        TextView priorityTitle = (TextView) findViewById(R.id.priorityTitle);
        priorityTitle.setTypeface(uilFont);
        TextView attendeesTitle = (TextView) findViewById(R.id.attendeesTitle);
        attendeesTitle.setTypeface(uilFont);
        EditText eventNameBox = (EditText) findViewById(R.id.eventNameBox);
        eventNameBox.setTypeface(uilFont);
        EditText eventLocationBox = (EditText) findViewById(R.id.eventLocationBox);
        eventLocationBox.setTypeface(uilFont);
        Button createEventButton = (Button) findViewById(R.id.createEventButton);
        createEventButton.setTypeface(uilFont);


        int year = Calendar.getInstance().get(Calendar.YEAR);
        startDpb = new DatePickerBuilder().setStyleResId(R.style.BetterPickersDialogFragment_Light).setYear(year).setFragmentManager(getSupportFragmentManager()).addDatePickerDialogHandler(START_WINDOW_HANDLER);
        endDpb = new DatePickerBuilder().setStyleResId(R.style.BetterPickersDialogFragment_Light).setYear(year).setFragmentManager(getSupportFragmentManager()).addDatePickerDialogHandler(END_WINDOW_HANDLER);
        durationPicker = new HmsPickerBuilder().setStyleResId(R.style.BetterPickersDialogFragment_Light);
        durationPicker.setFragmentManager(getSupportFragmentManager());
        durationPicker.addHmsPickerDialogHandler(EVENT_DURATION_HANDLER);
        Calendar instance = Calendar.getInstance();
        timePicker = RadialTimePickerDialog.newInstance(TIME_CALLBACK,  instance.get(Calendar.HOUR_OF_DAY), instance.get(Calendar.MINUTE), true);

    }


    public void openStartWindowPicker(View view) {
        startDpb.show();
    }

    public void openEndWindowPicker(View view) {
        endDpb.show();
    }

    public void openTimePicker(View view) {
        timePicker.show(getSupportFragmentManager(), "Pick Time");
    }

    public void openDurationPicker(View view) {
        durationPicker.show();
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

    private HmsPickerDialogFragment.HmsPickerDialogHandler EVENT_DURATION_HANDLER = new HmsPickerDialogFragment.HmsPickerDialogHandler() {

        final EventDuration e = new EventDuration();

        @Override
        public void onDialogHmsSet(int i, int i2, int i3, int i4) {
            e.setHours(i2);
            e.setMinutes(i3);

            eventDuration = e;

            String hoursString;
            String minutesString;
            String durationSeperator;

            //set hours string
            if(i2 == 0) {
                hoursString = "";
            } else if (i2 == 1) {
                hoursString = "1 hour";
            } else {
                hoursString = Integer.toString(i2) + " hours";
            }

            //set minutes string
            if(i3 == 0) {
                minutesString = "";
            } else if (i3 == 1) {
                minutesString = "1 minute";
            } else {
                minutesString = Integer.toString(i3) + " minutes";
            }

            //set duration seperator string
            if (i2 == 0 || i3 == 0) {
                durationSeperator = "";
            } else {
                durationSeperator = ", ";
            }

            durationTextView.setText(hoursString + durationSeperator + minutesString);
        }
    };

    private DatePickerDialogFragment.DatePickerDialogHandler START_WINDOW_HANDLER = new DatePickerDialogFragment.DatePickerDialogHandler() {

        final Calendar c = Calendar.getInstance();

        @Override
        public void onDialogDateSet(int i, int i2, int i3, int i4) {
            c.set(i2, i3, i4);
            startWindow = c.getTime();
            startDateTextView.setText(df.format(startWindow));
        }
    };

    private DatePickerDialogFragment.DatePickerDialogHandler END_WINDOW_HANDLER = new DatePickerDialogFragment.DatePickerDialogHandler() {

        final Calendar c = Calendar.getInstance();

        @Override
        public void onDialogDateSet(int i, int i2, int i3, int i4) {
            c.set(i2, i3, i4);
            endWindow = c.getTime();
            endDateTextView.setText(df.format(endWindow));
        }
    };

    private RadialTimePickerDialog.OnTimeSetListener TIME_CALLBACK = new RadialTimePickerDialog.OnTimeSetListener() {

        final Calendar c = Calendar.getInstance();

        @Override
        public void onTimeSet(RadialPickerLayout radialPickerLayout, int i, int i2) {
            c.set(0,0,0,i,i2);
            preferredTime = c.getTime();
            preferredTextView.setText(tf.format(preferredTime));
        }
    };

    public void setPriority(View view) {
        Button p1 = (Button) findViewById(R.id.priorityOneButton);
        Button p2 = (Button) findViewById(R.id.priorityTwoButton);
        Button p3 = (Button) findViewById(R.id.priorityThreeButton);
        Button p4 = (Button) findViewById(R.id.priorityFourButton);
        Button p5 = (Button) findViewById(R.id.priorityFiveButton);

        switch (view.getId()) {
            case R.id.priorityOneButton:
                //set button alpha values
                p1.setAlpha(1);
                p2.setAlpha((float) 0.5);
                p3.setAlpha((float) 0.5);
                p4.setAlpha((float) 0.5);
                p5.setAlpha((float) 0.5);

                //set priority value
                eventPriority = 1;
                break;
            case R.id.priorityTwoButton:
                //set button alpha values
                p1.setAlpha((float) 0.5);
                p2.setAlpha(1);
                p3.setAlpha((float) 0.5);
                p4.setAlpha((float) 0.5);
                p5.setAlpha((float) 0.5);

                //set priority value
                eventPriority = 2;
                break;
            case R.id.priorityThreeButton:
                //set button alpha values
                p1.setAlpha((float) 0.5);
                p2.setAlpha((float) 0.5);
                p3.setAlpha(1);
                p4.setAlpha((float) 0.5);
                p5.setAlpha((float) 0.5);

                //set priority value
                eventPriority = 3;
                break;
            case R.id.priorityFourButton:
                //set button alpha values
                p1.setAlpha((float) 0.5);
                p2.setAlpha((float) 0.5);
                p3.setAlpha((float) 0.5);
                p4.setAlpha(1);
                p5.setAlpha((float) 0.5);

                //set priority value
                eventPriority = 4;
                break;
            case R.id.priorityFiveButton:
                //set button alpha values
                p1.setAlpha((float) 0.5);
                p2.setAlpha((float) 0.5);
                p3.setAlpha((float) 0.5);
                p4.setAlpha((float) 0.5);
                p5.setAlpha(1);

                //set priority value
                eventPriority = 5;
                break;
        }
    }

}
