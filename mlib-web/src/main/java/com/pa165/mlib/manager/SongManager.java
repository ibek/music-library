package com.pa165.mlib.manager;

import com.pa165.mlib.dto.AlbumTO;
import com.pa165.mlib.dto.SongTO;
import com.pa165.mlib.exception.DuplicateException;
import com.pa165.mlib.service.AlbumService;
import com.pa165.mlib.service.ArtistService;
import com.pa165.mlib.service.GenreService;
import com.pa165.mlib.service.SongService;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ibek
 */
@SessionScoped
@ManagedBean
@Named
public class SongManager implements Serializable {
    
    @Inject
    transient Logger logger;
    
    @Inject
    SongService songService;
    
    @Inject
    ArtistService artistService;
    
    @Inject
    AlbumService albumService;
    
    @Inject
    GenreService genreService;
    
    private SongTO songTO = new SongTO();
    private String formAlbum;
    private String formArtist;
    private String formGenre;
    
    public void init() {
        songTO = new SongTO();
    }

    public SongService getSongService() {
        return songService;
    }

    public ArtistService getArtistService() {
        return artistService;
    }

    public AlbumService getAlbumService() {
        return albumService;
    }

    public GenreService getGenreService() {
        return genreService;
    }
    
    public SongTO getSongTO() {
        return songTO;
    }

    public String getFormAlbum() {
        return formAlbum;
    }

    public void setFormAlbum(String formAlbum) {
        this.formAlbum = formAlbum;
    }

    public String getFormArtist() {
        return formArtist;
    }

    public void setFormArtist(String formArtist) {
        this.formArtist = formArtist;
    }

    public String getFormGenre() {
        return formGenre;
    }

    public void setFormGenre(String formGenre) {
        this.formGenre = formGenre;
    }
    
    
    
    public void create() {
        if (formAlbum != null && !"".equals(formAlbum)) {
            songTO.setAlbum(albumService.getAlbum(formAlbum));
        }
        if (formArtist != null && !"".equals(formArtist)) {
            songTO.setArtist(artistService.getArtist(formArtist));
        }
        if (formGenre != null && !"".equals(formGenre)) {
            songTO.setGenre(genreService.getGenre(formGenre));
        }
        
        logger.log(Level.INFO, "Creating {0}", songTO);
        try {
            songService.createNewSong(songTO);
            init();
        } catch (DuplicateException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("The song cannot be created because a song with this title already exists."));
        }
    }
    
    public void remove(SongTO song) {
        logger.log(Level.INFO, "Removing {0}", song);
        songService.removeSong(song);
        init();
    }
    
    public List<SongTO> getSongsInAlbum(AlbumTO album) {
        return songService.getSongsInAlbum(album);
    }
    
}
