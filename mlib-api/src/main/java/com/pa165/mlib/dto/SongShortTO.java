package com.pa165.mlib.dto;

import java.util.Objects;

/**
 *
 * @author brazdil
 */
public class SongShortTO {
    
    private String title;    
    private ArtistTO artist;
    private AlbumTO album;
    
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.title);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SongShortTO other = (SongShortTO) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return true;
    }
    
}
