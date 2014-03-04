package phyraxi.domain;

/**
 * A natural satellite orbiting a host star or planet.
 * 
 * @author jakaarl
 */
public class Satellite {

	protected String name;
	protected int distance;

	public Satellite(String name, int distance) {
		this.name = name;
		this.distance = distance;
	}
	
	protected Satellite() {
		// only for use by subclasses!
	}
	
	public String getName() {
		return name;
	}

	/**
	 * Gets (average) distance from the planet's host star or planet. For satellites orbiting stars,
	 * distance is in 1/100 astronomical units, while for satellites orbiting planets distance is
	 * in host planet radiuses.
	 * 
	 * @return	distance.
	 */
	public int getDistance() {
		return distance;
	}

}