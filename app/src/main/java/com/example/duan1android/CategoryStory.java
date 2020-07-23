package com.example.duan1android;

public class CategoryStory {
    private String name ;
    private int number ;
    private int sourceImage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSourceImage() {
        return sourceImage;
    }

    public void setSourceImage(int sourceImage) {
        this.sourceImage = sourceImage;
    }

    public CategoryStory(String name, int number, int sourceImage) {
        this.name = name;
        this.number = number;
        this.sourceImage = sourceImage;
    }
}
