package com.planit.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.planit.fragments.profile.LinkedAccountsFragment;
import com.planit.fragments.profile.RulesFragment;

public class ProfileTabPagerAdapter extends FragmentPagerAdapter {

    public ProfileTabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                return new LinkedAccountsFragment();
            case 1:
                return new RulesFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }

}