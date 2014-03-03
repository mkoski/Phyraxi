package phyraxi.domain;

/**
 * 
 * @author Jani Kaarela (@gmail.com)
 */
public class Planet {
	
	public enum Type {
		ASTEROID_BELT,
		PLANETOID,
		TERRESTRIAL,
		ICE_GIANT,
		CTHONIAN,
		JOVIAN;
	}
	
	private String name;
	private Type type;
	private int distance; // in 1/100 astronomical units
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
	
	public String getName() {
		return name;
	}
	
	public Type getType() {
		return type;
	}

	/**
	 * Gets distance from the planet's host star or planet.
	 * 
	 * @return	distance, in 1/100 astronomical units.
	 */
	public int getDistance() {
		return distance;
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
	
	/**
	 * Instantiates a &quot;planet&quot; for an asteroid belt. Of planet properties, density applies
	 * to asteroid belts, indicating the average density (and thus mineral richness) of asteroids.
	 * 
	 * @param name		asteroid belt name.
	 * @param density	asteroids' average density.
	 * 
	 * @return	an planet object representing an asteroid belt. 
	 */
	public static Planet createAsteroidBelt(String name, int density) {
		Planet asteroids = new Planet();
		asteroids.name = name;
		asteroids.type = Type.ASTEROID_BELT;
		asteroids.density = density;
		return asteroids;
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
		
		public Builder setType(Type type) {
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
