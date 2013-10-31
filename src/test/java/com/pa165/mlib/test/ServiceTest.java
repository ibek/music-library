package com.pa165.mlib.test;

import com.pa165.mlib.dao.GenreDao;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.pa165.mlib.dto.GenreTO;
import com.pa165.mlib.entity.Genre;
import com.pa165.mlib.service.impl.GenreServiceImpl;
import com.pa165.mlib.utils.EntityDTOTransformer;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author xbek
 */
public class ServiceTest {
    
    @Test
    public void testGenreServiceCRUD() throws Exception {
        GenreServiceImpl gs = new GenreServiceImpl();
        EntityDTOTransformer transformer = new EntityDTOTransformer();
        gs.setTransformer(transformer);
        
        GenreDao gd = mock(GenreDao.class);
        Genre rock = new Genre();
        rock.setName("rock");
        when(gd.getGenre("rock")).thenReturn(rock);
        when(gd.getGenre("trance")).thenReturn(null);
        gs.setGenreDao(gd);
        
        GenreTO g = gs.createNewGenre("rock");
        assertEquals(g.getName(), "rock");
        
        GenreTO g2 = gs.getGenre("rock");
        assertEquals(g, g2);
        
        g2.setName("trance");
        GenreTO updated = gs.updateGenre(g, g2);
        assertEquals(g2, updated);
        
        boolean removed = gs.removeGenre("trance");
        assertFalse(removed); // the mock DAO returns null for trance Genre
        GenreTO empty = gs.getGenre("trance");
        assertNull(empty);
        
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
