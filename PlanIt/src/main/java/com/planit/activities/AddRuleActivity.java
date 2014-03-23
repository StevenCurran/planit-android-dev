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
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.planit.Participant;
import com.planit.R;
import com.planit.Rule;
import com.planit.adapters.ParticipantsArrayAdapter;
import com.planit.constants.UrlServerConstants;
import com.planit.utils.WebClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    ArrayList<Integer> selectedPriorities;
    ArrayList secondPartDetails;
    ParticipantsArrayAdapter adapter;
    ArrayList<Participant> people = new ArrayList<>();
    ArrayList<Participant> attendingPeople = new ArrayList<>();
    TextView ruleDescriptionContainer;
    private Gson gson = new Gson();
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
        ruleDescriptionContainer = (TextView) findViewById(R.id.ruleDescriptionContainer);
        ruleDescriptionContainer.setTypeface(uilFont);
        newRule.setFirstPart("");
        newRule.setSecondPart("");
        newRule.setThirdPart("");
        selectedPriorities = new ArrayList<Integer>();

    }

    public void onTryToRule(View view) {
        tryToBtn.setAlpha(1);
        tryNotToBtn.setAlpha((float) 0.5);
        newRule.setFirstPart("Try to");
        updateRuleString();
        withOrFromTitle.setVisibility(View.VISIBLE);
        eventTypeContainer.setVisibility(View.VISIBLE);
    }

    public void onTryNotToRule(View view) {
        tryNotToBtn.setAlpha(1);
        tryToBtn.setAlpha((float) 0.5);
        newRule.setFirstPart("Try not to");
        updateRuleString();
        withOrFromTitle.setVisibility(View.VISIBLE);
        eventTypeContainer.setVisibility(View.VISIBLE);
    }

    public void onTaggedAsRule(View view) {
        tagAsBtn.setAlpha(1);
        fromBtn.setAlpha((float) 0.5);
        anyBtn.setAlpha((float) 0.5);
        priorityBtn.setAlpha((float) 0.5);
        newRule.setSecondPart("tagged as");
        updateRuleString();

        //show tags pop up
        initiateTagsPopupWindow();
    }

    public void onFromRule(View view) {
        fromBtn.setAlpha(1);
        tagAsBtn.setAlpha((float) 0.5);
        anyBtn.setAlpha((float) 0.5);
        priorityBtn.setAlpha((float) 0.5);
        newRule.setSecondPart("from");
        updateRuleString();

        //show people pop up
        initiatePeoplePopupWindow();
    }

    public void onPriorityRule(View view) {
        anyBtn.setAlpha((float) 0.5);
        fromBtn.setAlpha((float) 0.5);
        tagAsBtn.setAlpha((float) 0.5);
        priorityBtn.setAlpha(1);
        newRule.setSecondPart("with priority");
        updateRuleString();

        //show priorities pop up
        initiatePriorityPopupWindow();
    }

    public void onAnyRule(View view) {
        anyBtn.setAlpha(1);
        fromBtn.setAlpha((float) 0.5);
        tagAsBtn.setAlpha((float) 0.5);
        priorityBtn.setAlpha((float) 0.5);
        newRule.setSecondPart("any");
        updateRuleString();
        whenTitle.setVisibility(View.VISIBLE);
        timeRuleContainer.setVisibility(View.VISIBLE);
    }

    public void onBeforeRule(View view) {
        beforeBtn.setAlpha(1);
        betweenBtn.setAlpha((float) 0.5);
        afterBtn.setAlpha((float) 0.5);
        onBtn.setAlpha((float) 0.5);
        newRule.setThirdPart("before");
        updateRuleString();

        //show time pop up
        initiateTimePopupWindow("before");
    }

    public void onBetweenRule(View view) {
        beforeBtn.setAlpha((float) 0.5);
        betweenBtn.setAlpha(1);
        afterBtn.setAlpha((float) 0.5);
        onBtn.setAlpha((float) 0.5);
        newRule.setThirdPart("between");
        updateRuleString();

        //show time pop up
        initiateTimePopupWindow("between");

    }

    public void onAfterRule(View view) {
        beforeBtn.setAlpha((float) 0.5);
        betweenBtn.setAlpha((float) 0.5);
        afterBtn.setAlpha(1);
        onBtn.setAlpha((float) 0.5);
        newRule.setThirdPart("after");
        updateRuleString();

        //show time pop up
        initiateTimePopupWindow("after");

    }

    public void onOnRule(View view) {
        beforeBtn.setAlpha((float) 0.5);
        betweenBtn.setAlpha((float) 0.5);
        afterBtn.setAlpha((float) 0.5);
        onBtn.setAlpha(1);
        newRule.setThirdPart("on");
        updateRuleString();

        //show time pop up
        initiateTimePopupWindow("on");

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
            whenTitle.setVisibility(View.VISIBLE);
            timeRuleContainer.setVisibility(View.VISIBLE);
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
            attendingPeople = adapter.attendingParticipants;
            secondPartDetails = attendingPeople;
            whenTitle.setVisibility(View.VISIBLE);
            timeRuleContainer.setVisibility(View.VISIBLE);
            updateRuleString();
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

            ListView listview = (ListView) layout.findViewById(R.id.peopleList);
            if (adapter == null)
                adapter = new ParticipantsArrayAdapter(context, getPeople());
            listview.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Participant> getPeople() {

        final ArrayList<Participant> p = new ArrayList<>();

        WebClient.get(UrlServerConstants.ATTENDEES, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONArray response) {

                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        Participant user = gson.fromJson(jsonObject.toString(), Participant.class);
                        user.setAttending(false);
                        p.add(user);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                people.clear();
                people.addAll(p);

                adapter.clear();
                for (Participant participant : p) {
                    adapter.add(participant);
                }
                adapter.notifyDataSetChanged();
            }


        });

        return people;

    }

    //================================================================================
    // Priority
    //================================================================================

    private PopupWindow priorityPopup;
    private View.OnClickListener done_priority_button_click_listener = new View.OnClickListener() {
        public void onClick(View v) {
            priorityPopup.dismiss();
            secondPartDetails = selectedPriorities;
            whenTitle.setVisibility(View.VISIBLE);
            timeRuleContainer.setVisibility(View.VISIBLE);
            updateRuleString();
        }
    };

    private View.OnClickListener select_priority_button_click_listener = new View.OnClickListener() {
        public void onClick(View v) {
            //record selected priorities

            switch (v.getId()) {
                case R.id.popup_priorityOneButton:
                    if (priorityOneBtn.getAlpha() == 1) {
                        priorityOneBtn.setAlpha((float) 0.5);
                        selectedPriorities.remove((Integer) 1);
                    } else {
                        priorityOneBtn.setAlpha(1);
                        //add to array
                        selectedPriorities.add(1);
                    }
                    break;
                case R.id.popup_priorityTwoButton:
                    if (priorityTwoBtn.getAlpha() == 1) {
                        priorityTwoBtn.setAlpha((float) 0.5);
                        //remove from array
                        selectedPriorities.remove((Integer) 2);
                    } else {
                        priorityTwoBtn.setAlpha(1);
                        //add to array
                        selectedPriorities.add(2);
                    }
                    break;
                case R.id.popup_priorityThreeButton:
                    if (priorityThreeBtn.getAlpha() == 1) {
                        priorityThreeBtn.setAlpha((float) 0.5);
                        //remove from array
                        selectedPriorities.remove((Integer) 3);
                    } else {
                        priorityThreeBtn.setAlpha(1);
                        //add to array
                        selectedPriorities.add(3);
                    }
                    break;
                case R.id.popup_priorityFourButton:
                    if (priorityFourBtn.getAlpha() == 1) {
                        priorityFourBtn.setAlpha((float) 0.5);
                        //remove from array
                        selectedPriorities.remove((Integer) 4);
                    } else {
                        priorityFourBtn.setAlpha(1);
                        //add to array
                        selectedPriorities.add(4);
                    }
                    break;
                case R.id.popup_priorityFiveButton:
                    if (priorityFiveBtn.getAlpha() == 1) {
                        priorityFiveBtn.setAlpha((float) 0.5);
                        //remove from array
                        selectedPriorities.remove((Integer) 5);
                    } else {
                        priorityFiveBtn.setAlpha(1);
                        //add to array
                        selectedPriorities.add(5);
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

            for (int i = 0; i < selectedPriorities.size(); i++) {
                switch (selectedPriorities.get(i)) {
                    case 1 :
                        priorityOneBtn.setAlpha(1);
                        break;
                    case 2 :
                        priorityTwoBtn.setAlpha(1);
                        break;
                    case 3 :
                        priorityThreeBtn.setAlpha(1);
                        break;
                    case 4 :
                        priorityFourBtn.setAlpha(1);
                        break;
                    case 5 :
                        priorityFiveBtn.setAlpha(1);
                        break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //================================================================================
    // Time
    //================================================================================

    private PopupWindow timePopup;
    private View.OnClickListener done_time_button_click_listener = new View.OnClickListener() {
        public void onClick(View v) {
            //record selected people
            createRuleBtn.setVisibility(View.VISIBLE);
            timePopup.dismiss();
            updateRuleString();
        }
    };

    private void initiateTimePopupWindow(String option) {
        try {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.time_popup, (ViewGroup) findViewById(R.layout.add_rule_view), false);
            timePopup = new PopupWindow(layout, 600, 600);
            timePopup.showAtLocation(layout, Gravity.CENTER, 0, 0);

            TextView timePopUpTitle = (TextView) layout.findViewById(R.id.timePopupTitle);
            timePopUpTitle.setTypeface(uilFont);
            Button doneTimePeopleButton = (Button) layout.findViewById(R.id.doneSelectTimeButton);
            doneTimePeopleButton.setTypeface(uilFont);
            doneTimePeopleButton.setOnClickListener(done_time_button_click_listener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //================================================================================
    // Rule String
    //================================================================================

    public void updateRuleString() {
        String rs = "";

        switch (newRule.getSecondPart()) {
            case "any":
                rs = newRule.getFirstPart() + " schedule any events ";
                break;
            case "tagged as":
                rs = newRule.getFirstPart() + " schedule events tagged as <tags>";
                break;
            case "from":
                rs = newRule.getFirstPart() + " schedule events created by ";
                if (attendingPeople.size() == 1) {
                    rs += attendingPeople.get(0).getFirstName() + " " + attendingPeople.get(0).getLastName();
                } else if (attendingPeople.size() > 1) {
                    for (int i = 0; i < attendingPeople.size(); i++) {
                        rs += attendingPeople.get(i).getFirstName() + " " + attendingPeople.get(i).getLastName();
                        if (i == attendingPeople.size()-2)
                            rs += " or ";
                        else if (i != attendingPeople.size()-1)
                            rs += ", ";
                    }
                }
                break;
            case "with priority":
                rs = newRule.getFirstPart();
                if (selectedPriorities.size() == 1) {
                    rs += " schedule priority " + selectedPriorities.get(0).toString() + " events";
                } else if (selectedPriorities.size() > 1){
                    rs = newRule.getFirstPart() + " schedule events with priorities ";
                    for (int i = 0; i < selectedPriorities.size(); i++) {
                        rs += selectedPriorities.get(i).toString();
                        if (i == selectedPriorities.size()-2)
                            rs += " or ";
                        else if (i != selectedPriorities.size()-1)
                            rs += ", ";
                    }
                }
                break;
            case "":
                rs = newRule.getFirstPart();
                break;
        }

        switch (newRule.getThirdPart()) {
            case "before":
                rs += " before <date>.";
                break;
            case "between":
                rs += " between <date> and <date>.";
                break;
            case "after":
                rs += " after <date>.";
                break;
            case "on":
                rs += " on <day/days>.";
                break;
        }

        newRule.setDescription(rs);
        ruleDescriptionContainer.setText(rs);
    }

}
