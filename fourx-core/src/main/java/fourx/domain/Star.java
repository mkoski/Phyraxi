package fourx.domain;

/**
 * A (single) star.
 * 
 * @see http://en.wikipedia.org/wiki/Stellar_classification
 */
public class Star {
	
	public enum Generation {
		YOUNG, OLD, ANCIENT;
	}

	private final String name;
	private final SpectralType spectralType;
	private final int spectralNumber;
	private final LuminosityClass luminosityClass;
	private final double mass;
	private final double brightness;
	private final int surfaceTemperature;

	public Star(String name, SpectralType spectralType, int spectralNumber, LuminosityClass luminosityClass,
			double mass, double brightness, int surfaceTemperature) {
		this.name = name;
		this.spectralType = spectralType;
		this.spectralNumber = spectralNumber;
		this.luminosityClass = luminosityClass;
		this.mass = mass;
		this.brightness = brightness;
		this.surfaceTemperature = surfaceTemperature;
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
	public int getSurfaceTemperature() {
		return surfaceTemperature;
	}

	public String toString() {
		return name + " [ " + spectralType.toString() + spectralNumber + luminosityClass.toString() + " ]";
	}

}
