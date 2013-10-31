package com.pa165.mlib.service.impl;

import com.pa165.mlib.dao.ArtistDao;
import com.pa165.mlib.dto.ArtistTO;
import com.pa165.mlib.dto.SongTO;
import com.pa165.mlib.entity.Artist;
import com.pa165.mlib.service.ArtistService;
import com.pa165.mlib.utils.EntityDTOTransformer;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author ibek
 */
public class ArtistServiceImpl implements ArtistService {
    
    @Inject
    ArtistDao ad;
    
    @Inject
    EntityDTOTransformer transformer;

    @Override
    public ArtistTO createNewArtist(String name) {
        Artist artist = new Artist();
        artist.setName(name);
        ad.addArtist(artist);
        return transformer.tranformArtistTO(artist);
    }

    @Override
    public ArtistTO getArtist(Long id) {
        return transformer.tranformArtistTO(ad.getArtist(id));
    }

    @Override
    public ArtistTO updateArtist(ArtistTO oldArtist, ArtistTO newArtist) {
        Artist artist = ad.getArtist(oldArtist.getId());
        artist.setName(newArtist.getName());
        ad.updateArtist(artist);
        return newArtist;
    }

    @Override
    public boolean removeArtist(Long id) {
        Artist artist = ad.getArtist(id);
        if (artist == null) {
            return false;
        }
        ad.removeArtist(artist);
        return true;
    }

    @Override
    public boolean removeArtist(ArtistTO artist) {
        return removeArtist(artist.getId());
    }

    @Override
    public List<ArtistTO> getAllArtists() {
        List<ArtistTO> artists = new ArrayList<>();
        for (Artist artist : ad.getAll()) {
            artists.add(transformer.tranformArtistTO(artist));
        }
        return artists;
    }

    @Override
    public List<ArtistTO> getArtistsByName(String name) {
        List<ArtistTO> artists = new ArrayList<>();
        for (Artist artist : ad.getArtist(name)) {
            artists.add(transformer.tranformArtistTO(artist));
        }
        return artists;
    }
    
}
