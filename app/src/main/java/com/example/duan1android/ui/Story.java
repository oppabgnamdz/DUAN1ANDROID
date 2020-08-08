package com.example.duan1android.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.duan1android.Adapter.AdapterStory;
import com.example.duan1android.DAO.StoryDao;
import com.example.duan1android.NameCompare;
import com.example.duan1android.R;
import com.example.duan1android.StoryNameCompare;

import java.util.ArrayList;
import java.util.Collections;


public class Story extends Fragment {
    RecyclerView lvListStory;
    AdapterStory adapterStory;
    StoryDao storyDao;
    int position;
    ArrayList<com.example.duan1android.Model.Story> stories;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

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
                break;
            case 1:
                stories = storyDao.getAllStory("Truyện cười Thế Giới");
                adapterStory = new AdapterStory(stories);
                break;
            case 2:
                stories = storyDao.getAllStory("Truyện cười Việt Nam");
                adapterStory = new AdapterStory(stories);
                break;
            case 3:
                stories = storyDao.getAllStory("Truyện cười dân gian");
                adapterStory = new AdapterStory(stories);
                break;
            case 4:
                stories = storyDao.getAllStory("Truyện cười hiện đại");
                adapterStory = new AdapterStory(stories);
                break;
            case 5:
                stories = storyDao.getAllStory("Truyện cười Tổng hợp");
                adapterStory = new AdapterStory(stories);
                break;
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        lvListStory.setLayoutManager(linearLayoutManager);
        lvListStory.setHasFixedSize(true);
        lvListStory.setAdapter(adapterStory);

        return view;
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView =(SearchView) item.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                arrayAdapter.getFilter().filter(newText);
//                typeAdapter.filter(newText);
                adapterStory.filter(newText);
                return false;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_sort_az){
//            Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();
//            Log.e("list", stories.toString());
            Collections.sort(stories,new StoryNameCompare());
//            Log.e("list", stories.toString());
            adapterStory.notifyDataSetChanged();
        }
        else if(item.getItemId() == R.id.action_sort_za){
            Collections.sort(stories,new StoryNameCompare());
            Collections.reverse(stories);
            adapterStory.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }
}
