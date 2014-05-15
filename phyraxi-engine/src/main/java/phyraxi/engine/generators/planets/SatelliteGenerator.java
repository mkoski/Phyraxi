package phyraxi.engine.generators.planets;

import java.util.List;

import phyraxi.domain.Satellite;
import phyraxi.domain.Star;

/**
 * An interface defining a satellite generator. Implementations can safely assume instances are
 * not shared between threads.
 * 
 * @author Jani Kaarela (@gmail.com)
 */
public interface SatelliteGenerator {
	
	/**
	 * Generates planets and other satellites for given star.
	 * 
	 * @param hostStar	star that the satellites orbit.
	 * 
	 * @return	list of satellites.
	 */
	List<Satellite> generateSatellites(Star hostStar);

}
