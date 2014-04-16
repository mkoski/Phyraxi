package phyraxi.utils;

import static phyraxi.utils.EarthConstants.EARTH_DISTANCE_FROM_THE_SUN_IN_METERS;
import static phyraxi.utils.EarthConstants.EARTH_MASS_IN_KILOGRAMS;
import static phyraxi.utils.EarthConstants.EARTH_RADIUS_IN_METERS;

/**
 * 
 * @author Jani Kaarela (@gmail.com)
 */
public class UnitConversions {
	
	public static final double ASTRONOMICAL_UNIT = EARTH_DISTANCE_FROM_THE_SUN_IN_METERS;
	
	public static double centiAstronomicalUnitsToMeters(int centiAstronomicalUnits) {
		return (centiAstronomicalUnits / 100d) * ASTRONOMICAL_UNIT;
	}
	
	public static double centiEarthRadiusesToMeters(int earthRadiuses) {
		return (earthRadiuses / 100) * EARTH_RADIUS_IN_METERS;
	}
	
	public static double centiEarthMassesToKilograms(int earthMasses) {
		return (earthMasses / 100) * EARTH_MASS_IN_KILOGRAMS;
	}

}
