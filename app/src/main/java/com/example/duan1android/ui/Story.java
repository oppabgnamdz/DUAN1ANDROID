package com.example.duan1android.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.duan1android.Adapter.AdapterStory;
import com.example.duan1android.DAO.StoryDao;
import com.example.duan1android.R;

import java.util.ArrayList;


public class Story extends Fragment {
    RecyclerView lvListStory;
    AdapterStory adapterStory;
    StoryDao storyDao;
    int position;
    ArrayList<com.example.duan1android.Model.Story> stories;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_story, container, false);
        lvListStory = view.findViewById(R.id.lvListStory);
        storyDao = new StoryDao(getContext());
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            position = bundle.getInt("position");
        }
        switch (position) {
            case 0:
                stories = storyDao.getAllStory("Truyện cười Vova");
                adapterStory = new AdapterStory(stories);

        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        lvListStory.setLayoutManager(linearLayoutManager);
        lvListStory.setHasFixedSize(true);
        lvListStory.setAdapter(adapterStory);

        return view;
    }
}