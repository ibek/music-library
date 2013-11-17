package com.pa165.mlib.bean;

import com.pa165.mlib.dto.GenreTO;
import com.pa165.mlib.exception.DuplicateException;
import com.pa165.mlib.service.GenreService;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ibek
 */
@SessionScoped
@ManagedBean
@Named
public class GenreManager implements Serializable {
    
    @Inject
    transient Logger logger;
    
    @Inject
    GenreService service;
    
    private GenreTO gto = new GenreTO();
    
    public GenreService getService() {
        return service;
    }
    
    public GenreTO getGenreTO() {
        return gto;
    }
    
    public void create() {
        logger.log(Level.INFO, "Creating {0}", gto);
        try {
            service.createNewGenre(gto);
        } catch (DuplicateException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("The genre cannot be created because it already exists."));
        }
    }
    
}
