package fourx.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * Tests for {@link DistanceMarginCoordinatesKey}.
 * 
 * @author Jani Kaarela (@gmail.com)
 */
public class DistanceMarginCoordinatesKeyTest {
	
	@Test
	public void shouldHaveEqualHashCodes() {
		DistanceMarginCoordinatesKey key1 = new DistanceMarginCoordinatesKey(new Coordinates(1, 4, 10));
		DistanceMarginCoordinatesKey key2 = new DistanceMarginCoordinatesKey(new Coordinates(4, 2, -10));
		// Z-coord should not matter, and X and Y are close enough
		assertEquals(key1.hashCode(), key2.hashCode());
	}
	
	@Test
	public void shouldBeEqual() {
		DistanceMarginCoordinatesKey key1 = new DistanceMarginCoordinatesKey(new Coordinates(1, 4, 10));
		DistanceMarginCoordinatesKey key2 = new DistanceMarginCoordinatesKey(new Coordinates(4, 2, -10));
		// Z-coord should not matter, and X and Y are close enough
		assertEquals(key1, key2);
	}
	
	@Test
	public void shouldNotBeEqual() {
		DistanceMarginCoordinatesKey key1 = new DistanceMarginCoordinatesKey(new Coordinates(-1, 6, 10));
		DistanceMarginCoordinatesKey key2 = new DistanceMarginCoordinatesKey(new Coordinates(7, 11, 10));
		assertNotEquals(key1, key2);
	}

}
