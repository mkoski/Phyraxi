package phyraxi.utils;

import phyraxi.domain.Star;

/**
 * Calculater for planet properties etc.
 * 
 * @author Jani Kaarela (@gmail.com)
 */
public class PlanetPropertiesCalculator {
	
	static final double SUN_LUMINOSITY_IN_WATTS = 3.846e26;
	private static final double STEFAN_BOLTZMANN_CONSTANT = 5.670373e-8;
	private static final double ASTRONOMICAL_UNIT_IN_METERS = 1.496e11;
	private static final double APPROXIMATE_PLANET_RIGIDITY = 2e10;
	private static final double EART_RADIUS_IN_METERS = 6371000;
	private static final double EARTH_MASS_IN_KILOGRAMS = 5.97219e24;
	private static final double SUN_MASS_IN_KILOGRAMS = 1.9891e30;
	private static final double CELESTIAL_TIME_SCALE = 0.2e10; // 2 billion years
	
	/**
	 * Calculate planetary equilibrium temperate.
	 * 
	 * @param star		host star.
	 * @param distance	distance from star, in 1/100 astronomical units.
	 * @param albedo	planet albedo.
	 * 
	 * @return	equilibrium temperature.
	 */
	public double calculateEquilibriumTemperature(Star star, int distance, double albedo) {
		return Math.pow(
				(calculateAbsorbedFlux(calculateFluxDensity(star, distance), albedo) / STEFAN_BOLTZMANN_CONSTANT),
				0.25);
	}
	
	double calculateAbsorbedFlux(double flux, double albedo) {
		return (flux / 4) * (1 - albedo);
	}
	
	double calculateFluxDensity(Star star, int distance) {
		return (
				convertSolarLuminositiesToWatts(star.getBrightness()) // radiation
				/ (4 * Math.PI * Math.pow(convertAstronomicalUnitsToMeters(distance), 2))); // spherical area at distance
	}
	
	double convertSolarLuminositiesToWatts(double luminosity) {
		return luminosity * SUN_LUMINOSITY_IN_WATTS;
	}
	
	double convertAstronomicalUnitsToMeters(int distance) {
		return ASTRONOMICAL_UNIT_IN_METERS * (distance / 100d);
	}
	
	/**
	 * Calculate Hill Sphere radius for planet. Stable satellites can only orbit the planet well within
	 * the Hill Sphere. Notice, that mass unit does not matter as long as the same unit is used for
	 * both the star and the planet.
	 * 
	 * @param distance		distance from the star, in 1/100 AUs.
	 * @param starMass		star mass.
	 * @param planetMass	planet mass.
	 * 
	 * @return
	 */
	public double calculateHillSphereRadius(int distance, double starMass, double planetMass) {
		return convertAstronomicalUnitsToMeters(distance) * (Math.pow((planetMass / starMass), (1/3)));
	}
	
	/**
	 * Calculates Roche Limit for a planet and a (rigid) satellite. A satellite orbiting at or under the limit
	 * will be destroyed by tidal forces and turn into a ring of debris. 
	 * 
	 * @param planetDensity		planet density.
	 * @param satelliteDensity	satellite density.
	 * 
	 * @return	Roche Limit, as multiples of planet radius.
	 */
	public double calculateRocheLimit(int planetDensity, int satelliteDensity) {
		return 1.44 * (Math.pow((planetDensity / satelliteDensity), (1/3)));
	}
	
	// TODO: method for determining tidal locking, see http://en.wikipedia.org/wiki/Tidal_locking
	public boolean isPlanetTidallyLocked(int planetDistance, int planetRadius, int planetMass, double starMass) {
		return (
				6 * (Math.pow(convertAstronomicalUnitsToMeters(planetDistance), 6) * convertEarthRadiusesToMeters(planetRadius) *
				APPROXIMATE_PLANET_RIGIDITY) /
				convertEarthMassesToKilograms(planetMass) * Math.pow(convertSunMassesToKilograms(starMass), 2)
				) > CELESTIAL_TIME_SCALE;
	}
	
	private double convertEarthRadiusesToMeters(int earthRadiuses) {
		return (earthRadiuses * 100) * EART_RADIUS_IN_METERS;
	}
	
	private double convertEarthMassesToKilograms(int earthMasses) {
		return (earthMasses * 100) * EARTH_MASS_IN_KILOGRAMS;
	}
	
	private double convertSunMassesToKilograms(double sunMasses) {
		return sunMasses * SUN_MASS_IN_KILOGRAMS;
	}
	
	/*
	 * Resources:
	 * - http://en.wikipedia.org/wiki/Planetary_equilibrium_temperature
	 * - http://www.dangermouse.net/gurps/science/temps.html
	 * - http://bartonpaullevenson.com/NewPlanetTemps.html
	 * - http://en.wikipedia.org/wiki/Greenhouse_effect
	 */

}
