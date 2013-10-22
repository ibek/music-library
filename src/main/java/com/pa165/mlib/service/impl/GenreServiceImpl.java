package com.pa165.mlib.service.impl;

import com.pa165.mlib.dao.GenreDao;
import com.pa165.mlib.dto.Genre;
import com.pa165.mlib.service.GenreService;
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

    @Override
    public Genre createNewGenre(String name) {
        com.pa165.mlib.entity.Genre g = new com.pa165.mlib.entity.Genre();
        g.setName(name);
        gd.addGenre(g);
        Genre ng = new Genre();
        ng.setName(name);
        return ng;
    }

    @Override
    public List<Genre> getAllGenres() {
        List<Genre> list = new ArrayList<>();
        for (com.pa165.mlib.entity.Genre g : gd.getAll()) {
            Genre dto = new Genre();
            dto.setName(g.getName());
            list.add(dto);
        }
        return list;
    }
    
}
