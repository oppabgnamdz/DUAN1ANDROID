package com.example.duan1android.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.duan1android.Adapter.AdapterFavourite;
import com.example.duan1android.Adapter.AdapterStory;
import com.example.duan1android.DAO.StoryDao;
import com.example.duan1android.Model.Story;
import com.example.duan1android.R;

import java.util.ArrayList;


public class Favourite extends Fragment {
    RecyclerView recyclerView;
    StoryDao storyDao;
    ArrayList<Story> stories;
    AdapterFavourite adapterFavourite;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        recyclerView = view.findViewById(R.id.listFavourite);
        storyDao = new StoryDao(getContext());
        stories = storyDao.getAllStory(1);
        adapterFavourite = new AdapterFavourite(stories);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterFavourite);
        adapterFavourite.OnItemImageClick(new AdapterFavourite.OnClick() {
            @Override
            public void callBackClick(String name, int position) {
                Story story = storyDao.getStory(name);
                story.setLikeStatus(0);
                storyDao.updateStory(story);
                stories.remove(position);
                adapterFavourite.notifyDataSetChanged();
            }
        });
        return view;
    }
}