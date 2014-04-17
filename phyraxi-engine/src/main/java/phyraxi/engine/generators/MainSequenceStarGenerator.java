package phyraxi.engine.generators;

import phyraxi.domain.Star;
import phyraxi.domain.StarPopulation;
import phyraxi.engine.generators.names.StarNameGenerator;

/**
 * A generator for generating a main-sequence star population.
 * 
 * @author Jani Kaarela
 */
public interface MainSequenceStarGenerator {
	
	/**
	 * Generate a star of the given generation.
	 * 
	 * @param generation	star generation.
	 * 
	 * @return	a generated star. 
	 */
	Star generateStar(StarPopulation generation);
	
	/**
	 * Sets the generator for star names.
	 * 
	 * @param starNameGenerator	star name generator.
	 */
	void setNameGenerator(StarNameGenerator starNameGenerator);

}
