package com.example.duan1android.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.duan1android.DAO.StoryDao;
import com.example.duan1android.Model.Story;
import com.example.duan1android.R;


public class CurrentStory extends Fragment {
    String nameStory;
    String nameType;
    StoryDao storyDao;
    TextView tvTitleStory;
    TextView tvContentStory;
    ScrollView scBack;
    boolean isChecked = false;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_current_story, container, false);
        tvTitleStory = view.findViewById(R.id.tvTitleStory);
        tvContentStory = view.findViewById(R.id.tvContentStory);
        scBack = view.findViewById(R.id.scBack);
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

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.read_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem checkable = menu.findItem(R.id.black_white);
        checkable.setChecked(isChecked);
        MenuItem like = menu.findItem(R.id.like);
        Story story = storyDao.getStory(nameStory);
        if(story.getLikeStatus() == 0){
            like.setIcon(R.drawable.ic_baseline_favorite_24);
        }
        else {
            like.setIcon(R.drawable.ic_baseline_favorite_24_red);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Story story = storyDao.getStory(nameStory);
        if(item.getItemId() == R.id.share){
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, story.getStoryName());
            intent.putExtra(Intent.EXTRA_TEXT,story.getStoryContent() );
            startActivity(Intent.createChooser(intent, "share" ));
        }
        else if(item.getItemId() == R.id.small_size){
            tvContentStory.setTextSize(10);
            tvTitleStory.setTextSize(12);
        }
        else if(item.getItemId() == R.id.normal_size){
            tvContentStory.setTextSize(15);
            tvTitleStory.setTextSize(19);
        }
        else if(item.getItemId() == R.id.big_size){
            tvContentStory.setTextSize(20);
            tvTitleStory.setTextSize(25);
        }
        else if(item.getItemId() == R.id.black_white){
            isChecked = !item.isChecked();
            item.setChecked(isChecked);
            if(isChecked){
                scBack.setBackgroundColor(getResources().getColor(R.color.dark));
                tvContentStory.setTextColor(getResources().getColor(R.color.colorAccent));
                tvTitleStory.setTextColor(getResources().getColor(R.color.colorAccent));
            }
            else {
                scBack.setBackgroundColor(getResources().getColor(R.color.white));
                tvContentStory.setTextColor(getResources().getColor(R.color.split));
                tvTitleStory.setTextColor(getResources().getColor(R.color.split));
            }
        }
        else if(item.getItemId() == R.id.like){
            if(story.getLikeStatus() == 0){
                story.setLikeStatus(1);
                long update = storyDao.updateStory(story);
                item.setIcon(R.drawable.ic_baseline_favorite_24_red);
            }
            else {
                story.setLikeStatus(0);
                long update = storyDao.updateStory(story);
                item.setIcon(R.drawable.ic_baseline_favorite_24);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}