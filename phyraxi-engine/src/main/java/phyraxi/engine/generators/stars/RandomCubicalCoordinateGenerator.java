package phyraxi.engine.generators.stars;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import phyraxi.domain.Coordinates;
import phyraxi.domain.DistanceMarginCoordinatesKey;

public class RandomCubicalCoordinateGenerator implements CoordinateGenerator {
	
	@Override
	public List<Coordinates> generateStarSystemCoordinates(int starCount) {
		// TODO: repetition, pull up to an abstract super class?
		if (starCount < 1 || starCount > MAX_STAR_COUNT) {
			throw new IllegalArgumentException("Invalid galaxy size: " + starCount);
		}
		Random random = new Random();
		Map<DistanceMarginCoordinatesKey, Coordinates> generatedCoords = new HashMap<>(starCount);
		Coordinates maxDimensions = getMapDimensions(starCount);
		while (generatedCoords.size() < starCount) {
			int x = randomDistance(maxDimensions.x, random);
			int y = randomDistance(maxDimensions.y, random);
			int z = randomDistance(maxDimensions.z, random);
			Coordinates coordinates = new Coordinates(x, y, z);
			DistanceMarginCoordinatesKey key = new DistanceMarginCoordinatesKey(coordinates);
			if (!generatedCoords.containsKey(key)) {
				generatedCoords.put(key, coordinates);
			}
		}
		return new ArrayList<Coordinates>(generatedCoords.values());
	}

	@Override
	public Coordinates getMapDimensions(int numberOfStars) {
		int maxDimension = numberOfStars * MAP_SIZE_FACTOR;
		return new Coordinates(maxDimension, maxDimension, maxDimension);
	}

	@Override
	public UniverseShape getUniverseShape() {
		return UniverseShape.CUBE;
	}
	
	// TODO: create abstract base class, pull this up
	int randomDistance(int maxDistance, Random random) {
		int distance = (int) Math.round(maxDistance * Math.pow(random.nextFloat(), DISTANCE_WEIGHTING_FACTOR));
		return distance;
	}

}
