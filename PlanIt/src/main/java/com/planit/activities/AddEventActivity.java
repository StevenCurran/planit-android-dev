package com.planit.activities;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.doomonafireball.betterpickers.calendardatepicker.CalendarDatePickerDialog;
import com.doomonafireball.betterpickers.hmspicker.HmsPickerBuilder;
import com.doomonafireball.betterpickers.hmspicker.HmsPickerDialogFragment;
import com.doomonafireball.betterpickers.radialtimepicker.RadialPickerLayout;
import com.doomonafireball.betterpickers.radialtimepicker.RadialTimePickerDialog;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.planit.Event;
import com.planit.EventDuration;
import com.planit.Participant;
import com.planit.QueryResponse;
import com.planit.R;
import com.planit.adapters.AttendeesArrayAdapter;
import com.planit.constants.GlobalUserStore;
import com.planit.constants.UrlServerConstants;
import com.planit.utils.UrlParamUtils;
import com.planit.utils.WebClient;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Gareth on 18/03/2014.
 */
public class AddEventActivity extends FragmentActivity {

    final Context context = this;
    EditText eventNameBox;
    EditText eventLocationBox;
    AttendeesArrayAdapter adapter;
    ArrayList<Participant> attendees = new ArrayList<>();
    Typeface uilFont;
    Typeface uiFont;
    DateFormat queryDF = new SimpleDateFormat("EE d MMMM yyy - kk:mm");
    private Bundle b = new Bundle();
    private Gson gson = new Gson();
    private CalendarDatePickerDialog startDpb;
    private CalendarDatePickerDialog endDpb;
    private RadialTimePickerDialog startTimePicker;
    private RadialTimePickerDialog endTimePicker;
    private HmsPickerBuilder durationPicker;
    private TextView startDateTextView;
    private TextView endDateTextView;
    private TextView durationTextView;
    private Button createEventButton;
    private Date startWindow;
    private CalendarDatePickerDialog.OnDateSetListener START_WINDOW_HANDLER = new CalendarDatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(CalendarDatePickerDialog calendarDatePickerDialog, int i, int i2, int i3) {
            final Calendar c = Calendar.getInstance();

            c.set(i, i2, i3);
            startWindow = c.getTime();
            //then open start date
            openStartTimePicker();
        }
    };

    private Date endWindow;
    private CalendarDatePickerDialog.OnDateSetListener END_WINDOW_HANDLER = new CalendarDatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(CalendarDatePickerDialog calendarDatePickerDialog, int i, int i2, int i3) {
            final Calendar c = Calendar.getInstance();

            c.set(i, i2, i3);
            endWindow = c.getTime();
            //then open start date
            openEndTimePicker();
        }
    };

    private Date startTime;
    private RadialTimePickerDialog.OnTimeSetListener START_TIME_CALLBACK = new RadialTimePickerDialog.OnTimeSetListener() {

        final Calendar c = Calendar.getInstance();

        @Override
        public void onTimeSet(RadialPickerLayout radialPickerLayout, int i, int i2) {
            c.set(0, 0, 0, i, i2);
            startTime = c.getTime();
            //generate a start date string
            startDateTextView.setText(df.format(startWindow) + " '" + dfy.format(startWindow) + " - " + tf.format(startTime));
            startWindow.setHours(startTime.getHours());
            startWindow.setMinutes(startTime.getMinutes());
        }
    };
    private Date endTime;
    private RadialTimePickerDialog.OnTimeSetListener END_TIME_CALLBACK = new RadialTimePickerDialog.OnTimeSetListener() {

        final Calendar c = Calendar.getInstance();

        @Override
        public void onTimeSet(RadialPickerLayout radialPickerLayout, int i, int i2) {
            c.set(0, 0, 0, i, i2);
            endTime = c.getTime();
            //generate an end date string
            endDateTextView.setText(df.format(endWindow) + " '" + dfy.format(endWindow) + " - " + tf.format(endTime));
            endWindow.setHours(endTime.getHours());
            endWindow.setMinutes(endTime.getMinutes());
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
    private SimpleDateFormat df = new SimpleDateFormat("d MMM");
    private SimpleDateFormat dfy = new SimpleDateFormat("yy");
    private SimpleDateFormat tf = new SimpleDateFormat("kk:mm");
    private int eventPriority;
    private View.OnClickListener proceed_button_click_listener = new View.OnClickListener() {
        public void onClick(View v) {


           // addEventToAndroidCal();

            Event e = new Event();
            e.setTitle(eventNameBox.getText().toString());
            e.setLocation(eventLocationBox.getText().toString());
            e.setStartDate(startWindow);
            e.setEndDate(endWindow);
            e.setDuration(eventDuration);
            e.setPriority(eventPriority);
            e.setParticipants(attendees);


            RequestParams params = new RequestParams();
            params.put("attendees", UrlParamUtils.addAttendees(attendees));
            params.put("startDate", UrlParamUtils.addDate(startWindow));
            params.put("endDate", UrlParamUtils.addDate(endWindow));
            params.put("userid", GlobalUserStore.getUser().getUserId());
            params.put("eventname", eventNameBox.getText().toString());

            WebClient.post(UrlServerConstants.ADD_EVENT, params, new AsyncHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    System.out.println("Succes!");
                    super.onSuccess(statusCode, headers, responseBody);
                }
            });


            creationResponsePopup.dismiss();
          //  Intent intent = new Intent(context, ScheduleActivity.class);
          //  startActivity(intent);
        }
    };
    private PopupWindow creationResponsePopup;
    private View.OnClickListener cancel_button_click_listener = new View.OnClickListener() {
        public void onClick(View v) {
            creationResponsePopup.dismiss();
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
        startDateTextView = (TextView) findViewById(R.id.startDateText);
        startDateTextView.setTypeface(uilFont);
        endDateTextView = (TextView) findViewById(R.id.endDateText);
        endDateTextView.setTypeface(uilFont);
        durationTextView = (TextView) findViewById(R.id.durationText);
        durationTextView.setTypeface(uilFont);
        TextView priorityTitle = (TextView) findViewById(R.id.priorityTitle);
        priorityTitle.setTypeface(uilFont);
        TextView attendeesTitle = (TextView) findViewById(R.id.attendeesTitle);
        attendeesTitle.setTypeface(uilFont);
        eventNameBox = (EditText) findViewById(R.id.eventNameBox);
        eventNameBox.setTypeface(uilFont);
        eventLocationBox = (EditText) findViewById(R.id.eventLocationBox);
        eventLocationBox.setTypeface(uilFont);
//        createEventButton = (Button) findViewById(R.id.createEventButton);
//        createEventButton.setTypeface(uilFont);
//        createEventButton.setOnClickListener(CREATE_EVENT_LISTENER);

        Button planitButton = (Button) findViewById(R.id.planitButton);
        planitButton.setTypeface(uilFont);

        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        startDpb = CalendarDatePickerDialog.newInstance(START_WINDOW_HANDLER, year, month, day);
        endDpb = CalendarDatePickerDialog.newInstance(END_WINDOW_HANDLER, year, month, day);
        durationPicker = new HmsPickerBuilder().setStyleResId(R.style.BetterPickersDialogFragment_Light);
        durationPicker.setFragmentManager(getSupportFragmentManager());
        durationPicker.addHmsPickerDialogHandler(EVENT_DURATION_HANDLER);
        Calendar instance = Calendar.getInstance();
        startTimePicker = RadialTimePickerDialog.newInstance(START_TIME_CALLBACK, instance.get(Calendar.HOUR_OF_DAY), instance.get(Calendar.MINUTE), true);
        endTimePicker = RadialTimePickerDialog.newInstance(END_TIME_CALLBACK, instance.get(Calendar.HOUR_OF_DAY), instance.get(Calendar.MINUTE), true);

        it.sephiroth.android.library.widget.HListView listview = (it.sephiroth.android.library.widget.HListView) findViewById(R.id.attendeesList);
        adapter = new AttendeesArrayAdapter(context, getAttendees());
        listview.setAdapter(adapter);


    }

    public void openStartWindowPicker(View view) {
        startDpb.show(getSupportFragmentManager(), "Pick Date");
    }

    public void openEndWindowPicker(View view) {
        endDpb.show(getSupportFragmentManager(), "Pick Date");
    }

    public void openStartTimePicker() {
        startTimePicker.show(getSupportFragmentManager(), "Pick Time");
    }

    public void openEndTimePicker() {
        endTimePicker.show(getSupportFragmentManager(), "Pick Time");
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

    private void initiateResponsePopupWindow(QueryResponse response) {
        try {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.event_creation_popup, (ViewGroup) findViewById(R.layout.add_event), false);
            creationResponsePopup = new PopupWindow(layout, 600, 400);
            creationResponsePopup.showAtLocation(layout, Gravity.CENTER, 0, 0);

            TextView creationTitle = (TextView) layout.findViewById(R.id.eventPopupTitle);
            creationTitle.setTypeface(uilFont);
            TextView bestTimeDetails = (TextView) layout.findViewById(R.id.bestTimeDetails);
            bestTimeDetails.setTypeface(uilFont);
            TextView additionalDetails = (TextView) layout.findViewById(R.id.additionalDetails);
            additionalDetails.setTypeface(uilFont);

            bestTimeDetails.setText(queryDF.format(response.getSuggestedDate()));

            if (response.getConflictingEvents().length > 0) {
                String message = "This date would cause " + response.getConflictingEvents().length + " people to reschedule other events.";
                additionalDetails.setText(message);
            } else {
                String message = "This date doesn't cause conflicts in anyone's schedule.";
                additionalDetails.setText(message);
            }

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

    public void doPlanit(View view) {

        //do server stuff - get response with a date and conflict IDs

        RequestParams params = new RequestParams();
        params.put("attendees", UrlParamUtils.addAttendees(attendees));
        params.put("startDate", UrlParamUtils.addDate(startWindow));
        params.put("endDate", UrlParamUtils.addDate(endWindow));
        params.put("userid", GlobalUserStore.getUser().getUserId());
        params.put("eventname", eventNameBox.getText().toString());
        params.put("duration", UrlParamUtils.addDuration(eventDuration));
        params.put("priority", 3+"");


        WebClient.post(UrlServerConstants.PLANIT, params, new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String s = new String(responseBody);

                System.out.println(s);
                //do things with the response.

                QueryResponse qr = new QueryResponse();
                qr.setSuggestedDate(new Date());
                qr.setConflictingEvents(new int[]{1});
                initiateResponsePopupWindow(qr);
            }
        });


    }

    public void addEventToAndroidCal() {
        Event e = new Event();
        e.setTitle(eventNameBox.getText().toString());
        e.setLocation(eventLocationBox.getText().toString());
        e.setStartDate(startWindow);
        e.setEndDate(endWindow);
        e.setDuration(eventDuration);
        e.setPriority(eventPriority);
        e.setParticipants(attendees);

        ContentResolver cr = getContentResolver();
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.DTSTART, startWindow.getTime());
        values.put(CalendarContract.Events.DTEND, endWindow.getTime());
        values.put(CalendarContract.Events.TITLE, eventNameBox.getText().toString());
        values.put(CalendarContract.Events.CALENDAR_ID, 1);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());
        Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);

        long eventID = Long.parseLong(uri.getLastPathSegment());

        for (Participant attendee : attendees) {

            ContentValues attendeeValues = new ContentValues();
            attendeeValues.put(CalendarContract.Attendees.ATTENDEE_NAME, attendee.getFirstName());
            attendeeValues.put(CalendarContract.Attendees.ATTENDEE_EMAIL, attendee.getEmail());
            attendeeValues.put(CalendarContract.Attendees.ATTENDEE_RELATIONSHIP, CalendarContract.Attendees.RELATIONSHIP_ATTENDEE);
            attendeeValues.put(CalendarContract.Attendees.ATTENDEE_TYPE, CalendarContract.Attendees.TYPE_OPTIONAL);
            attendeeValues.put(CalendarContract.Attendees.ATTENDEE_STATUS, CalendarContract.Attendees.ATTENDEE_STATUS_INVITED);
            attendeeValues.put(CalendarContract.Attendees.EVENT_ID, eventID);
            cr.insert(CalendarContract.Attendees.CONTENT_URI, attendeeValues);
        }


    }

    private int getCalenadarId() {

        String projection[] = {"_id", "calendar_displayName"};
        Uri calendars;
        calendars = Uri.parse("content://com.android.calendar/calendars");

        ContentResolver contentResolver = getContentResolver();
        Cursor managedCursor = contentResolver.query(calendars, projection, null, null, null);

        List<String> m_calendars = new ArrayList<>();

        if (managedCursor.moveToFirst()) {
            String calName;
            String calID;
            int cont = 0;
            int nameCol = managedCursor.getColumnIndex(projection[1]);
            int idCol = managedCursor.getColumnIndex(projection[0]);
            do {
                calName = managedCursor.getString(nameCol);
                calID = managedCursor.getString(idCol);
                m_calendars.add(calName + "," + calID);
                cont++;
            } while (managedCursor.moveToNext());
            managedCursor.close();
        }
        return Integer.parseInt(m_calendars.get(0).split(",")[1]);

    }

//    private View.OnClickListener CREATE_EVENT_LISTENER = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {

//        }
//    };
}
