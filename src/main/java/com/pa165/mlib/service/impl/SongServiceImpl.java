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
import com.pa165.mlib.utils.EntityDTOTransformer;
import java.util.ArrayList;
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
    
    @Inject
    EntityDTOTransformer transformer;

    @Override
    public SongTO createNewSong(String title, Integer bitrate, Integer position, String commentary,
                                GenreTO genre, AlbumTO album, ArtistTO artist) {
        Song s = new Song();
        s.setTitle(title);
        s.setBitrate(bitrate);
        s.setPosition(position);
        s.setCommentary(commentary);
        if (genre != null) {
            s.setGenre(genreDao.getGenre(genre.getName()));
        }
        if (album != null) {
            s.setAlbum(albumDao.getAlbum(album.getTitle()));
        }
        if (artist != null) {
            s.setArtist(artistDao.getArtist(artist.getName()));
        }
        songDao.addSong(s);
        return transformer.transformSongTO(s);
    }

    @Override
    public List<SongTO> getAllSongs() {
        List<SongTO> songs = new ArrayList<>();
        
        for (Song s : songDao.getAll()) {
            songs.add(transformer.transformSongTO(s));
        }
        return songs;
    }

    @Override
    public SongTO getSong(String title) {
        return transformer.transformSongTO(songDao.getSong(title));
    }

    @Override
    public SongTO updateSong(SongTO oldSong, SongTO newSong) {
        Song s = songDao.getSong(oldSong.getTitle());
        s.setTitle(newSong.getTitle());
        s.setBitrate(newSong.getBitrate());
        s.setPosition(newSong.getPosition());
        s.setCommentary(newSong.getCommentary());
        if (newSong.getGenre() != null) {
            s.setGenre(genreDao.getGenre(newSong.getGenre().getName()));
        }
        if (newSong.getAlbum() != null) {
            s.setAlbum(albumDao.getAlbum(newSong.getAlbum().getTitle()));
        }
        if (newSong.getArtist() != null) {
            s.setArtist(artistDao.getArtist(newSong.getArtist().getName()));
        }
        songDao.updateSong(s);
        return newSong;
    }

    @Override
    public boolean removeSong(String title) {
        Song song = songDao.getSong(title);
        if (song == null) {
            return false;
        } else {
            songDao.removeSong(song);
            return true;
        }
    }

    @Override
    public boolean removeSong(SongTO song) {
        return removeSong(song.getTitle());
    }
    

    public void setSongDao(SongDao songDao) {
        this.songDao = songDao;
    }

    public void setGenreDao(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    public void setAlbumDao(AlbumDao albumDao) {
        this.albumDao = albumDao;
    }

    public void setArtistDao(ArtistDao artistDao) {
        this.artistDao = artistDao;
    }
        
    public void setTransformer(EntityDTOTransformer transformer) {
        this.transformer = transformer;
    }
    
}
