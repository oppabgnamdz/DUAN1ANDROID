package com.example.duan1android;

import com.example.duan1android.Model.Story;

import java.util.Comparator;

public class StoryNameCompare implements Comparator<com.example.duan1android.Model.Story> {
    @Override
    public int compare(Story s1, Story s2) {
        return s1.getStoryName().compareToIgnoreCase(s2.getStoryName());
    }
}
