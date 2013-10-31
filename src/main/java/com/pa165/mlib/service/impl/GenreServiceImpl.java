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
    public GenreTO updateGenre(GenreTO oldGenre, GenreTO newGenre) {
        Genre genre = gd.getGenre(oldGenre.getName());
        genre.setName(newGenre.getName());
        gd.updateGenre(genre);
        return newGenre;
    }

    @Override
    public boolean removeGenre(String name) {
        Genre genre = gd.getGenre(name);
        if (genre == null) {
            return false;
        }
        gd.removeGenre(genre);
        return true;
    }
    
    public void setGenreDao(GenreDao genreDao) {
        this.gd = genreDao;
    }
    
    public void setTransformer(EntityDTOTransformer transformer) {
        this.transformer = transformer;
    }
    
}
