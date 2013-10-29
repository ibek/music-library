package com.pa165.mlib.dto;

import com.pa165.mlib.entity.Album;
import com.pa165.mlib.entity.Artist;
import com.pa165.mlib.entity.Genre;

/**
 * Song Transfer Object
 * 
 * @author brazdil
 */
public class SongTO {
    
    private String title;    
    private Integer bitrate;
    private Integer position;
    private String commentary;
    private Genre genre;
    private Album album;
    private Artist artist;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getBitrate() {
        return bitrate;
    }

    public void setBitrate(Integer bitrate) {
        this.bitrate = bitrate;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
    
    
}
