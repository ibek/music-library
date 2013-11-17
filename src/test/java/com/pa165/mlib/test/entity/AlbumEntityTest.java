package com.pa165.mlib.test.entity;

import com.pa165.mlib.dao.AlbumDao;
import com.pa165.mlib.dao.ArtistDao;
import com.pa165.mlib.dao.SongDao;
import com.pa165.mlib.dao.impl.AlbumDaoImpl;
import com.pa165.mlib.dao.impl.ArtistDaoImpl;
import com.pa165.mlib.dao.impl.SongDaoImpl;
import com.pa165.mlib.entity.Album;
import com.pa165.mlib.entity.Artist;
import com.pa165.mlib.entity.Song;
import com.pa165.mlib.test.EntityTestBase;
import java.util.List;
import javax.persistence.EntityManager;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author xbek
 */
public class AlbumEntityTest extends EntityTestBase {

    @Test
    public void testAlbumsWithArtist() throws Exception {

        Artist artist1 = new Artist();
        artist1.setName("Yoshida Brothers");
        ArtistDaoImpl arM = new ArtistDaoImpl();
        EntityManager em = getTestEntityManager();
        arM.setEntityManager(em);

        em.getTransaction().begin();
        arM.addArtist(artist1);
        em.getTransaction().commit();

        Album album1 = new Album();
        album1.setTitle("Best of Asia");
        album1.setReleased(2001);
        AlbumDaoImpl am = new AlbumDaoImpl();
        EntityManager em2 = getTestEntityManager();
        am.setEntityManager(em2);
        em2.getTransaction().begin();
        am.addAlbum(album1);
        em2.getTransaction().commit();

        Album album2 = new Album();
        album2.setTitle("Inside of the Sun");
        album2.setReleased(2001);
        em2.getTransaction().begin();
        am.addAlbum(album2);
        em2.getTransaction().commit();

        Song song1 = new Song();
        song1.setTitle("Comodo");
        song1.setArtist(artist1);
        song1.setAlbum(album1);
        SongDaoImpl sm = new SongDaoImpl();
        EntityManager em3 = getTestEntityManager();
        sm.setEntityManager(em3);
        em3.getTransaction().begin();
        sm.addSong(song1);
        em3.getTransaction().commit();

        Song song2 = new Song();
        song2.setTitle("Kodo");
        song2.setArtist(artist1);
        song2.setAlbum(album2);
        em3.getTransaction().begin();
        sm.addSong(song2);
        em3.getTransaction().commit();

        Song song3 = new Song();
        song3.setTitle("Kagero");
        song3.setArtist(artist1);
        song3.setAlbum(album2);
        em3.getTransaction().begin();
        sm.addSong(song3);
        em3.getTransaction().commit();

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
        AlbumDaoImpl am = new AlbumDaoImpl();
        EntityManager em = getTestEntityManager();
        am.setEntityManager(em);
        em.getTransaction().begin();
        am.addAlbum(album1);
        em.getTransaction().commit();
        Album a2 = am.getAlbum(album1.getTitle());
        a2.setTitle("updatedName");
        em.getTransaction().begin();
        am.updateAlbum(a2);
        em.getTransaction().commit();
        Album a3 = am.getAlbum(a2.getTitle());

        assertEquals(a2, a3);

    }

    @Test
    public void testAlbumRemove() throws Throwable {
        Album a = new Album();
        a.setTitle("album1");
        AlbumDaoImpl ad = new AlbumDaoImpl();
        EntityManager em = getTestEntityManager();
        ad.setEntityManager(em);
        em.getTransaction().begin();
        ad.addAlbum(a);
        em.getTransaction().commit();
        Album a2 = ad.getAlbum(a.getId());
        em.getTransaction().begin();
        ad.removeAlbum(a2);
        em.getTransaction().commit();
        Album empty = ad.getAlbum(a.getId());
        assertNull(empty);
    }
}