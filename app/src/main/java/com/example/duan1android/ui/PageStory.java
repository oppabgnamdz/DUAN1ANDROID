package com.example.duan1android.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.duan1android.DAO.StoryDao;
import com.example.duan1android.DAO.TypeDAO;
import com.example.duan1android.R;


public class PageStory extends Fragment {
    TextView tvTitleStory, tvContentStory;
    int positionPage;
    String nameType;
    StoryDao storyDao;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    int lightState;
    int sizeTitle, sizeContent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_page_story, container, false);
        tvContentStory = view.findViewById(R.id.tvContentStory);
        tvTitleStory = view.findViewById(R.id.tvTitleStory);
        storyDao = new StoryDao(getContext());
        sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        positionPage = getArguments().getInt("positionPage");
        nameType = getArguments().getString("nameType");
        tvTitleStory.setText(storyDao.getAllStory(nameType).get(positionPage).getStoryName());
        tvContentStory.setText(storyDao.getAllStory(nameType).get(positionPage).getStoryContent());
        lightState = sharedPref.getInt("lightState", 0);
        sizeContent = sharedPref.getInt("sizeContent", 14);
        sizeTitle = sharedPref.getInt("sizeTitle", 18);
        if (lightState == 0) {
            tvContentStory.setTextSize(sizeContent);
            tvTitleStory.setTextSize(sizeTitle);
            tvContentStory.setTextColor(getResources().getColor(R.color.split));
            tvTitleStory.setTextColor(getResources().getColor(R.color.split));
        } else {
            tvContentStory.setTextSize(sizeContent);
            tvTitleStory.setTextSize(sizeTitle);
            tvContentStory.setTextColor(getResources().getColor(R.color.colorAccent));
            tvTitleStory.setTextColor(getResources().getColor(R.color.colorAccent));
        }

        return view;
    }
}