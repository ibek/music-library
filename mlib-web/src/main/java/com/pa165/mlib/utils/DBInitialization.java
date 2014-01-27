package com.pa165.mlib.utils;

import com.pa165.mlib.dto.AlbumTO;
import com.pa165.mlib.dto.ArtistTO;
import com.pa165.mlib.dto.GenreTO;
import com.pa165.mlib.dto.Role;
import com.pa165.mlib.dto.SongTO;
import com.pa165.mlib.dto.UserTO;
import com.pa165.mlib.exception.DuplicateException;
import com.pa165.mlib.service.AlbumService;
import com.pa165.mlib.service.ArtistService;
import com.pa165.mlib.service.GenreService;
import com.pa165.mlib.service.SongService;
import com.pa165.mlib.service.UserService;
import javax.annotation.PostConstruct;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RunAs;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author ibek
 */
@Startup
@Singleton
@DeclareRoles("ADMIN")
@RunAs("ADMIN")
public class DBInitialization {
    
    @Inject
    SongService songService;
    
    @Inject
    ArtistService artistService;
    
    @Inject
    AlbumService albumService;
    
    @Inject
    GenreService genreService;
    
    @PostConstruct
    public void init() {
        /*System.out.println("initializing database ...");
        GenreTO genre = new GenreTO();
        AlbumTO album = new AlbumTO();
        ArtistTO artist = new ArtistTO();
        SongTO song = new SongTO();
        
        try {
            genre.setName("Alternative");
            genreService.createNewGenre(genre);
            genre.setName("Pop");
            genreService.createNewGenre(genre);
            genre.setName("Rock");
            genreService.createNewGenre(genre);
            
            album.setTitle("The Dark Side of the Moon");
            album.setReleased(1973);
            albumService.createNewAlbum(album);
            album.setTitle("Wish You Were Here");
            album.setReleased(1975);
            albumService.createNewAlbum(album);
            
            artist.setName("Roger Waters");
            artistService.createNewArtist(artist);
            artist.setName("Pink Floyd");
            artistService.createNewArtist(artist);
            
            song.setTitle("Shine On You Crazy Diamond");
            song.setAlbum(album);
            song.setArtist(artist);
            song.setGenre(genre);
            songService.createNewSong(song);
            
        } catch (DuplicateException ex) {
            
        }
                */
    }
    
}
