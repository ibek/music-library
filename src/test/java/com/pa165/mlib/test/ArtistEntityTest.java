package com.pa165.mlib.test;

import com.pa165.mlib.dao.ArtistDao;
import com.pa165.mlib.dao.impl.ArtistDaoImpl;
import com.pa165.mlib.entity.Artist;
import javax.persistence.EntityManager;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 *
 * @author xbek
 */
public class ArtistEntityTest extends EntityTestBase {

    @Test
    public void testArtistRemove() throws Throwable {
        Artist artist = new Artist();
        artist.setName("Michael");
        ArtistDaoImpl ad = new ArtistDaoImpl();
        EntityManager em = getTestEntityManager();
        ad.setEntityManager(em);
        em.getTransaction().begin();
        ad.addArtist(artist);
        em.getTransaction().commit();
        Artist a2 = ad.getArtist(artist.getId());
        em.getTransaction().begin();
        ad.removeArtist(a2);
        em.getTransaction().commit();
        Artist empty = ad.getArtist(artist.getId());
        assertNull(empty);
    }
    
    @Test
    public void testArtistUpdate() throws Exception {
        Artist mike = new Artist();
        mike.setName("Mike");
        ArtistDaoImpl am = new ArtistDaoImpl();
        EntityManager em = getTestEntityManager();
        am.setEntityManager(em);
        em.getTransaction().begin();
        am.addArtist(mike);
        em.getTransaction().commit();
        
        Artist mike2 = am.getArtist(mike.getId());
        assertEquals(mike, mike2);
        
        mike.setName("Michael");
        em.getTransaction().begin();
        mike = am.updateArtist(mike);
        em.getTransaction().commit();
        
    }
    
}
