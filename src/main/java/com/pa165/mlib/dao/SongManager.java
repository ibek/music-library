package com.pa165.mlib.dao;

import com.pa165.mlib.entity.Song;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Song DAO.
 * Transactions are managed by container.
 * @author ibek
 */
@Stateless
public class SongManager {
    
    @PersistenceContext(unitName = "mlib-pu")
    EntityManager em;
    
    public void addSong(Song song) {
        em.persist(song);
    }
    
    public Song updateSong(Song song) {
        if (song == null) {
            return null;
        }
        return em.merge(song);
    }
    
    public void removeSong(Song song) {
        if (song != null && !em.contains(song)) {
            song = em.merge(song);
        }
        em.remove(song);
    }
    
    public List<Song> getAll() {
        return em.createQuery("SELECT s FROM Song s")
                .getResultList();
    }
    
    public Song getSong(long id) {
        return (Song) em.createQuery("SELECT a FROM Album a WHERE a.id : id")
                .setParameter("id", id)
                .getSingleResult();
    }
    
    public List<Song> getSongsWithTitle(String title) {
        return em.createQuery("SELECT s FROM Song s WHERE s.title = :title")
                .setParameter("title", title)
                .getResultList();
    }
    
}
