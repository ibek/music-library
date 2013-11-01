package com.pa165.mlib.service.impl;

import com.pa165.mlib.dao.ArtistDao;
import com.pa165.mlib.dto.ArtistTO;
import com.pa165.mlib.entity.Artist;
import com.pa165.mlib.service.ArtistService;
import com.pa165.mlib.utils.EntityDTOTransformer;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

/**
 *
 * @author ibek
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ArtistServiceImpl implements ArtistService {
    
    @Inject
    ArtistDao ad;
    
    @Inject
    EntityDTOTransformer transformer;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ArtistTO createNewArtist(String name) {
        Artist artist = new Artist();
        artist.setName(name);
        ad.addArtist(artist);
        return transformer.transformArtistTO(artist);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ArtistTO updateArtist(ArtistTO oldArtist, ArtistTO newArtist) {
        Artist artist = ad.getArtist(oldArtist.getName());
        artist.setName(newArtist.getName());
        ad.updateArtist(artist);       
        return newArtist;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean removeArtist(String name) {
        Artist artist = ad.getArtist(name);
        if (artist == null) {
            return false;
        }
        ad.removeArtist(artist);
        return true;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean removeArtist(ArtistTO artist) {
        return removeArtist(artist.getName());
    }

    @Override
    public List<ArtistTO> getAllArtists() {
        List<ArtistTO> artists = new ArrayList<>();
        for (Artist artist : ad.getAll()) {
            artists.add(transformer.transformArtistTO(artist));
        }
        return artists;
    }

    @Override
    public ArtistTO getArtist(String name) {
        return transformer.transformArtistTO(ad.getArtist(name));
    }
    
    public void setArtistDao(ArtistDao artistDao) {
        this.ad = artistDao;
    }
    
    public void setTransformer(EntityDTOTransformer transformer) {
        this.transformer = transformer;
    }
    
}
