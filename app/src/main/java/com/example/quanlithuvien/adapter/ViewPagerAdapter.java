package com.example.quanlithuvien.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.quanlithuvien.TopNamFragment;
import com.example.quanlithuvien.TopNgayFragment;
import com.example.quanlithuvien.TopThangFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new TopNgayFragment();
            case 1:
                return new TopThangFragment();
            case 2:
                return new TopNamFragment();
            default:
                return new TopNgayFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Top day";
                break;
            case 1:
                title = "Top month";
                break;
            case 2:
                title = "Top year";
                break;
        }
        return title;
    }
}
