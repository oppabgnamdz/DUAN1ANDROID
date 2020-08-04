package com.example.duan1android.Adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1android.MainActivity;
import com.example.duan1android.Model.Type;
import com.example.duan1android.R;

import java.util.ArrayList;
import java.util.Locale;

public class AdapterCategoryStory extends RecyclerView.Adapter<AdapterCategoryStory.MyHolder> {
    private ArrayList<Type> items;
    ArrayList<Type> backupList;
    public AdapterCategoryStory(ArrayList<Type> items) {
        this.items = items;
        backupList = new ArrayList<>();
        backupList.addAll(items);
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
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        holder.tv1.setText(items.get(position).getTypeName());
        holder.tv2.setText("Số lượng truyện: " + String.valueOf(items.get(position).getTypeCount()));
        holder.tv1.setHeight(MainActivity.height / 20);
        holder.tv2.setHeight(MainActivity.height / 20);
        holder.image.setImageResource(items.get(position).getImageSource());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(MainActivity.width / 6, MainActivity.height / 10);
        holder.image.setLayoutParams(layoutParams);
        holder.view.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.nav_story, bundle));
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

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        items.clear();
        if (charText.length() == 0) {
            items.addAll(backupList);
        } else {
            for (Type type : backupList) {
                if (type.getTypeName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    items.add(type);
                }
            }
        }
        notifyDataSetChanged();
    }
}
