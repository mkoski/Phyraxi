package phyraxi.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import phyraxi.domain.LuminosityClass;
import phyraxi.domain.SpectralType;
import phyraxi.domain.Star;
import phyraxi.domain.StarPopulation;

public class PlanetPropertiesCalculatorTest {
	
	private static final Star SUN = new Star("Sol", StarPopulation.DISC_POPULATION_I, SpectralType.G, 2,
			LuminosityClass.MAIN_SEQUENCE, 1.0, 1.0, 5778);
	private static final int EARTH_DISTANCE = 100;
	private static final double EARTH_ALBEDO = 0.306d;
	private static final int EARTH_EQUILIBRIUM_TEMPERATURE = 254;
	private static final int OBSERVED_EARTH_SOLAR_FLUX = 1366;
	private static final double SUN_MASS_IN_KILOGRAMS = 1.99e30;
	private static final double EARTH_MASS_IN_KILOGRAMS = 5.97e24;
	private static final int EARTH_MEAN_RADIUS_IN_METERS = 6_371_000;
	private static final double EARTH_GRAVITY = 9.80665;
	private static final double EARTH_HILL_SPHERE_RADIUS = 1.496e11;
	private static final int EARTH_DENSITY = 5513;
	private static final int MOON_DENSITY = 3346;
	private static final double EARTH_MOON_ROCHE_LIMIT = 1.44d;
	private static final int MOON_MEAN_ORBITAL_DISTANCE_IN_METERS = 384_399_000;
	private static final int MOON_MEAN_RADIUS_IN_METERS = 1_737_100;
	private static final double MOON_MASS_IN_KILOGRAMS = 7.3477e22;
	private static final double MOON_GRAVITY = 1.6249;
	
	private PlanetPropertiesCalculator planetPropertiesCalculator = new PlanetPropertiesCalculator();
	
	@Test
	public void convertedSolarLuminositiesShouldMatch() {
		double watts = planetPropertiesCalculator.convertSolarLuminositiesToWatts(1.0);
		assertEquals(PlanetPropertiesCalculator.SUN_LUMINOSITY_IN_WATTS, watts, 0.1d);
	}
	
	@Test
	public void calculatedEquilibriumTemperatureShouldMatchTheoretical() {
		double temperature = planetPropertiesCalculator.calculateEquilibriumTemperature(
				SUN, EARTH_DISTANCE, EARTH_ALBEDO);
		assertEquals(EARTH_EQUILIBRIUM_TEMPERATURE, temperature, 1.0d);
	}
	
	@Test
	public void calculateFluxDensityShouldMatchObserved() {
		double fluxDensity = planetPropertiesCalculator.calculateFluxDensity(SUN, EARTH_DISTANCE);
		assertEquals(OBSERVED_EARTH_SOLAR_FLUX, fluxDensity, 2.0d);
	}
	
	@Test
	public void calculateHillSphereRadiusShouldMatchExpected() {
		double radius = planetPropertiesCalculator.calculateHillSphereRadius(EARTH_DISTANCE, SUN_MASS_IN_KILOGRAMS, EARTH_MASS_IN_KILOGRAMS);
		assertEquals(EARTH_HILL_SPHERE_RADIUS, radius, 0.1d);
	}
	
	@Test
	public void calculateRocheLimitShouldMatchExpected() {
		double limit = planetPropertiesCalculator.calculateRocheLimit(EARTH_DENSITY, MOON_DENSITY);
		assertEquals(EARTH_MOON_ROCHE_LIMIT, limit, 0.1d);
	}
	
	@Test
	public void calculateGravityShouldMatchExpectedForEarth() {
		double gravity = planetPropertiesCalculator.calculateGravity(
				EARTH_MASS_IN_KILOGRAMS, EARTH_MEAN_RADIUS_IN_METERS);
		assertEquals(EARTH_GRAVITY, gravity, 0.01d);
	}
	
	@Test
	public void calculateGravityShouldMatchExpectedForMoon() {
		double gravity = planetPropertiesCalculator.calculateGravity(
				MOON_MASS_IN_KILOGRAMS, MOON_MEAN_RADIUS_IN_METERS);
		assertEquals(MOON_GRAVITY, gravity, 0.01d);
	}
	
	@Test
	public void moonShouldBeConsideredTidallyLockedToEarth() {
		assertTrue(planetPropertiesCalculator.isSatelliteTidallyLocked(
				MOON_MEAN_ORBITAL_DISTANCE_IN_METERS, MOON_MEAN_RADIUS_IN_METERS,
				MOON_MASS_IN_KILOGRAMS, EARTH_MASS_IN_KILOGRAMS));
	}
	
	@Test
	@Ignore("Something fishy here - has to be fixed!")
	public void earthShouldNotBeConsideredTidallyLockedToSun() {
		assertFalse(planetPropertiesCalculator.isSatelliteTidallyLocked(
				planetPropertiesCalculator.convertCentiAstronomicalUnitsToMeters(100),
				planetPropertiesCalculator.convertCentiEarthRadiusesToMeters(100),
				EARTH_MASS_IN_KILOGRAMS, SUN_MASS_IN_KILOGRAMS));
	}

}
