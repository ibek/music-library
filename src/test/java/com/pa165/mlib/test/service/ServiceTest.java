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
        
        GenreTO ng = new GenreTO();
        ng.setName("rock");
        GenreTO g = gs.createNewGenre(ng);
        assertEquals(g.getName(), "rock");
        
        GenreTO g2 = gs.getGenre("rock");
        assertEquals(g, g2);
        
        g2.setName("trance");
        GenreTO updated = gs.updateGenre(g, g2);
        assertEquals(g2, updated);
        
        GenreTO gto = new GenreTO();
        gto.setName("trance");
        boolean removed = gs.removeGenre(gto);
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
    public void testAlbumServiceGetAll() throws Exception {
        AlbumServiceImpl as = new AlbumServiceImpl();
        EntityDTOTransformer transformer = new EntityDTOTransformer();
        as.setTransformer(transformer);
        AlbumDao ad = mock(AlbumDao.class);
        
        when(ad.getAll()).thenReturn(new ArrayList<Album>(){{
            Album a1 = new Album();
            a1.setTitle("a1");
            a1.setReleased(2012);
            add(a1);
            Album a2 = new Album();
            a2.setTitle("a2");
            a2.setReleased(2013);
            add(a2);
        }});
        
        as.setAlbumDao(ad);
        List<AlbumTO> all = as.getAllAlbums();
        assertEquals(2, all.size());
        assertEquals("a1", all.get(0).getTitle());
        assertEquals("a2", all.get(1).getTitle());
    }
    
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
        
        ArtistTO a = as.createNewArtist("Lou Reed");
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
    
    
    @Test
    public void testSongServiceCRUD() throws Exception {
        SongServiceImpl songService = new SongServiceImpl();
        EntityDTOTransformer transformer = new EntityDTOTransformer();
        songService.setTransformer(transformer);
        SongDao songDao = mock(SongDao.class);
        songService.setSongDao(songDao);
        
        String title = "Title";
        Integer bitrate = 256;
        Integer position = 3;
        String commentary = "Social Commentary";
        
        Song s = new Song();
        s.setTitle(title);
        s.setBitrate(bitrate);
        s.setPosition(position);
        s.setCommentary(commentary);
        
        when(songDao.getSong(title)).thenReturn(s);       
        
        SongTO proTestSong = songService.createNewSong(title, bitrate, position, commentary, null, null, null);
        assertEquals(title, proTestSong.getTitle());
        assertEquals(bitrate, proTestSong.getBitrate());
        assertEquals(position, proTestSong.getPosition());
        assertEquals(commentary, proTestSong.getCommentary());
        
        SongTO protiTestSong = songService.getSong(title);
        assertEquals(proTestSong, protiTestSong);
        
        String newTitle = "Remastered";
        protiTestSong.setTitle(newTitle);
        protiTestSong = songService.updateSong(proTestSong, protiTestSong);
        assertEquals(newTitle, protiTestSong.getTitle());
        
        boolean removed = songService.removeSong(title);
        assertTrue(removed);
        
        removed = songService.removeSong(proTestSong);
        assertTrue(removed);
    }
    
    @Test
    public void testSongServiceGetAll() throws Exception {
        SongServiceImpl songService = new SongServiceImpl();
        EntityDTOTransformer transformer = new EntityDTOTransformer();
        songService.setTransformer(transformer);
        SongDao songDao = mock(SongDao.class);
        songService.setSongDao(songDao);
        
        when(songDao.getAll()).thenReturn(new ArrayList<Song>(){{
            Song s1 = new Song();
            s1.setTitle("Song 1");
            add(s1);
            Song s2 = new Song();
            s2.setTitle("Song 2");
            add(s2);
        }});
        
        List<SongTO> all = songService.getAllSongs();
        assertEquals(2, all.size());
        assertEquals("Song 1", all.get(0).getTitle());
        assertEquals("Song 2", all.get(1).getTitle());
        /**/
    }
}
