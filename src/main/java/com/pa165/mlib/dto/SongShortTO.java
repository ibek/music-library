package com.pa165.mlib.dto;

import com.pa165.mlib.entity.Album;
import com.pa165.mlib.entity.Artist;

/**
 *
 * @author brazdil
 */
public class SongShortTO {
    
    private String title;    
    private Artist artist;
    private Album album;
    
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the album
     */
    public Album getAlbum() {
        return album;
    }

    /**
     * @param album the album to set
     */
    public void setAlbum(Album album) {
        this.album = album;
    }

    /**
     * @return the artist
     */
    public Artist getArtist() {
        return artist;
    }

    /**
     * @param artist the artist to set
     */
    public void setArtist(Artist artist) {
        this.artist = artist;
    }
    
}
