/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.mlib.dao;

import com.pa165.mlib.entity.Genre;
import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext; 

/**
 * Genre DAO
 * Transaction are managed by container
 * @author brazdil
 */
@Singleton
public class GenreManager {
    
    @PersistenceContext(unitName = "mlib-pu")
    EntityManager em;
    
    public void addGenre(Genre genre) {
        em.persist(genre);
    }
    
    public Genre updateGenre(Genre genre) {
        if (genre == null) {
            return null;
        }
        return em.merge(genre);
    }
    
    public void removeGenre(Genre genre) {
        if(genre != null && !em.contains(genre)) {
            em.merge(genre);
        }
        em.remove(genre);
    }
    
    public List<Genre> getAll() {
        return em.createQuery("SELECT g FROM Genre g")
                .getResultList();
    }
    
    public Genre getGenre(String name) {
            return (Genre) em.createQuery("SELECT g FROM Genre g WHERE g.name = :name")
                .setParameter("name", name)
                .getSingleResult();
    }
}
