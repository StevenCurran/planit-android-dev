package com.planit;

/**
 * Created by Steven on 17/02/14.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.util.Date;

public class MainActivity extends Activity {

    private Button button;

    public void onCreate(Bundle savedInstanceState) {
        final Context context = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        button = (Button) findViewById(R.id.buttonUrl);

        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, WebViewActivity.class);
                startActivity(intent);
            }

        });

    }

    public void openCalendarEvent(View view){
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra(CalendarContract.Events.TITLE, "Learn to add events");
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "BCB lebbsss");
        intent.putExtra(CalendarContract.Events.DESCRIPTION, "This may be pretty tough to do though....");
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, new Date());
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
        intent.setData(CalendarContract.Events.CONTENT_URI);
        startActivity(intent);
    }

}