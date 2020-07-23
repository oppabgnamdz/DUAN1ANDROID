package com.example.duan1android.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySQLOpenHelper extends SQLiteOpenHelper {
    String createType = "CREATE TABLE Type(TypeID integer primary key AUTOINCREMENT, TypeName text, TypeCount integer,ImageSource integer)";
    String createStory = "CREATE TABLE Story(StoryID integer primary key AUTOINCREMENT, TypeName text, StoryName text, StoryContent text, LikeStatus integer)";
    public MySQLOpenHelper(@Nullable Context context) {
        super(context,"MyProject", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createType);
        db.execSQL(createStory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
