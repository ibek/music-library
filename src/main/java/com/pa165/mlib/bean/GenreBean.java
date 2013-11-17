package com.pa165.mlib.bean;

import com.pa165.mlib.service.GenreService;
import java.io.Serializable;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ibek
 */
@Named("genre")
public class GenreBean implements Serializable {
    
    @Inject
    GenreService service;
    
    public GenreService getService() {
        return service;
    }
    
}
