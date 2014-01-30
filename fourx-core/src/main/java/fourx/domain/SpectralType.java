package fourx.domain;

/**
 * Star spectral types.
 */
public enum SpectralType {

	/** Blue, > 33000K. */
	O(15.0, 50.0, 33000, 50000),
	/** Blue-white, 10000-33000K. */
	B(3.0, 20.0, 10000, 33000),
	/** White, 7500-10000K. */
	A(1.5, 3.5, 7500, 10000),
	/** Yellow-white, 6000-7500K. */
	F(1.0, 2.0, 6000, 7000),
	/** Yellow, 5200-6000K. */
	G(0.6, 1.2, 5200, 6000),
	/** Orange, 3700-5200K. */
	K(0.3, 0.9, 3700, 5200),
	/** Red, 2000-3700K. */
	M(0.08, 0.6, 2000, 3700);
	
	private final double minMass;
	private final double maxMass;
	private final int minEffectiveTemperature;
	private final int maxEffectiveTemperature;
	
	private SpectralType(double minMass, double maxMass, int minEffectiveTemperature, int maxEffectiveTemperature) {
		this.minMass = minMass;
		this.maxMass = maxMass;
		this.minEffectiveTemperature = minEffectiveTemperature;
		this.maxEffectiveTemperature = maxEffectiveTemperature;
	}
	
	public double getMinMass() {
		return minMass;
	}
	
	public double getMaxMass() {
		return maxMass;
	}
	
	public int getMinEffectiveTemperature() {
		return minEffectiveTemperature;
	}
	
	public int getMaxEffectiveTemperature() {
		return maxEffectiveTemperature;
	}

}
