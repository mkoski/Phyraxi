package fourx.engine.generators;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import fourx.command.GameSettings;
import fourx.domain.Coordinates;

/**
 * Tests for {@link Random2DCoordinateGenerator}.
 * 
 * @author Jani Kaarela
 */
public class Random2DCoordinateGeneratorTest {

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionOnGalaxySizeLessThanOne() {
		GameSettings gameSettings = new GameSettings.Builder().setGalaxySize(0).build();
		CoordinateGenerator generator = new Random2DCoordinateGenerator();
		generator.generateStarSystemCoordinates(gameSettings);
	}

	@Test
	public void shouldGenerateCorrectNumberOfCoordinates() {
		int galaxySize = 10;
		GameSettings gameSettings = new GameSettings.Builder().setGalaxySize(galaxySize).build();
		CoordinateGenerator generator = new Random2DCoordinateGenerator();
		List<Coordinates> coordinates = generator.generateStarSystemCoordinates(gameSettings);
		assertEquals(galaxySize, coordinates.size());
	}

}
