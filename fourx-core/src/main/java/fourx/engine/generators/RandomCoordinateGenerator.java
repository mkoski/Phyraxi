package fourx.engine.generators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import fourx.command.GameSettings;
import fourx.domain.Coordinates;
import fourx.domain.DistanceMarginCoordinatesKey;

/**
 * A very simple coordinate generator returning completely random coordinates.
 * 
 * @author Jani Kaarela
 */
public class RandomCoordinateGenerator implements CoordinateGenerator {
	
	private static final int MAX_STAR_COUNT = 1000;
	private static final int MAP_SIZE_FACTOR = 5;

	@Override
	public List<Coordinates> generateStarSystemCoordinates(GameSettings gameSettings) {
		int starCount = gameSettings.getGalaxySize();
		return generateStarSystemCoordinates(starCount);
	}
	
	public List<Coordinates> generateStarSystemCoordinates(int starCount) {
		if (starCount < 1 || starCount > MAX_STAR_COUNT) {
			throw new IllegalArgumentException("Invalid galaxy size: " + starCount);
		}
		final int mapSize = MAP_SIZE_FACTOR * starCount;
		final int maxCoord = mapSize + 1;
		final int offset = mapSize / 2;
		Random random = new Random();
		Map<DistanceMarginCoordinatesKey, Coordinates> generatedCoords = new HashMap<>(starCount);
		//List<Coordinates> coordinates = new ArrayList<>(starCount);
		while (generatedCoords.size() < starCount) {
			int x = random.nextInt(maxCoord) - offset;
			int y = random.nextInt(maxCoord) - offset;
			int maxZ = (int) Math.floor(maxCoord / Math.sqrt(distanceFromOrigo(x, y)));
			int zOffset = maxZ / 2;
			int z = random.nextInt(maxZ) - zOffset;
			Coordinates coordinates = new Coordinates(x, y, z);
			DistanceMarginCoordinatesKey key = new DistanceMarginCoordinatesKey(coordinates);
			if (!generatedCoords.containsKey(key)) {
				generatedCoords.put(key, coordinates);
			}
		}
		return new ArrayList<Coordinates>(generatedCoords.values());
	}
	
	double distanceFromOrigo(int x, int y) {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}

}
