package com.example.mediaplayer.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaplayer.model.Album;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumHolder> {

    private Context mContext;
    private List<Album> mItems;

    public void setAlbums(List<Album> items) {
        mItems = items;
    }

    public AlbumAdapter(Context context, List<Album> items) {
        mContext = context;
        mItems = items;
    }

    @NonNull
    @Override
    public AlbumHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView textView = new TextView(mContext);
        return new AlbumHolder(textView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumHolder holder, int position) {
        holder.bindAlbumُ(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class AlbumHolder extends RecyclerView.ViewHolder {

        private TextView mTitleText;
        private Album mAlbum;

        public AlbumHolder(@NonNull View itemView) {
            super(itemView);

            mTitleText = (TextView) itemView;
        }

        public void bindAlbumُ(Album Album) {
            mAlbum = Album;
            mTitleText.setText(mAlbum.getAlbumName());
        }
    }
}
