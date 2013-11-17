package com.pa165.mlib.manager;

import com.pa165.mlib.dto.ArtistTO;
import com.pa165.mlib.exception.DuplicateException;
import com.pa165.mlib.service.ArtistService;
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
public class ArtistManager implements Serializable {
    
    @Inject
    transient Logger logger;
    
    @Inject
    ArtistService service;
    
    private ArtistTO artistTO = new ArtistTO();
    
    public void init() {
        artistTO = new ArtistTO();
    }
    
    public ArtistService getService() {
        return service;
    }
    
    public ArtistTO getArtistTO() {
        return artistTO;
    }
    
    public void create() {
        logger.log(Level.INFO, "Creating {0}", artistTO);
        try {
            service.createNewArtist(artistTO);
            init();
        } catch (DuplicateException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("The artist cannot be created because it already exists."));
        }
    }
    
    public void remove(ArtistTO artist) {
        logger.log(Level.INFO, "Removing {0}", artist);
        service.removeArtist(artist);
        init();
    }
    
}
