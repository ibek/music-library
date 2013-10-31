package com.pa165.mlib.utils;

import com.pa165.mlib.dto.GenreTO;
import com.pa165.mlib.entity.Genre;
import javax.ejb.Stateless;

/**
 *
 * @author ibek
 */
@Stateless
public class EntityDTOTransformer {
    
    public GenreTO transformGenreTO(Genre genre) {
        if (genre == null) {
            return null;
        }
        GenreTO gto = new GenreTO();
        gto.setName(genre.getName());
        return gto;
    }
    
}
