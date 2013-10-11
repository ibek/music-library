package com.pa165.mlib.test;

import static org.junit.Assert.*;

import com.pa165.mlib.dao.GenreManager;
import com.pa165.mlib.dao.SongManager;
import com.pa165.mlib.entity.Genre;
import com.pa165.mlib.entity.Song;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author ibek
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
    
}
