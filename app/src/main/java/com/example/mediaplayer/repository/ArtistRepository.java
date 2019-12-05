package com.example.mediaplayer.repository;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import androidx.annotation.RequiresApi;

import com.example.mediaplayer.model.Artist;

import java.util.ArrayList;
import java.util.List;

public class ArtistRepository {
    private static ArtistRepository instance;
    List<Artist> mArtists=new ArrayList<>();

    private String ArtistName ;
    Long ArtistId;

    private Context mContext;
    public static ArtistRepository getInstance(Context mContext) {
        if (instance == null)
            instance = new ArtistRepository(mContext);

        return instance;

    }

    public ArtistRepository(Context context) {
        mContext =context.getApplicationContext();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<Artist> getArtists() {
        ContentResolver ArtistResolver = mContext.getContentResolver();
        Uri ArtistUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor ArtistCursor=ArtistResolver.query(ArtistUri,null,null,null);
        try {
            ArtistCursor.moveToFirst();
            while (!ArtistCursor.isAfterLast()) {
                ArtistId = ArtistCursor.getLong(ArtistCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST_ID));
                ArtistName = ArtistCursor.getString(ArtistCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));

                Artist artist = new Artist(ArtistId);
                artist.setArtistName(ArtistName);
                mArtists.add(artist);
                ArtistCursor.moveToNext();
            }
        }
        finally {
            ArtistCursor.close();
        }

        return mArtists;
    }
    public void setArtists(List<Artist> mArtists) {
        this.mArtists = mArtists;
    }
}
