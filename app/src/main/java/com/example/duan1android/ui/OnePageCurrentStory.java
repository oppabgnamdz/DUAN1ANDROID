package com.example.duan1android.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.duan1android.DAO.StoryDao;
import com.example.duan1android.R;


public class OnePageCurrentStory extends Fragment {
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    TextView tvTitleStory, tvContentStory;
    String nameStory, nameType;
    StoryDao storyDao;
    int positionPage;
    int lightState;
    int sizeTitle, sizeContent;
    FrameLayout bgColor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one_page_current_story, container, false);
        tvContentStory = view.findViewById(R.id.tvContentStory);
        tvTitleStory = view.findViewById(R.id.tvTitleStory);
        bgColor = view.findViewById(R.id.bgColor);
        sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        storyDao = new StoryDao(getContext());
        nameStory = sharedPref.getString("nameStory", "false");
        nameType = sharedPref.getString("nameType", "false");
        positionPage = sharedPref.getInt("positionPage", 0);
        tvTitleStory.setText(storyDao.getAllStory(nameType).get(positionPage).getStoryName());
        tvContentStory.setText(storyDao.getAllStory(nameType).get(positionPage).getStoryContent());
        lightState = sharedPref.getInt("lightState", 0);
        sizeContent = sharedPref.getInt("sizeContent", 0);
        sizeTitle = sharedPref.getInt("sizeTitle", 0);
        if (lightState == 0) {
            bgColor.setBackgroundColor(getResources().getColor(R.color.white));
            tvContentStory.setTextSize(sizeContent);
            tvTitleStory.setTextSize(sizeTitle);
            tvContentStory.setTextColor(getResources().getColor(R.color.split));
            tvTitleStory.setTextColor(getResources().getColor(R.color.split));
        } else {
            bgColor.setBackgroundColor(getResources().getColor(R.color.dark));
            tvContentStory.setTextSize(sizeContent);
            tvTitleStory.setTextSize(sizeTitle);
            tvContentStory.setTextColor(getResources().getColor(R.color.colorAccent));
            tvTitleStory.setTextColor(getResources().getColor(R.color.colorAccent));
        }

        return view;
    }
}