package com.pa165.mlib.test.entity;

import com.pa165.mlib.dao.impl.UserDaoImpl;
import com.pa165.mlib.entity.User;
import com.pa165.mlib.test.EntityTestBase;
import javax.persistence.EntityManager;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author xbek
 */
public class UserEntityTest extends EntityTestBase {
    
    @Test
    public void testAddUser() throws Throwable {
        UserDaoImpl ud = new UserDaoImpl();
        EntityManager em = getTestEntityManager();
        ud.setEntityManager(em);
        
        User peter = new User();
        peter.setUsername("peterParker");
        peter.setPasswordHash("04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb");
        
        em.getTransaction().begin();
        ud.addUser(peter);
        em.getTransaction().commit();
        
        User user = ud.getUser("peterParker");
        assertEquals(peter, user);
    }
    
}
