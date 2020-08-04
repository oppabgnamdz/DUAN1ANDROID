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
import com.example.duan1android.R;


public class CurrentStory extends Fragment {
    String nameStory;
    String nameType;
    StoryDao storyDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_current_story, container, false);
        TextView tvTitleStory = view.findViewById(R.id.tvTitleStory);
        TextView tvContentStory = view.findViewById(R.id.tvContentStory);
        storyDao = new StoryDao(getContext());
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            nameStory = bundle.getString("nameStory");
            nameType = bundle.getString("nameType");
            SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("nameStory", nameStory);
            editor.putString("nameType", nameType);
            editor.commit();
            for (int i = 0; i < storyDao.getAllStory(nameType).size(); i++) {
                if (nameStory.equals(storyDao.getAllStory(nameType).get(i).getStoryName())) {
                    tvTitleStory.setText(nameStory);
                    tvContentStory.setText(storyDao.getAllStory(nameType).get(i).getStoryContent());
                }
            }
        } else {
            SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
            if (sharedPref != null) {
                nameStory = sharedPref.getString("nameStory", "false");
                nameType = sharedPref.getString("nameType", "false");
                Log.e("name",nameStory + nameType);

                for (int i = 0; i < storyDao.getAllStory(nameType).size(); i++) {
                    if (nameStory.equals(storyDao.getAllStory(nameType).get(i).getStoryName())) {
                        tvTitleStory.setText(nameStory);
                        tvContentStory.setText(storyDao.getAllStory(nameType).get(i).getStoryContent());
                    }
                }
            }


        }

        return view;
    }
}