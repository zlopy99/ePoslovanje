package com.example.eprisustvo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.eprisustvo.fragments.StudentFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new StudentFragment();
            case 1:
                return new ProfesorFragment();
            default:
                return new ProfesorFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
