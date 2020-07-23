package com.example.duan1android.Model;

public class Type {
    int TypeID;
    String TypeName;
    int TypeCount;
    public Type(){

    }
    public Type(String typeName, int TypeCount) {
        TypeName = typeName;
        this.TypeID = 0;
        this.TypeCount = TypeCount;
    }

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

    @Override
    public String toString() {
        return "Type{" +
                "TypeID=" + TypeID +
                ", TypeName='" + TypeName + '\'' +
                ", TypeCount=" + TypeCount +
                '}';
    }
}
