package com.pa165.mlib.dao.impl;

import com.pa165.mlib.dao.ArtistDao;
import com.pa165.mlib.entity.Artist;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Artist DAO
 * Transaction are managed by container
 * @author brazdil
 */
@Stateless
public class ArtistDaoImpl implements ArtistDao {
    
    @PersistenceContext(unitName = "mlib-pu")
    EntityManager em;
    
    /**
     * Persists the given artist to persistence context
     * @param artist 
     */
    @Override
    public void addArtist(Artist artist) {
        em.persist(artist);
    }
    
    /**
     * Update the given artist
     * @param artist
     * @return 
     */
    @Override
    public Artist updateArtist(Artist artist) {
        if (artist == null) {
            return null;
        }
        return em.merge(artist);
    }
    
    /**
     * Remove the given artist from persistence context
     * @param artist 
     */
    @Override
    public void removeArtist(Artist artist) {
        if(artist != null && !em.contains(artist)) {
            em.merge(artist);
        }
        em.remove(artist);
    }
    
    /**
     * Read all artists
     * @return 
     */
    @Override
    public List<Artist> getAll() {
        return em.createQuery("SELECT a FROM Artist a")
                .getResultList();
    }
    
    /**
     * Read single artist via artist ID
     * @param id
     * @return 
     */
    @Override
    public Artist getArtist(Long id) {
        return (Artist) em.createQuery("SELECT a FROM Artist a WHERE a.id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }
    
    /**
     * Read artist/-s via artist name 
     * @param name
     * @return 
     */
    @Override
    public List<Artist> getArtist(String name) {
        return em.createQuery("SELECT a FROM Artist a WHERE a.name = :name")
                .setParameter("name", name)
                .getResultList();
    }
}
