/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.mlib.dao;

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
public class ArtistManager {
    
    @PersistenceContext(unitName = "mlib-pu")
    EntityManager em;
    
    /**
     * Persists the given artist to persistence context
     * @param artist 
     */
    public void addArtist(Artist artist) {
        em.persist(artist);
    }
    
    /**
     * Update the given artist
     * @param artist
     * @return 
     */
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
    public List<Artist> getAll() {
        return em.createQuery("SELECT a FROM Artist a")
                .getResultList();
    }
    
    /**
     * Read single artist via artist ID
     * @param id
     * @return 
     */
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
    public List<Artist> getArtist(String name) {
        return em.createQuery("SELECT a FROM Artist a WHERE a.name = :name")
                .setParameter("name", name)
                .getResultList();
    }
}
