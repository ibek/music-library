package com.pa165.mlib.service;

import com.pa165.mlib.dto.GenreTO;
import java.util.List;

/**
 *
 * @author xbek
 */
public interface GenreService {
    
    List<GenreTO> getAllGenres();
    
    GenreTO createNewGenre(String name);
    
}
