package com.pa165.mlib.test.service;

import com.pa165.mlib.dao.AlbumDao;
import com.pa165.mlib.dto.AlbumTO;
import com.pa165.mlib.dao.ArtistDao;
import com.pa165.mlib.dao.GenreDao;
import com.pa165.mlib.dao.SongDao;
import com.pa165.mlib.dto.ArtistTO;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.pa165.mlib.dto.GenreTO;
import com.pa165.mlib.dto.SongTO;
import com.pa165.mlib.entity.Album;
import com.pa165.mlib.service.impl.AlbumServiceImpl;
import com.pa165.mlib.entity.Artist;
import com.pa165.mlib.entity.Genre;
import com.pa165.mlib.entity.Song;
import com.pa165.mlib.service.impl.ArtistServiceImpl;
import com.pa165.mlib.service.impl.GenreServiceImpl;
import com.pa165.mlib.service.impl.SongServiceImpl;
import com.pa165.mlib.utils.EntityDTOTransformer;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author xbek
 */
public class SongServiceTest {
    
    @Test
    public void testSongServiceCRUD() throws Exception {
        SongServiceImpl songService = new SongServiceImpl();
        EntityDTOTransformer transformer = new EntityDTOTransformer();
        songService.setTransformer(transformer);
        SongDao songDao = mock(SongDao.class);
        songService.setSongDao(songDao);
        
        String title = "Title";
        Integer bitrate = 256;
        Integer position = 3;
        String commentary = "Social Commentary";
        
        SongTO testSong = new SongTO();
        testSong.setTitle(title);
        testSong.setBitrate(bitrate);
        testSong.setPosition(position);
        testSong.setCommentary(commentary);
        
        Song s = new Song();
        s.setTitle(title);
        s.setBitrate(bitrate);
        s.setPosition(position);
        s.setCommentary(commentary);
        
        when(songDao.getSong(title)).thenReturn(s);       
        
        //SongTO proTestSong = songService.createNewSong(title, bitrate, position, commentary, null, null, null);
        SongTO proTestSong = songService.createNewSong(testSong);
        assertEquals(title, proTestSong.getTitle());
        assertEquals(bitrate, proTestSong.getBitrate());
        assertEquals(position, proTestSong.getPosition());
        assertEquals(commentary, proTestSong.getCommentary());
        
        SongTO protiTestSong = songService.getSong(title);
        assertEquals(proTestSong, protiTestSong);
        
        String newTitle = "Remastered";
        protiTestSong.setTitle(newTitle);
        protiTestSong = songService.updateSong(proTestSong, protiTestSong);
        assertEquals(newTitle, protiTestSong.getTitle());
        
        boolean removed = songService.removeSong(title);
        assertTrue(removed);
        
        removed = songService.removeSong(proTestSong);
        assertTrue(removed);
    }
    
    @Test
    public void testSongServiceGetAll() throws Exception {
        SongServiceImpl songService = new SongServiceImpl();
        EntityDTOTransformer transformer = new EntityDTOTransformer();
        songService.setTransformer(transformer);
        SongDao songDao = mock(SongDao.class);
        songService.setSongDao(songDao);
        
        when(songDao.getAll()).thenReturn(new ArrayList<Song>(){{
            Song s1 = new Song();
            s1.setTitle("Song 1");
            add(s1);
            Song s2 = new Song();
            s2.setTitle("Song 2");
            add(s2);
        }});
        
        List<SongTO> all = songService.getAllSongs();
        assertEquals(2, all.size());
        assertEquals("Song 1", all.get(0).getTitle());
        assertEquals("Song 2", all.get(1).getTitle());
        /**/
    }
}
