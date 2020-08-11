package com.example.duan1android.Adapter;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.duan1android.DAO.StoryDao;
import com.example.duan1android.ui.PageStory;

public class AdapterViewPager extends FragmentStatePagerAdapter {
    private String nameType;
    int numberStory;

    public AdapterViewPager(@NonNull FragmentManager fm, String nameType, int numberStory) {
        super(fm);
        this.nameType = nameType;
        this.numberStory = numberStory;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        PageStory pageStory = new PageStory();
        Bundle bundle = new Bundle();
        bundle.putInt("positionPage", position);
        bundle.putString("nameType", nameType);
        pageStory.setArguments(bundle);
        return pageStory;
    }

    @Override
    public int getCount() {
        return numberStory;
    }
}
