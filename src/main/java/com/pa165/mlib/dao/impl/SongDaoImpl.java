package com.pa165.mlib.dao.impl;

import com.pa165.mlib.dao.SongDao;
import com.pa165.mlib.entity.Song;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * Song DAO.
 * Transactions are managed by container.
 * @author ibek
 */
public class SongDaoImpl implements SongDao {
    
    @PersistenceContext(unitName = "mlib-pu")
    EntityManager em;
    
    /**
     * Persist the given song to persistence context.
     * @param song to be persisted
     */
    @Override
    public void addSong(Song song) {
        em.persist(song);
    }
    
    /**
     * Update the given song in persistence context and database after the commit.
     * @param song to be updated
     * @return updated song
     */
    @Override
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
    @Override
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
    @Override
    public List<Song> getAll() {
        return em.createQuery("SELECT s FROM Song s", Song.class)
                .getResultList();
    }
    
    /**
     * Get song with given unique identifier.
     * @param id unique identifier for song
     * @return song
     */
    @Override
    public Song getSong(long id) {
        Song s = null;
        try {
            s = em.createQuery("SELECT s FROM Song s WHERE s.id = :id", Song.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            
        }
        return s;
    }
    
    /**
     * Get all the songs with given title.
     * @param title what should be song called
     * @return songs with defined title
     */
    @Override
    public Song getSong(String title) {
        Song s = null;
        try {
            s = em.createQuery("SELECT s FROM Song s WHERE s.title = :title", Song.class)
                    .setParameter("title", title)
                    .getSingleResult();
        } catch (NoResultException e) {
            
        }
        return s;
    }
    
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
}
