package com.pa165.mlib.service;

import com.pa165.mlib.dto.Genre;
import java.util.List;

/**
 *
 * @author xbek
 */
public interface GenreService {
    
    List<Genre> getAllGenres();
    
    Genre createNewGenre(String name);
    
}
