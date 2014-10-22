package phyraxi.server.rest.service.starmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import phyraxi.domain.Coordinates;
import phyraxi.domain.LuminosityClass;
import phyraxi.domain.SpectralType;
import phyraxi.domain.Star;
import phyraxi.domain.StarPopulation;
import phyraxi.engine.generators.GeneratorFactory;
import phyraxi.engine.generators.names.StarNameGenerator;
import phyraxi.engine.generators.stars.CoordinateGenerator;
import phyraxi.engine.generators.stars.MainSequenceStarGenerator;
import phyraxi.engine.generators.stars.RandomCoordinateGenerator;
import phyraxi.engine.generators.stars.StatisticalStarGenerator;
import phyraxi.server.rest.data.StarMap;
import phyraxi.server.rest.data.StarMap.StarInfo;


/**
 * A resource returning different star maps.
 * 
 * @author Jani Kaarela (@gmail.com)
 */
@Path("starmap")
@Produces(MediaType.APPLICATION_JSON)
public class StarMapResource {

	static final int RANDOM_MAP_DEFAULT_SIZE = 30;
	static final int RANDOM_MAP_MAX_SIZE = 1000;
	static final int NEAR_SPACE_RADIUS = 1250;
	static final List<StarInfo> NEAR_SPACE = Arrays.asList(
			new StarInfo(new Star("Sol", StarPopulation.DISC_POPULATION_I, SpectralType.G, 2,
					LuminosityClass.MAIN_SEQUENCE, 1.0, 1.0, 5777),
					new Coordinates(0, 0, 0)),
			new StarInfo(new Star("Proxima Centauri", StarPopulation.DISC_POPULATION_I, SpectralType.M, 6,
					LuminosityClass.MAIN_SEQUENCE, 0.123, 0.0000555, 2700),
					new Coordinates(-304,292,-14)),
			new StarInfo(new Star("Alpha Centauri", StarPopulation.DISC_POPULATION_I, SpectralType.G, 2,
					LuminosityClass.MAIN_SEQUENCE, 1.1, 1.519, 5790),
					new Coordinates(-307,315,-5)),
			new StarInfo(new Star("Barnard's Star", StarPopulation.DISC_POPULATION_I, SpectralType.M, 4,
					LuminosityClass.MAIN_SEQUENCE, 0.144, 0.0035, 3134),
					new Coordinates(297,494,145)),
			new StarInfo(new Star("Wolf 359", StarPopulation.DISC_POPULATION_I, SpectralType.M, 7,
					LuminosityClass.MAIN_SEQUENCE, 0.09, 0.001, 2800),
					new Coordinates(-391,-190,647)),
			new StarInfo(new Star("Lalande 21185", StarPopulation.DISC_POPULATION_I, SpectralType.M, 2,
					LuminosityClass.MAIN_SEQUENCE, 0.46, 0.025, 3828),
					new Coordinates(-31,-345,756)),
			new StarInfo(new Star("Sirius", StarPopulation.DISC_POPULATION_I, SpectralType.A, 1,
					LuminosityClass.MAIN_SEQUENCE, 2.02, 25.4, 9940),
					new Coordinates(-623,-577,-133)),
			new StarInfo(new Star("Luyten 726-8", StarPopulation.DISC_POPULATION_I, SpectralType.M, 6,
					LuminosityClass.MAIN_SEQUENCE, 0.102, 0.00006, 2670),
					new Coordinates(17,-215,-846)),
			new StarInfo(new Star("Ross 154", StarPopulation.DISC_POPULATION_I, SpectralType.M, 4,
					LuminosityClass.MAIN_SEQUENCE, 0.17, 0.0038, 3340),
					new Coordinates(187,935,-173)),
			new StarInfo(new Star("Ross 248", StarPopulation.DISC_POPULATION_I, SpectralType.M, 6,
					LuminosityClass.MAIN_SEQUENCE, 0.136, 0.0018, 2799),
					new Coordinates(929,-338,-300)),
			new StarInfo(new Star("Epsilon Eridani", StarPopulation.DISC_POPULATION_I, SpectralType.K, 2,
					LuminosityClass.MAIN_SEQUENCE, 0.82, 0.34, 5084),
					new Coordinates(-192,-674,-782)),
			new StarInfo(new Star("Lacaille 9352", StarPopulation.DISC_POPULATION_I, SpectralType.M, 1,
					LuminosityClass.MAIN_SEQUENCE, 0.503, 0.033, 3626),
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
		RandomCoordinateGenerator coordinateGenerator = new RandomCoordinateGenerator();
		StatisticalStarGenerator starGenerator = new StatisticalStarGenerator();
		return generateRandomStarMap(size, coordinateGenerator, starGenerator);
	}
	
	@POST @Path("/random") @Consumes(MediaType.APPLICATION_JSON)
	public StarMap randomStarMap(StarMapParameters parameters) {
		GeneratorFactory factory = new GeneratorFactory();
		CoordinateGenerator coordinateGenerator = factory.coordinateGenerator(parameters.coordinateGenerator);
		StarNameGenerator starNameGenerator = factory.starNameGenerator(parameters.nameGenerator);
		MainSequenceStarGenerator starGenerator = factory.mainSequenceStarGenerator(parameters.starGenerator);
		starGenerator.setNameGenerator(starNameGenerator);
		return generateRandomStarMap(parameters.size, coordinateGenerator, starGenerator);
	}
	
	private StarMap generateRandomStarMap(int size, CoordinateGenerator coordinateGenerator,
			MainSequenceStarGenerator starGenerator) {
		if (size < 1 || size > RANDOM_MAP_MAX_SIZE) {
			throw new IllegalArgumentException("Invalid star map size: " + size);
		}
		List<StarInfo> stars = new ArrayList<>(size);
		List<Coordinates> coordinateList = coordinateGenerator.generateStarSystemCoordinates(size);
		int radius = coordinateGenerator.getMapRadius(size);
		for (Coordinates coordinates : coordinateList) {
			Star star = starGenerator.generateStar(StarPopulation.DISC_POPULATION_I); // TODO: determine population
			StarInfo starInfo = new StarInfo(star, coordinates);
			stars.add(starInfo);
		}
		
		return new StarMap(radius, stars);
	}

}
