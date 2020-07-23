package com.example.duan1android.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.funnystory.DB.MySQLOpenHelper;
import com.example.funnystory.Model.Type;

import java.util.ArrayList;
import java.util.List;

public class TypeDAO {
    MySQLOpenHelper mySQLOpenHelper;
    SQLiteDatabase sqLiteDatabase;
    Context context;

    public TypeDAO(Context context) {
        this.context = context;
        mySQLOpenHelper = new MySQLOpenHelper(context);
        sqLiteDatabase = mySQLOpenHelper.getWritableDatabase();
    }

    public long insertType(Type type){
        ContentValues contentValues = new ContentValues();
        contentValues.put("TypeName", type.getTypeName());
        contentValues.put("TypeCount", String.valueOf(type.getTypeCount()));
        long result = sqLiteDatabase.insert("Type", null, contentValues);
        return result;
    }

    public List<Type> getAllType(){
        List<Type> list = new ArrayList<>();
        String querry = "Select * From Type";
        Cursor cursor = sqLiteDatabase.rawQuery(querry, null);
        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Type type = new Type();
                type.setTypeID(Integer.parseInt(cursor.getString(0)));
                type.setTypeName(cursor.getString(1));
                type.setTypeCount(Integer.parseInt(cursor.getString(2)));
                list.add(type);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list;
    }
    public List<String> getAllTypeName(){
        List<String> list = new ArrayList<>();
        String querry = "Select * From Type";
        Cursor cursor = sqLiteDatabase.rawQuery(querry, null);
        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                list.add(cursor.getString(1));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list;
    }
    public long updateType(Type type){
        ContentValues contentValues = new ContentValues();
        contentValues.put("TypeName", type.getTypeName());
        contentValues.put("TypeCount", String.valueOf(type.getTypeCount()));
        long result = sqLiteDatabase.update("Type",contentValues,"TypeID=?",new String[]{String.valueOf(type.getTypeID())});
        return result;
    }
}
