package com.pa165.mlib.service;

import com.pa165.mlib.dto.ArtistTO;
import java.util.List;

/**
 * Album Service
 * 
 * @author ibek
 */
public interface ArtistService {
    
    ArtistTO createNewArtist(String name);
    
    ArtistTO getArtist(Long id);
    
    ArtistTO getArtist(String name);
    
    ArtistTO updateArtist(ArtistTO oldArtist, ArtistTO newArtist);
            
    boolean removeArtist(String name);
    
    boolean removeArtist(ArtistTO artist);
    
    List<ArtistTO> getAllArtists();
    
    
}
