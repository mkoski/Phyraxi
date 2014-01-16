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
	public Coordinates convertToCoordinates(float longitude, float latitude, int distance) {
		long z = Math.round(Math.sin(latitude) * distance); // TODO: what about negative latitude?
		/*
		long x...
		long y...
		TODO: how to handle negative longitude and longitude > 90 in the calculations?! THINK!
		x is something to the effect of Math.sin(longitude) * planarDistance 
		TODO: check values against Integer.MAX_VALUE and throw IllegalArgumentException, if exceeded
		*/
		return null;
	}
	
	public static void main(String[] args) {
		if (args.length > 0) {
			String[] data = args;
			GalacticCoordsConverter converter = new GalacticCoordsConverter();
			// TODO: read in data (format?), convert and print
		} else {
			throw new IllegalArgumentException("No data to convert!");
		}
	}

}
