package com.pa165.mlib.utils;

import com.pa165.mlib.dto.AlbumTO;
import com.pa165.mlib.dto.ArtistTO;
import com.pa165.mlib.dto.GenreTO;
import com.pa165.mlib.dto.SongTO;
import com.pa165.mlib.entity.Album;
import com.pa165.mlib.entity.Artist;
import com.pa165.mlib.entity.Genre;
import com.pa165.mlib.entity.Song;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author ibek
 */
@Stateless
public class EntityDTOTransformer {
    
    public GenreTO transformGenreTO(Genre genre) {
        if (genre == null) {
            return null;
        }
        GenreTO gto = new GenreTO();
        gto.setName(genre.getName());
        return gto;
    }
    
    public SongTO transformSongTO(Song song) {
        SongTO sto = new SongTO();

        sto.setBitrate(song.getBitrate());
        sto.setCommentary(song.getCommentary());
        sto.setPosition(song.getPosition());
        sto.setTitle(song.getTitle());
        
        if (song.getGenre() != null) {
            sto.setGenre(transformGenreTO(song.getGenre()));
        }
        if (song.getAlbum() != null) {
            sto.setAlbum(transformAlbumTO(song.getAlbum()));
        }
        if (song.getArtist() != null) {
            sto.setArtist(transformArtistTO(song.getArtist()));
        }
        
        return sto;
    }
    
    public AlbumTO transformAlbumTO(Album album) {
        AlbumTO ato = new AlbumTO();
        
        ato.setTitle(album.getTitle());
        ato.setCover(album.getCover());
        ato.setReleased(album.getReleased());
        
       /** List<Song> songList = new ArrayList<>();
        songList = album.getSongs();
        List<SongTO> songToList = null;
        
        if (songList != null) {
            songToList = new ArrayList<>();
            for (Song s : songList) {
                songToList.add(transformSongTO(s));
            }
        }*/
        
        //ato.setSongs(songToList);
       
        return ato;
    }
    
    public ArtistTO transformArtistTO(Artist artist) {
        if (artist == null) {
            return null;
        }
        ArtistTO to = new ArtistTO();
        to.setId(artist.getId());
        to.setName(artist.getName());
        return to;
    }
    
}
