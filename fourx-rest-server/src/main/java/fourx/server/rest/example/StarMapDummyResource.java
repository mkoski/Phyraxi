package fourx.server.rest.example;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fourx.domain.Coordinates;
import fourx.domain.LuminosityClass;
import fourx.domain.SpectralType;
import fourx.domain.Star;
import fourx.server.rest.data.StarMap;
import fourx.server.rest.data.StarMap.StarInfo;

/**
 * A dummy resource returning a hard-coded star map.
 * 
 * @author Jani Kaarela (@gmail.com)
 */
@Path("dummy-starmap")
public class StarMapDummyResource {
    
    private static final List<StarInfo> STARS = Arrays.asList(
	    	new StarInfo(
	    		new Star("Sol", SpectralType.G, 2, LuminosityClass.MAIN_SEQUENCE, 1.0, 1.0, 5777),
	    		new Coordinates(0,  0,  0))
	    );
    private static final StarMap DUMMY_STARMAP = new StarMap(STARS);
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public StarMap getStarMap() {
	return DUMMY_STARMAP;
    }

}
