package com.example.quanlithuvien;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SellingBookAdapter extends RecyclerView.Adapter<SellingBookAdapter.ViewHolder> {
    ArrayList<MainModelBook> mainModelBooks;
    Context context;

    public SellingBookAdapter(ArrayList<MainModelBook> mainModelBooks, Context context) {
        this.mainModelBooks = mainModelBooks;
        this.context = context;
    }

    @NonNull
    @Override
    public SellingBookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SellingBookAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageResource(mainModelBooks.get(position).getLangLogo());
        holder.textView.setText(mainModelBooks.get(position).getLangName());
    }

    @Override
    public int getItemCount() {
        return mainModelBooks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_vieww);
            textView = itemView.findViewById(R.id.text_vieww);
        }
    }
}
