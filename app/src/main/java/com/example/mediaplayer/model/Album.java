package com.example.mediaplayer.model;

public class Album {
    private String mAlbumName;
    private Long mAlbumId;

    public String getAlbumName() {
        return mAlbumName;
    }

    public void setAlbumName(String mAlbumName) {
        this.mAlbumName = mAlbumName;
    }

    public Long getAlbumId() {
        return mAlbumId;
    }

    public Album(String mAlbumName, Long mAlbumId) {
        this.mAlbumName = mAlbumName;
        this.mAlbumId = mAlbumId;
    }

    public Album(Long mAlbumId) {
        this.mAlbumId=mAlbumId;
    }

    public Album() {
    }
}
