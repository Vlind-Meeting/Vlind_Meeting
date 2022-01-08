package com.example.vlind_meeting;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MsgTabAdapter extends FragmentPagerAdapter {


    public MsgTabAdapter(@NonNull FragmentManager fm)
    {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        if(position==0)
            return new MsgSentTabFragment();
        else
            return new MsgReceivedTabFragment();
    }

    @Override
    public int getCount()
    {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Tab_1";
        } else {
            return "Tab_2";
        }
    }
}
