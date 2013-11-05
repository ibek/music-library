package com.pa165.mlib.test;

import com.pa165.mlib.dao.SongDao;
import com.pa165.mlib.dao.impl.SongDaoImpl;
import com.pa165.mlib.entity.Song;
import javax.persistence.EntityManager;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 *
 * @author xbek
 */
public class SongEntityTest extends EntityTestBase {
    
    @Test
    public void testSongRemove() throws Throwable {
        Song s = new Song();
        s.setTitle("mysong");
        SongDaoImpl sd = new SongDaoImpl();
        EntityManager em = getTestEntityManager();
        sd.setEntityManager(em);
        em.getTransaction().begin();
        sd.addSong(s);
        em.getTransaction().commit();
        Song s2 = sd.getSong(s.getId());
        em.getTransaction().begin();
        sd.removeSong(s2);
        em.getTransaction().commit();
        Song empty = sd.getSong(s.getId());
        assertNull(empty);
    }
    
    @Test
    public void testSongSearch() throws Exception {
        Song lala = new Song();
        lala.setTitle("lala");
        SongDaoImpl sm = new SongDaoImpl();
        EntityManager em = getTestEntityManager();
        sm.setEntityManager(em);
        em.getTransaction().begin();
        sm.addSong(lala);
        em.getTransaction().commit();
        Song ohm = new Song();
        String ohmTitle = "ohm";
        ohm.setTitle(ohmTitle);
        em.getTransaction().begin();
        sm.addSong(ohm);
        em.getTransaction().commit();
        Song result = sm.getSong(ohmTitle);
        assertNotNull(result);
        assertEquals(ohm.getTitle(), result.getTitle());
    }
    
}
