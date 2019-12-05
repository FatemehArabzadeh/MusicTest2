package com.example.mediaplayer.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaplayer.model.Song;


import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongHolder> {

    private Context mContext;
    private List<Song> mItems;
    public TextView textView;
    public void setSongs(List<Song> items) {
        mItems = items;
    }

    public SongAdapter(Context context, List<Song> items) {
        mContext = context;
        mItems = items;
        textView=new TextView(mContext);
    }

    @NonNull
    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        textView = new TextView(mContext);
        textView.setPadding(20,20,20,20);
        return new SongHolder(textView);
    }

    @Override
    public void onBindViewHolder(@NonNull SongHolder holder, int position) {
        holder.bindSongُ(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class SongHolder extends RecyclerView.ViewHolder {

        private TextView mTitleText;
        private Song mSong;

        public SongHolder(@NonNull View itemView) {
            super(itemView);

            mTitleText = (TextView) itemView;
        }

        public void bindSongُ(Song song) {
            mSong = song;
            mTitleText.setText(mSong.getName());
        }
    }
}
