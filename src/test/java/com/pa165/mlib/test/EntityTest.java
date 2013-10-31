package com.pa165.mlib.test;

import com.pa165.mlib.dao.AlbumDao;
import com.pa165.mlib.dao.ArtistDao;
import com.pa165.mlib.dao.GenreDao;
import com.pa165.mlib.dao.SongDao;
import com.pa165.mlib.dao.impl.AlbumDaoImpl;
import com.pa165.mlib.dao.impl.ArtistDaoImpl;
import static org.junit.Assert.*;

import com.pa165.mlib.dao.impl.GenreDaoImpl;
import com.pa165.mlib.dao.impl.SongDaoImpl;
import com.pa165.mlib.entity.Album;
import com.pa165.mlib.entity.Artist;
import com.pa165.mlib.entity.Genre;
import com.pa165.mlib.entity.Song;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import org.junit.Test;

/**
 *
 * @author ibek, brazdil
 */
public class EntityTest extends TestBase {

    @Test
    public void testGenrePersist() throws Exception {
        Genre rock = new Genre();
        rock.setName("rock");
        GenreDao gm = lookupBy(GenreDaoImpl.class);
        gm.addGenre(rock);
        Genre rock2 = gm.getGenre("rock");
        assertEquals(rock, rock2);
    }
    
    @Test
    public void testSongRemove() throws Throwable {
        Song s = new Song();
        s.setTitle("mysong");
        SongDao sd = lookupBy(SongDaoImpl.class);
        sd.addSong(s);
        Song s2 = sd.getSong(s.getId());
        sd.removeSong(s2);
        Song empty = sd.getSong(s.getId());
        assertNull(empty);
    }
    
    @Test
    public void testSongSearch() throws Exception {
        Song lala = new Song();
        lala.setTitle("lala");
        SongDao sm = lookupBy(SongDaoImpl.class);
        sm.addSong(lala);
        Song ohm = new Song();
        String ohmTitle = "ohm";
        ohm.setTitle(ohmTitle);
        sm.addSong(ohm);
        List<Song> results = sm.getSongsWithTitle(ohmTitle);
        assertEquals(1, results.size());
        assertEquals(ohm.getTitle(), results.get(0).getTitle());
    }
    
    @Test
    public void testAlbumsWithArtist() throws Exception {
        
        Artist artist1 = new Artist();
        artist1.setName("Yoshida Brothers");
        ArtistDao arM = lookupBy(ArtistDaoImpl.class);
        arM.addArtist(artist1);
        
        Album album1 = new Album();
        album1.setTitle("Best of Asia");
        album1.setReleased(2001);
        AlbumDao am = lookupBy(AlbumDaoImpl.class);
        am.addAlbum(album1);
        
        Album album2 = new Album();
        album2.setTitle("Inside of the Sun");
        album2.setReleased(2001);
        am.addAlbum(album2);
        
        Song song1 = new Song();
        song1.setTitle("Comodo");
        song1.setArtist(artist1);
        song1.setAlbum(album1);
        SongDao sm = lookupBy(SongDaoImpl.class);
        sm.addSong(song1);
        
        Song song2 = new Song();
        song2.setTitle("Kodo");
        song2.setArtist(artist1);
        song2.setAlbum(album2);
        sm.addSong(song2);
        
        Song song3 = new Song();
        song3.setTitle("Kagero");
        song3.setArtist(artist1);
        song3.setAlbum(album2);
        sm.addSong(song3);

        List<Album> results = am.getAlbumsWithArtist(artist1);
        assertEquals(2, results.size());
        assertTrue(results.contains(album1));
        assertTrue(results.contains(album2));
    }
        
    @Test
    public void testArtistCRUD() throws Exception {
        Artist mike = new Artist();
        mike.setName("Mike");
        ArtistDao am = lookupBy(ArtistDaoImpl.class);
        am.addArtist(mike);
        
        Artist mike2 = am.getArtist(mike.getId());
        assertEquals(mike, mike2);
        
        mike.setName("Michael");
        mike = am.updateArtist(mike);
        
        mike2 = am.getArtist(mike.getId());
        assertEquals("Michael", mike2.getName());
        
        am.removeArtist(mike2);
        mike2 = am.getArtist(mike2.getId());
        assertNull(mike2);
        
    }
    
}
