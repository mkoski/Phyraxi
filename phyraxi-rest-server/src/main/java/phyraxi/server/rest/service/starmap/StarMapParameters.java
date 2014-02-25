package phyraxi.server.rest.service.starmap;

public class StarMapParameters {
	private static final int DEFAULT_SIZE = StarMapResource.RANDOM_MAP_DEFAULT_SIZE;
	public int size = DEFAULT_SIZE;
	public String coordinateGenerator = StarMapGeneratorFactory.DEFAULT_COORDINATE_GENERATOR;
	public String starGenerator = StarMapGeneratorFactory.DEFAULT_STAR_GENERATOR;
	public String nameGenerator = StarMapGeneratorFactory.DEFAULT_STAR_NAME_GENERATOR;
}