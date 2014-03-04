package com.planit.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.planit.R;
import com.planit.User;
import com.planit.adapters.ProfileTabPagerAdapter;
import com.planit.constants.GlobalUserStore;

import java.util.Calendar;

public class ProfileActivity extends FragmentActivity {

    private ViewPager viewPager;
    private ProfileTabPagerAdapter profilePagerAdapter;
    final Context context = this;

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
    }

    public String getDayString(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);

        return Integer.toString(day);
    }

    public void setUserDetails(){
        TextView userNameText = (TextView) findViewById(R.id.userNameText);
        TextView userEmailText = (TextView) findViewById(R.id.userEmailText);

        User currentUser = GlobalUserStore.getUser();

        userNameText.setText(currentUser.getName());
        userEmailText.setText(currentUser.getEmail());

        ImageView userPicture = (ImageView) findViewById(R.id.userPicture);
        //if there is a user image, make it circley, if there isn't, use the default image
        if(GlobalUserStore.getUser().getImage() != null){
            Bitmap userImage = GlobalUserStore.getUser().getImage().getBitmap();
            userPicture.setImageBitmap(getRoundedShape(userImage));
        }else{
            userPicture.setImageResource(R.drawable.default_user_photo);
        }
    }

    public Bitmap getRoundedShape(Bitmap sourceBitmap) {
        // TODO Auto-generated method stub
        int targetWidth = 114;
        int targetHeight = 114;
        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
                targetHeight,Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(targetBitmap);
        Path path = new Path();
        path.addCircle(((float) targetWidth - 1) / 2,
                ((float) targetHeight - 1) / 2,
                (Math.min(((float) targetWidth),
                        ((float) targetHeight)) / 2),
                Path.Direction.CCW);

        canvas.clipPath(path);

        canvas.drawBitmap(sourceBitmap,
                new Rect(0, 0, sourceBitmap.getWidth(),
                        sourceBitmap.getHeight()),
                new Rect(0, 0, targetWidth,
                        targetHeight), null);
        return targetBitmap;
    }

    public void doSignOut(View view){
        Intent intent = new Intent(context, LoginActivity.class);
        startActivity(intent);
    }

    public void doEditProfile(View view){
        //do edit profile
    }

    public void goToSchedule(View view){
        //go to schedule
    }

    public void goToNotifications(View view){
        //go to notification
    }

    //disabling back button on profile activity to prevent going back to login loading screen
    @Override
    public void onBackPressed() {
    }


}