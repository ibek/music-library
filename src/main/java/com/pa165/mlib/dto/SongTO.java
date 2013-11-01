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
    private GenreTO genre;
    private AlbumTO album;
    private ArtistTO artist;

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
     * @return the bitrate
     */
    public Integer getBitrate() {
        return bitrate;
    }

    /**
     * @param bitrate the bitrate to set
     */
    public void setBitrate(Integer bitrate) {
        this.bitrate = bitrate;
    }

    /**
     * @return the position
     */
    public Integer getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Integer position) {
        this.position = position;
    }

    /**
     * @return the commentary
     */
    public String getCommentary() {
        return commentary;
    }

    /**
     * @param commentary the commentary to set
     */
    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    /**
     * @return the genre
     */
    public GenreTO getGenre() {
        return genre;
    }

    /**
     * @param genre the genre to set
     */
    public void setGenre(GenreTO genre) {
        this.genre = genre;
    }

    /**
     * @return the album
     */
    public AlbumTO getAlbum() {
        return album;
    }

    /**
     * @param album the album to set
     */
    public void setAlbum(AlbumTO album) {
        this.album = album;
    }

    /**
     * @return the artist
     */
    public ArtistTO getArtist() {
        return artist;
    }

    /**
     * @param artist the artist to set
     */
    public void setArtist(ArtistTO artist) {
        this.artist = artist;
    }
    
    
}
