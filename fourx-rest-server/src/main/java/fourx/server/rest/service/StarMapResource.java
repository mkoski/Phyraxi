package fourx.server.rest.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fourx.domain.Coordinates;
import fourx.domain.LuminosityClass;
import fourx.domain.SpectralType;
import fourx.domain.Star;
import fourx.engine.generators.RandomCoordinateGenerator;
import fourx.engine.generators.StatisticalStarGenerator;
import fourx.server.rest.data.StarMap;
import fourx.server.rest.data.StarMap.StarInfo;

/**
 * A resource returning different star maps.
 * 
 * @author Jani Kaarela (@gmail.com)
 */
@Path("starmap")
@Produces(MediaType.APPLICATION_JSON)
public class StarMapResource {

	private static final int RANDOM_MAP_DEFAULT_SIZE = 30;
	private static final int RANDOM_MAP_MAX_SIZE = 1000;
	private static final int NEAR_SPACE_RADIUS = 1250;
	private static final List<StarInfo> NEAR_SPACE = Arrays.asList(
			new StarInfo(new Star(
					"Sol", SpectralType.G, 2, LuminosityClass.MAIN_SEQUENCE, 1.0, 1.0, 5777),
					new Coordinates(0, 0, 0)),
			new StarInfo(new Star(
					"Proxima Centauri", SpectralType.M, 6, LuminosityClass.MAIN_SEQUENCE, 0.123, 0.0000555, 2700),
					new Coordinates(-304,292,-14)),
			new StarInfo(new Star(
					"Alpha Centauri", SpectralType.G, 2, LuminosityClass.MAIN_SEQUENCE, 1.1, 1.519, 5790),
					new Coordinates(-307,315,-5)),
			new StarInfo(new Star(
					"Barnard's Star", SpectralType.M, 4, LuminosityClass.MAIN_SEQUENCE, 0.144, 0.0035, 3134),
					new Coordinates(297,494,145)),
			new StarInfo(new Star(
					"Wolf 359", SpectralType.M, 7, LuminosityClass.MAIN_SEQUENCE, 0.09, 0.001, 2800),
					new Coordinates(-391,-190,647)),
			new StarInfo(new Star(
					"Lalande 21185", SpectralType.M, 2, LuminosityClass.MAIN_SEQUENCE, 0.46, 0.025, 3828),
					new Coordinates(-31,-345,756)),
			new StarInfo(new Star(
					"Sirius", SpectralType.A, 1, LuminosityClass.MAIN_SEQUENCE, 2.02, 25.4, 9940),
					new Coordinates(-623,-577,-133)),
			new StarInfo(new Star(
					"Luyten 726-8", SpectralType.M, 6, LuminosityClass.MAIN_SEQUENCE, 0.102, 0.00006, 2670),
					new Coordinates(17,-215,-846)),
			new StarInfo(new Star(
					"Ross 154", SpectralType.M, 4, LuminosityClass.MAIN_SEQUENCE, 0.17, 0.0038, 3340),
					new Coordinates(187,935,-173)),
			new StarInfo(new Star(
					"Ross 248", SpectralType.M, 6, LuminosityClass.MAIN_SEQUENCE, 0.136, 0.0018, 2799),
					new Coordinates(929,-338,-300)),
			new StarInfo(new Star(
					"Epsilon Eridani", SpectralType.K, 2, LuminosityClass.MAIN_SEQUENCE, 0.82, 0.34, 5084),
					new Coordinates(-192,-674,-782)),
			new StarInfo(new Star(
					"Lacaille 9352", SpectralType.M, 1, LuminosityClass.MAIN_SEQUENCE, 0.503, 0.033, 3626),
					new Coordinates(39,435,-980))
	);

	@GET @Path("/near-space")
	public StarMap nearSpaceStarMap() {
		return new StarMap(NEAR_SPACE_RADIUS, NEAR_SPACE);
	}
	
	@GET @Path("/random")
	public StarMap randomStarMap() {
		return randomStarMap(RANDOM_MAP_DEFAULT_SIZE);
	}
	
	@GET @Path("/random/{size}")
	public StarMap randomStarMap(@PathParam("size") int size) {
		if (size < 1 || size > RANDOM_MAP_MAX_SIZE) {
			throw new IllegalArgumentException("Invalid star map size: " + size);
		}
		List<StarInfo> stars = new ArrayList<>(size);
		RandomCoordinateGenerator coordinateGenerator = new RandomCoordinateGenerator();
		List<Coordinates> coordinateList = coordinateGenerator.generateStarSystemCoordinates(size);
		int radius = coordinateGenerator.getMapRadius(size);
		StatisticalStarGenerator starGenerator = new StatisticalStarGenerator();
		for (Coordinates coordinates : coordinateList) {
			Star star = starGenerator.generateStar(Star.Generation.POPULATION_I); // couldn't care less
			StarInfo starInfo = new StarInfo(star, coordinates);
			stars.add(starInfo);
		}
		
		return new StarMap(radius, stars);
	}

}
