package phyraxi.engine.generators.planets;

import java.util.ArrayList;
import java.util.List;

import phyraxi.domain.Planet;
import phyraxi.domain.PlanetType;
import phyraxi.domain.Satellite;
import phyraxi.domain.Star;

/**
 * A base class for planet generators.
 * 
 * @author Jani Kaarela (@gmail.com)
 */
public abstract class AbstractPlanetGenerator implements SatelliteGenerator {
	
	static final int LIQUID_WATER_ZONE_ENERGY_FLUX_LOWER_LIMIT = 1;
	static final int LIQUID_WATER_ZONE_ENERGY_FLUX_UPPER_LIMIT = 2;
	
	public List<Satellite> generateSatellites(Star hostStar) {
		SatelliteSlot[] slots = determineSatelliteSlots(hostStar);
		List<Satellite> satellites = new ArrayList<>(slots.length);
		for (SatelliteSlot slot : slots) {
			if (slotHasPlanetaryBody(hostStar, slot)) {
				int density = determinePlanetDensity(hostStar, slot);
				PlanetType type = determinePlanetType(hostStar, slot, density);
				satellites.add(generatePlanet(hostStar, slot, type, density));
			} else {
				satellites.add(generateNonPlanetarySatellite(hostStar, slot));
			}
		}
		return satellites;
	}
	
	/**
	 * Determines orbital &quot;slots&quot; for satellites.
	 * 
	 * @param hostStar	host star.
	 * 
	 * @return an array of satellite slots.
	 */
	protected abstract SatelliteSlot[] determineSatelliteSlots(Star hostStar);
	
	/**
	 * Determines if orbit has an actual planetary body.
	 * 
	 * @param hostStar	host star.
	 * @param slot		satellite slot.
	 * 
	 * @return	<code>true</code> if a planet, <code>false</code> a non-planetary object.
	 */
	protected abstract boolean slotHasPlanetaryBody(Star hostStar, SatelliteSlot slot);
	
	/**
	 * Determines density (in 1/100 Earth densities) of a planet.
	 * 
	 * @param hostStar	host star.
	 * @param slot		satellite slot.
	 * 
	 * @return	density (in 1/100 Earth densities) of planet.
	 */
	protected abstract int determinePlanetDensity(Star hostStar, SatelliteSlot slot);
	
	/**
	 * Determines planet type for given satellite slot.
	 * 
	 * @param hostStar		host star.
	 * @param slot			satellite slot.
	 * @param planetDensity	planet density.
	 * 
	 * @return	a planet type.
	 */
	protected abstract PlanetType determinePlanetType(Star hostStar, SatelliteSlot slot, int planetDensity);
	
	/**
	 * Generates a non-planetary satellite.
	 * 
	 * @param hostStar	host star.
	 * @param slot		satellite slot.
	 * 
	 * @return	generated satellite.
	 */
	protected abstract Satellite generateNonPlanetarySatellite(Star hostStar, SatelliteSlot slot);
	
	/**
	 * Generates a planet.
	 * 
	 * @param hostStar	host star.
	 * @param slot		satellite slot.
	 * @param type		planet type.
	 * @param density	planet density (1/100 Earths).
	 * 
	 * @return	generated planet.
	 */
	protected abstract Planet generatePlanet(Star hostStar, SatelliteSlot slot, PlanetType type, int density);
	
	/**
	 * A &quot;slot&quot; for a {@link Satellite}.
	 */
	static class SatelliteSlot {
		/** Slot number, starts from 1 indicating the inner-most slot. */
		final int number;
		/** Mean distance, in 1/100 astronomical units. */
		final int distance;
		/** Energy flux received at the orbit. */
		final int energyFlux;
		/** Zone of the orbit. */
		final Zone zone;
		SatelliteSlot(int number, int distance, int energyFlux, Zone zone) {
			this.number = number;
			this.distance = distance;
			this.energyFlux = energyFlux;
			this.zone = zone;
		}
	}
	
	enum Zone {
		/** Hot zone, where liquid water exists only under very specific conditions. */
		INNER,
		/** Temperate zone, where liquid water exists readily under typical planetary conditions. */
		LIQUID_WATER,
		/** Cold zone, where water is frozen except under typical planetary conditions. */
		OUTER;
	}

}
