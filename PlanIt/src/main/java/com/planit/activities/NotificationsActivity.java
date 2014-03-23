package com.planit.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.planit.Notification;
import com.planit.R;
import com.planit.adapters.NotificationsArrayAdapter;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Gareth on 17/03/2014.
 */
public class NotificationsActivity extends Activity {

    final Context context = this;
    NotificationsArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications);

        //set fonts
        Typeface uilFont = Typeface.createFromAsset(getAssets(), "fonts/segoeuisl.ttf");

        Button scheduleButton = (Button) findViewById(R.id.scheduleButton);
        scheduleButton.setTypeface(uilFont);
        TextView title = (TextView) findViewById(R.id.screenTitle);
        title.setTypeface(uilFont);

        //set schedule button date
        scheduleButton.setText(getDayString());

        ListView listview = (ListView) findViewById(R.id.notificationsList);
        adapter = new NotificationsArrayAdapter(context, getNotifications());
        listview.setAdapter(adapter);
    }

    public ArrayList<Notification> getNotifications(){

        ArrayList<Notification> notifications = new ArrayList<>();

        //do server fings

        Notification n1 = new Notification();
        n1.setTitle("Conflict");
        n1.setDetails("Josh invited you to 'Group Meeting' on Wed 19 March at 10:00. " +
                        "You have a lower priority event at this time - 'Meeting with Ted'." );

        Notification n2 = new Notification();
        n2.setTitle("Conflict");
        n2.setDetails("Steven invited you to 'Android Dev' on Thur 2 April at 11:30. " +
                "You have a higher priority event at this time - 'Meeting with Fatih'." );

        Notification n3 = new Notification();
        n3.setTitle("New Invitation");
        n3.setDetails("Jay invited you to 'HPC Assignment Work' on Fri 21 March at 15:00." );

        Notification n4 = new Notification();
        n4.setTitle("New Invitation");
        n4.setDetails("Josh invited you to 'After Work Drinks' on Fri 21 March at 18:00." );

        notifications.add(n1);
        notifications.add(n2);
        notifications.add(n3);
        notifications.add(n4);

        return notifications;
    }


    public String getDayString() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);

        return Integer.toString(day);
    }

    public void goToProfile(View view) {
        Intent intent = new Intent(context, ProfileActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_right);
    }

    public void goToSchedule(View view) {
        Intent intent = new Intent(context, ScheduleActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_right);
    }

}
