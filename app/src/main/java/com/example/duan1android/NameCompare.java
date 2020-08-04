package com.example.duan1android;

import com.example.duan1android.Model.Type;
import com.example.duan1android.ui.Story;

import java.util.Comparator;

public class NameCompare implements Comparator<Type> {
    @Override
    public int compare(Type t1, Type t2) {
        return t1.getTypeName().compareToIgnoreCase(t2.getTypeName());
    }
}
