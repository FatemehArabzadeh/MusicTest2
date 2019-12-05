package com.example.mediaplayer.repository;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import androidx.annotation.RequiresApi;

import com.example.mediaplayer.model.Album;

import java.util.ArrayList;
import java.util.List;

public class AlbumRepository {
    private static AlbumRepository instance;
    List<Album> mAlbums=new ArrayList<>();

    private String AlbumName ;
    Long AlbumId;

    private Context mContext;
    public static AlbumRepository getInstance(Context mContext) {
        if (instance == null)
            instance = new AlbumRepository(mContext);

        return instance;

    }

    public AlbumRepository(Context context) {
        mContext =context.getApplicationContext();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<Album> getAlbums() {
     ContentResolver AlbumResolver = mContext.getContentResolver();
        Uri AlbumUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor AlbumCursor=AlbumResolver.query(AlbumUri,null,null,null);
        try {
            AlbumCursor.moveToFirst();
            while (!AlbumCursor.isAfterLast()) {
                AlbumId = AlbumCursor.getLong(AlbumCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
                AlbumName = AlbumCursor.getString(AlbumCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));

                Album album = new Album(AlbumId);
                album.setAlbumName(AlbumName);
                mAlbums.add(album);
                AlbumCursor.moveToNext();
            }
        }finally {
            AlbumCursor.close();
        }
        return mAlbums;
    }
    public void setAlbums(List<Album> mAlbums) {
        this.mAlbums = mAlbums;
    }
}
