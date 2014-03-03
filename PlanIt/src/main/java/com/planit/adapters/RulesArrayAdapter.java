package com.planit.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.planit.R;
import com.planit.Rule;

import java.util.ArrayList;

/**
 * Created by Gareth on 03/03/2014.
 */
public class RulesArrayAdapter extends ArrayAdapter<Rule> {

    private final Context context;
    private final ArrayList<Rule> rules;

    public RulesArrayAdapter(Context context, ArrayList<Rule> rules) {
        super(context, R.layout.rule_list_item, rules);
        this.context = context;
        this.rules = rules;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rule_list_item, parent, false);

        Typeface uilFont = Typeface.createFromAsset(context.getAssets(), "fonts/segoeuisl.ttf");

        //set text and font
        TextView ruleName = (TextView) rowView.findViewById(R.id.ruleName);
        TextView ruleDescription = (TextView) rowView.findViewById(R.id.ruleDescription);

        ruleName.setText(rules.get(position).getName());
        ruleName.setTypeface(uilFont);
        ruleDescription.setText(rules.get(position).getDescription());
        ruleDescription.setTypeface(uilFont);

        //set button text and click handlers
        Button editRuleButton = (Button) rowView.findViewById(R.id.editRuleButton);
        editRuleButton.setTypeface(uilFont);
        editRuleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doEditRule(view);
            }
        });

        Button deleteRuleButton = (Button) rowView.findViewById(R.id.deleteRuleButton);
        deleteRuleButton.setTypeface(uilFont);
        deleteRuleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doDeleteRule(view);
            }
        });

        //set toggle button value
        ToggleButton ruleActiveToggle = (ToggleButton) rowView.findViewById(R.id.ruleActiveToggle);
        ruleActiveToggle.setChecked(rules.get(position).getActive());
        ruleActiveToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doChangeRuleActiveState(view);
            }
        });

        return rowView;
    }

    public void doEditRule(View view){
        //do fings
    }

    public void doDeleteRule(View view){
        //do fings
    }

    public void doChangeRuleActiveState(View view){
        //do fings
    }

}
