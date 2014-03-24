package com.planit.fragments.profile;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.planit.LinkedAccount;
import com.planit.R;
import com.planit.activities.AddLinkedAccountActivity;
import com.planit.adapters.LinkedAccountsArrayAdapter;

import java.util.ArrayList;

public class LinkedAccountsFragment extends Fragment {

    View rootView;
    Context context;
    LinkedAccountsArrayAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.linked_accounts_fragment_view, container, false);
        context = rootView.getContext();

        //set fonts
        Typeface uiFont = Typeface.createFromAsset(context.getAssets(), "fonts/segoeui.ttf");

        TextView linkedAccountsTitle = (TextView) rootView.findViewById(R.id.linkedAccountsTitle);
        linkedAccountsTitle.setTypeface(uiFont);

        //set on click for add account button
        Button addLinkedAccountButton = (Button) rootView.findViewById(R.id.addLinkedAccountButton);
        addLinkedAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doAddAccount();
            }
        });

        ListView listview = (ListView) rootView.findViewById(R.id.linkedAccountsList);
        adapter = new LinkedAccountsArrayAdapter(context, getLinkedAccounts());
        listview.setAdapter(adapter);
        listview.setDivider(null);

        return rootView;
    }

    public ArrayList<LinkedAccount> getLinkedAccounts() {

        ArrayList<LinkedAccount> linkedAccounts = new ArrayList<>();

        //find all the linked accounts and add them to list - need to add checks for no linked accounts etc.
        //do le server fings and process server results

        LinkedAccount testlinkedAccount1 = new LinkedAccount();
        testlinkedAccount1.accountId = "gas001@gmail.com";
        testlinkedAccount1.accountProvider = "Google";
        testlinkedAccount1.displayInCal = true;


        LinkedAccount testlinkedAccount2 = new LinkedAccount();
        testlinkedAccount2.accountId = "gsmith28@facebook.com";
        testlinkedAccount2.accountProvider = "Facebook";
        testlinkedAccount2.displayInCal = false;

        linkedAccounts.add(testlinkedAccount1);
        linkedAccounts.add(testlinkedAccount2);

        return linkedAccounts;
    }

    public void doAddAccount() {
        Intent intent = new Intent(context, AddLinkedAccountActivity.class);
        startActivity(intent);
    }


}