package com.planit.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.planit.Notification;
import com.planit.R;

import java.util.ArrayList;

/**
 * Created by Gareth on 22/03/2014.
 */
public class NotificationsArrayAdapter extends ArrayAdapter<Notification> {

    private final Context context;
    public final ArrayList<Notification> notifications;

    public NotificationsArrayAdapter(Context context, ArrayList<Notification> notifications) {
        super(context, R.layout.notification_list_item, notifications);
        this.context = context;
        this.notifications = notifications;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.notification_list_item, parent, false);

        Typeface uilFont = Typeface.createFromAsset(context.getAssets(), "fonts/segoeuisl.ttf");

        //set text and font
        TextView notificationTitle = (TextView) rowView.findViewById(R.id.notificationTitle);
        TextView notificationDetails = (TextView) rowView.findViewById(R.id.notificationDetails);
        Button rejectButton = (Button) rowView.findViewById(R.id.rejectNotificationButton);
        Button rescheduleButton = (Button) rowView.findViewById(R.id.rescheduleNotificationButton);
        Button acceptButton = (Button) rowView.findViewById(R.id.acceptNotificationButton);

        notificationTitle.setText(notifications.get(position).getTitle());
        notificationTitle.setTypeface(uilFont);
        notificationDetails.setText(notifications.get(position).getDetails());
        notificationDetails.setTypeface(uilFont);
        rejectButton.setTypeface(uilFont);
        rescheduleButton.setTypeface(uilFont);
        acceptButton.setTypeface(uilFont);

        //set buttons depending on type of notification
        if (notifications.get(position).getTitle() == "Conflict")
        {
            notificationTitle.setTextColor(context.getResources().getColor(R.color.planit_red));
            rejectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rejectNotification();
                }
            });
            rescheduleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rescheduleNotification();
                }
            });
            acceptButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    acceptNotification();
                }
            });
        } else {
            rescheduleButton.setText("Acknowledge");
            rescheduleButton.setBackgroundResource(R.drawable.small_green_button);
            rescheduleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    acceptNotification();
                }
            });
            rejectButton.setVisibility(View.INVISIBLE);
            acceptButton.setVisibility(View.INVISIBLE);
        }

        return rowView;
    }

    public void rejectNotification() {
        //do server things
    }

    public void rescheduleNotification() {
        //do server things
    }

    public void acceptNotification() {
        //do server things
    }
}
