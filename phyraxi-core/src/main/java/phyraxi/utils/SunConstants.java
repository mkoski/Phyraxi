package phyraxi.utils;

/**
 * Constants for the physical properties of the Sun.
 * 
 * @author Jani Kaarela (@gmail.com)
 */
public class SunConstants {
	
	public static final double SUN_MASS_IN_KILOGRAMS = 1.9891e30;
	public static final double SUN_LUMINOSITY_IN_WATTS = 3.846e26;
	
	double convertSolarLuminositiesToWatts(double luminosity) {
		return luminosity * SUN_LUMINOSITY_IN_WATTS;
	}

}
