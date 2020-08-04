package com.example.duan1android.Adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1android.DAO.StoryDao;
import com.example.duan1android.Model.Story;
import com.example.duan1android.R;

import java.util.ArrayList;

public class AdapterStory extends RecyclerView.Adapter<AdapterStory.ViewHolder> {
    ArrayList<Story> stories;
    StoryDao storyDao;

    public AdapterStory(ArrayList<Story> stories) {
        this.stories = stories;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_story, parent, false);
        storyDao = new StoryDao(parent.getContext());
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Bundle bundle = new Bundle();
        bundle.putString("nameStory", stories.get(position).getStoryName());
        bundle.putString("nameType", stories.get(position).getStoryType());
        holder.tvNameStory.setText(stories.get(position).getStoryName());
        if (stories.get(position).getLikeStatus() == 0) {
            holder.imageStory.setImageResource(R.drawable.favourite);
        } else {
            holder.imageStory.setImageResource(R.drawable.yes);
        }
        holder.view.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.nav_currentstory, bundle));
        holder.imageStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Story story = stories.get(position);
                if (story.getLikeStatus() == 0) {
                    story.setLikeStatus(1);
                    long update = storyDao.updateStory(story);
                    if (update > 0) {
                        holder.imageStory.setImageResource(R.drawable.yes);
                        Toast.makeText(holder.imageStory.getContext(), "Đã yêu thích thành công", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    story.setLikeStatus(0);
                    long update = storyDao.updateStory(story);
                    if (update > 0) {
                        holder.imageStory.setImageResource(R.drawable.favourite);
                        Toast.makeText(holder.imageStory.getContext(), "Đã bỏ thích thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView tvNameStory;
        private ImageView imageStory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvNameStory = itemView.findViewById(R.id.tvNameStory);
            this.imageStory = itemView.findViewById(R.id.imageStory);
            this.view = itemView;
        }
    }
}
