package com.example.duan1android.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

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
import android.widget.Toast;

import com.example.duan1android.Adapter.AdapterViewPager;
import com.example.duan1android.DAO.StoryDao;
import com.example.duan1android.Model.Story;
import com.example.duan1android.R;


public class CurrentStory extends Fragment {
    String nameStory;
    String nameType;
    StoryDao storyDao;
    ViewPager pager;
    ScrollView scBack;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    boolean isChecked = false;
    int lightState;
    int positionPage;
    AdapterViewPager adapterViewPager;

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
        pager = view.findViewById(R.id.viewPager);
        scBack = view.findViewById(R.id.scBack);
        sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        storyDao = new StoryDao(getContext());
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            nameStory = bundle.getString("nameStory");
            nameType = bundle.getString("nameType");
            adapterViewPager = new AdapterViewPager(getFragmentManager(), nameType, storyDao.getAllStory(nameType).size());
            pager.setAdapter(adapterViewPager);
            for (int i = 0; i < storyDao.getAllStory(nameType).size(); i++) {
                if (nameStory.equals(storyDao.getAllStory(nameType).get(i).getStoryName())) {
                    positionPage = i;
                }
            }
            pager.setCurrentItem(positionPage);
            pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    positionPage = position;
                    editor.putInt("positionPage", positionPage);
                    editor.commit();
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            editor.putInt("positionPage", positionPage);
            editor.putString("nameStory", nameStory);
            editor.putString("nameType", nameType);
            editor.commit();
        }
        lightState = sharedPref.getInt("lightState", 0);
        if (lightState == 0) {
            isChecked = false;
            scBack.setBackgroundColor(getResources().getColor(R.color.white));
        } else {
            isChecked = true;
            scBack.setBackgroundColor(getResources().getColor(R.color.dark));
        }
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.read_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

//    @Override
//    public void onPrepareOptionsMenu(@NonNull Menu menu) {
//        super.onPrepareOptionsMenu(menu);
//        MenuItem checkable = menu.findItem(R.id.black_white);
//        checkable.setChecked(isChecked);
//        MenuItem like = menu.findItem(R.id.like);
//        Story story = storyDao.getStory(nameStory);
//        if (story.getLikeStatus() == 0) {
//            like.setIcon(R.drawable.ic_baseline_favorite_24);
//        } else {
//            like.setIcon(R.drawable.ic_baseline_favorite_24_red);
//        }
//    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Story story = storyDao.getStory(nameStory);
        if (item.getItemId() == R.id.share) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, story.getStoryName());
            intent.putExtra(Intent.EXTRA_TEXT, story.getStoryContent());
            startActivity(Intent.createChooser(intent, "share"));
        } else if (item.getItemId() == R.id.small_size) {
            editor.putInt("sizeTitle", 12);
            editor.putInt("sizeContent", 10);
            editor.commit();
            adapterViewPager = new AdapterViewPager(getFragmentManager(), nameType, storyDao.getAllStory(nameType).size());
            pager.setAdapter(adapterViewPager);
            pager.setCurrentItem(positionPage);
        } else if (item.getItemId() == R.id.normal_size) {
            editor.putInt("sizeTitle", 19);
            editor.putInt("sizeContent", 15);
            editor.commit();
            adapterViewPager = new AdapterViewPager(getFragmentManager(), nameType, storyDao.getAllStory(nameType).size());
            pager.setAdapter(adapterViewPager);
            pager.setCurrentItem(positionPage);

        } else if (item.getItemId() == R.id.big_size) {
            editor.putInt("sizeTitle", 25);
            editor.putInt("sizeContent", 20);
            editor.commit();
            adapterViewPager = new AdapterViewPager(getFragmentManager(), nameType, storyDao.getAllStory(nameType).size());
            pager.setAdapter(adapterViewPager);
            pager.setCurrentItem(positionPage);

        } else if (item.getItemId() == R.id.black_white) {
            isChecked = !item.isChecked();
            item.setChecked(isChecked);
            if (isChecked == true) {
                editor.putInt("lightState", 1);
                editor.commit();
                scBack.setBackgroundColor(getResources().getColor(R.color.dark));
                adapterViewPager = new AdapterViewPager(getFragmentManager(), nameType, storyDao.getAllStory(nameType).size());
                pager.setAdapter(adapterViewPager);
                pager.setCurrentItem(positionPage);

            } else {
                editor.putInt("lightState", 0);
                editor.commit();
                scBack.setBackgroundColor(getResources().getColor(R.color.white));
                adapterViewPager = new AdapterViewPager(getFragmentManager(), nameType, storyDao.getAllStory(nameType).size());
                pager.setAdapter(adapterViewPager);
                pager.setCurrentItem(positionPage);
            }
        } else if (item.getItemId() == R.id.like) {
            if (storyDao.getAllStory(nameType).get(positionPage).getLikeStatus() == 0) {
                Story story1 = storyDao.getAllStory(nameType).get(positionPage);
                story1.setLikeStatus(1);
                long update = storyDao.updateStory(story1);
                if (update > 0) {
                    Toast.makeText(getContext(), "Đã thêm vào danh sách yêu thích", Toast.LENGTH_SHORT).show();
                }
//                item.setIcon(R.drawable.ic_baseline_favorite_24_red);
            } else {
                Story story1 = storyDao.getAllStory(nameType).get(positionPage);
                story1.setLikeStatus(0);
                long update = storyDao.updateStory(story1);
                if (update > 0) {
                    Toast.makeText(getContext(), "Đã xóa khỏi danh sách yêu thích", Toast.LENGTH_SHORT).show();
                }
//                item.setIcon(R.drawable.ic_baseline_favorite_24);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}