package com.pa165.mlib.test;

import com.pa165.mlib.dao.AlbumManager;
import com.pa165.mlib.dao.ArtistManager;
import static org.junit.Assert.*;

import com.pa165.mlib.dao.GenreManager;
import com.pa165.mlib.dao.SongManager;
import com.pa165.mlib.entity.Album;
import com.pa165.mlib.entity.Artist;
import com.pa165.mlib.entity.Genre;
import com.pa165.mlib.entity.Song;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import org.junit.BeforeClass;
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
        GenreManager gm = lookupBy(GenreManager.class);
        gm.addGenre(rock);
        Genre rock2 = gm.getGenre("rock");
        assertEquals(rock, rock2);
    }
    
    @Test
    public void testSongSearch() throws Exception {
        Song lala = new Song();
        lala.setTitle("lala");
        SongManager sm = lookupBy(SongManager.class);
        sm.addSong(lala);
        Song ohm = new Song();
        String ohmTitle = "ohm";
        ohm.setTitle(ohmTitle);
        sm.addSong(ohm);
        List<Song> results = sm.getSongsWithTitle(ohmTitle);
        assertEquals(1, results.size());
        assertEquals(results.get(0), ohm);
    }
    
    @Test
    public void testAlbumsWithArtist() throws Exception {
        
        Artist artist1 = new Artist();
        artist1.setName("Yoshida Brothers");
        ArtistManager arM = lookupBy(ArtistManager.class);
        arM.addArtist(artist1);
        
        Album album1 = new Album();
        album1.setTitle("Best of Asia");
        album1.setReleased("2001");
        AlbumManager am = lookupBy(AlbumManager.class);
        am.addAlbum(album1);
        
        Album album2 = new Album();
        album2.setTitle("Inside of the Sun");
        album2.setReleased("2001");
        am.addAlbum(album2);
        
        Song song1 = new Song();
        song1.setTitle("Comodo");
        song1.setArtist(artist1);
        song1.setAlbum(album1);
        SongManager sm = lookupBy(SongManager.class);
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
    
}
