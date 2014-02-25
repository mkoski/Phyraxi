package phyraxi.engine.generators;

/**
 * Utilities for calculating star properties.
 * 
 * @author jakaarl
 */
public class StarPropertiesCalculator {
	
	/**
	 * Calculates estimated brightness (luminosity, Sol = 1.0) for a main sequence star.
	 * 
	 * @param mass	star mass (Sol = 1.0).
	 * 
	 * @return	estimated brightness.
	 */
	public double calculateBrightness(double mass) {
		double multiplier;
		double exponent;
		if (mass < 0.43) {
			multiplier = 0.23;
			exponent = 2.3;
		} else if (mass < 2) {
			multiplier = 1;
			exponent = 4;
		} else if (mass < 20) {
			multiplier = 1.5;
			exponent = 3.5;
		} else {
			multiplier = 3200;
			exponent = 1;
		}
		return multiplier * Math.pow(mass, exponent);
	}
	
	public double calculateDiameter(double mass, int effectiveTemperature) {
		return 0;
	}

}
