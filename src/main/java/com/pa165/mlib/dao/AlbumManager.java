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
    
    public void addAlbum(Album album) {
        em.persist(album);
    }
    
    public Album updateAlbum(Album album) {
        if (album == null) {
            return null;
        }
        return em.merge(album);
    }
    
    public void removeAlbum(Album album) {
        if (album != null && !em.contains(album)) {
            album = em.merge(album);
        }
        em.remove(album);
    }
    
    public List<Album> getAll() {
        return em.createQuery("SELECT a FROM Album a")
                .getResultList();
    }
    
    public Album getAlbum(long id) {
        return (Album) em.createQuery("SELECT a FROM Album a WHERE a.id : id")
                .setParameter("id", id)
                .getSingleResult();
    }
    
    public List<Album> getAlbumsWithTitle(String title) {
        return em.createQuery("SELECT a FROM Album a WHERE a.title = :title")
                .setParameter("title", title)
                .getResultList();
    }
    
    public List<Album> getAlbumsWithArtist(Artist artist) {
        return em.createQuery("select distinct a from album a inner join a.songs as song inner join  song.artist as artist where artist.id = :artist")
                .setParameter("artist", artist.getId())
                .getResultList();
    }
    
}
