package phyraxi.engine.generators.stars;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import phyraxi.domain.Coordinates;
import phyraxi.engine.generators.stars.CoordinateGenerator;
import phyraxi.engine.generators.stars.RandomSphericalCoordinateGenerator;


/**
 * Tests for {@link RandomSphericalCoordinateGenerator}.
 * 
 * @author Jani Kaarela
 */
public class RandomCoordinateGeneratorTest {

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionOnGalaxySizeLessThanOne() {
		CoordinateGenerator generator = new RandomSphericalCoordinateGenerator();
		generator.generateStarSystemCoordinates(0);
	}

	@Test
	public void shouldGenerateCorrectNumberOfCoordinates() {
		int galaxySize = 10;
		CoordinateGenerator generator = new RandomSphericalCoordinateGenerator();
		List<Coordinates> coordinates = generator.generateStarSystemCoordinates(galaxySize);
		assertEquals(galaxySize, coordinates.size());
		for (Coordinates coords : coordinates) {
			System.out.println(coords);
		}
	}

}
