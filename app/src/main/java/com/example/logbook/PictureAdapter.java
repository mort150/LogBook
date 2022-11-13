package com.example.logbook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logbook.database.PictureEntity;

import java.util.List;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {
    public interface OnClickListener {
        public void OnClick(View view, int position);
    }

    public PictureAdapter(List<PictureEntity> pictures) {
        this.pictures = pictures;
    }

    private List<PictureEntity> pictures;

    public List<PictureEntity> getPictures() {
        return pictures;
    }

    public void setPictures(List<PictureEntity> pictures) {
        this.pictures = pictures;
    }

    public PictureAdapter.OnClickListener onClickListener;

    public void setOnClickListener(PictureAdapter.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView pictureName;
        public TextView description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pictureName = itemView.findViewById(R.id.pictureName);
            description = itemView.findViewById(R.id.description);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onClickListener.OnClick(view, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public PictureAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        return new PictureAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PictureAdapter.ViewHolder holder, int position) {
        holder.pictureName.setText(pictures.get(position).name);
        holder.description.setText("Created at: " + pictures.get(position).create_at);
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }
}

