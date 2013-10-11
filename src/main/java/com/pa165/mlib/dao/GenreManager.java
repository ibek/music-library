/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.mlib.dao;

import com.pa165.mlib.entity.Genre;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext; 

/**
 * Genre DAO
 * Transaction are managed by container
 * @author brazdil
 */
@Stateless
public class GenreManager {
    
    @PersistenceContext(unitName = "mlib-pu")
    EntityManager em;
    
    /**
     * Persists the given genre to persistence context
     * @param genre 
     */
    public void addGenre(Genre genre) {
        em.persist(genre);
    }
    
    /**
     * Update the given genre
     * @param genre
     * @return 
     */
    public Genre updateGenre(Genre genre) {
        if (genre == null) {
            return null;
        }
        return em.merge(genre);
    }
    
    /**
     * Remove the given genre from persistence context
     * @param genre 
     */
    public void removeGenre(Genre genre) {
        if(genre != null && !em.contains(genre)) {
            em.merge(genre);
        }
        em.remove(genre);
    }
    
    /**
     * Read all genres
     * @return 
     */
    public List<Genre> getAll() {
        return em.createQuery("SELECT g FROM Genre g")
                .getResultList();
    }
    
    /**
     * Read genre via genre name
     * @param name
     * @return 
     */
    public Genre getGenre(String name) {
            return (Genre) em.createQuery("SELECT g FROM Genre g WHERE g.name = :name")
                .setParameter("name", name)
                .getSingleResult();
    }
}
