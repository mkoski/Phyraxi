package phyraxi.domain;

/**
 * 
 * @author Jani Kaarela (@gmail.com)
 */
public class Planet {
	
	public enum Type {
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



	public int getDistance() {
		return distance;
	}



	public int getRadius() {
		return radius;
	}



	public int getDensity() {
		return density;
	}



	public int getGravity() {
		return gravity;
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
		
		public Planet build() {
			Planet built = planet;
			planet = new Planet();
			return built;
		}
		
	}

}
