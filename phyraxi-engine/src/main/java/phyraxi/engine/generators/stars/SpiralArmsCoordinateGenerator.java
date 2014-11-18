package phyraxi.engine.generators.stars;

import java.util.List;

import phyraxi.domain.Coordinates;


/**
 * 
 */
public class SpiralArmsCoordinateGenerator implements CoordinateGenerator {

	public List<Coordinates> generateStarSystemCoordinates(int numberOfStars) {
		// TODO: implement!
		// - should create a denser central bulge with more variable Z coords
		// - each "cluster" should be a spiral arm
		// - arms should be angles reasonably regularly, small random variation
		// - randomly determine spiral direction
		// - divide stars semi-evenly to bulge and arms
		// - bulge has decreasing density and Z-variation further from center
		// - for arms, "bending" increases and Z-variation decreases with
		// distance
		// for bending the spiral:
		// http://en.wikipedia.org/wiki/Hyperbolic_function
		return null;
	}

	public Coordinates getMapDimensions(int numberOfStars) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UniverseShape getUniverseShape() {
		return UniverseShape.SPHERE;
	}

}
