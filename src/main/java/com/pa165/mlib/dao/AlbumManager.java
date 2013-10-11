package com.pa165.mlib.dao;

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
public class AlbumManager {
    
    @PersistenceContext(unitName = "mlib-pu")
    EntityManager em;
    
    /**
     * Persist the given album to persistence context.
     * @param album to be persisted
     */
    public void addAlbum(Album album) {
        em.persist(album);
    }
    
    /**
     * Update the given album in persistence context and database after the commit.
     * @param album to be updated
     * @return updated album
     */
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
    public List<Album> getAll() {
        return em.createQuery("SELECT a FROM Album a")
                .getResultList();
    }
    
    /**
     * Get album with given unique identifier.
     * @param id unique identifier for album
     * @return album
     */
    public Album getAlbum(long id) {
        return (Album) em.createQuery("SELECT a FROM Album a WHERE a.id : id")
                .setParameter("id", id)
                .getSingleResult();
    }
    
    /**
     * Get all the albums with given title.
     * @param title what should be albums called
     * @return albums with defined title
     */
    public List<Album> getAlbumsWithTitle(String title) {
        return em.createQuery("SELECT a FROM Album a WHERE a.title = :title")
                .setParameter("title", title)
                .getResultList();
    }
    
    /**
     * Get all the albums with given artist.
     * @param artist who is author of the required albums
     * @return albums with defined artist
     */
    public List<Album> getAlbumsWithArtist(Artist artist) {
        return em.createQuery("select distinct a from Album a inner join a.songs as song inner join  song.artist as artist where artist.id = :artist")
                .setParameter("artist", artist.getId())
                .getResultList();
    }
    
}
