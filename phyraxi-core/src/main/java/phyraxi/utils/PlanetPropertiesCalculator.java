package phyraxi.utils;

import phyraxi.domain.Star;

/**
 * 
 * @author Jani Kaarela (@gmail.com)
 */
public class PlanetPropertiesCalculator {
	
	static final double SUN_LUMINOSITY_IN_WATTS = 3.846e26;
	private static final double STEFAN_BOLTZMANN_CONSTANT = 5.670373e-8;
	private static final double ASTRONOMICAL_UNIT_IN_METERS = 1.496e11;
	
	
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
				/ (4 * Math.PI * Math.pow(convertToMeters(distance), 2))); // spherical area at distance
	}
	
	double convertSolarLuminositiesToWatts(double luminosity) {
		return luminosity * SUN_LUMINOSITY_IN_WATTS;
	}
	
	double convertToMeters(int distance) {
		return ASTRONOMICAL_UNIT_IN_METERS * (distance / 100d);
	}
	
	/*
	 * Resources:
	 * - http://en.wikipedia.org/wiki/Planetary_equilibrium_temperature
	 * - http://www.dangermouse.net/gurps/science/temps.html
	 * - http://bartonpaullevenson.com/NewPlanetTemps.html
	 * - http://en.wikipedia.org/wiki/Greenhouse_effect
	 */

}
