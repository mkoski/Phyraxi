package phyraxi.engine.generators;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import phyraxi.domain.SpectralType;
import phyraxi.domain.Star;
import phyraxi.domain.StarPopulation;
import phyraxi.engine.generators.StatisticalStarGenerator;


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
	
	@Ignore("This merely demonstrates the results, doesn't test anything.")
	@Test
	public void testGenerator() {
		for (int i = 0; i < 20; i++) {
			Star star = generator.generateStar(StarPopulation.DISC_POPULATION_I);
			System.out.println(String.valueOf(star.getSpectralType()) + star.getSpectralNumber() +
					star.getLuminosityClass() +	" " + star.getMass() + "M(Sol); " + star.getBrightness() + "L(Sol); " +
					star.getEffectiveTemperature() + "K");
		}
	}

}
