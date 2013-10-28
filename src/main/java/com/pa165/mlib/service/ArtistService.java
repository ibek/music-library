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
    
    List<ArtistTO> getArtistByName(String name);
    
    ArtistTO createNewArtist(String name, List<SongTO> songs);
    
}
