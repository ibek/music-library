package com.pa165.mlib.service.impl;

import com.pa165.mlib.dao.AlbumDao;
import com.pa165.mlib.dto.AlbumTO;
import com.pa165.mlib.dto.SongTO;
import com.pa165.mlib.entity.Album;
import com.pa165.mlib.service.AlbumService;
import com.pa165.mlib.utils.EntityDTOTransformer;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author brazdil
 */
@Stateless
public class AlbumServiceImpl implements AlbumService{
    
    @Inject
    AlbumDao albumDao;
    
    @Inject
    EntityDTOTransformer transformer;
    
    @Override
    public List<AlbumTO> getAllAlbums(){
        List<AlbumTO> list = new ArrayList<>();
     
        for (Album a : albumDao.getAll()) {
            AlbumTO ato = transformer.transformAlbumTO(a);
            list.add(ato);
        }
        return list;
    }
    
    @Override
    public AlbumTO getAlbumByTitle(String title){
        AlbumTO ato = transformer.transformAlbumTO(albumDao.getAlbum(title));
        return ato;
    }
    
    @Override
    public List<AlbumTO> getAlbumByRelease(Integer year){
        
        List<AlbumTO> listAlbumTO = new ArrayList<>();
        
        for (Album a : albumDao.getAll()) {
            
            AlbumTO ato = transformer.transformAlbumTO(a);
            if (ato.getReleased() == year) {
                listAlbumTO.add(ato);
            }
        }
        return listAlbumTO;
    }
    
    @Override
    public AlbumTO createNewAlbum(String title, byte[] cover, Integer year, List<SongTO> songs) {
        
        Album album = new Album();
        
        album.setTitle(title);
        album.setReleased(year);
        album.setCover(cover);
        
        AlbumTO ato = transformer.transformAlbumTO(album);
        ato.setSongs(songs);
        
        return ato;
    }
    
    @Override
    public AlbumTO getAlbum(String title) {
        return transformer.transformAlbumTO(albumDao.getAlbum(title));
    }
    
    @Override
    public AlbumTO updateAlbum(AlbumTO oldAlbum, AlbumTO newAlbum) {
        
        Album album = albumDao.getAlbum(oldAlbum.getTitle());
        album.setTitle(newAlbum.getTitle());
        albumDao.updateAlbum(album);        
        return newAlbum;
    }
    
    @Override
    public boolean removeAlbum(String title) {
        
        Album album = albumDao.getAlbum(title);
        if (album == null) {
            return false;
        }
        albumDao.removeAlbum(album);
        return true;
    }
    
    public void setAlbumDao(AlbumDao albumDao) {
        this.albumDao = albumDao;
    }
    
    public void setTransformer(EntityDTOTransformer transformer) {
        this.transformer = transformer;
    }
    
}
