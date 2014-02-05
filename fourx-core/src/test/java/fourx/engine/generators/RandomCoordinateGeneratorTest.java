package fourx.engine.generators;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import fourx.command.GameSettings;
import fourx.domain.Coordinates;

/**
 * Tests for {@link RandomCoordinateGenerator}.
 * 
 * @author Jani Kaarela
 */
public class RandomCoordinateGeneratorTest {

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionOnGalaxySizeLessThanOne() {
		GameSettings gameSettings = new GameSettings.Builder().setGalaxySize(0).build();
		CoordinateGenerator generator = new RandomCoordinateGenerator();
		generator.generateStarSystemCoordinates(gameSettings);
	}

	@Test
	public void shouldGenerateCorrectNumberOfCoordinates() {
		int galaxySize = 10;
		GameSettings gameSettings = new GameSettings.Builder().setGalaxySize(galaxySize).build();
		CoordinateGenerator generator = new RandomCoordinateGenerator();
		List<Coordinates> coordinates = generator.generateStarSystemCoordinates(gameSettings);
		assertEquals(galaxySize, coordinates.size());
		for (Coordinates coords : coordinates) {
			System.out.println(coords);
		}
	}

}
