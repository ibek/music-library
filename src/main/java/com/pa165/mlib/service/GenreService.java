package com.pa165.mlib.service;

import com.pa165.mlib.dto.GenreTO;
import java.util.List;

/**
 *
 * @author xbek
 */
public interface GenreService {
    
    GenreTO createNewGenre(String name);
    
    GenreTO getGenre(String name);
    
    GenreTO updateGenre(GenreTO genre);
    
    boolean removeGenre(String genre);
    
    List<GenreTO> getAllGenres();
    
    List<GenreTO> getGenres(String... names);
    
}
