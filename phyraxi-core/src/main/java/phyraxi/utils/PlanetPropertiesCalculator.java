package phyraxi.utils;

import phyraxi.domain.Star;

/**
 * Calculator for planet properties etc.
 * 
 * @author Jani Kaarela (@gmail.com)
 */
public class PlanetPropertiesCalculator {
	
	static final double SUN_LUMINOSITY_IN_WATTS = 3.846e26;
	private static final double STEFAN_BOLTZMANN_CONSTANT = 5.670373e-8;
	private static final double ASTRONOMICAL_UNIT_IN_METERS = 1.496e11;
	private static final int EARTH_RADIUS_IN_METERS = 6_371_000;
	private static final double EARTH_MASS_IN_KILOGRAMS = 5.97219e24;
	private static final double SUN_MASS_IN_KILOGRAMS = 1.9891e30;
	private static final double GRAVITATIONAL_CONSTANT = 6.67384e-11;
	private static final double PRIMARY_TO_SATELLITE_GRAVITY_RATIO_THRESHOLD = 0.00125d; // arbitrary choice!
	
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
				/ (4 * Math.PI * Math.pow(convertCentiAstronomicalUnitsToMeters(distance), 2))); // spherical area at distance
	}
	
	double convertSolarLuminositiesToWatts(double luminosity) {
		return luminosity * SUN_LUMINOSITY_IN_WATTS;
	}
	
	// notice distance should be in 1/100 AUs
	double convertCentiAstronomicalUnitsToMeters(int distance) {
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
		return convertCentiAstronomicalUnitsToMeters(distance) * (Math.pow((planetMass / starMass), (1/3)));
	}
	
	/**
	 * Calculates Roche Limit for a planet and a (rigid) satellite. A satellite orbiting at or under the limit
	 * will be destroyed by tidal forces and turn into a ring of debris. Density unit does not matter as long
	 * as it is the same for both the planet and the satellite. 
	 * 
	 * @param planetDensity		planet density.
	 * @param satelliteDensity	satellite density.
	 * 
	 * @return	Roche Limit, as multiples of planet radius.
	 */
	public double calculateRocheLimit(double planetDensity, double satelliteDensity) {
		return 1.44 * (Math.pow((planetDensity / satelliteDensity), (1/3)));
	}
	
	/**
	 * Calculates gravity at a distance from an object. Given the mass of a planet and it's
	 * radius, gives surface gravity. The correct units are kilograms and meters, but can
	 * also be used with relative units - for example, giving mass and radius relative to
	 * Earth, the result is gravity as multiples of Earth's gravity.
	 * 
	 * @param mass		object mass (kilograms or relative).
	 * @param distance	distance from the center of the mass (meters or relative).
	 * 
	 * @return	gravity, either as m/s^2 or relative.
	 */
	public double calculateGravity(double mass, double distance) {
		// g = GM / r^2
		return (GRAVITATIONAL_CONSTANT * mass) / Math.pow(distance, 2);
	}
	/**
	 * Checks if a satellite is (likely to be) tidally locked to it host star/planet (&quot;primary&quot;).
	 * Notice, that for highly eccentric orbits, locking leads to a spin-orbit resonance other than 1:1;
	 * this method doesn't take eccentricity into effect and considers such cases tidally locked. Also,
	 * the implementation is <b>very</b> simplified and unscientific!
	 * 
	 * @param satelliteDistanceInMeters	mean orbital distance from the host. 
	 * @param satelliteRadiusInMeters	mean radius of the satellite.
	 * @param satelliteMassInKilograms	mass of the satellite.
	 * @param primaryMassInKilograms	mass of the host star/planet.
	 * @return
	 */
	public boolean isSatelliteTidallyLocked(double satelliteDistanceInMeters, double satelliteRadiusInMeters,
			double satelliteMassInKilograms, double primaryMassInKilograms) {
		double satelliteSurfaceGravity = calculateGravity(satelliteMassInKilograms, satelliteRadiusInMeters);
		double primaryGravityOnSatelliteSurface = calculateGravity(
				primaryMassInKilograms, satelliteDistanceInMeters - satelliteRadiusInMeters);
		double primaryToSatelliteGravityRatio = primaryGravityOnSatelliteSurface / satelliteSurfaceGravity;
		return (primaryToSatelliteGravityRatio > PRIMARY_TO_SATELLITE_GRAVITY_RATIO_THRESHOLD);
	}
	
	public double convertCentiEarthRadiusesToMeters(int earthRadiuses) {
		return (earthRadiuses / 100) * EARTH_RADIUS_IN_METERS;
	}
	
	public double convertCentiEarthMassesToKilograms(int earthMasses) {
		return (earthMasses / 100) * EARTH_MASS_IN_KILOGRAMS;
	}
	
	public double convertSunMassesToKilograms(double sunMasses) {
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
