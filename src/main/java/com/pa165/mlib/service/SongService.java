package com.pa165.mlib.service;

import com.pa165.mlib.dto.AlbumTO;
import com.pa165.mlib.dto.ArtistTO;
import com.pa165.mlib.dto.GenreTO;
import com.pa165.mlib.dto.SongTO;
import java.util.List;

/**
 * Album Service
 */
public interface SongService {
    
    List<SongTO> getAllSongs();
    
    List<SongTO> getSongByTitle(String title);
    
    SongTO createNewSong(String title, Integer bitrate, Integer position, String commentary,
                            GenreTO genre, AlbumTO album, ArtistTO artist);
    
}
