package phyraxi.utils;

import phyraxi.domain.LuminosityClass;
import phyraxi.domain.SpectralType;
import phyraxi.domain.Star;
import phyraxi.domain.StarPopulation;

/**
 * Constants for the physical properties of the Sun.
 * 
 * @author Jani Kaarela (@gmail.com)
 */
public class SunConstants {
	
	public static final double SUN_MASS_IN_KILOGRAMS = 1.9891e30;
	public static final double SUN_LUMINOSITY_IN_WATTS = 3.846e26;
	public static final Star SUN = new Star("Sol", StarPopulation.DISC_POPULATION_I, SpectralType.G, 2,
			LuminosityClass.MAIN_SEQUENCE, 1.0, 1.0, 5778);
	
	double convertSolarLuminositiesToWatts(double luminosity) {
		return luminosity * SUN_LUMINOSITY_IN_WATTS;
	}

}
