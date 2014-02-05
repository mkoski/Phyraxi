package fourx.domain;

/**
 * A map key for coordinates. Using the key is a hacky solution for discouraging stars
 * overlapping each other on the galactic plane. A real solution will either generate
 * coordinates with a smarter algorithm, or divide them into some sort of cells in
 * which checking the closest neighbor is a cheap enough solution.
 * 
 * @author Jani Kaarela (@gmail.com)
 */
public class DistanceMarginCoordinatesKey {
	
	private static final int DISTANCE_MARGIN = 500;
	private final Coordinates coordinates;
	
	public DistanceMarginCoordinatesKey(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	
	@Override
	public int hashCode() {
		int hashCode = 113;
		hashCode += (coordinates.x / DISTANCE_MARGIN);
		hashCode += (coordinates.y / DISTANCE_MARGIN * 13121);
		return hashCode;
	}
	
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object instanceof DistanceMarginCoordinatesKey) {
			Coordinates that = ((DistanceMarginCoordinatesKey) object).coordinates;
			return (this.coordinates.x / DISTANCE_MARGIN == that.x / DISTANCE_MARGIN)
					&& (this.coordinates.y / DISTANCE_MARGIN == that.y / DISTANCE_MARGIN);
		} else {
			return false;
		}
	}

}
