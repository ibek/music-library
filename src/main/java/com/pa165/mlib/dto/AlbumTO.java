package com.pa165.mlib.dto;

import java.util.List;

/**
 * Album Transfer Object
 * 
 * @author tomparys
 */
public class AlbumTO {
    
    private String title;
    
    private byte[] cover;
    
    private Integer released;
    
    private List<SongTO> songs;
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getCover() {
        return cover;
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }
    
    public Integer getReleased() {
        return released;
    }

    public void setReleased(Integer released) {
        this.released = released;
    }

    
    public List<SongTO> getSongs() {
        return songs;
    }

    public void setSongs(List<SongTO> songs) {
        this.songs = songs;
    }

}
