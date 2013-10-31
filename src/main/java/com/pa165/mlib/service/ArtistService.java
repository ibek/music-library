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
    
    ArtistTO updateArtist(ArtistTO oldArtist, ArtistTO newArtist);
            
    boolean removeArtist(Long id);
    
    boolean removeArtist(ArtistTO artist);
    
    List<ArtistTO> getAllArtists();
    
    List<ArtistTO> getArtistsByName(String name);
    
    
}
