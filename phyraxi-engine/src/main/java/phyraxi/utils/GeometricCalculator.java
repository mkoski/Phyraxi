package phyraxi.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * A utility for geometry-related calculation.
 * 
 * @author Jani Kaarela (@gmail.com)
 */
public class GeometricCalculator {
	
	private static final BigDecimal FOUR = new BigDecimal(4d);
	private static final BigDecimal PI = new BigDecimal(Math.PI);
	
	public double calculateSphereVolume(double radius) {
		BigDecimal precisionRadius = new BigDecimal(radius);
		return new BigDecimal(4d / 3d)
			.multiply(new BigDecimal(Math.PI))
			.multiply(precisionRadius.pow(3))
			.doubleValue();
	}
	
	public double calculateSphereRadius(double volume) {
		BigDecimal precisionVolume = new BigDecimal(volume);
		return Math.pow(precisionVolume
				.divide(new BigDecimal (4d / 3d * Math.PI), 9, RoundingMode.HALF_UP)
				.doubleValue(), (1d / 3d));
	}
	
	public double calculateSphereRadius(double mass, double density) {
		double volume = new BigDecimal(mass).divide(new BigDecimal(density)).doubleValue();
		return calculateSphereRadius(volume);
	}
	
	public double calculateSphereArea(double radius) {
		BigDecimal preciseRadius = new BigDecimal(radius);
		return preciseRadius.pow(2).multiply(FOUR).multiply(PI).doubleValue();
	}

}
