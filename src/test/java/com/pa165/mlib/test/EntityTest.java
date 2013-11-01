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
import org.junit.Test;

/**
 *
 * @author ibek, brazdil, slatinsky
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
    public void testGenreRemove() throws Throwable {
        Genre genre = new Genre();
        genre.setName("pop");
        GenreDao gd = lookupBy(GenreDaoImpl.class);
        gd.addGenre(genre);
        Genre g2 = gd.getGenre(genre.getName());
        gd.removeGenre(g2);
        Genre empty = gd.getGenre(genre.getName());
        assertNull(empty);
    }
   
   @Test
    public void testGenreUpdate() throws Throwable {
        Genre g = new Genre();
        g.setName("newGenre");
	g.setId(88L);
        GenreDao gm = lookupBy(GenreDaoImpl.class);
        gm.addGenre(g);
        Genre g2 = gm.getGenre(g.getName());
	g2.setName("updatedName");
        gm.updateGenre(g2);
        Genre g3 = gm.getGenre(g2.getName());

	assertEquals(g2, g3);

    }
   
@Test
    public void testGetAllGenres() throws Exception  {
	
	GenreDao gm = lookupBy(GenreDaoImpl.class);
	
        List<Genre> gList = new ArrayList<>();

        assertEquals(gList, gm.getAll());
        
        Genre g1 = new Genre();
	g1.setName("rock");
        Genre g2 = new Genre();
	g2.setName("Fetal");
        Genre g3 = new Genre();
	g3.setName("Rap");

        gm.addGenre(g1);
        gm.addGenre(g2);
        gm.addGenre(g3);

        gList.add(g1);
        gList.add(g2);
        gList.add(g3);

        List<Genre> gListFromDAO = gm.getAll();;              
        assertEquals(gList, gListFromDAO);
        
    }
    
    @Test
    public void testSongCRUD() throws Exception {
        Song song = new Song();
        song.setTitle("song1");
        SongDao sm = lookupBy(SongDaoImpl.class);
        sm.addSong(song);
        
        Song song2 = sm.getSong(song.getId());
        assertEquals(song, song2);
        
        song.setTitle("TheSong");
        song = sm.updateSong(song);
        
        song2 = sm.getSong(song.getId());
        assertEquals("TheSong", song2.getTitle());
        
        sm.removeSong(song2);
        song2 = sm.getSong(song2.getId());
        assertNull(song2);
        
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
        Song result = sm.getSong(ohmTitle);
        assertNotNull(result);
        assertEquals(ohm.getTitle(), result.getTitle());
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
    public void testAlbumUpdate() throws Throwable {
        Album album1 = new Album();
        album1.setTitle("album1");
        album1.setReleased(2006);
        AlbumDao am = lookupBy(AlbumDaoImpl.class);
        am.addAlbum(album1);
        Album a2 = am.getAlbum(album1.getTitle());
	a2.setTitle("updatedName");
        am.updateAlbum(a2);
        Album a3 = am.getAlbum(a2.getTitle());

	assertEquals(a2, a3);

    }
    
    @Test
        public void testAlbumRemove() throws Throwable {
        Album a = new Album();
        a.setTitle("album1");
        AlbumDao ad = lookupBy(AlbumDaoImpl.class);
        ad.addAlbum(a);
        Album a2 = ad.getAlbum(a.getId());
        ad.removeAlbum(a2);
        Album empty = ad.getAlbum(a.getId());
        assertNull(empty);
    }
     
    @Test
    public void testArtistRemove() throws Throwable {
        Artist artist = new Artist();
        artist.setName("Michael");
        ArtistDao ad = lookupBy(ArtistDaoImpl.class);
        ad.addArtist(artist);
        Artist a2 = ad.getArtist(artist.getId());
        ad.removeArtist(a2);
        Artist empty = ad.getArtist(artist.getId());
        assertNull(empty);
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
