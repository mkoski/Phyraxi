package fourx.engine.generators;

import java.util.List;

import fourx.domain.Coordinates;

/**
 * 
 */
public interface CoordinateGenerator {

	List<Coordinates> generateStarSystemCoordinates(int numberOfStars);
	
	int getMapRadius(int numberOfStars);

}