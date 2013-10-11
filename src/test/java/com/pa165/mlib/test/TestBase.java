package com.pa165.mlib.test;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import org.junit.BeforeClass;

/**
 *
 * @author ibek
 */
public class TestBase {
    
    private static final String MODULE_NAME = "music-library";
    
    private static Context ctx;

    @BeforeClass
    public static void createContainer() throws Exception {
        ctx = EJBContainer.createEJBContainer().getContext();
    }

    protected <T> T lookupBy(Class<T> type) throws NamingException {
        return (T) ctx.lookup("java:global/" + MODULE_NAME + "/"
                + type.getSimpleName());
    }
}
