/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pa165.mlib.service.impl;

import com.pa165.mlib.dao.AlbumDao;
import com.pa165.mlib.dto.AlbumTO;
import com.pa165.mlib.dto.SongTO;
import com.pa165.mlib.entity.Song;
import com.pa165.mlib.service.AlbumService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author brazdil
 */
public class AlbumServiceImpl implements AlbumService{
    
    @Inject
    AlbumDao albumDao;
    
    @Override
    public List<AlbumTO> getAllAlbums(){
        List<AlbumTO> list = new ArrayList<>();
     
        for (com.pa165.mlib.entity.Album a : albumDao.getAll()) {
            AlbumTO ato = new AlbumTO();
            
            
            
            list.add(ato);
        }
        return list;
    }
    
    @Override
    public List<AlbumTO> getAlbumByTitle(String title){
        List<AlbumTO> list = new ArrayList<>();
        
        
        return list;
    }
    
    @Override
    public List<AlbumTO> getAlbumByRelease(Integer year){
        List<AlbumTO> list = new ArrayList<>();
        
        
        return list;
    }
    
    @Override
    public AlbumTO createNewAlbum(String title, Integer year, List<SongTO> songs){
        
        AlbumTO albumTO = new AlbumTO();
        
        com.pa165.mlib.entity.Album album = new com.pa165.mlib.entity.Album();
        
        album.setTitle(title);
        album.setReleased(year);
        
        Iterator<SongTO> iterator = songs.iterator();
        
        while (iterator.hasNext()){
            
        }
        
        return albumTO;
    }
    
}
