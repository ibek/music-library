package com.pa165.mlib.service.impl;

import com.pa165.mlib.dao.GenreDao;
import com.pa165.mlib.dto.GenreTO;
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
    public GenreTO createNewGenre(String name) {
        com.pa165.mlib.entity.Genre g = new com.pa165.mlib.entity.Genre();
        g.setName(name);
        gd.addGenre(g);
        GenreTO ng = new GenreTO();
        ng.setName(name);
        return ng;
    }

    @Override
    public List<GenreTO> getAllGenres() {
        List<GenreTO> list = new ArrayList<>();
        for (com.pa165.mlib.entity.Genre g : gd.getAll()) {
            GenreTO dto = new GenreTO();
            dto.setName(g.getName());
            list.add(dto);
        }
        return list;
    }
    
}
