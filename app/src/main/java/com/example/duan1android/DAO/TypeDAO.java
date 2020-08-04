package com.example.duan1android.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1android.DB.MySQLOpenHelper;
import com.example.duan1android.Model.Type;

import java.util.ArrayList;
import java.util.List;

public class  TypeDAO {
    MySQLOpenHelper mySQLOpenHelper;
    SQLiteDatabase sqLiteDatabase;
    Context context;

    public TypeDAO(Context context) {
        this.context = context;
        mySQLOpenHelper = new MySQLOpenHelper(context);
        sqLiteDatabase = mySQLOpenHelper.getWritableDatabase();
    }

    public long insertType(Type type) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("TypeName", type.getTypeName());
        contentValues.put("TypeCount", String.valueOf(type.getTypeCount()));
        contentValues.put("ImageSource", String.valueOf(type.getImageSource()));
        long result = sqLiteDatabase.insert("Type", null, contentValues);
        return result;
    }

    public List<Type> getAllType() {
        List<Type> list = new ArrayList<>();
        String querry = "Select * From Type";
        Cursor cursor = sqLiteDatabase.rawQuery(querry, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                int typeName = Integer.parseInt(cursor.getString(2));
                int imageSource = Integer.parseInt(cursor.getString(3));
                Type type = new Type(id, name, typeName, imageSource);
                list.add(type);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list;
    }

    public List<String> getAllTypeName() {
        List<String> list = new ArrayList<>();
        String querry = "Select * From Type";
        Cursor cursor = sqLiteDatabase.rawQuery(querry, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                list.add(cursor.getString(1));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list;
    }

    public long updateType(Type type) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("TypeName", type.getTypeName());
        contentValues.put("TypeCount", String.valueOf(type.getTypeCount()));
        contentValues.put("ImageSource", String.valueOf(type.getImageSource()));
        long result = sqLiteDatabase.update("Type", contentValues, "TypeID=?", new String[]{String.valueOf(type.getTypeID())});
        return result;
    }
}
