package com.example.vlind_meeting;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MsgTabAdapter extends FragmentPagerAdapter {

    private MsgListener msgListener;
    private String receive_number;

    public MsgTabAdapter(@NonNull FragmentManager fm, MsgListener listener, String number)
    {
        super(fm);
        msgListener = listener;
        receive_number = number;
    }


    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        if(position==0)
            return new MsgSentTabFragment(msgListener);
        else
            return new MsgReceivedTabFragment(msgListener, receive_number);
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
            return "Sent";
        } else {
            return "Received";
        }
    }
}
