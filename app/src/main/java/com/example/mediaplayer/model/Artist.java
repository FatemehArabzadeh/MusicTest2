package com.example.mediaplayer.model;

public class Artist {
    private String mArtistName;
    private Long mArtistId;

    public String getArtistName() {
        return mArtistName;
    }

    public void setArtistName(String mArtistName) {
        this.mArtistName = mArtistName;
    }

    public Long getArtistId() {
        return mArtistId;
    }

    public Artist(String mArtistName, Long mArtistId) {
        this.mArtistName = mArtistName;
        this.mArtistId = mArtistId;
    }

    public Artist(Long mArtistId) {
        this.mArtistId=mArtistId;
    }

    public Artist() {
    }
}
