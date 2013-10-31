package com.pa165.mlib.service.impl;

import com.pa165.mlib.dao.GenreDao;
import com.pa165.mlib.dto.GenreTO;
import com.pa165.mlib.entity.Genre;
import com.pa165.mlib.service.GenreService;
import com.pa165.mlib.utils.EntityDTOTransformer;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author xbek
 */
@Stateless
public class GenreServiceImpl implements GenreService {
    
    @Inject
    GenreDao gd;
    
    @Inject
    EntityDTOTransformer transformer;

    @Override
    public GenreTO createNewGenre(String name) {
        Genre g = new Genre();
        g.setName(name);
        gd.addGenre(g);
        GenreTO ng = new GenreTO();
        ng.setName(name);
        return ng;
    }

    @Override
    public List<GenreTO> getAllGenres() {
        List<GenreTO> list = new ArrayList<>();
        for (Genre g : gd.getAll()) {
            GenreTO dto = new GenreTO();
            dto.setName(g.getName());
            list.add(dto);
        }
        return list;
    }

    @Override
    public GenreTO getGenre(String name) {
        return transformer.transformGenreTO(gd.getGenre(name));
    }

    @Override
    public GenreTO updateGenre(GenreTO genre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeGenre(String genre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<GenreTO> getGenres(String... names) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void setGenreDao(GenreDao genreDao) {
        this.gd = genreDao;
    }
    
}
