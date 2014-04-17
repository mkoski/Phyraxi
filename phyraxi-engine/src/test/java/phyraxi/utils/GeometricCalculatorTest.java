package phyraxi.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for {@link GeometricCalculator}.
 * 
 * @author Jani Kaarela (@gmail.com)
 */
public class GeometricCalculatorTest {
	
	private final GeometricCalculator calculator = new GeometricCalculator();
	
	@Test
	public void radiusToVolumeMatchesExpected() {
		double radius = EarthConstants.EARTH_RADIUS_IN_METERS;
		double volume = calculator.calculateSphereVolume(radius);
		assertEquals(EarthConstants.EARTH_VOLUME_IN_CUBIC_METERS, volume, 0.00001e21);
	}
	
	@Test
	public void volumeToRadiusMatchesExpected() {
		double volume = EarthConstants.EARTH_VOLUME_IN_CUBIC_METERS;
		double radius = calculator.calculateSphereRadius(volume);
		assertEquals(EarthConstants.EARTH_RADIUS_IN_METERS, radius, 10.0d);
	}
	
	@Test
	public void volumeToRadiusAndBackMatchesOriginal() {
		double originalVolume = EarthConstants.EARTH_VOLUME_IN_CUBIC_METERS;
		double radius = calculator.calculateSphereRadius(originalVolume);
		double roundTrippedVolume = calculator.calculateSphereVolume(radius);
		assertEquals(originalVolume, roundTrippedVolume, 0.00001e21);
	}

}
