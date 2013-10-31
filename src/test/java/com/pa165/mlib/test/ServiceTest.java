package com.pa165.mlib.test;

import com.pa165.mlib.dao.GenreDao;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.pa165.mlib.dto.GenreTO;
import com.pa165.mlib.entity.Genre;
import com.pa165.mlib.service.impl.GenreServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author xbek
 */
public class ServiceTest {
    
    @Test
    public void testGenreServiceCreate() throws Exception {
        GenreServiceImpl gs = new GenreServiceImpl();
        
        GenreDao gd = mock(GenreDao.class);
        gs.setGenreDao(gd);
        
        GenreTO g = gs.createNewGenre("rock");
        assertEquals(g.getName(), "rock");
    }
    
    @Test
    public void testGenreServiceGetAll() throws Exception {
        GenreServiceImpl gs = new GenreServiceImpl();
        GenreDao gd = mock(GenreDao.class);
        
        when(gd.getAll()).thenReturn(new ArrayList<Genre>(){{
            Genre rock = new Genre();
            rock.setName("rock");
            add(rock);
            Genre trance = new Genre();
            trance.setName("trance");
            add(trance);
        }});
        
        gs.setGenreDao(gd);
        List<GenreTO> all = gs.getAllGenres();
        assertEquals(2, all.size());
        assertEquals("rock", all.get(0).getName());
        assertEquals("trance", all.get(1).getName());
    }
    
}
