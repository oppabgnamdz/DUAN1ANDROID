package com.example.duan1android.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1android.MainActivity;
import com.example.duan1android.R;
import com.example.duan1android.CategoryStory;

import java.util.ArrayList;

public class AdapterCategoryStory extends RecyclerView.Adapter<AdapterCategoryStory.MyHolder> {
    private ArrayList<CategoryStory> items;
    public OnClickItem onClickItem;

    public AdapterCategoryStory(ArrayList<CategoryStory> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_category_story, parent, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        holder.tv1.setText(items.get(position).getName());
        holder.tv2.setText("Số lượng truyện: " + String.valueOf(items.get(position).getNumber()));
        holder.tv1.setHeight(MainActivity.height / 20);
        holder.tv2.setHeight(MainActivity.height / 20);
        holder.image.setImageResource(items.get(position).getSourceImage());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(MainActivity.width / 6, MainActivity.height / 10);
        holder.image.setLayoutParams(layoutParams);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItem.callBackClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public TextView tv1, tv2;
        public ImageView image;
        public View view;

        public MyHolder(@NonNull View view) {
            super(view);
            this.view = view;
            tv1 = view.findViewById(R.id.tvName);
            tv2 = view.findViewById(R.id.tvNumber);
            image = view.findViewById(R.id.image);
        }
    }
    public void onCLick(OnClickItem onClickItem){
        this.onClickItem = onClickItem;
    }

    public interface OnClickItem {
        void callBackClick(int position);
    }
}
