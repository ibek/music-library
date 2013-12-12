/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pa165.mlib.utils;

import com.pa165.mlib.dto.ArtistTO;
import com.pa165.mlib.service.ArtistService;
import com.pa165.mlib.service.GenreService;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author ibek
 */
@FacesConverter(value="artistConverter", forClass = ArtistTO.class)
public class ArtistConverter implements Converter {
    
    @Inject
    ArtistService as;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return as.getArtist(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        ArtistTO artist = null;
        if (artist instanceof ArtistTO){
            artist = (ArtistTO) value;
            return artist.getName();
        }
        return "";
    }
    
}
