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
import com.example.duan1android.DAO.TypeDAO;
import com.example.duan1android.Model.Type;
import com.example.duan1android.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryStory#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryStory extends Fragment {
    ArrayList<Type> stories;
    TypeDAO typeDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_story, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.lvListCategory);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        stories = new ArrayList<>();
        typeDAO = new TypeDAO(getContext());
        stories = (ArrayList<Type>) typeDAO.getAllType();
        AdapterCategoryStory adapterCategoryStory = new AdapterCategoryStory(stories);
        adapterCategoryStory.onCLick(new AdapterCategoryStory.OnClickItem() {
            @Override
            public void callBackClick(int position) {
                Log.e("position", position + "");
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterCategoryStory);
        return view;
    }
}