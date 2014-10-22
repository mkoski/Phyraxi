package phyraxi.engine.generators;

import java.util.ArrayList;
import java.util.List;

import phyraxi.domain.Coordinates;
import phyraxi.domain.Star;
import phyraxi.domain.StarPopulation;
import phyraxi.domain.StarSystem;
import phyraxi.domain.StarSystem.StarHierarchy;
import phyraxi.engine.generators.names.StarNameGenerator;
import phyraxi.engine.generators.stars.CoordinateGenerator;
import phyraxi.engine.generators.stars.MainSequenceStarGenerator;

public class UniverseGenerator {
	
	private static final GeneratorFactory GENERATOR_FACTORY = new GeneratorFactory();
	private static final int DEFAULT_NUMBER_OF_STARS = 30;
	private final CoordinateGenerator coordinateGenerator;
	private final MainSequenceStarGenerator starGenerator;
	private final int numberOfStars;
	
	public UniverseGenerator() {
		this(
				GENERATOR_FACTORY.defaultCoordinateGenerator(),
				GENERATOR_FACTORY.defaultMainSequenceStarGenerator(),
				GENERATOR_FACTORY.defaultStarNameGenerator(),
				DEFAULT_NUMBER_OF_STARS
			);
	}
	
	public UniverseGenerator(CoordinateGenerator coordinateGenerator, MainSequenceStarGenerator starGenerator,
			StarNameGenerator starNameGenerator, int numberOfStars) {
		this.coordinateGenerator = coordinateGenerator;
		this.starGenerator = starGenerator;
		this.starGenerator.setNameGenerator(starNameGenerator);
		this.numberOfStars = numberOfStars;
	}
	
	public List<StarSystem> generate() {
		List<StarSystem> universe = new ArrayList<>(numberOfStars);
		List<Coordinates> coordinateList = coordinateGenerator.generateStarSystemCoordinates(numberOfStars);
		for (Coordinates coordinates : coordinateList) {
			Star star = starGenerator.generateStar(StarPopulation.DISC_POPULATION_I); // TODO: determine population
			// TODO: determine binaries, companion stars and such...
			StarSystem system = new StarSystem(star.getName(), coordinates, StarHierarchy.createSingleStar(star));
			universe.add(system);
		}
		return universe;
	}

}
