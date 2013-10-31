package com.pa165.mlib.test;

import com.pa165.mlib.dao.AlbumDao;
import com.pa165.mlib.dto.AlbumTO;
import com.pa165.mlib.dao.ArtistDao;
import com.pa165.mlib.dao.GenreDao;
import com.pa165.mlib.dto.ArtistTO;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.pa165.mlib.dto.GenreTO;
import com.pa165.mlib.entity.Album;
import com.pa165.mlib.service.impl.AlbumServiceImpl;
import com.pa165.mlib.entity.Artist;
import com.pa165.mlib.entity.Genre;
import com.pa165.mlib.service.impl.ArtistServiceImpl;
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
        EntityDTOTransformer transformer = new EntityDTOTransformer();
        gs.setTransformer(transformer);
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
    
    @Test
    public void testAlbumServiceCRUD() throws Exception {
        AlbumServiceImpl as = new AlbumServiceImpl();
        EntityDTOTransformer transformer = new EntityDTOTransformer();
        as.setTransformer(transformer);
        
        AlbumDao ad = mock(AlbumDao.class);
        Album album = new Album();
        album.setTitle("bestOf");
        album.setReleased(2013);
        when(ad.getAlbum("bestOf")).thenReturn(album);
        
        as.setAlbumDao(ad);
        AlbumTO bestOf = as.createNewAlbum("bestOf", null, 2013, null);
        assertEquals("bestOf", bestOf.getTitle());
        assertEquals(2013, (int) bestOf.getReleased());
        
        AlbumTO bestOf2 = as.getAlbum("bestOf");
        assertEquals(bestOf, bestOf2);
        
        bestOf2.setReleased(2012);
        bestOf2 = as.updateAlbum(bestOf, bestOf2);
        assertEquals(2012, (int) bestOf2.getReleased());
        
        boolean removed = as.removeAlbum("bestOf");
        assertTrue(removed);
    }
    
    @Test
    public void testArtistServiceCRUD() throws Exception {
        
        ArtistServiceImpl as = new ArtistServiceImpl();
        EntityDTOTransformer transformer = new EntityDTOTransformer();
        as.setTransformer(transformer);
        
        ArtistDao ad = mock(ArtistDao.class);
        Artist artist = new Artist();
        artist.setName("Janis Joplin");
        when(ad.getArtist("Janis Joplin")).thenReturn(artist);
        when(ad.getArtist("Milos Zeman")).thenReturn(null);
        as.setArtistDao(ad);
        
        ArtistTO a = as.createNewArtist("Michal Hašek");
        assertEquals(a.getName(), "Michal Hašek");
        
        ArtistTO a2 = as.getArtist("Michal Hašek");
        assertEquals(a, a2);
        
        a2.setName("Michal Prášek");
        ArtistTO updated = as.updateArtist(a, a2);
        assertEquals(a2, updated);
        
        /*
        boolean removed = as.removeArtist();
        assertFalse(removed); // the mock DAO returns null for trance Genre
        GenreTO empty = gs.getGenre("trance");
        assertNull(empty);
        
        */
    }
    
}
