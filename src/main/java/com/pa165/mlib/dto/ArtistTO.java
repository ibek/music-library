package com.pa165.mlib.dto;

/**
 * Artist Transfer Object
 * 
 * @author Ragu
 */
public class ArtistTO {

    private Long id;
    
    private String name;
    
    public ArtistTO() {
    }

    public ArtistTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    
     public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }    
    
}
