package com.planit.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.doomonafireball.betterpickers.datepicker.DatePickerBuilder;
import com.doomonafireball.betterpickers.datepicker.DatePickerDialogFragment;
import com.doomonafireball.betterpickers.hmspicker.HmsPickerBuilder;
import com.doomonafireball.betterpickers.hmspicker.HmsPickerDialogFragment;
import com.doomonafireball.betterpickers.radialtimepicker.RadialPickerLayout;
import com.doomonafireball.betterpickers.radialtimepicker.RadialTimePickerDialog;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.planit.Event;
import com.planit.EventDuration;
import com.planit.Participant;
import com.planit.R;
import com.planit.adapters.AttendeesArrayAdapter;
import com.planit.constants.UrlServerConstants;
import com.planit.utils.WebClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Gareth on 18/03/2014.
 */
public class AddEventActivity extends FragmentActivity {

    final Context context = this;
    private View.OnClickListener proceed_button_click_listener = new View.OnClickListener() {
        public void onClick(View v) {
            //server fings
            conflictsPopup.dismiss();
            Intent intent = new Intent(context, ScheduleActivity.class);
            startActivity(intent);
        }
    };
    EditText eventNameBox;
    EditText eventLocationBox;
    AttendeesArrayAdapter adapter;
    ArrayList<Participant> attendees = new ArrayList<>();
    Typeface uilFont;
    Typeface uiFont;
    private Bundle b = new Bundle();
    private Gson gson = new Gson();
    private DatePickerBuilder startDpb;
    private DatePickerBuilder endDpb;
    private RadialTimePickerDialog timePicker;
    private HmsPickerBuilder durationPicker;
    private TextView startDateTextView;
    private TextView endDateTextView;
    private TextView durationTextView;
    private TextView preferredTextView;
    private Date startWindow;
    private DatePickerDialogFragment.DatePickerDialogHandler START_WINDOW_HANDLER = new DatePickerDialogFragment.DatePickerDialogHandler() {

        final Calendar c = Calendar.getInstance();

        @Override
        public void onDialogDateSet(int i, int i2, int i3, int i4) {
            c.set(i2, i3, i4);
            startWindow = c.getTime();
            startDateTextView.setText(df.format(startWindow));
        }
    };
    private Date endWindow;
    private DatePickerDialogFragment.DatePickerDialogHandler END_WINDOW_HANDLER = new DatePickerDialogFragment.DatePickerDialogHandler() {

        final Calendar c = Calendar.getInstance();

        @Override
        public void onDialogDateSet(int i, int i2, int i3, int i4) {
            c.set(i2, i3, i4);
            endWindow = c.getTime();
            endDateTextView.setText(df.format(endWindow));
        }
    };
    private Date preferredTime;
    private RadialTimePickerDialog.OnTimeSetListener TIME_CALLBACK = new RadialTimePickerDialog.OnTimeSetListener() {

        final Calendar c = Calendar.getInstance();

        @Override
        public void onTimeSet(RadialPickerLayout radialPickerLayout, int i, int i2) {
            c.set(0, 0, 0, i, i2);
            preferredTime = c.getTime();
            preferredTextView.setText(tf.format(preferredTime));
        }
    };
    private EventDuration eventDuration;
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
            if (i2 == 0) {
                hoursString = "";
            } else if (i2 == 1) {
                hoursString = "1 hour";
            } else {
                hoursString = i2 + " hours";
            }

            //set minutes string
            if (i3 == 0) {
                minutesString = "";
            } else if (i3 == 1) {
                minutesString = "1 min";
            } else {
                minutesString = i3 + " mins";
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
    private SimpleDateFormat df = new SimpleDateFormat("E d MMM yyy");
    private SimpleDateFormat tf = new SimpleDateFormat("kk:mm");
    private int eventPriority;
    private PopupWindow conflictsPopup;
    private View.OnClickListener cancel_button_click_listener = new View.OnClickListener() {
        public void onClick(View v) {
            conflictsPopup.dismiss();
        }
    };

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);

        //set fonts
        uilFont = Typeface.createFromAsset(getAssets(), "fonts/segoeuisl.ttf");
        uiFont = Typeface.createFromAsset(getAssets(), "fonts/segoeui.ttf");

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
        eventNameBox = (EditText) findViewById(R.id.eventNameBox);
        eventNameBox.setTypeface(uilFont);
        eventLocationBox = (EditText) findViewById(R.id.eventLocationBox);
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
        timePicker = RadialTimePickerDialog.newInstance(TIME_CALLBACK, instance.get(Calendar.HOUR_OF_DAY), instance.get(Calendar.MINUTE), true);

        it.sephiroth.android.library.widget.HListView listview = (it.sephiroth.android.library.widget.HListView) findViewById(R.id.attendeesList);
        adapter = new AttendeesArrayAdapter(context, getAttendees());
        listview.setAdapter(adapter);

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

    private ArrayList<Participant> getAttendees() {

        final ArrayList<Participant> a = new ArrayList<>();

        //using code from add participants screen, just to test!!!!!!
        WebClient.get(UrlServerConstants.ATTENDEES, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONArray response) {

                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        Participant user = gson.fromJson(jsonObject.toString(), Participant.class);
                        user.setAttending(false);
                        a.add(user);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                attendees.clear();
                attendees.addAll(a);

                adapter.clear();
                for (Participant participant : a) {
                    adapter.add(participant);
                }
                adapter.notifyDataSetChanged();
            }


        });


        return attendees;

    }

    private void initiateConflictPopupWindow(String message) {
        try {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.event_conflict_popup, (ViewGroup) findViewById(R.layout.add_event), false);
            conflictsPopup = new PopupWindow(layout, 600, 400);
            conflictsPopup.showAtLocation(layout, Gravity.CENTER, 0, 0);

            TextView conflictTitle = (TextView) layout.findViewById(R.id.conflictTitle);
            conflictTitle.setTypeface(uilFont);
            TextView conflictDetails = (TextView) layout.findViewById(R.id.conflictDetails);
            conflictDetails.setTypeface(uilFont);
            conflictDetails.setText(message);

            Button proceedBtn = (Button) layout.findViewById(R.id.proceedWithEventCreationButton);
            proceedBtn.setTypeface(uilFont);
            proceedBtn.setOnClickListener(proceed_button_click_listener);
            Button cancelBtn = (Button) layout.findViewById(R.id.cancelEventCreationButtton);
            cancelBtn.setTypeface(uilFont);
            cancelBtn.setOnClickListener(cancel_button_click_listener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doCreateEvent(View view) {
        Event e = new Event();
        e.setTitle(eventNameBox.getText().toString());
        e.setLocation(eventLocationBox.getText().toString());
        e.setStartDate(startWindow);
        e.setEndDate(endWindow);
        e.setDuration(eventDuration);
        e.setPreferredTime(preferredTime);
        e.setPriority(eventPriority);
        e.setParticipants(attendees);

        //do server stuff - find if there if other people's schedules will have to be changed
        Boolean schedulesHaveToChange = true;

        if (schedulesHaveToChange) {
            String conflictMessage = "Gareth will have to reschedule a high priority event.\n\nAre you sure you want to continue?";
            initiateConflictPopupWindow(conflictMessage);

        } else {
            //add the event to server etc.
            Intent intent = new Intent(context, ScheduleActivity.class);
            startActivity(intent);
        }
    }
}
