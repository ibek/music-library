package com.pa165.mlib.service.impl;

import com.pa165.mlib.dao.AlbumDao;
import com.pa165.mlib.dao.ArtistDao;
import com.pa165.mlib.dao.GenreDao;
import com.pa165.mlib.dao.SongDao;
import com.pa165.mlib.dto.AlbumTO;
import com.pa165.mlib.dto.ArtistTO;
import com.pa165.mlib.dto.GenreTO;
import com.pa165.mlib.dto.SongTO;
import com.pa165.mlib.entity.Song;
import com.pa165.mlib.service.SongService;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Implementation of SongService
 * 
 * @author tomparys
 */
@Stateless
public class SongServiceImpl implements SongService {
    
    @Inject
    SongDao songDao;
    
    @Inject
    GenreDao genreDao;
    
    @Inject
    AlbumDao albumDao;
    
    @Inject
    ArtistDao artistDao;

    @Override
    public SongTO createNewSong(String title, Integer bitrate, Integer position, String commentary,
                                GenreTO genre, AlbumTO album, ArtistTO artist) {
        Song s = new Song();
        s.setTitle(title);
        s.setBitrate(bitrate);
        s.setPosition(position);
        s.setCommentary(commentary);
        s.setGenre(genreDao.getGenre(genre.getName()));
        s.setAlbum(albumDao.getAlbum(album.getTitle()));
        //s.setArtist(artistDao.getArtist(artist.getName()));
            // uncomment when the AlbumTO method was implemented
        return getSongTO(s);
    }

    @Override
    public List<SongTO> getAllSongs() {
        return null;
    }
    
    @Override
    public List<SongTO> getSongByTitle(String title) {
        return null;
    }
    
    private SongTO getSongTO(Song song) {
        SongTO sto = new SongTO();
        // todo after SongTO is implemented
        return sto;
    }
    
}
