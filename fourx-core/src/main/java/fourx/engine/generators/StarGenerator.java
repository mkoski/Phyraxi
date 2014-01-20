package fourx.engine.generators;

import fourx.domain.Star;

/**
 * 
 * @author Jani Kaarela
 */
public interface StarGenerator {
	
	/**
	 * Generate a star of the given generation.
	 * 
	 * @param generation	star generation.
	 * 
	 * @return	a generated star. 
	 */
	Star generateStar(Star.Generation generation);

}
