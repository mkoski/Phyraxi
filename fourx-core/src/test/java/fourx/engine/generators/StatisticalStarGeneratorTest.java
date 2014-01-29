package fourx.engine.generators;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

/**
 * @author Jani Kaarela (@gmail.com)
 *
 */
public class StatisticalStarGeneratorTest {
	
	@Test
	public void testDetermineMass() {
		final int testRunSize = 50;
		final boolean printDebugInfo = true;
		StatisticalStarGenerator generator = new StatisticalStarGenerator();
		List<Double> masses = new ArrayList<>(testRunSize);
		double totalMasses = 0;
		for (int i = 0; i < testRunSize; i++) {
			double mass = generator.determineMass(null, null, null);
			totalMasses += mass;
			masses.add(new Double(mass));
		}
		final double minMass = Collections.min(masses);
		final double maxMass = Collections.max(masses);
		if (printDebugInfo) {
			System.out.println("Test run size: " + testRunSize);
			System.out.println("Minimum mass: " + minMass);
			System.out.println("Maximum mass: " + maxMass);
			System.out.println("Average mass: " + (totalMasses / testRunSize));
		}
		assertTrue(minMass >= StatisticalStarGenerator.MINIMUM_SOLAR_MASSES);
		assertTrue(maxMass <= StatisticalStarGenerator.MAXIMUM_SOLAR_MASSES);
	}

}
