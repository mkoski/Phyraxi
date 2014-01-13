package fourx.server.rest.data;

import java.util.List;

import fourx.domain.Coordinates;
import fourx.domain.Star;

/**
 * A data transfer object for star map.
 * 
 * @author Jani Kaarela (@gmail.com)
 */
public class StarMap {
    
    private final List<StarInfo> stars;
    
    public StarMap(List<StarInfo> stars) {
	this.stars = stars;
    }
    
    public List<StarInfo> getStars() {
	return stars;
    }
    
    public static class StarInfo {
	
	final Star star;
	final String type;
	final Coordinates coordinates;
	
	public StarInfo(Star star, Coordinates coordinates) {
	    this.star = star;
	    this.coordinates = coordinates;
	    this.type = star.getSpectralType().toString() + star.getSpectralNumber() + star.getLuminosityClass();
	}
	
	public String getName() {
	    return star.getName();
	}
	
	public String getType() {
	    return type;
	}
	
	public Coordinates getCoordinates() {
	    return coordinates;
	}
	
    }

}
