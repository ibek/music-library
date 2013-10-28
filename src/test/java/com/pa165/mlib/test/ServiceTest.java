package com.pa165.mlib.test;

import static org.junit.Assert.*;

import com.pa165.mlib.dto.GenreTO;
import com.pa165.mlib.service.GenreService;
import com.pa165.mlib.service.impl.GenreServiceImpl;
import org.junit.Test;

/**
 *
 * @author xbek
 */
public class ServiceTest extends TestBase {
    
    @Test
    public void testGenreServiceCreate() throws Exception {
        GenreService gs = lookupBy(GenreServiceImpl.class);
        GenreTO g = gs.createNewGenre("rock");
        assertEquals(g.getName(), "rock");
    }
    
}
