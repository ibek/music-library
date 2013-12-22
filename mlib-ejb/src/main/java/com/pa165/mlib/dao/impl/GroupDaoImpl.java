package com.pa165.mlib.dao.impl;

import com.pa165.mlib.dao.GroupDao;
import com.pa165.mlib.dao.GroupDao;
import com.pa165.mlib.entity.Group;
import com.pa165.mlib.entity.Group;
import com.pa165.mlib.entity.User;
import com.pa165.mlib.exception.DuplicateException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.hibernate.exception.ConstraintViolationException;

/**
 * Group DAO
 * @author ibek
 */
public class GroupDaoImpl implements GroupDao {
    
    @Inject
    Logger logger;
    
    @PersistenceContext(unitName = "mlib-pu")
    EntityManager em;
    
    @Override
    public void addGroup(Group group) throws DuplicateException {
        try {
            em.persist(group);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Cannot create a new group due to: {0}", ex.getMessage());
            if (ex.getCause() instanceof ConstraintViolationException) {
                throw new DuplicateException();
            }
            throw ex;
        }
    }
    
    @Override
    public void removeGroup(Group group) {
        if(group != null && !em.contains(group)) {
            em.merge(group);
        }
        em.remove(group);
    }
    
    @Override
    public List<Group> getAll() {
        return em.createQuery("SELECT g FROM Group g ORDER BY g.username", Group.class)
                .getResultList();
    }
    
    @Override
    public List<Group> getGroups(User username) {
         return em.createQuery("SELECT g FROM Group g WHERE g.username = :username", Group.class)
                   .setParameter("username", username).getResultList();
    }
    
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
}
