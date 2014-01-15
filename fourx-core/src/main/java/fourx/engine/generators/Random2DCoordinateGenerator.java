package fourx.engine.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fourx.command.GameSettings;
import fourx.domain.Coordinates;

/**
 * A very simple coordinate generator returning completely random coordinates. Z-axis is always zero,
 * and there are no safeguards against multiple instances of the same coordinates.
 * 
 * @author Jani Kaarela
 */
public class Random2DCoordinateGenerator implements CoordinateGenerator {

	@Override
	public List<Coordinates> generateStarSystemCoordinates(GameSettings gameSettings) {
		int starCount = gameSettings.getGalaxySize();
		if (starCount < 1) {
			throw new IllegalArgumentException("Invalid galaxy size: " + starCount);
		}
		final int mapSize = 10 * starCount;
		final int maxCoord = mapSize + 1;
		final int offset = mapSize / 2;
		Random random = new Random();
		List<Coordinates> coordinates = new ArrayList<>(starCount);
		for (int i = 0; i < starCount; i++) {
			int x = random.nextInt(maxCoord) - offset;
			int y = random.nextInt(maxCoord) - offset;
			int z = 0; // we totally don't care about Z-axis at this point!
			coordinates.add(new Coordinates(x, y, z)); // we don't care about multiple instances of same coords either
		}
		return coordinates;
	}

}
