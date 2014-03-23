package com.planit.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    Button allBtn;
    Rule newRule;
    EditText ruleNameBox;

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
        TextView withOrFromTitle = (TextView) findViewById(R.id.withOrFromTitle);
        withOrFromTitle.setTypeface(uilFont);
        fromBtn = (Button) findViewById(R.id.fromRuleButton);
        fromBtn.setTypeface(uilFont);
        tagAsBtn = (Button) findViewById(R.id.taggedAsRuleButton);
        tagAsBtn.setTypeface(uilFont);
        allBtn = (Button) findViewById(R.id.allRuleButton);
        allBtn.setTypeface(uilFont);
        Button createRuleBtn = (Button) findViewById(R.id.createRuleButton);
        createRuleBtn.setTypeface(uilFont);

    }

    public void onTryToRule(View view) {
        tryToBtn.setAlpha(1);
        tryNotToBtn.setAlpha((float) 0.5);
        newRule.setFirstPart("Try to");
    }

    public void onTryNotToRule(View view) {
        tryNotToBtn.setAlpha(1);
        tryToBtn.setAlpha((float) 0.5);
        newRule.setFirstPart("Try not to");
    }

    public void onTaggedAsRule(View view) {
        tagAsBtn.setAlpha(1);
        fromBtn.setAlpha((float) 0.5);
        allBtn.setAlpha((float) 0.5);
        newRule.setSecondPart("tagged as");

        //show tags
    }

    public void onFromRule(View view) {
        fromBtn.setAlpha(1);
        tagAsBtn.setAlpha((float) 0.5);
        allBtn.setAlpha((float) 0.5);
        newRule.setSecondPart("from");

        //show people
    }

    public void onAllRule(View view) {
        allBtn.setAlpha(1);
        fromBtn.setAlpha((float) 0.5);
        tagAsBtn.setAlpha((float) 0.5);
        newRule.setSecondPart("all");
    }

    public void doCreateRule(View view) {
        //do stuff
    }

}
