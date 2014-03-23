package com.planit.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.planit.R;
import com.planit.Rule;

/**
 * Created by Gareth on 04/03/2014.
 */
public class AddRuleActivity extends Activity {

    final Context context = this;
    private Bundle b = new Bundle();
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
    Rule newRule;
    EditText ruleNameBox;
    TextView withOrFromTitle;
    TextView whenTitle;
    LinearLayout eventTypeContainer;
    LinearLayout timeRuleContainer;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_rule_view);

        newRule = new Rule();

        //set fonts
        Typeface uilFont = Typeface.createFromAsset(getAssets(), "fonts/segoeuisl.ttf");

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

        timeRuleContainer.setVisibility(View.VISIBLE);

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

}
