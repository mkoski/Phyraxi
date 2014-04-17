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
	private static final double ASSUMED_PLANETARY_BODY_ALBEDO = 0.2;
	private static final int WATER_ZONE_LOWER_TEMPERATURE_BOUND = 270;
	private static final int WATER_ZONE_UPPER_TEMPERATURE_BOUND = 380;
	
	private final GeometricCalculator geometricCalculator = new GeometricCalculator();
	
	public double convertSunMassesToKilograms(double sunMasses) {
		return sunMasses * SUN_MASS_IN_KILOGRAMS;
	}
	
	public double convertSolarLuminositiesToWatts(double luminosity) {
		return luminosity * SUN_LUMINOSITY_IN_WATTS;
	}
	
	/**
	 * Calculates flux density at given distance (in 1/100 AUs).
	 * 
	 * @param star		star.
	 * @param distance	distance from the star (1/100 AUs).
	 * 
	 * @return	flux density.
	 */
	public double calculateFluxDensity(Star star, int distance) {
		return convertSolarLuminositiesToWatts(star.getBrightness())
		/ geometricCalculator.calculateSphereArea(UnitConversions.centiAstronomicalUnitsToMeters(distance));
	}
	
	public int calculateInnerZoneBoundary(Star star) {
		return 0;
	}
	
	public int calculateHabitableZoneBoundary(Star star) {
		return 0;
	}
	
	public int calculateOuterZoneBoundary(Star star) {
		return 0;
	}

}
