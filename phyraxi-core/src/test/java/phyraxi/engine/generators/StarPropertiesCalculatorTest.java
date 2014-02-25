package phyraxi.engine.generators;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import phyraxi.engine.generators.StarPropertiesCalculator;

/**
 * @author Jani Kaarela (@gmail.com)
 *
 */
public class StarPropertiesCalculatorTest {
	
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

}
