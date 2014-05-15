package phyraxi.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests for {@link StarPropertiesCalculator}.
 * 
 * @author Jani Kaarela (@gmail.com)
 */
public class StarPropertiesCalculatorTest {

	private static final int EARTH_DISTANCE_IN_CENTI_AUS = 100;
	
	private final StarPropertiesCalculator calculator = new StarPropertiesCalculator();
	
	@Test
	public void brightnessOfTheSunShouldBeOne() {
		assertEquals(1.0, calculator.calculateBrightness(1.0), 0.01);
	}
	
	@Test
	public void brightnessOfAlphaCentauriShouldMatchObserved() {
		assertEquals(1.519, calculator.calculateBrightness(1.1), 0.1);
	}
	
	@Test
	public void brightnessOfAEAurigaeShouldMatchObserved() {
		assertEquals(33000, calculator.calculateBrightness(17), 3300);
	}
	
	@Test
	public void brightnessOfGliese687ShouldMatchObserved() {
		assertEquals(0.00389, calculator.calculateBrightness(0.401), 0.1);
	}
	
	@Test
	public void convertedSolarLuminositiesShouldMatch() {
		double watts = calculator.convertSolarLuminositiesToWatts(1.0);
		assertEquals(SunConstants.SUN_LUMINOSITY_IN_WATTS, watts, 0.1d);
	}
	
	@Test
	public void calculateFluxDensityShouldMatchObserved() {
		int fluxDensity = calculator.calculateFluxDensity(SunConstants.SUN, EARTH_DISTANCE_IN_CENTI_AUS);
		assertTrue(Math.abs(EarthConstants.EARTH_SOLAR_FLUX - fluxDensity) < 3);
	}
}
