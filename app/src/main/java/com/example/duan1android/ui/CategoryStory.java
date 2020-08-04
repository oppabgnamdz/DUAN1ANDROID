package com.example.duan1android.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.duan1android.Adapter.AdapterCategoryStory;
import com.example.duan1android.DAO.TypeDAO;
import com.example.duan1android.Model.Type;
import com.example.duan1android.R;

import java.util.ArrayList;


public class CategoryStory extends Fragment {
    ArrayList<Type> stories;
    TypeDAO typeDAO;
    AdapterCategoryStory adapterCategoryStory;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

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
        adapterCategoryStory = new AdapterCategoryStory(stories);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterCategoryStory);
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
                adapterCategoryStory.filter(newText);
                return false;
            }
        });
    }
}