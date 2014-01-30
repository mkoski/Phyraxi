package fourx.engine.generators;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fourx.domain.SpectralType;

/**
 * @author Jani Kaarela (@gmail.com)
 *
 */
public class StatisticalStarGeneratorTest {
	
	private StatisticalStarGenerator generator = new StatisticalStarGenerator();
	
	@Test
	public void maxTemperatureShouldHaveSpectralNumberZero() {
		int spectralNumber = generator.determineSpectralNumber(
				SpectralType.A, SpectralType.A.getMaxEffectiveTemperature());
		assertEquals(0, spectralNumber);
	}
	
	@Test
	public void minTemperatureShouldHaveSpectralNumberNine() {
		int spectralNumber = generator.determineSpectralNumber(
				SpectralType.A, SpectralType.A.getMinEffectiveTemperature());
		assertEquals(9, spectralNumber);
	}

}
