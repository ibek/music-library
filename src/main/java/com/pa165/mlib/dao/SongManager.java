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
    
    /**
     * Persist the given song to persistence context.
     * @param song to be persisted
     */
    public void addSong(Song song) {
        em.persist(song);
    }
    
    /**
     * Update the given song in persistence context and database after the commit.
     * @param song to be updated
     * @return updated song
     */
    public Song updateSong(Song song) {
        if (song == null) {
            return null;
        }
        return em.merge(song);
    }
    
    /**
     * Remove song from the persistence context and database after the commit.
     * @param song to be removed
     */
    public void removeSong(Song song) {
        if (song != null && !em.contains(song)) {
            song = em.merge(song);
        }
        em.remove(song);
    }
    
    /**
     * Get all the songs.
     * @return all the songs
     */
    public List<Song> getAll() {
        return em.createQuery("SELECT s FROM Song s")
                .getResultList();
    }
    
    /**
     * Get song with given unique identifier.
     * @param id unique identifier for song
     * @return song
     */
    public Song getSong(long id) {
        return (Song) em.createQuery("SELECT a FROM Album a WHERE a.id : id")
                .setParameter("id", id)
                .getSingleResult();
    }
    
    /**
     * Get all the songs with given title.
     * @param title what should be song called
     * @return songs with defined title
     */
    public List<Song> getSongsWithTitle(String title) {
        return em.createQuery("SELECT s FROM Song s WHERE s.title = :title")
                .setParameter("title", title)
                .getResultList();
    }
    
}
