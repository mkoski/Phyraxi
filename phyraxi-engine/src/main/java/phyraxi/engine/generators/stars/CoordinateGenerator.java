package phyraxi.engine.generators.stars;

import java.util.List;

import phyraxi.domain.Coordinates;


/**
 * 
 */
public interface CoordinateGenerator {

	public static final int MAX_STAR_COUNT = 1000;
	public static final int MAP_SIZE_FACTOR = 250;
	public static final int DISTANCE_WEIGHTING_FACTOR = 3;
	public static final int Z_FLATTENING_FACTOR = 3;

	List<Coordinates> generateStarSystemCoordinates(int numberOfStars);
	
	Coordinates getMapDimensions(int numberOfStars);
	
	UniverseShape getUniverseShape();
	
	public enum UniverseShape {
		SPHERE,
		CUBE
	}

}