package phyraxi.server.rest.service.starmap;

import phyraxi.engine.generators.GeneratorFactory;

public class StarMapParameters {
	private static final int DEFAULT_SIZE = StarMapResource.RANDOM_MAP_DEFAULT_SIZE;
	public int size = DEFAULT_SIZE;
	public String coordinateGenerator = GeneratorFactory.DEFAULT_COORDINATE_GENERATOR_KEY;
	public String starGenerator = GeneratorFactory.DEFAULT_STAR_GENERATOR_KEY;
	public String nameGenerator = GeneratorFactory.DEFAULT_STAR_NAME_GENERATOR_KEY;
}