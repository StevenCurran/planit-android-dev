package com.planit.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.planit.R;
import com.planit.Rule;

import java.util.ArrayList;

/**
 * Created by Gareth on 04/03/2014.
 */
public class AddRuleActivity extends Activity {

    final Context context = this;
    Button tryToBtn;
    Button tryNotToBtn;
    Button fromBtn;
    Button tagAsBtn;
    Button anyBtn;
    Button priorityBtn;
    Button beforeBtn;
    Button betweenBtn;
    Button afterBtn;
    Button onBtn;
    Button createRuleBtn;
    Button priorityOneBtn;
    Button priorityTwoBtn;
    Button priorityThreeBtn;
    Button priorityFourBtn;
    Button priorityFiveBtn;
    Rule newRule;
    EditText ruleNameBox;
    TextView withOrFromTitle;
    TextView whenTitle;
    LinearLayout eventTypeContainer;
    LinearLayout timeRuleContainer;
    ArrayList<Boolean> selectedPriorities;
    ArrayList secondPartDetails;
    Typeface uilFont;
    private Bundle b = new Bundle();

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_rule_view);

        newRule = new Rule();

        //set fonts
        uilFont = Typeface.createFromAsset(getAssets(), "fonts/segoeuisl.ttf");

        ruleNameBox = (EditText) findViewById(R.id.ruleNameBox);
        ruleNameBox.setTypeface(uilFont);
        TextView title = (TextView) findViewById(R.id.screenTitle);
        title.setTypeface(uilFont);
        TextView tryTitle = (TextView) findViewById(R.id.tryTitle);
        tryTitle.setTypeface(uilFont);
        tryToBtn = (Button) findViewById(R.id.tryToRuleButton);
        tryToBtn.setTypeface(uilFont);
        tryNotToBtn = (Button) findViewById(R.id.tryNotToRuleButton);
        tryNotToBtn.setTypeface(uilFont);
        withOrFromTitle = (TextView) findViewById(R.id.withOrFromTitle);
        withOrFromTitle.setTypeface(uilFont);
        fromBtn = (Button) findViewById(R.id.fromRuleButton);
        fromBtn.setTypeface(uilFont);
        tagAsBtn = (Button) findViewById(R.id.taggedAsRuleButton);
        tagAsBtn.setTypeface(uilFont);
        anyBtn = (Button) findViewById(R.id.anyRuleButton);
        anyBtn.setTypeface(uilFont);
        priorityBtn = (Button) findViewById(R.id.priorityRuleButton);
        priorityBtn.setTypeface(uilFont);
        whenTitle = (TextView) findViewById(R.id.whenTitle);
        whenTitle.setTypeface(uilFont);
        beforeBtn = (Button) findViewById(R.id.beforeRuleButton);
        beforeBtn.setTypeface(uilFont);
        betweenBtn = (Button) findViewById(R.id.betweenRuleButton);
        betweenBtn.setTypeface(uilFont);
        afterBtn = (Button) findViewById(R.id.afterRuleButton);
        afterBtn.setTypeface(uilFont);
        onBtn = (Button) findViewById(R.id.onRuleButton);
        onBtn.setTypeface(uilFont);
        createRuleBtn = (Button) findViewById(R.id.createRuleButton);
        createRuleBtn.setTypeface(uilFont);
        eventTypeContainer = (LinearLayout) findViewById(R.id.eventTypeContainer);
        timeRuleContainer = (LinearLayout) findViewById(R.id.timeRuleContainer);

    }

    public void onTryToRule(View view) {
        tryToBtn.setAlpha(1);
        tryNotToBtn.setAlpha((float) 0.5);
        newRule.setFirstPart("Try to");
        withOrFromTitle.setVisibility(View.VISIBLE);
        eventTypeContainer.setVisibility(View.VISIBLE);
    }

    public void onTryNotToRule(View view) {
        tryNotToBtn.setAlpha(1);
        tryToBtn.setAlpha((float) 0.5);
        newRule.setFirstPart("Try not to");
        withOrFromTitle.setVisibility(View.VISIBLE);
        eventTypeContainer.setVisibility(View.VISIBLE);
    }

    public void onTaggedAsRule(View view) {
        tagAsBtn.setAlpha(1);
        fromBtn.setAlpha((float) 0.5);
        anyBtn.setAlpha((float) 0.5);
        priorityBtn.setAlpha((float) 0.5);
        newRule.setSecondPart("tagged as");
        whenTitle.setVisibility(View.VISIBLE);

        //show tags pop up
        initiateTagsPopupWindow();


        timeRuleContainer.setVisibility(View.VISIBLE);

    }

    public void onFromRule(View view) {
        fromBtn.setAlpha(1);
        tagAsBtn.setAlpha((float) 0.5);
        anyBtn.setAlpha((float) 0.5);
        priorityBtn.setAlpha((float) 0.5);
        newRule.setSecondPart("from");
        whenTitle.setVisibility(View.VISIBLE);

        //show people pop up
        initiatePeoplePopupWindow();

        timeRuleContainer.setVisibility(View.VISIBLE);

    }

    public void onPriorityRule(View view) {
        anyBtn.setAlpha((float) 0.5);
        fromBtn.setAlpha((float) 0.5);
        tagAsBtn.setAlpha((float) 0.5);
        priorityBtn.setAlpha(1);
        newRule.setSecondPart("with priority");
        whenTitle.setVisibility(View.VISIBLE);

        //show priorities pop up
        initiatePriorityPopupWindow();
    }

    public void onAnyRule(View view) {
        anyBtn.setAlpha(1);
        fromBtn.setAlpha((float) 0.5);
        tagAsBtn.setAlpha((float) 0.5);
        priorityBtn.setAlpha((float) 0.5);
        newRule.setSecondPart("all");
        whenTitle.setVisibility(View.VISIBLE);
        timeRuleContainer.setVisibility(View.VISIBLE);
    }

    public void onBeforeRule(View view) {
        beforeBtn.setAlpha(1);
        betweenBtn.setAlpha((float) 0.5);
        afterBtn.setAlpha((float) 0.5);
        onBtn.setAlpha((float) 0.5);
        newRule.setThirdPart("before");

        //show time selector pop up

        createRuleBtn.setVisibility(View.VISIBLE);

    }

    public void onBetweenRule(View view) {
        beforeBtn.setAlpha((float) 0.5);
        betweenBtn.setAlpha(1);
        afterBtn.setAlpha((float) 0.5);
        onBtn.setAlpha((float) 0.5);
        newRule.setThirdPart("between");

        //show time selectors pop up

        createRuleBtn.setVisibility(View.VISIBLE);

    }

    public void onAfterRule(View view) {
        beforeBtn.setAlpha((float) 0.5);
        betweenBtn.setAlpha((float) 0.5);
        afterBtn.setAlpha(1);
        onBtn.setAlpha((float) 0.5);
        newRule.setThirdPart("after");

        //show time selectors pop up

        createRuleBtn.setVisibility(View.VISIBLE);

    }

    public void onOnRule(View view) {
        beforeBtn.setAlpha((float) 0.5);
        betweenBtn.setAlpha((float) 0.5);
        afterBtn.setAlpha((float) 0.5);
        onBtn.setAlpha(1);
        newRule.setThirdPart("on");

        //show day selectors pop up

        createRuleBtn.setVisibility(View.VISIBLE);
    }

    public void doCreateRule(View view) {
        //do stuff
    }

    //================================================================================
    // Tags
    //================================================================================

    private PopupWindow tagsPopup;
    private View.OnClickListener done_tags_button_click_listener = new View.OnClickListener() {
        public void onClick(View v) {
            //record selected tags
            tagsPopup.dismiss();
        }
    };

    private void initiateTagsPopupWindow() {
        try {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.tags_pop_up, (ViewGroup) findViewById(R.layout.add_rule_view), false);
            tagsPopup = new PopupWindow(layout, 600, 600);
            tagsPopup.showAtLocation(layout, Gravity.CENTER, 0, 0);

            TextView tagsPopupTitle = (TextView) layout.findViewById(R.id.tagsPopUpTitle);
            tagsPopupTitle.setTypeface(uilFont);
            Button doneSelectTagsButton = (Button) layout.findViewById(R.id.doneSelectTagsButton);
            doneSelectTagsButton.setTypeface(uilFont);
            doneSelectTagsButton.setOnClickListener(done_tags_button_click_listener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //================================================================================
    // People
    //================================================================================

    private PopupWindow peoplePopup;
    private View.OnClickListener done_people_button_click_listener = new View.OnClickListener() {
        public void onClick(View v) {
            //record selected people
            peoplePopup.dismiss();
        }
    };

    private void initiatePeoplePopupWindow() {
        try {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.people_pop_up, (ViewGroup) findViewById(R.layout.add_rule_view), false);
            peoplePopup = new PopupWindow(layout, 600, 600);
            peoplePopup.showAtLocation(layout, Gravity.CENTER, 0, 0);

            TextView peoplePopUpTitle = (TextView) layout.findViewById(R.id.peoplePopUpTitle);
            peoplePopUpTitle.setTypeface(uilFont);
            Button doneSelectPeopleButton = (Button) layout.findViewById(R.id.doneSelectPeopleButton);
            doneSelectPeopleButton.setTypeface(uilFont);
            doneSelectPeopleButton.setOnClickListener(done_people_button_click_listener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //================================================================================
    // Priority
    //================================================================================

    private PopupWindow priorityPopup;
    private View.OnClickListener done_priority_button_click_listener = new View.OnClickListener() {
        public void onClick(View v) {
            priorityPopup.dismiss();
            secondPartDetails = selectedPriorities;
            timeRuleContainer.setVisibility(View.VISIBLE);
        }
    };
    private View.OnClickListener select_priority_button_click_listener = new View.OnClickListener() {
        public void onClick(View v) {
            //record selected priorities

            switch (v.getId()) {
                case R.id.popup_priorityOneButton:
                    if (priorityOneBtn.getAlpha() == 1) {
                        priorityOneBtn.setAlpha((float) 0.5);
                        selectedPriorities.set(0, false);
                    } else {
                        priorityOneBtn.setAlpha(1);
                        //add to array
                        selectedPriorities.set(0, true);}
                    break;
                case R.id.popup_priorityTwoButton:
                    if (priorityTwoBtn.getAlpha() == 1) {
                        priorityTwoBtn.setAlpha((float) 0.5);
                        //remove from array
                        selectedPriorities.set(1, false);
                    } else {
                        priorityTwoBtn.setAlpha(1);
                        //add to array
                        selectedPriorities.set(1, true);
                    }
                    break;
                case R.id.popup_priorityThreeButton:
                    if (priorityThreeBtn.getAlpha() == 1) {
                        priorityThreeBtn.setAlpha((float) 0.5);
                        //remove from array
                        selectedPriorities.set(2, false);
                    } else {
                        priorityThreeBtn.setAlpha(1);
                        //add to array
                        selectedPriorities.set(2, true);
                    }
                    break;
                case R.id.popup_priorityFourButton:
                    if (priorityFourBtn.getAlpha() == 1) {
                        priorityFourBtn.setAlpha((float) 0.5);
                        //remove from array
                        selectedPriorities.set(3, false);
                    } else {
                        priorityFourBtn.setAlpha(1);
                        //add to array
                        selectedPriorities.set(3, true);
                    }
                    break;
                case R.id.popup_priorityFiveButton:
                    if (priorityFiveBtn.getAlpha() == 1) {
                        priorityFiveBtn.setAlpha((float) 0.5);
                        //remove from array
                        selectedPriorities.set(4, false);
                    } else {
                        priorityFiveBtn.setAlpha(1);
                        //add to array
                        selectedPriorities.set(4, true);
                    }
                    break;
            }
        }
    };

    private void initiatePriorityPopupWindow() {
        try {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.priority_pop_up, (ViewGroup) findViewById(R.layout.add_rule_view), false);
            priorityPopup = new PopupWindow(layout, 600, 230);
            priorityPopup.showAtLocation(layout, Gravity.CENTER, 0, 0);

            TextView peoplePopUpTitle = (TextView) layout.findViewById(R.id.priorityPopUpTitle);
            peoplePopUpTitle.setTypeface(uilFont);
            Button doneSelectPeopleButton = (Button) layout.findViewById(R.id.doneSelectPriorityButton);
            doneSelectPeopleButton.setTypeface(uilFont);
            doneSelectPeopleButton.setOnClickListener(done_priority_button_click_listener);

            priorityOneBtn = (Button) layout.findViewById(R.id.popup_priorityOneButton);
            priorityTwoBtn = (Button) layout.findViewById(R.id.popup_priorityTwoButton);
            priorityThreeBtn = (Button) layout.findViewById(R.id.popup_priorityThreeButton);
            priorityFourBtn = (Button) layout.findViewById(R.id.popup_priorityFourButton);
            priorityFiveBtn = (Button) layout.findViewById(R.id.popup_priorityFiveButton);
            priorityOneBtn.setOnClickListener(select_priority_button_click_listener);
            priorityTwoBtn.setOnClickListener(select_priority_button_click_listener);
            priorityThreeBtn.setOnClickListener(select_priority_button_click_listener);
            priorityFourBtn.setOnClickListener(select_priority_button_click_listener);
            priorityFiveBtn.setOnClickListener(select_priority_button_click_listener);

            if (selectedPriorities == null) {
                selectedPriorities = new ArrayList<Boolean>(5);
                for (int i = 0; i < 5; i++)
                    selectedPriorities.add(false);
            } else {
                if (selectedPriorities.get(0) == true)
                    priorityOneBtn.setAlpha(1);
                if (selectedPriorities.get(1) == true)
                    priorityTwoBtn.setAlpha(1);
                if (selectedPriorities.get(2) == true)
                    priorityThreeBtn.setAlpha(1);
                if (selectedPriorities.get(3) == true)
                    priorityFourBtn.setAlpha(1);
                if (selectedPriorities.get(4) == true)
                    priorityFiveBtn.setAlpha(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
