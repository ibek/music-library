/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pa165.mlib.manager;

import com.pa165.mlib.dto.AlbumTO;
import com.pa165.mlib.exception.DuplicateException;
import com.pa165.mlib.service.AlbumService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author brazdil
 */
public class AlbumManager {
    
    @Inject
    transient Logger logger;
    
    @Inject
    AlbumService service;
    
    private AlbumTO albumTO = new AlbumTO();
    
    public void init() {
        albumTO = new AlbumTO();
    }
    
    public AlbumService getService() {
        return service;
    }
    
    public AlbumTO getAlbumTO() {
        return albumTO;
    }
    
    public void create() {
        logger.log(Level.INFO, "Creating {0}", albumTO);
        try {
            service.createNewAlbum(albumTO);
            init();
        } catch (DuplicateException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("The artist cannot be created because it already exists."));
        }
    }
    
    public void remove(AlbumTO album) {
        logger.log(Level.INFO, "Removing {0}", album);
        service.removeAlbum(album);
        init();
    }
    
}
