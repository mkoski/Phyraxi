package phyraxi.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import phyraxi.domain.LuminosityClass;
import phyraxi.domain.SpectralType;
import phyraxi.domain.Star;
import phyraxi.domain.StarPopulation;

/**
 * Tests for {@link StarPropertiesCalculator}.
 * 
 * @author Jani Kaarela (@gmail.com)
 */
public class StarPropertiesCalculatorTest {

	private static final Star SUN = new Star("Sol", StarPopulation.DISC_POPULATION_I, SpectralType.G, 2,
			LuminosityClass.MAIN_SEQUENCE, 1.0, 1.0, 5778);
	private static final int EARTH_DISTANCE_IN_CENTI_AUS = 100;
	private static final int OBSERVED_EARTH_SOLAR_FLUX = 1366;
	
	private final StarPropertiesCalculator calculator = new StarPropertiesCalculator();
	
	@Test
	public void convertedSolarLuminositiesShouldMatch() {
		double watts = calculator.convertSolarLuminositiesToWatts(1.0);
		assertEquals(SunConstants.SUN_LUMINOSITY_IN_WATTS, watts, 0.1d);
	}
	
	@Test
	public void calculateFluxDensityShouldMatchObserved() {
		double fluxDensity = calculator.calculateFluxDensity(SUN, EARTH_DISTANCE_IN_CENTI_AUS);
		assertEquals(OBSERVED_EARTH_SOLAR_FLUX, fluxDensity, 2.0d);
	}
}
