package phyraxi.engine.generators;

import java.util.List;

import phyraxi.domain.Planet;
import phyraxi.domain.Star;

/**
 * 
 * @author Jani Kaarela (@gmail.com)
 */
public interface PlanetGenerator {
	
	/**
	 * Generates planets for given star.
	 * 
	 * @param hostStar	star that the planets orbit.
	 * 
	 * @return	list of planets.
	 */
	List<Planet> generatePlanets(Star hostStar);

}
