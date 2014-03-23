package com.planit.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.BinaryHttpResponseHandler;
import com.planit.R;
import com.planit.User;
import com.planit.adapters.ProfileTabPagerAdapter;
import com.planit.constants.GlobalUserStore;
import com.planit.tasks.AsyncDeviceRegistrationTask;
import com.planit.utils.ImageTransformer;
import com.planit.utils.WebClient;

import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProfileActivity extends FragmentActivity {

    final Context context = this;
    final ExecutorService imageExecService = Executors.newFixedThreadPool(1);
    private ViewPager viewPager;
    private ProfileTabPagerAdapter profilePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        //set fonts
        Typeface uilFont = Typeface.createFromAsset(getAssets(), "fonts/segoeuisl.ttf");

        Button scheduleButton = (Button) findViewById(R.id.scheduleButton);
        scheduleButton.setTypeface(uilFont);
        TextView title = (TextView) findViewById(R.id.screenTitle);
        title.setTypeface(uilFont);
        TextView userNameText = (TextView) findViewById(R.id.userNameText);
        userNameText.setTypeface(uilFont);
        TextView userEmailText = (TextView) findViewById(R.id.userEmailText);
        userEmailText.setTypeface(uilFont);
        Button signOutButton = (Button) findViewById(R.id.signOutButton);
        signOutButton.setTypeface(uilFont);
        Button editProfileButton = (Button) findViewById(R.id.editProfileButton);
        editProfileButton.setTypeface(uilFont);

        viewPager = (ViewPager) findViewById(R.id.pager);
        profilePagerAdapter = new ProfileTabPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(profilePagerAdapter);

        //set schedule button date
        scheduleButton.setText(getDayString());

        //set user details
        setUserDetails();

        new Thread(new AsyncDeviceRegistrationTask(context, this)).start();

    }

    public String getDayString() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);

        return Integer.toString(day);
    }

    public void setUserDetails() {
        TextView userNameText = (TextView) findViewById(R.id.userNameText);
        TextView userEmailText = (TextView) findViewById(R.id.userEmailText);

        User currentUser = GlobalUserStore.getUser();

        userNameText.setText(currentUser.getName());
        userEmailText.setText(currentUser.getEmail());

        final ImageView userPicture = (ImageView) findViewById(R.id.userPicture);
        //if there is a user image, make it circley, if there isn't, use the default image

        String imageUrl = currentUser.getImageUrl();
        String[] allowedContentTypes = new String[]{"image/jpeg", "image/png"};

        WebClient.get(imageUrl, null, new BinaryHttpResponseHandler(allowedContentTypes) {
            @Override
            public void onSuccess(byte[] fileData) {
                BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(), BitmapFactory.decodeByteArray(fileData, 0, fileData.length));
                GlobalUserStore.getUser().setImage(bitmapDrawable);
                userPicture.setImageBitmap(ImageTransformer.getRoundedShape(bitmapDrawable.getBitmap()));
            }
        });


    }


    public void doSignOut(View view) {
        Intent intent = new Intent(context, LoginActivity.class);
        startActivity(intent);
    }

    public void doEditProfile(View view) {
        //do edit profile
    }

    public void goToSchedule(View view) {
        Intent intent = new Intent(context, ScheduleActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    public void goToNotifications(View view) {
        Intent intent = new Intent(context, NotificationsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

}