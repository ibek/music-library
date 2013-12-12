/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pa165.mlib.utils;

import com.pa165.mlib.dto.GenreTO;
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
@FacesConverter(value="genreConverter", forClass = GenreTO.class)
public class GenreConverter implements Converter {
    
    @Inject
    GenreService gs;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return gs.getGenre(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        GenreTO genre = null;
        if (genre instanceof GenreTO){
            genre = (GenreTO) value;
            return genre.getName();
        }
        return "";
    }
    
}
