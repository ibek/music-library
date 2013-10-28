package com.pa165.mlib.dao.impl;

import com.pa165.mlib.dao.AlbumDao;
import com.pa165.mlib.entity.Album;
import com.pa165.mlib.entity.Artist;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Album DAO.
 * Transactions are managed by container.
 * @author ibek
 */
@Stateless
public class AlbumDaoImpl implements AlbumDao {
    
    @PersistenceContext(unitName = "mlib-pu")
    EntityManager em;
    
    /**
     * Persist the given album to persistence context.
     * @param album to be persisted
     */
    @Override
    public void addAlbum(Album album) {
        em.persist(album);
    }
    
    /**
     * Update the given album in persistence context and database after the commit.
     * @param album to be updated
     * @return updated album
     */
    @Override
    public Album updateAlbum(Album album) {
        if (album == null) {
            return null;
        }
        return em.merge(album);
    }
    
    /**
     * Remove album from the persistence context and database after the commit.
     * @param album to be removed
     */
    @Override
    public void removeAlbum(Album album) {
        if (album != null && !em.contains(album)) {
            album = em.merge(album);
        }
        em.remove(album);
    }
    
    /**
     * Get all the albums.
     * @return all the albums
     */
    @Override
    public List<Album> getAll() {
        return em.createQuery("SELECT a FROM Album a", Album.class)
                .getResultList();
    }
    
    /**
     * Get album with given unique identifier.
     * @param id unique identifier for album
     * @return album
     */
    @Override
    public Album getAlbum(long id) {
        return em.createQuery("SELECT a FROM Album a WHERE a.id = :id", Album.class)
                .setParameter("id", id)
                .getSingleResult();
    }
    
    /**
     * Get the album with a given title.
     * @param title of the album in search
     * @return album with defined title
     */
    @Override
    public List<Album> getAlbum(String title) {
        return em.createQuery("SELECT a FROM Album a WHERE a.title = :title", Album.class)
                .setParameter("title", title)
                .getResultList();
    }
    
    /**
     * Get all the albums with given artist.
     * @param artist who is author of the required albums
     * @return albums with defined artist
     */
    @Override
    public List<Album> getAlbumsWithArtist(Artist artist) {
        return em.createQuery("select distinct a from Album a inner join a.songs as song inner join  song.artist as artist where artist.id = :artist", Album.class)
                .setParameter("artist", artist.getId())
                .getResultList();
    }
    
}
