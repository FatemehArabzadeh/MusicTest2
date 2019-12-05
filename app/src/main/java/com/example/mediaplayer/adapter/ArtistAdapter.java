package com.example.mediaplayer.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaplayer.model.Artist;

import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistHolder> {

    private Context mContext;
    private List<Artist> mItems;

    public void setArtists(List<Artist> items) {
        mItems = items;
    }

    public ArtistAdapter(Context context, List<Artist> items) {
        mContext = context;
        mItems = items;
    }

    @NonNull
    @Override
    public ArtistHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView textView = new TextView(mContext);
        return new ArtistHolder(textView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistHolder holder, int position) {
        holder.bindArtistُ(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ArtistHolder extends RecyclerView.ViewHolder {

        private TextView mTitleText;
        private Artist mArtist;

        public ArtistHolder(@NonNull View itemView) {
            super(itemView);

            mTitleText = (TextView) itemView;
        }

        public void bindArtistُ(Artist Artist) {
            mArtist = Artist;
            mTitleText.setText(mArtist.getArtistName());
        }
    }
}
