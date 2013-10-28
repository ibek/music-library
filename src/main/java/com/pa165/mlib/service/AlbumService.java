package com.pa165.mlib.service;

import com.pa165.mlib.dto.AlbumTO;
import com.pa165.mlib.dto.SongTO;
import java.util.List;

/**
 * Album Service
 */
public interface AlbumService {
    
    List<AlbumTO> getAllAlbums();
    
    List<AlbumTO> getAlbumByTitle(String title);
    
    List<AlbumTO> getAlbumByRelease(Integer year);
    
    AlbumTO createNewAlbum(String title, Integer year, List<SongTO> songs);
    
}
