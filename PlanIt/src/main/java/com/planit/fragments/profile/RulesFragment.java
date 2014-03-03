package com.planit.fragments.profile;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.planit.R;

/**
 * Created by Gareth on 03/03/2014.
 */
public class RulesFragment extends Fragment {

    View rootView;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.rules_fragment_view, container, false);
        context = rootView.getContext();

        //set fonts
        Typeface uiFont = Typeface.createFromAsset(context.getAssets(), "fonts/segoeui.ttf");
        TextView rulesTitle = (TextView) rootView.findViewById(R.id.schedulingRulesTitle);
        rulesTitle.setTypeface(uiFont);

        return rootView;
    }
}
