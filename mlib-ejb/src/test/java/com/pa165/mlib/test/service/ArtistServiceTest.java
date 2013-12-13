package com.pa165.mlib.test.service;

import com.pa165.mlib.dao.AlbumDao;
import com.pa165.mlib.dto.AlbumTO;
import com.pa165.mlib.dao.ArtistDao;
import com.pa165.mlib.dao.GenreDao;
import com.pa165.mlib.dao.SongDao;
import com.pa165.mlib.dto.ArtistTO;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.pa165.mlib.dto.GenreTO;
import com.pa165.mlib.dto.SongTO;
import com.pa165.mlib.entity.Album;
import com.pa165.mlib.service.impl.AlbumServiceImpl;
import com.pa165.mlib.entity.Artist;
import com.pa165.mlib.entity.Genre;
import com.pa165.mlib.entity.Song;
import com.pa165.mlib.service.impl.ArtistServiceImpl;
import com.pa165.mlib.service.impl.GenreServiceImpl;
import com.pa165.mlib.service.impl.SongServiceImpl;
import com.pa165.mlib.utils.EntityDTOTransformer;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author xbek
 */
public class ArtistServiceTest {
    
    @Test
    public void testArtistServiceCRUD() throws Exception {
       
        ArtistServiceImpl as = new ArtistServiceImpl();
        EntityDTOTransformer transformer = new EntityDTOTransformer();
        as.setTransformer(transformer);
        
        ArtistDao ad = mock(ArtistDao.class);
        Artist artist = new Artist();
        artist.setName("Lou Reed");
        Artist artist2 = new Artist();
        artist2.setName("Velvet Underground");
        when(ad.getArtist("Lou Reed")).thenReturn(artist);
        when(ad.getArtist("Velvet Underground")).thenReturn(artist2);
        when(ad.getArtist("James Bond")).thenReturn(null);
        as.setArtistDao(ad);
        
        ArtistTO ato = new ArtistTO();
        ato.setName("Lou Reed");
        ArtistTO a = as.createNewArtist(ato);
        assertEquals(a.getName(), "Lou Reed");
        
        ArtistTO a2 = as.getArtist("Lou Reed");
        assertEquals(a, a2);
        
        a2.setName("Velvet Underground");
        ArtistTO updated = as.updateArtist(a, a2);
        assertEquals(a2, updated);
        
        boolean removed = as.removeArtist("Lou Reed");
        assertTrue(removed);
        
        removed = as.removeArtist(a2);
        assertTrue(removed);
    }
    
    @Test
    public void testArtistServiceGetAll() throws Exception {
        ArtistServiceImpl as = new ArtistServiceImpl();
        EntityDTOTransformer transformer = new EntityDTOTransformer();
        as.setTransformer(transformer);
        ArtistDao ad = mock(ArtistDao.class);
        
        when(ad.getAll()).thenReturn(new ArrayList<Artist>(){{
            Artist a1 = new Artist();
            a1.setName("Billy Preston");
            add(a1);
            Artist a2 = new Artist();
            a2.setName("Leon Russell");
            add(a2);
        }});
        
        as.setArtistDao(ad);
        List<ArtistTO> all = as.getAllArtists();
        assertEquals(2, all.size());
        assertEquals("Billy Preston", all.get(0).getName());
        assertEquals("Leon Russell", all.get(1).getName());
    }
}
