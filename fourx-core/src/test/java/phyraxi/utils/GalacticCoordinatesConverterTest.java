package phyraxi.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import phyraxi.domain.Coordinates;
import phyraxi.utils.GalacticCoordinatesConverter;
import phyraxi.utils.GalacticCoordinatesConverter.GalacticCoordinates;


/**
 * Tests for {@link GalacticCoordinatesConverter}.
 * 
 * @author Jani Kaarela
 */
public class GalacticCoordinatesConverterTest {
	
	private GalacticCoordinatesConverter converter = new GalacticCoordinatesConverter();
	
	@Test(expected = IllegalArgumentException.class)
	public void outOfRangeDistanceShouldThrowIllegalArgumentException() {
		Coordinates coordinates = new Coordinates(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
		converter.convertToGalactic(coordinates);
	}
	
	@Test
	public void distanceShouldEqualXCoordinate() {
		float longitude = 90.0f;
		float latitude = 0.0f;
		int distance = 100;
		Coordinates coordinates = converter.convertToCartesian(longitude, latitude, distance);
		assertEquals(distance, coordinates.x);
	}
	
	@Test
	public void distanceShouldEqualNegatedXCoordinate() {
		float longitude = 270.0f;
		float latitude = 0.0f;
		int distance = 100;
		Coordinates coordinates = converter.convertToCartesian(longitude, latitude, distance);
		assertEquals(distance, - coordinates.x);
	}
	
	@Test
	public void distanceShouldEqualYCoordinate() {
		float longitude = 0.0f;
		float latitude = 0.0f;
		int distance = 100;
		Coordinates coordinates = converter.convertToCartesian(longitude, latitude, distance);
		assertEquals(distance, coordinates.y);
	}
	
	@Test
	public void distanceShouldEqualNegatedYCoordinate() {
		float longitude = 180.0f;
		float latitude = 0.0f;
		int distance = 100;
		Coordinates coordinates = converter.convertToCartesian(longitude, latitude, distance);
		assertEquals(distance, - coordinates.y);
	}
	
	@Test
	public void distanceShouldEqualZCoordinate() {
		float longitude = 0.0f;
		float latitude = 90.0f;
		int distance = 100;
		Coordinates coordinates = converter.convertToCartesian(longitude, latitude, distance);
		assertEquals(distance, coordinates.z);
	}
	
	@Test
	public void distanceShouldEqualNegatedZCoordinate() {
		float longitude = 0.0f;
		float latitude = -90.0f;
		int distance = 100;
		Coordinates coordinates = converter.convertToCartesian(longitude, latitude, distance);
		assertEquals(distance, - coordinates.z);
	}
	
	@Test
	public void testConversionRoundTrip() {
		float longitude = 45.0f;
		float latitude = 25.0f;
		int distance = 100;
		Coordinates cartesian = converter.convertToCartesian(longitude, latitude, distance);
		GalacticCoordinates galactic = converter.convertToGalactic(cartesian);
		assertEquals(distance, galactic.distance);
		// allow a large delta, rounding and using floats decreases accuracy
		assertEquals(longitude, galactic.longitude, 0.2f);
		assertEquals(latitude, galactic.latitude, 0.2f);
	}

}
