package com.pa165.mlib.test;

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
        GenreDaoImpl gm = lookupBy(GenreDaoImpl.class);
        gm.addGenre(rock);
        Genre rock2 = gm.getGenre("rock");
        assertEquals(rock, rock2);
    }
    
    @Test
    public void testSongSearch() throws Exception {
        Song lala = new Song();
        lala.setTitle("lala");
        SongDaoImpl sm = lookupBy(SongDaoImpl.class);
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
        ArtistDaoImpl arM = lookupBy(ArtistDaoImpl.class);
        arM.addArtist(artist1);
        
        Album album1 = new Album();
        album1.setTitle("Best of Asia");
        album1.setReleased("2001");
        AlbumDaoImpl am = lookupBy(AlbumDaoImpl.class);
        am.addAlbum(album1);
        
        Album album2 = new Album();
        album2.setTitle("Inside of the Sun");
        album2.setReleased("2001");
        am.addAlbum(album2);
        
        Song song1 = new Song();
        song1.setTitle("Comodo");
        song1.setArtist(artist1);
        song1.setAlbum(album1);
        SongDaoImpl sm = lookupBy(SongDaoImpl.class);
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
    public void testArtistWithSongs() throws Exception {
        Artist mike = new Artist();
        mike.setName("the best of");
        mike.setSongs(new ArrayList<Song>(){{
            Song s1 = new Song();
            s1.setTitle("s1");
            s1.setPosition(0);
            Song s2 = new Song();
            s2.setTitle("s2");
            s2.setPosition(1);
            Song s3 = new Song();
            s3.setTitle("s3");
            s3.setPosition(2);
            
            add(s1);
            add(s2);
            add(s3);
        }});
        ArtistDaoImpl am = lookupBy(ArtistDaoImpl.class);
        am.addArtist(mike);
        mike = am.updateArtist(mike);
        SongDaoImpl sm = lookupBy(SongDaoImpl.class);
        List<Song> s2list = sm.getSongsWithTitle("s2");
        assertEquals(1, s2list.size());
        assertEquals(mike.getSongs().get(1), s2list.get(0));
    }
    
}
