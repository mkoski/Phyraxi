package phyraxi.domain;

/**
 * 
 * @author Jani Kaarela (@gmail.com)
 */
public class Planet extends Satellite {
	
	private PlanetType type;
	private int radius; // in 1/100 Earth radiuses
	private int density; // in 1/100 Earth densities
	private int gravity; // in 1/100 Earth gravities
	private boolean tidallyLocked;
	
	/*
	 * TODO more planet properties
	 * - axial tilt, rotation
	 * - atmosphere and hydrosphere composition
	 * - biosphere
	 * - moons
	 */
	
	private Planet() {
		// supress public constructor
	}
	
	public PlanetType getType() {
		return type;
	}

	/**
	 * Gets radius, in Earth radiuses.
	 * 
	 * @return	planet radius in Earth radiuses.
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * Gets density, in Earth densities.
	 * 
	 * @return	planet density in Earth densities.
	 */
	public int getDensity() {
		return density;
	}

	/**
	 * Gets gravity, in Earth gravities.
	 * 
	 * @return	planet gravity in Earth gravities.
	 */
	public int getGravity() {
		return gravity;
	}
	
	/**
	 * Whether or not planet is tidally locked to it's host star/planet.
	 * A tidally locked planet always has the same side facing it's host.
	 * 
	 * @return	whether the planet is tidally locked.
	 */
	public boolean isTidallyLocked() {
		return tidallyLocked;
	}

	public static class Builder {
		
		private Planet planet;
		
		public Builder() {
			planet = new Planet();
		}
		
		public Builder setName(String name) {
			planet.name = name;
			return this;
		}
		
		public Builder setType(PlanetType type) {
			planet.type = type;
			return this;
		}
		
		public Builder setDistance(int distance) {
			planet.distance = distance;
			return this;
		}
		
		public Builder setRadius(int radius) {
			planet.radius = radius;
			return this;
		}
		
		public Builder setDensity(int density) {
			planet.density = density;
			return this;
		}
		
		public Builder setGravity(int gravity) {
			planet.gravity = gravity;
			return this;
		}
		
		public Builder setTidallyLocked(boolean tidallyLocked) {
			planet.tidallyLocked = tidallyLocked;
			return this;
		}
		
		public Planet build() {
			Planet built = planet;
			planet = new Planet();
			return built;
		}
		
	}

}
