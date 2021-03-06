package phyraxi.domain;

/**
 * A (single) star.
 * 
 * @see http://en.wikipedia.org/wiki/Stellar_classification
 */
public class Star {
	
	private final String name;
	private final StarPopulation population;
	private final SpectralType spectralType;
	private final int spectralNumber;
	private final LuminosityClass luminosityClass;
	private final double mass;
	private final double brightness;
	private final int effectiveTemperature;

	public Star(String name, StarPopulation population, SpectralType spectralType, int spectralNumber,
			LuminosityClass luminosityClass, double mass, double brightness, int effectiveTemperature) {
		this.name = name;
		this.population = population;
		this.spectralType = spectralType;
		this.spectralNumber = spectralNumber;
		this.luminosityClass = luminosityClass;
		this.mass = mass;
		this.brightness = brightness;
		this.effectiveTemperature = effectiveTemperature;
	}

	/**
	 * Gets the star name.
	 * 
	 * @return star name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the star population.
	 * 
	 * @return	star population.
	 */
	public StarPopulation getPopulation() {
		return population;
	}
	
	/**
	 * Gets the spectral type of the star. Spectral type is closely related to
	 * the surface temperature and color of the star.
	 * 
	 * @return the spectral type.
	 */
	public SpectralType getSpectralType() {
		return spectralType;
	}

	/**
	 * Gets the spectral number (0 to 9) of the star. Smaller means closer to
	 * the previous type and larger closer to the next type; ie. "G9" indicates
	 * a type closer to K than F.
	 * 
	 * @return the spectral number.
	 */
	public int getSpectralNumber() {
		return spectralNumber;
	}

	/**
	 * Gets the luminosity class of the star. It correlates to size and
	 * brightness of the star, ranging from bright hypergiants to dim dwarfs.
	 * 
	 * @return the luminosity class.
	 */
	public LuminosityClass getLuminosityClass() {
		return luminosityClass;
	}

	/**
	 * Gets the relative (to Sol) star mass.
	 * 
	 * @return star mass.
	 */
	public double getMass() {
		return mass;
	}

	/**
	 * Gets the relative (to Sol) star brightness.
	 * 
	 * @return the brightness.
	 */
	public double getBrightness() {
		return brightness;
	}

	/**
	 * Gets the &quot;effective&quot; surface temperature, in Kelvins.
	 * 
	 * @return the surfaceTemperature
	 */
	public int getEffectiveTemperature() {
		return effectiveTemperature;
	}

	public String toString() {
		return name + " [ " + spectralType.toString() + spectralNumber + luminosityClass.toString() + " ]";
	}

}
