/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.mlib.dao;

import com.pa165.mlib.entity.Artist;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Artist DAO
 * Transaction are managed by container
 * @author brazdil
 */
public class ArtistManager {
    
    @PersistenceContext(unitName = "mlib-pu")
    EntityManager em;
    
    public void addArtist(Artist artist) {
        em.persist(artist);
    }
    
    public Artist updateArtist(Artist artist) {
        if (artist == null) {
            return null;
        }
        return em.merge(artist);
    }
    
    public void removeArtist(Artist artist) {
        if(artist != null && !em.contains(artist)) {
            em.merge(artist);
        }
        em.remove(artist);
    }
    
    public List<Artist> getAll() {
        return em.createQuery("SELECT a FROM Artist a")
                .getResultList();
    }
    
    public Artist getArtist(Long id) {
        return (Artist) em.createQuery("SELECT a FROM Artist a WHERE a.id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }
    
    public List<Artist> getArtist(String name) {
        return em.createQuery("SELECT a FROM Artist a WHERE a.name = :name")
                .setParameter("name", name)
                .getResultList();
    }
}
