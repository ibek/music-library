package com.pa165.mlib.utils;

import com.pa165.mlib.dto.GenreTO;
import com.pa165.mlib.exception.DuplicateException;
import com.pa165.mlib.service.GenreService;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author ibek
 */
@Startup
@Singleton
public class DBInitialization {
    
    @Inject
    GenreService gs;
    
    @PostConstruct
    public void init() {
        System.out.println("initializing database ...");
        GenreTO genre = new GenreTO();
        genre.setName("Alternative");
        try {
            gs.createNewGenre(genre);
            genre.setName("Pop");
            gs.createNewGenre(genre);
            genre.setName("Rock");
            gs.createNewGenre(genre);
        } catch (DuplicateException ex) {
            
        }
    }
    
}
