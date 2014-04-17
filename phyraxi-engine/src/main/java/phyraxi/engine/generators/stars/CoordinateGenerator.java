package phyraxi.engine.generators.stars;

import java.util.List;

import phyraxi.domain.Coordinates;


/**
 * 
 */
public interface CoordinateGenerator {

	List<Coordinates> generateStarSystemCoordinates(int numberOfStars);
	
	int getMapRadius(int numberOfStars);

}