package com.pa165.mlib.dto;

import com.pa165.mlib.entity.Song;
import java.util.List;

/**
 * Album Transfer Object
 * 
 * @author tomparys
 */
public class AlbumTO {
    
    private String title;
    
    private String cover;
    
    private String released;
    
    private List<SongTO> songs;
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
    
    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    
    public List<SongTO> getSongs() {
        return songs;
    }

    public void setSongs(List<SongTO> songs) {
        this.songs = songs;
    }

}
