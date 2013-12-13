/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pa165.mlib.utils;

import com.pa165.mlib.dto.AlbumTO;
import com.pa165.mlib.service.AlbumService;
import com.pa165.mlib.service.GenreService;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ibek
 */
@Named
public class AlbumConverter implements Converter, Serializable {
    
    @Inject
    AlbumService as;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return as.getAlbum(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        AlbumTO album = null;
        if (album instanceof AlbumTO){
            album = (AlbumTO) value;
            return album.getTitle();
        }
        return "";
    }
    
}
