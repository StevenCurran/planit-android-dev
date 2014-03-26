package com.planit.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.planit.Notification;
import com.planit.R;
import com.planit.activities.EventDetailsActivity;
import com.planit.activities.RescheduleActivity;

import java.util.ArrayList;

import javax.xml.transform.Source;

/**
 * Created by Gareth on 22/03/2014.
 */
public class NotificationsArrayAdapter extends ArrayAdapter<Notification> {

    public final ArrayList<Notification> notifications;
    private final Context context;

    public NotificationsArrayAdapter(Context context, ArrayList<Notification> notifications) {
        super(context, R.layout.notification_list_item, notifications);
        this.context = context;
        this.notifications = notifications;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

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
                acceptNotification(notifications.get(position));
            }
        });

        //set buttons depending on type of notification
        if (notifications.get(position).getTitle() == "Conflict") {
            notificationTitle.setTextColor(context.getResources().getColor(R.color.planit_red));
            rescheduleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), RescheduleActivity.class);
                    intent.putExtra("notificationId", notifications.get(position).getId());
                    getContext().startActivity(intent);
                }
            });
        } else {
            notificationTitle.setTextColor(context.getResources().getColor(R.color.planit_blue));
            rescheduleButton.setVisibility(View.INVISIBLE);
        }

        return rowView;
    }

    public void rejectNotification() {
        //do server things
    }

    public void rescheduleNotification() {
        //do server things
    }

    public void acceptNotification(Notification not) {
        System.out.println(not.getId());
        notifications.remove(not);
        this.notifyDataSetChanged();
    }
}
