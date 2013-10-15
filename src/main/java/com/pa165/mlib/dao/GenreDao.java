package com.pa165.mlib.dao;

import com.pa165.mlib.entity.Genre;
import java.util.List;

/**
 *
 * @author xbek
 */
public interface GenreDao {

    /**
     * Persists the given genre to persistence context
     * @param genre
     */
    void addGenre(Genre genre);

    /**
     * Read all genres
     * @return
     */
    List<Genre> getAll();

    /**
     * Read genre via genre name
     * @param name
     * @return
     */
    Genre getGenre(String name);

    /**
     * Remove the given genre from persistence context
     * @param genre
     */
    void removeGenre(Genre genre);

    /**
     * Update the given genre
     * @param genre
     * @return
     */
    Genre updateGenre(Genre genre);
    
}
