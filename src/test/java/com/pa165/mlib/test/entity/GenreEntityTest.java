package com.pa165.mlib.test.entity;

import com.pa165.mlib.dao.impl.GenreDaoImpl;
import com.pa165.mlib.entity.Genre;
import com.pa165.mlib.test.EntityTestBase;
import javax.persistence.EntityManager;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author xbek
 */
public class GenreEntityTest extends EntityTestBase {
    
    @Test
    public void testGenrePersist() throws Exception {
        Genre rock = new Genre();
        rock.setName("rock");
        GenreDaoImpl gm = new GenreDaoImpl();
        EntityManager em = getTestEntityManager();
        gm.setEntityManager(em);
        em.getTransaction().begin();
        gm.addGenre(rock);
        em.getTransaction().commit();
        Genre rock2 = gm.getGenre("rock");
        assertEquals(rock, rock2);
    }
    
}
