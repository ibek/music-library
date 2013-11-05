package com.pa165.mlib.dao.impl;

import com.pa165.mlib.dao.ArtistDao;
import com.pa165.mlib.entity.Artist;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
            artist = em.merge(artist);
        }
        em.remove(artist);
    }
    
    /**
     * Read all artists
     * @return 
     */
    @Override
    public List<Artist> getAll() {
        return em.createQuery("SELECT a FROM Artist a", Artist.class)
                .getResultList();
    }
    
    /**
     * Read single artist via artist ID
     * @param id
     * @return 
     */
    @Override
    public Artist getArtist(Long id) {
        Artist a = null;
        try {
            a = em.createQuery("SELECT a FROM Artist a WHERE a.id = :id", Artist.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            
        }
        return a;
    }
    
    /**
     * Read artist/-s via artist name 
     * @param name
     * @return 
     */
    @Override
    public Artist getArtist(String name) {
        Artist a = null;
        try {
            a = em.createQuery("SELECT a FROM Artist a WHERE a.name = :name", Artist.class)
                .setParameter("name", name)
                .getSingleResult();
        } catch (NoResultException e) {
            
        }
        return a;
    }
    
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
}
