package phyraxi.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import phyraxi.domain.LuminosityClass;
import phyraxi.domain.SpectralType;
import phyraxi.domain.Star;

public class PlanetPropertiesCalculatorTest {
	
	private static final Star SUN = new Star("Sol", SpectralType.G, 2, LuminosityClass.MAIN_SEQUENCE, 1.0, 1.0, 5778);
	private static final int EARTH_DISTANCE = 100;
	private static final double EARTH_ALBEDO = 0.306d;
	private static final double EARTH_EQUILIBRIUM_TEMPERATURE = 254d;
	private static final double OBSERVED_EARTH_SOLAR_FLUX = 1366d;
	
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

}
