/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pa165.mlib.manager;

import com.pa165.mlib.dto.AlbumTO;
import com.pa165.mlib.exception.DuplicateException;
import com.pa165.mlib.service.AlbumService;
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
 * @author brazdil
 */
@SessionScoped
@ManagedBean
@Named
public class AlbumManager implements Serializable {
    
    @Inject
    transient Logger logger;
    
    @Inject
    AlbumService service;
    
    private AlbumTO albumTO = new AlbumTO();
    
    public void init() {
        albumTO = new AlbumTO();
    }
    
    public AlbumTO init(String name) {
        albumTO = service.getAlbum(name);
        return albumTO;
    }
    
    public AlbumService getService() {
        return service;
    }
    
    public AlbumTO getAlbumTO() {
        return albumTO;
    }
    
    public String create() {
        logger.log(Level.INFO, "Creating {0}", albumTO);
        try {
            service.createNewAlbum(albumTO);
            init();
        } catch (DuplicateException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("The album cannot be created because it already exists."));
            return "album_detail";
        }
        return "albums";
    }
    
    public String removeAlbum() {
        logger.log(Level.INFO, "Removing {0}", albumTO);
        service.removeAlbum(albumTO);
        init();
        return "albums";
    }
    
    public String updateAlbum() {
        logger.log(Level.INFO, "Updating {0}", albumTO);
        service.updateAlbum(service.getAlbum(albumTO.getTitle()), albumTO);
        init();
        return "albums";
    }
    
}
