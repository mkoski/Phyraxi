package phyraxi.utils;

import static org.junit.Assert.assertEquals;

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
	private static final double EARTH_EQUILIBRIUM_TEMPERATURE = 254d;
	private static final double OBSERVED_EARTH_SOLAR_FLUX = 1366d;
	private static final double SUN_MASS = 1.99e30;
	private static final double EARTH_MASS = 5.97e24;
	private static final double EARTH_HILL_SPHERE_RADIUS = 1.496e11;
	private static final int EARTH_DENSITY = 5513;
	private static final int MOON_DENSITY = 3346;
	private static final double EARTH_MOON_ROCHE_LIMIT = 1.44d;
	
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
		double radius = planetPropertiesCalculator.calculateHillSphereRadius(EARTH_DISTANCE, SUN_MASS, EARTH_MASS);
		assertEquals(EARTH_HILL_SPHERE_RADIUS, radius, 0.1d);
	}
	
	@Test
	public void calculateRocheLimitShouldMatchExpected() {
		double limit = planetPropertiesCalculator.calculateRocheLimit(EARTH_DENSITY, MOON_DENSITY);
		assertEquals(EARTH_MOON_ROCHE_LIMIT, limit, 0.1d);
	}

}
