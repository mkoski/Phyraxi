package fourx.utils;

import fourx.domain.Coordinates;

/**
 * Utility for converting <a href="http://en.wikipedia.org/wiki/Galactic_coordinate_system">galactic coordinates</a>
 * to cartesian XYZ coordinates.
 *  
 * @author Jani Kaarela
 */
public class GalacticCoordsConverter {
	
	/**
	 * Converts galactic coordinates to {@link Coordinates}.
	 * 
	 * @param longitude	galactic longitude in degrees (positive, measured clockwise from direction of galactic center).
	 * @param latitude	galactic latitude in degrees (inclination from galactic plane).
	 * @param distance	distance, in 1/100's of light years.
	 * 
	 * @return	cartesian coordinates.
	 */
	public Coordinates convertToCartesian(float longitude, float latitude, int distance)
			throws IllegalArgumentException {
		if (longitude < 0 || longitude > 360) {
			throw new IllegalArgumentException("Invalid longitude: " + longitude);
		}
		double radLongitude = Math.toRadians(longitude);
		double radLatitude = Math.toRadians(latitude);
		// X and Y formulae are switched, since they assume longitude is measured from X axis.
		long x = Math.round(distance * Math.cos(radLatitude) * Math.sin(radLongitude));
		long y = Math.round(distance * Math.cos(radLatitude) * Math.cos(radLongitude));;
		long z = Math.round(distance * Math.sin(radLatitude));
		// no single coordinate can be greater than distance, so casts are safe 
		return new Coordinates((int) x, (int) y, (int) z);
	}
	
	/**
	 * Converts cartesian (X,Y,Z) coordinates to {@link GalacticCoordinates}.
	 * 
	 * @param cartesian	cartesian coordinates.
	 * 
	 * @return	galactic coordinates.
	 * 
	 * @throws	IllegalArgumentException	if distance exceeds <code>Integer.MAX_VALUE</code>.
	 */
	public GalacticCoordinates convertToGalactic(Coordinates cartesian) {
		double distance = Math.sqrt((Math.pow(cartesian.x, 2) + Math.pow(cartesian.y, 2) + Math.pow(cartesian.z, 2)));
		if (Math.round(distance) > Integer.MAX_VALUE) {
			// distance can exceed Integer.MAX_VALUE
			throw new IllegalArgumentException("Invalid coordinates: distance exceeds maximum Integer value.");
		}
		int roundedDistance = (int) Math.round(distance);
		float latitude = (float) Math.toDegrees(Math.asin(cartesian.z / distance));
		float longitude = (float) Math.toDegrees(Math.atan(cartesian.y / cartesian.x));
		return new GalacticCoordinates(longitude, latitude, roundedDistance);
	}
	
	
	
	/**
	 * Reads in arguments as coordinates in the format: <code>longitude;latitude;distance</code>,
	 * multiple coordinates as separate arguments. Longitude and latitude should be valid
	 * <code>floats</code> while distance should be an integer (any unit will do, but 1/100
	 * light years is the assumed unit).
	 * 
	 * @param args	command line arguments.
	 */
	public static void main(String[] args) {
		if (args.length > 0) {
			GalacticCoordsConverter converter = new GalacticCoordsConverter();
			for (String arg : args) {
				String[] parts = arg.split(";");
				if (parts.length != 3) {
					throw new IllegalArgumentException("Invalid argument: " + arg);
				}
				float longitude = Float.parseFloat(parts[0]);
				float latitude = Float.parseFloat(parts[1]);
				int distance = Math.abs(Integer.parseInt(parts[2]));
				System.out.println(converter.convertToCartesian(longitude, latitude, distance));
			}
		} else {
			throw new IllegalArgumentException("No data to convert!");
		}
	}
	
	public static class GalacticCoordinates {
		
		final float longitude;
		final float latitude;
		final int distance;
		
		GalacticCoordinates(float longitude, float latitude, int distance) {
			this.longitude = longitude;
			this.latitude = latitude;
			this.distance = distance;
		}
	}

}
