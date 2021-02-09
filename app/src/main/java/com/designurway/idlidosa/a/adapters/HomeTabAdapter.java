package com.designurway.idlidosa.a.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.designurway.idlidosa.ui.home_page.fragments.ComboFragment;
import com.designurway.idlidosa.ui.home_page.fragments.FeaturedFragment;

public class HomeTabAdapter extends FragmentStatePagerAdapter {

    final int TabCount;

    public HomeTabAdapter(@NonNull FragmentManager fm, int tabcount) {
        super(fm, tabcount);
        this.TabCount = tabcount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case (0):
                return new FeaturedFragment();

            case (1):
                return new ComboFragment();

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return TabCount;
    }
}
