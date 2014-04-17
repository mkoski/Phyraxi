package phyraxi.engine.generators.planets;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import phyraxi.domain.Planet;
import phyraxi.domain.SpectralType;
import phyraxi.domain.Star;
import phyraxi.domain.StarPopulation;
import phyraxi.utils.PlanetPropertiesCalculator;

/**
 * 
 * 
 * @author Jani Kaarela (@gmail.com)
 */
public class PseudoRealitisticPlanetGenerator implements PlanetGenerator {
	
	// TODO: this is a real eye-sore, refactor heavily!!!
	
	private static final double TERRESTRIAL_PLANET_MIN_DENSITY = 0.5;
	private final Random random = new Random();
	private final PlanetPropertiesCalculator calculator = new PlanetPropertiesCalculator();
	
	public List<Planet> generatePlanets(Star star) {
		PlanetarySystemParameters modifiers = PlanetarySystemParameters.getModifiersFor(star);
		int planetCount = determinePlanetCount(modifiers.planetCount);
		
		int previousOrbit = 0;
		List<Planet> planets = new ArrayList<>();
		for (int i = 0; i < planetCount; i++) {
			Planet.Builder builder = new Planet.Builder();
			int orbitalDistance = determineOrbitDistance(star, previousOrbit);
			planets.add(generatePlanet(star, modifiers, orbitalDistance, builder));
		}
		return planets;
	}
	
	int determinePlanetCount(int countModifier) {
		int randomCount = random.nextInt(4) + Math.round((float) Math.pow(random.nextFloat(), 2) * countModifier);
		return Math.max(0, randomCount);
	}
	
	int determineOrbitDistance(Star star, int previousOrbit) {
		int distance;
		if (previousOrbit > 0) {
			distance = determineNextOrbit(previousOrbit);
		} else {
			distance = determineFirstOrbit(star);
		}
		return distance;
	}
	
	int determineFirstOrbit(Star star) {
		return Math.round((random.nextInt(51) + 10) * (float) star.getMass());
	}
	
	int determineNextOrbit(int previousOrbit) {
		return previousOrbit + Math.round(
				((random.nextInt(18) + 3) / 10f) *
				((float) Math.pow(random.nextFloat(), 2) + 0.4f) * previousOrbit);
	}
	
	Planet generatePlanet(Star star, PlanetarySystemParameters modifiers, int orbitalDistance, Planet.Builder builder) {
		int density = 0;
		int mass = 0;
		int radius = 0;
		builder.setDensity(density);
		builder.setDistance(orbitalDistance);
		builder.setMass(mass);
		int gravity = (int) Math.round(calculator.calculateGravity(mass, radius));
		builder.setGravity(gravity);
		builder.setName(star.getName() + " " + orbitalDistance); // TODO: roman numeral
		builder.setRadius(radius);
		// TODO implement planet generation, favor planetoids and asteroids for young stars and OBA stars
		return builder.build();
	}
	
	public static void main(String[] args) {
		PseudoRealitisticPlanetGenerator generator = new PseudoRealitisticPlanetGenerator();
		StarPopulation population = StarPopulation.DISC_POPULATION_I;
		SpectralType type = SpectralType.A;
		Star star = new Star("", population, type, 0, null, 1.0, 0, 0);
		PlanetarySystemParameters modifiers = PlanetarySystemParameters.getModifiersFor(star);
		int total = 0;
		for (int i = 0; i < 50; i++) {
			int count = generator.determinePlanetCount(modifiers.planetCount);
			total += count;
			System.out.println(count);
		}
		System.out.println("Average: " + total / 50);
		/*int planetCount = generator.determinePlanetCount(modifiers.planetCount);
		int previousOrbit = 0;
		for (int i = 0; i < planetCount; i++) {
			int orbit = generator.determineOrbitDistance(star, previousOrbit);
			System.out.println(orbit / 100f);
			previousOrbit = orbit;
		}*/
	}
	
	private static class PlanetarySystemParameters {
		
		private int innerZoneBoundary;
		private int planetCount;
		private int planetDensity;
		
		private PlanetarySystemParameters(int planetCount, int planetDensity) {
			this.planetCount = planetCount;
			this.planetDensity = planetDensity;
		}
		
		static PlanetarySystemParameters getModifiersFor(Star star) {
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
			return new PlanetarySystemParameters(count, density);
		}
		
		static int countInnerZoneBoundary(Star star) {
			return 0;
		}
	}

}
