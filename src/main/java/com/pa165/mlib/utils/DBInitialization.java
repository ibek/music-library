package com.pa165.mlib.utils;

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
        gs.createNewGenre("Alternative");
        gs.createNewGenre("Pop");
        gs.createNewGenre("Rock");
    }
    
}
