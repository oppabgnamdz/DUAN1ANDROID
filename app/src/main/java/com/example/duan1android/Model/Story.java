package com.example.duan1android.Model;

public class Story {
    int StoryID;
    String StoryType;
    String StoryName;
    String StoryContent;
    int LikeStatus;

    public Story(int storyID, String storyType, String storyName, String storyContent) {
        StoryID = storyID;
        StoryType = storyType;
        StoryName = storyName;
        StoryContent = storyContent;
        this.LikeStatus = 0;
    }
    public Story(){

    }

    public int getStoryID() {
        return StoryID;
    }

    public void setStoryID(int storyID) {
        StoryID = storyID;
    }

    public String getStoryType() {
        return StoryType;
    }

    public void setStoryType(String storyType) {
        StoryType = storyType;
    }

    public String getStoryName() {
        return StoryName;
    }

    public void setStoryName(String storyName) {
        StoryName = storyName;
    }

    public String getStoryContent() {
        return StoryContent;
    }

    public void setStoryContent(String storyContent) {
        StoryContent = storyContent;
    }

    public int getLikeStatus() {
        return LikeStatus;
    }

    public void setLikeStatus(int likeStatus) {
        LikeStatus = likeStatus;
    }

    @Override
    public String toString() {
        return "Story{" +
                "StoryID=" + StoryID +
                ", StoryType='" + StoryType + '\'' +
                ", StoryName='" + StoryName + '\'' +
                ", StoryContent='" + StoryContent + '\'' +
                ", LikeStatus=" + LikeStatus +
                '}';
    }
}
