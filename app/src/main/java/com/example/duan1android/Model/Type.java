package com.example.duan1android.Model;

public class Type {
    int TypeID;
    String TypeName;
    int TypeCount;
    int imageSource;

    public int getTypeID() {
        return TypeID;
    }

    public void setTypeID(int typeID) {
        TypeID = typeID;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public int getTypeCount() {
        return TypeCount;
    }

    public void setTypeCount(int typeCount) {
        TypeCount = typeCount;
    }

    public int getImageSource() {
        return imageSource;
    }

    public void setImageSource(int imageSource) {
        this.imageSource = imageSource;
    }

    public Type(int typeID, String typeName, int typeCount, int imageSource) {
        TypeID = typeID;
        TypeName = typeName;
        TypeCount = typeCount;
        this.imageSource = imageSource;
    }
}
