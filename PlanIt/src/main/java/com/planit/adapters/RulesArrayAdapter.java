package com.planit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
        TextView ruleName = (TextView) rowView.findViewById(R.id.ruleName);
        TextView ruleDescription = (TextView) rowView.findViewById(R.id.ruleDescription);
        ruleName.setText(rules.get(position).getName());
        ruleDescription.setText(rules.get(position).getDescription());

        return rowView;
    }
}
