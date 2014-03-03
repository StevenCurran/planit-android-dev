package com.planit.fragments.profile;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        ListView listview = (ListView) rootView.findViewById(R.id.rulesList);
        adapter = new RulesArrayAdapter(context, getRules());
        listview.setAdapter(adapter);
        listview.setDivider(null);

        return rootView;
    }

    private ArrayList<Rule> getRules(){

        ArrayList<Rule> rules = new ArrayList<>();

        Rule testRule1 = new Rule();
        testRule1.setName("Rule 1");
        testRule1.setDescription("Rule 1 Description");

        Rule testRule2 = new Rule();
        testRule2.setName("Rule 2");
        testRule2.setDescription("Rule 2 Description");

        rules.add(testRule1);
        rules.add(testRule2);

        return rules;
    }

}

