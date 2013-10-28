package com.pa165.mlib.service;

import com.pa165.mlib.dto.AlbumTO;
import com.pa165.mlib.dto.ArtistTO;
import com.pa165.mlib.dto.SongTO;
import java.util.List;

/**
 * Album Service
 */
public interface ArtistService {
    
    List<ArtistTO> getAllArtists();
    
    List<AlbumTO> getArtistByName(String name);
    
    AlbumTO createNewArtist(String name, List<SongTO> songs);
    
}
