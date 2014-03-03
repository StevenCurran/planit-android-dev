package com.planit.fragments.profile;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.planit.R;
import com.planit.Rule;
import com.planit.adapters.RulesArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Gareth on 03/03/2014.
 */
public class RulesFragment extends Fragment {

    View rootView;
    Context context;
    RulesArrayAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.rules_fragment_view, container, false);
        context = rootView.getContext();

        //set fonts
        Typeface uiFont = Typeface.createFromAsset(context.getAssets(), "fonts/segoeui.ttf");
        TextView rulesTitle = (TextView) rootView.findViewById(R.id.schedulingRulesTitle);
        rulesTitle.setTypeface(uiFont);

        //set on click for add account button
        Button addRuleButton = (Button) rootView.findViewById(R.id.addRuleButton);
        addRuleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doAddRule();
            }
        });

        ListView listview = (ListView) rootView.findViewById(R.id.rulesList);
        adapter = new RulesArrayAdapter(context, getRules());
        listview.setAdapter(adapter);

        return rootView;
    }

    private ArrayList<Rule> getRules(){

        ArrayList<Rule> rules = new ArrayList<>();

        Rule testRule1 = new Rule();
        testRule1.setName("No work after 6");
        testRule1.setDescription("No events tagged with 'Work' will be accepted after 18:00.");
        testRule1.setActive(false);

        Rule testRule2 = new Rule();
        testRule2.setName("Tuesday lunchtime tennis");
        testRule2.setDescription("No events will be accepted on Tuesdays between 12:30 and 13:30.");
        testRule2.setActive(true);

        rules.add(testRule1);
        rules.add(testRule2);

        return rules;
    }

    public void doAddRule(){
        //do fings
    }

}

