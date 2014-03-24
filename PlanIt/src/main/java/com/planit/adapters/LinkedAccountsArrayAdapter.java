package com.planit.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.planit.LinkedAccount;
import com.planit.R;

import java.util.ArrayList;

/**
 * Created by Gareth on 03/03/2014.
 */
public class LinkedAccountsArrayAdapter extends ArrayAdapter<LinkedAccount> {

    private final Context context;
    private final ArrayList<LinkedAccount> linkedAccounts;

    public LinkedAccountsArrayAdapter(Context context, ArrayList<LinkedAccount> linkedAccounts) {
        super(context, R.layout.linkedaccount_list_item, linkedAccounts);
        this.context = context;
        this.linkedAccounts = linkedAccounts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linkedaccount_list_item, parent, false);

        Typeface uilFont = Typeface.createFromAsset(context.getAssets(), "fonts/segoeuisl.ttf");

        //set provider icon
        ImageView accountProviderIcon = (ImageView) rowView.findViewById(R.id.accountProviderIcon);

        switch (linkedAccounts.get(position).getAccountProvider()) {
            case "Google":
                accountProviderIcon.setImageResource(R.drawable.google_logo);
                break;
            case "Outlook":
                accountProviderIcon.setImageResource(R.drawable.outlook_logo);
                break;
            case "Facebook":
                accountProviderIcon.setImageResource(R.drawable.facebook_logo);
                break;
        }

        //set account id
        TextView accountID = (TextView) rowView.findViewById(R.id.accountID);
        accountID.setTypeface(uilFont);
        accountID.setText(linkedAccounts.get(position).getAccountId());

        //set buttons text and click handlers
        Button editLinkedAccountButton = (Button) rowView.findViewById(R.id.editLinkedAccountButton);
        editLinkedAccountButton.setTypeface(uilFont);
        editLinkedAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doEditLinkedAccount(view);
            }
        });

        Button deleteLinkedAccountButton = (Button) rowView.findViewById(R.id.deleteLinkedAccountButton);
        deleteLinkedAccountButton.setTypeface(uilFont);
        deleteLinkedAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doDeleteLinkedAccount(view);
            }
        });

        //set toggle button value
        ToggleButton inCalToggleButton = (ToggleButton) rowView.findViewById(R.id.inCalToggle);
        inCalToggleButton.setChecked(linkedAccounts.get(position).displayInCal);
        inCalToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doChangeDisplayInCal(view);
            }
        });

        return rowView;
    }

    public void doEditLinkedAccount(View view) {
        //do fings
    }

    public void doDeleteLinkedAccount(View view) {
        //do fings
    }

    public void doChangeDisplayInCal(View view) {
        //do fings
    }

}
