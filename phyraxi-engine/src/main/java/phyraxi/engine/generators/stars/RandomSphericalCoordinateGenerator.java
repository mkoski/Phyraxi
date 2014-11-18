package phyraxi.engine.generators.stars;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import phyraxi.domain.Coordinates;
import phyraxi.domain.DistanceMarginCoordinatesKey;
import phyraxi.utils.GalacticCoordinatesConverter;


/**
 * A very simple coordinate generator returning very random coordinates.
 * 
 * @author Jani Kaarela
 */
public class RandomSphericalCoordinateGenerator implements CoordinateGenerator {
	
	private GalacticCoordinatesConverter converter = new GalacticCoordinatesConverter();
	
	@Override
	public List<Coordinates> generateStarSystemCoordinates(int starCount) {
		if (starCount < 1 || starCount > MAX_STAR_COUNT) {
			throw new IllegalArgumentException("Invalid galaxy size: " + starCount);
		}
		Random random = new Random();
		Map<DistanceMarginCoordinatesKey, Coordinates> generatedCoords = new HashMap<>(starCount);
		int maxDistance = getMapDimensions(starCount).x; // x = y = radius
		while (generatedCoords.size() < starCount) {
			int distance = randomDistance(maxDistance, random);
			float longitude = randomLongitude(random);
			Coordinates coordinates = randomCoordinates(distance, maxDistance, longitude, random);
			DistanceMarginCoordinatesKey key = new DistanceMarginCoordinatesKey(coordinates);
			if (!generatedCoords.containsKey(key)) {
				generatedCoords.put(key, coordinates);
			}
		}
		return new ArrayList<Coordinates>(generatedCoords.values());
	}
	
	public Coordinates getMapDimensions(int starCount) {
		int radius = MAP_SIZE_FACTOR * starCount;
		int depth = radius / Z_FLATTENING_FACTOR;
		return new Coordinates(radius, radius, depth);
	}
	
	int randomDistance(int maxDistance, Random random) {
		int distance = (int) Math.round(maxDistance * Math.pow(random.nextFloat(), DISTANCE_WEIGHTING_FACTOR));
		return distance;
	}
	
	float randomLongitude(Random random) {
		return random.nextFloat() * 360;
	}
	
	Coordinates randomCoordinates(int distance, int maxDistance, float longitude, Random random) {
		int maxZ = (maxDistance - distance) / Z_FLATTENING_FACTOR;
		int zOffset = maxZ / 2;
		int z = (int) Math.round(Math.pow(random.nextFloat(), DISTANCE_WEIGHTING_FACTOR) * maxZ) - zOffset;
		double radianLongitude = Math.toRadians(longitude);
		double radianLatitude = (distance > 0 ? Math.asin(z / distance) : 0);
		long x = converter.calculateX(distance, radianLatitude, radianLongitude);
		long y = converter.calculateY(distance, radianLatitude, radianLongitude);
		return new Coordinates((int) x, (int) y, (int) z);
	}

	@Override
	public UniverseShape getUniverseShape() {
		return UniverseShape.SPHERE;
	}

}
