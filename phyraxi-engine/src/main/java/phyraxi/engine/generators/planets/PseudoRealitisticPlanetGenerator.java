package phyraxi.engine.generators.planets;

import java.util.List;
import java.util.Random;

import phyraxi.domain.Planet;
import phyraxi.domain.PlanetType;
import phyraxi.domain.Satellite;
import phyraxi.domain.Star;
import phyraxi.utils.PlanetPropertiesCalculator;
import phyraxi.utils.StarPropertiesCalculator;

/**
 * 
 * 
 * @author Jani Kaarela (@gmail.com)
 */
public class PseudoRealitisticPlanetGenerator extends AbstractPlanetGenerator {
	
	private static final int PLANETOID_THRESHOLD = 0;
	private static final int PLANETARY_THRESHOLD = 10;
	private static final double TERRESTRIAL_PLANET_MIN_DENSITY = 0.5;
	
	private final Random random = new Random();
	private final PlanetPropertiesCalculator calculator = new PlanetPropertiesCalculator();
	private PlanetarySystemParameters parameters; 
	
	public List<Satellite> generateSatellites(Star star) {
		parameters = PlanetarySystemParameters.getParametersFor(star);
		return super.generateSatellites(star);
	}
	
	@Override
	protected SatelliteSlot[] determineSatelliteSlots(Star hostStar) {
		int planetCount = determinePlanetCount();
		SatelliteSlot[] slots = new SatelliteSlot[planetCount];
		StarPropertiesCalculator starCalculator = new StarPropertiesCalculator();
		for (int i = 1; i <= planetCount; i++) {
			int previousDistance = 0;
			if (i > 1) {
				previousDistance = slots[i - 1].distance;
			}
			int distance = determineOrbit(previousDistance, hostStar);
			int flux = starCalculator.calculateFluxDensity(hostStar, distance);
			Zone zone = determineOrbitZone(distance);
			slots[i] = new SatelliteSlot(i, distance, flux, zone);
		}
		return slots;
	}
	
	int determinePlanetCount() {
		int countModifier = parameters.planetCountModifier;
		int randomCount = random.nextInt(4) + Math.round((float) Math.pow(random.nextFloat(), 2) * countModifier);
		return Math.max(0, randomCount);
	}
	
	int determineOrbit(int previousOrbit, Star star) {
		if (previousOrbit == 0) {
			return Math.round((random.nextInt(51) + 10) * (float) star.getMass());
		} else {
			return previousOrbit + Math.round(previousOrbit * ((float) Math.pow(random.nextFloat(), 2) + 0.4f));
		}
	}
	
	Zone determineOrbitZone(int distance) {
		Zone zone = null;
		if (distance < parameters.innerZoneBoundary) {
			zone = Zone.INNER;
		} else if (distance > parameters.outerZoneBoundary) {
			zone = Zone.OUTER;
		} else {
			zone = Zone.LIQUID_WATER;
		}
		return zone;
	}
	
	@Override
	protected boolean slotHasPlanetaryBody(Star hostStar, SatelliteSlot slot) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected int determinePlanetDensity(Star hostStar, SatelliteSlot slot) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected PlanetType determinePlanetType(Star hostStar, SatelliteSlot slot, int planetDensity) {
		PlanetType planetType = null;
		int seed = random.nextInt(20) + parameters.planetCountModifier;
		if (seed < PLANETOID_THRESHOLD) {
			// null means non-planetary (asteroids?)
		} else if (seed < PLANETARY_THRESHOLD) {
			planetType = determinePlanetoidType();
		} else {
			if (isTerrestrial(slot)) {
				planetType = determineTerrestrialType(slot);
			} else {
				planetType = determineJovianType(slot);
			}
		}
		return planetType;
	}
	
	PlanetType determinePlanetoidType() {
		return PlanetType.CARBON_PLANETOID; // TODO: determine different planetoid types
	}
	
	boolean isTerrestrial(SatelliteSlot slot) {
		return true; // TODO: determine terrestrial vs jovian
	}
	
	PlanetType determineTerrestrialType(SatelliteSlot slot) {
		return null;
	}
	
	PlanetType determineJovianType(SatelliteSlot slot) {
		return null;
	}

	@Override
	protected Satellite generateNonPlanetarySatellite(Star hostStar, SatelliteSlot slot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Planet generatePlanet(Star hostStar, SatelliteSlot slot, PlanetType type, int density) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private static class PlanetarySystemParameters {
		
		private final int planetCountModifier;
		private final int planetDensityModifier;
		private final int innerZoneBoundary;
		private final int outerZoneBoundary;
		
		private PlanetarySystemParameters(int planetCountModifier, int planetDensityModifier, int innerZoneBoundary,
				int outerZoneBoundary) {
			this.planetCountModifier = planetCountModifier;
			this.planetDensityModifier = planetDensityModifier;
			this.innerZoneBoundary = innerZoneBoundary;
			this.outerZoneBoundary = outerZoneBoundary;
		}
		
		static PlanetarySystemParameters getParametersFor(Star star) {
			int count = 0;
			int density = 0;
			switch (star.getPopulation()) {
			case CORE_POPULATION_I:
				count = -10; density = 10; break;
			case INTERMEDIATE_POPULATION_I:
				count = -5; density = 5; break;
			case DISC_POPULATION_I:
				count = +4; density = 3; break;
			case DISC_POPULATION_II:
				count = +3; density = -2; break;
			case HALO_POPULATION_II:
				count = -4; density = -5; break;
			}
			switch (star.getSpectralType()) {
			case O:
				count += -5; density += 3; break;
			case B:
				count += -3; density += 2; break;
			case A:
				count += -1; density += 1; break;
			case F:
				count += 2; density += 0; break;
			case G:
				count += 5; density += 0; break;
			case K:
				count += 6; density += -1; break;
			case M:
				count += 3; density += -3; break;
			}
			StarPropertiesCalculator starCalculator = new StarPropertiesCalculator();
			int innerZoneBoundary = starCalculator.calculateInnerZoneBoundary(star);
			int outerZoneBoundary = starCalculator.calculateHabitableZoneBoundary(star);
			return new PlanetarySystemParameters(count, density, innerZoneBoundary, outerZoneBoundary);
		}
	}
}
