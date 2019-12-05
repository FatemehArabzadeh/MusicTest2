package com.example.mediaplayer.repository;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import androidx.annotation.RequiresApi;

import com.example.mediaplayer.controller.MediaPlayerActivity;
import com.example.mediaplayer.model.Song;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SongRepository {
    private static SongRepository instance;
    List<Song> mSongs = new ArrayList<>();
    private String songName;
    Long songId;
    Uri SongUri;
    String SongArtist;
    MediaPlayer mediaPlayer;
    BottomSheetCallBack bottomSheetCallBack;


    protected Context mContext;

    public static SongRepository getInstance(Context mContext) {
        if (instance == null)
            instance = new SongRepository(mContext);

        return instance;


    }
    public void stop(){
        if(mediaPlayer==null)
            return;

        else if (mediaPlayer.isPlaying())
            mediaPlayer.stop();
    }
    public void play(Song mSong){
        try {mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(mContext, SongRepository.getInstance(mContext).getSongUri(mSong.getSongId()));
            bottomSheetCallBack.showBottomSheet();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();





    }

    public SongRepository(Context context) {
        mContext = context.getApplicationContext();
        if(context instanceof MediaPlayerActivity)
          bottomSheetCallBack= (BottomSheetCallBack) context;


    }

    public Uri getSongUri(Long mSongId) {
        //  SongUri= MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        SongUri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, mSongId);
        return SongUri;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<Song> getSongs() {
        ContentResolver songResolver = mContext.getContentResolver();
        Cursor songCursor = songResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null);
        try {
            songCursor.moveToFirst();
            while (!songCursor.isAfterLast()) {
                songId = songCursor.getLong(songCursor.getColumnIndex(MediaStore.Audio.Media._ID));
                songName = songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                SongArtist=songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));

                Song song = new Song(songId);
                song.setName(songName);
                song.setSongArtist(SongArtist);
                mSongs.add(song);
                songCursor.moveToNext();

            }
        } finally {
            songCursor.close();
        }

        return mSongs;
    }

    public void setSongs(List<Song> mSongs) {
        this.mSongs = mSongs;
    }
    public interface BottomSheetCallBack

    {
        public void showBottomSheet();

    }

}
