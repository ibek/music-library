package com.pa165.mlib.test;

import static org.junit.Assert.*;

import com.pa165.mlib.dao.GenreManager;
import com.pa165.mlib.entity.Genre;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author ibek
 */
public class EntityTest {

    private static final String MODULE_NAME = "embedded";
    private static final String TARGET_DIR = "target/" + MODULE_NAME;
    private static Context context;

    @BeforeClass
    public static void createContainer() throws Exception {
        context = EJBContainer.createEJBContainer().getContext();
    }

    @Test
    public void testGenrePersist() throws Exception {
        Genre rock = new Genre();
        rock.setName("rock");
        GenreManager gm = (GenreManager) context.lookup("java:global/music-library/GenreManager");
        gm.addGenre(rock);
        Genre rock2 = gm.getGenre("rock");
        assertEquals(rock, rock2);
    }
}
