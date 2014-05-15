package phyraxi.utils;

import static phyraxi.utils.SunConstants.*;

import java.math.BigDecimal;

import phyraxi.domain.Star;

/**
 * 
 * @author Jani Kaarela (@gmail.com)
 */
public class StarPropertiesCalculator {
	
	private static final BigDecimal STEFAN_BOLTZMANN_CONSTANT = new BigDecimal(5.670373e-8);
	private static final double LIQUID_WATER_ZONE_INNER_LIMIT = 0.77;
	private static final double LIQUID_WATER_ZONE_OUTER_LIMIT = 1.69;
	
	private final GeometricCalculator geometricCalculator = new GeometricCalculator();
	
	public double convertSunMassesToKilograms(double sunMasses) {
		return sunMasses * SUN_MASS_IN_KILOGRAMS;
	}
	
	public double convertSolarLuminositiesToWatts(double luminosity) {
		return luminosity * SUN_LUMINOSITY_IN_WATTS;
	}
	
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
	
	/**
	 * Calculates flux density at given distance (in 1/100 AUs).
	 * 
	 * @param star		star.
	 * @param distance	distance from the star (1/100 AUs).
	 * 
	 * @return	flux density.
	 */
	public int calculateFluxDensity(Star star, int distance) {
		return (int) Math.round(convertSolarLuminositiesToWatts(star.getBrightness())
		/ geometricCalculator.calculateSphereArea(UnitConversions.centiAstronomicalUnitsToMeters(distance)));
	}
	
	public int calculateInnerZoneBoundary(Star star) {
		return (int) Math.round(star.getBrightness() * LIQUID_WATER_ZONE_INNER_LIMIT * 100);
	}
	
	public int calculateHabitableZoneBoundary(Star star) {
		return (int) Math.round(star.getBrightness() * LIQUID_WATER_ZONE_OUTER_LIMIT * 100);
	}

}
