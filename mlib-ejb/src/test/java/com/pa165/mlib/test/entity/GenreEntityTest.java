package com.pa165.mlib.test.entity;

import com.pa165.mlib.dao.impl.GenreDaoImpl;
import com.pa165.mlib.entity.Genre;
import com.pa165.mlib.test.EntityTestBase;
import javax.persistence.EntityManager;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author xbek
 */
public class GenreEntityTest extends EntityTestBase {
    
    @Test
    public void testAddGenre() throws Throwable {
        Genre rock = new Genre();
        rock.setName("rock1");
        GenreDaoImpl gm = new GenreDaoImpl();
        EntityManager em = getTestEntityManager();
        gm.setEntityManager(em);
        em.getTransaction().begin();
        gm.addGenre(rock);
        em.getTransaction().commit();
        Genre rock2 = gm.getGenre("rock1");
        assertEquals(rock, rock2);
    }
    
    @Test
    public void testGetAll() throws Throwable {
        Genre rock = new Genre();
        rock.setName("rock3");
        GenreDaoImpl gm = new GenreDaoImpl();
        EntityManager em = getTestEntityManager();
        gm.setEntityManager(em);
        em.getTransaction().begin();
        gm.addGenre(rock);
        em.getTransaction().commit();
        Genre rock2 = gm.getGenre("rock3");
        assertEquals(rock, rock2);
    }
    
    @Test
    public void testGetGenre() throws Throwable {
        Genre rock = new Genre();
        rock.setName("rock2");
        GenreDaoImpl gm = new GenreDaoImpl();
        EntityManager em = getTestEntityManager();
        gm.setEntityManager(em);
        em.getTransaction().begin();
        gm.addGenre(rock);
        em.getTransaction().commit();
        Genre rock2 = gm.getGenre("rock2");
        assertEquals(rock, rock2);
    }
    
    @Test
    public void testGenreRemove() throws Throwable {
        Genre rock = new Genre();
        rock.setName("rock4");
        GenreDaoImpl gm = new GenreDaoImpl();
        EntityManager em = getTestEntityManager();
        gm.setEntityManager(em);
        em.getTransaction().begin();
        gm.addGenre(rock);
        em.getTransaction().commit();
        Genre rock2 = gm.getGenre("rock4");
        
        em.getTransaction().begin();
        gm.removeGenre(rock2);
        em.getTransaction().commit();
        Genre empty = gm.getGenre(rock.getName());
        assertNull(empty);
    }
    
    @Test
    public void testGenreUpdate() throws Exception {
        Genre rock = new Genre();
        rock.setName("rock5");
        GenreDaoImpl gm = new GenreDaoImpl();
        EntityManager em = getTestEntityManager();
        gm.setEntityManager(em);
        em.getTransaction().begin();
        gm.addGenre(rock);
        em.getTransaction().commit();
        Genre rock2 = gm.getGenre("rock5");
        rock2.setName("superrock");
        
        em.getTransaction().begin();
        gm.updateGenre(rock2);
        em.getTransaction().commit();
        Genre g3 = gm.getGenre("superrock");
        assertTrue(g3 != null);
    }
    
}
