package com.example.vlind_meeting;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainAppActivityAdapter extends FragmentStateAdapter {
    public int mCount;
    private MatchingListener matchingListener;
    private List<Fragment> fragments = new ArrayList<>();

    public MainAppActivityAdapter(FragmentActivity fa, int count, MatchingListener listener) {
        super(fa);
        mCount = count;
        matchingListener = listener;
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getRealPosition(position);
        if(index==0) return new Match1Fragment(matchingListener);
        else if(index==1) return new Match2Fragment(matchingListener);
        else return new Match3Fragment(matchingListener);
    }

    @Override
    public int getItemCount() {
        return 3;
    }
    public int getRealPosition(int position) { return position % mCount; }


}
