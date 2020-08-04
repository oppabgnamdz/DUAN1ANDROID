package com.example.duan1android.DAO;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.duan1android.DB.MySQLOpenHelper;
import com.example.duan1android.Model.Story;

import java.util.ArrayList;

public class StoryDao {
    SQLiteDatabase sqLiteDatabase;
    MySQLOpenHelper mySQLOpenHelper;
    Context context;

    public StoryDao(Context context) {
        this.context = context;
        mySQLOpenHelper = new MySQLOpenHelper(context);
        sqLiteDatabase = mySQLOpenHelper.getWritableDatabase();
    }

    public long insertStory(Story story) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("TYPENAME", story.getStoryType());
        contentValues.put("STORYNAME", story.getStoryName());
        contentValues.put("LIKESTATUS", story.getLikeStatus());
        contentValues.put("STORYCONTENT", story.getStoryContent());
        long result = sqLiteDatabase.insert("STORY", null, contentValues);
        return result;
    }

    public long updateStory(Story story) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("LIKESTATUS", story.getLikeStatus());
        String[] args = {String.valueOf(story.getStoryID())};
        long result = sqLiteDatabase.update("STORY", contentValues, "STORYID = ?", args);
        return result;
    }

    public ArrayList<Story> getAllStory(String name) {
        ArrayList<Story> stories = new ArrayList<>();
        String query = "SELECT * FROM STORY WHERE TYPENAME = ?";
        String[] args = {name};
        Cursor cursor = sqLiteDatabase.rawQuery(query, args);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = Integer.parseInt(cursor.getString(0));
            String typeStory = cursor.getString(1);
            String nameStory = cursor.getString(2);
            String content = cursor.getString(3);
            int status = Integer.parseInt(cursor.getString(4));
            Story story = new Story(id, typeStory, nameStory, content, status);
            stories.add(story);
            cursor.moveToNext();
        }
        cursor.close();
        return stories;

    }

    public ArrayList<Story> getAllStory(int statusInput) {
        ArrayList<Story> stories = new ArrayList<>();
        String query = "SELECT * FROM STORY WHERE LIKESTATUS = ?";
        String[] args = {String.valueOf(statusInput)};
        Cursor cursor = sqLiteDatabase.rawQuery(query, args);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = Integer.parseInt(cursor.getString(0));
            String typeStory = cursor.getString(1);
            String nameStory = cursor.getString(2);
            String content = cursor.getString(3);
            int status = Integer.parseInt(cursor.getString(4));
            Story story = new Story(id, typeStory, nameStory, content, status);
            stories.add(story);
            cursor.moveToNext();
        }
        cursor.close();
        return stories;

    }

    public ArrayList<Story> getAllStory() {
        ArrayList<Story> stories = new ArrayList<>();
        String query = "SELECT * FROM STORY ";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = Integer.parseInt(cursor.getString(0));
            String typeStory = cursor.getString(1);
            String nameStory = cursor.getString(2);
            String content = cursor.getString(3);
            int status = Integer.parseInt(cursor.getString(4));
            Story story = new Story(id, typeStory, nameStory, content, status);
            stories.add(story);
            cursor.moveToNext();
        }
        cursor.close();
        return stories;

    }

    public Story getStory(String name) {
        String query = "SELECT * FROM STORY WHERE StoryName = ? ";
        String[] args = {name};
        Cursor cursor = sqLiteDatabase.rawQuery(query, args);
        cursor.moveToFirst();
        int id = Integer.parseInt(cursor.getString(0));
        String typeStory = cursor.getString(1);
        String nameStory = cursor.getString(2);
        String content = cursor.getString(3);
        int status = Integer.parseInt(cursor.getString(4));
        Story story = new Story(id, typeStory, nameStory, content, status);
        cursor.close();
        return story;

    }

}
