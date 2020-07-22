package com.example.duan1android.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duan1android.Adapter.AdapterCategoryStory;
import com.example.duan1android.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryStory#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryStory extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_story, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.lvListCategory);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        ArrayList<com.example.duan1android.CategoryStory> stories = new ArrayList<>();
        stories.add(new com.example.duan1android.CategoryStory("truyện tranh",100,R.drawable.banner));
        stories.add(new com.example.duan1android.CategoryStory("truyện song ngữ",51,R.drawable.roses));
        stories.add(new com.example.duan1android.CategoryStory("truyện nhật bản",63,R.drawable.police));
        stories.add(new com.example.duan1android.CategoryStory("truyện nhật bản",63,R.drawable.police));
        stories.add(new com.example.duan1android.CategoryStory("truyện nhật bản",63,R.drawable.police));
        AdapterCategoryStory adapterCategoryStory = new AdapterCategoryStory(stories);
        adapterCategoryStory.onCLick(new AdapterCategoryStory.OnClickItem() {
            @Override
            public void callBackClick(int position) {
                Log.e("position",position+"");
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterCategoryStory);
        return view;
    }
}