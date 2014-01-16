package fourx.utils;

import fourx.domain.Coordinates;

/**
 * Utility for converting <a href="http://en.wikipedia.org/wiki/Galactic_coordinate_system">galactic coordinates</a>
 * to XYZ coordinates.
 *  
 * @author jakaarl
 */
public class GalacticCoordsConverter {
	
	/**
	 * Converts galactic coordinates to {@link Coordinates}.
	 * 
	 * @param longitude	galactic longitude in degrees.
	 * @param latitude	galactic latitude in degrees.
	 * @param distance	distance, in 1/100's of light years.
	 * 
	 * @return	coordinates.
	 */
	public Coordinates convertToCartesian(float longitude, float latitude, int distance) {
		long x = Math.round(distance * Math.sin(longitude) * Math.cos(latitude));
		long y = Math.round(distance * Math.sin(longitude) * Math.sin(latitude));
		long z = Math.round(distance * Math.cos(longitude));
		if (x > Integer.MAX_VALUE || y > Integer.MAX_VALUE || z > Integer.MAX_VALUE) {
			throw new IllegalArgumentException("Distance " + distance + " too great: "
					+ "resulting coordinates exceed maximum Integer value.");
		}
		return new Coordinates((int) x, (int) y, (int) z);
	} // TODO: reverse conversion, tests
	
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

}
