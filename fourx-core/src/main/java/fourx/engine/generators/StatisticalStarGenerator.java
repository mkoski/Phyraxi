package fourx.engine.generators;

import fourx.domain.Star;
import fourx.domain.Star.Generation;

/**
 * A star generator which aims to create &quot;statistically sound&quot; star populations, with a
 * slight nod towards diversity at the expense of realism.
 * 
 * @author Jani Kaarela
 */
public class StatisticalStarGenerator implements StarGenerator {

	public Star generateStar(Generation generation) {
		/*
		 * TODO statistically semi-realistic generation
		 * - determine spectral type
		 * - determine mass
		 * - determine luminosity class
		 * - calculate brightness, radius, effective temperature
		 */
		return null;
	}
	
	/*
	 * type distribution could be for example:
	 * M 40%, K 20%, G 15%, F 10%, A 5%, B 5%, O 5%
	 */

}
