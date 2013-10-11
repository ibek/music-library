package com.pa165.mlib.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ibek
 */
public class AlbumManager {
    
    @PersistenceContext(unitName = "mlib-pu")
    EntityManager em;
    
}
