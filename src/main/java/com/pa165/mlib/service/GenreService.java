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
    
    GenreTO updateGenre(GenreTO oldGenre, GenreTO newGenre);
    
    boolean removeGenre(String name);
    
    List<GenreTO> getAllGenres();
    
}
