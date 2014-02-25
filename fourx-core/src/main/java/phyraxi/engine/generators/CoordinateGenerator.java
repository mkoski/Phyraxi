package phyraxi.engine.generators;

import java.util.List;

import phyraxi.domain.Coordinates;


/**
 * 
 */
public interface CoordinateGenerator {

	List<Coordinates> generateStarSystemCoordinates(int numberOfStars);
	
	int getMapRadius(int numberOfStars);

}