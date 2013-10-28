package com.pa165.mlib.service;

import com.pa165.mlib.dto.AlbumTO;
import com.pa165.mlib.dto.ArtistTO;
import com.pa165.mlib.dto.GenreTO;
import java.util.List;

/**
 * Album Service
 */
public interface SongService {
    
    List<AlbumTO> getAllSongs();
    
    List<AlbumTO> getSongByTitle(String title);
    
    AlbumTO createNewSong(String title, Integer bitrate, Integer position, String commentary,
                            GenreTO genre, AlbumTO album, ArtistTO artist);
    
}
