package com.pa165.mlib.dao.impl;

import com.pa165.mlib.dao.UserDao;
import com.pa165.mlib.dao.UserDao;
import com.pa165.mlib.entity.User;
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
 * User DAO
 * @author ibek
 */
public class UserDaoImpl implements UserDao {
    
    @Inject
    Logger logger;
    
    @PersistenceContext(unitName = "mlib-pu")
    EntityManager em;
    
    @Override
    public void addUser(User user) throws DuplicateException {
        try {
            em.persist(user);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Cannot create a new user due to: {0}", ex.getMessage());
            if (ex.getCause() instanceof ConstraintViolationException) {
                throw new DuplicateException();
            }
            throw ex;
        }
    }
    
    @Override
    public User updateUser(User user) {
        if (user == null) {
            return null;
        }
        return em.merge(user);
    }
    
    @Override
    public void removeUser(User user) {
        if(user != null && !em.contains(user)) {
            em.merge(user);
        }
        em.remove(user);
    }
    
    @Override
    public List<User> getAll() {
        return em.createQuery("SELECT u FROM MlibUser u ORDER BY u.username", User.class)
                .getResultList();
    }
    
    @Override
    public User getUser(String username) {
         User g = null;
         try {
            g = em.createQuery("SELECT u FROM MlibUser u WHERE u.username = :username", User.class)
                   .setParameter("username", username)
                   .getSingleResult();
         } catch (NoResultException e) {
             e.printStackTrace();
         }
         return g;
    }
    
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
}
