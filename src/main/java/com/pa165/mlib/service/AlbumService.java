package com.pa165.mlib.service;

import com.pa165.mlib.dto.AlbumTO;
import com.pa165.mlib.dto.SongTO;
import java.util.List;

/**
 * Album Service
 */
public interface AlbumService {
    
    List<AlbumTO> getAllAlbums();
    
    AlbumTO createNewAlbum(String title, byte[] cover, Integer year, List<SongTO> songs);
    
    AlbumTO getAlbum(String title);
    
    AlbumTO updateAlbum(AlbumTO oldAlbum, AlbumTO newAlbum);
    
    boolean removeAlbum(String title);
    
}
