package phyraxi.server.rest.service.starmap;

import phyraxi.engine.generators.CoordinateGenerator;
import phyraxi.engine.generators.MainSequenceStarGenerator;
import phyraxi.engine.generators.RandomCoordinateGenerator;
import phyraxi.engine.generators.StatisticalStarGenerator;
import phyraxi.engine.generators.names.ConstellationStarNameGenerator;
import phyraxi.engine.generators.names.RandomStarNameGenerator;
import phyraxi.engine.generators.names.StarNameGenerator;

/**
 * Factory class for generators used by {@link StarMapResource}.
 * 
 * @author jakaarl
 */
public class StarMapGeneratorFactory {
	
	static final String DEFAULT_COORDINATE_GENERATOR = AvailableCoordinateGenerator.RANDOM.name();
	static final String DEFAULT_STAR_NAME_GENERATOR = AvailableStarNameGenerator.CONSTELLATIONS.name();
	static final String DEFAULT_STAR_GENERATOR = AvailableMainSequenceStarGenerator.STATISTICAL.name();
	
	/**
	 * Gets a coordinate generator.
	 * 
	 * @param key	generator key.
	 * 
	 * @return	a coordinate generator.
	 */
	public CoordinateGenerator coordinateGenerator(String key) {
		Class<? extends CoordinateGenerator> generatorClass =
				AvailableCoordinateGenerator.valueOf(key.toUpperCase()).generator;
		return instantiateGenerator(generatorClass);
	}
	
	/**
	 * Gets a star name generator.
	 * 
	 * @param key	generator key.
	 * 
	 * @return	a star name generator.
	 */
	public StarNameGenerator starNameGenerator(String key) {
		Class<? extends StarNameGenerator> generatorClass =
				AvailableStarNameGenerator.valueOf(key.toUpperCase()).generator;
		return instantiateGenerator(generatorClass);
	}
	
	/**
	 * Gets a star generator.
	 * 
	 * @param key	generator key.
	 * 
	 * @return	a star generator.
	 */
	public MainSequenceStarGenerator mainSequenceStarGenerator(String key) {
		Class<? extends MainSequenceStarGenerator> generatorClass =
				AvailableMainSequenceStarGenerator.valueOf(key.toUpperCase()).generator;
		return instantiateGenerator(generatorClass);
	}
	
	private <T> T instantiateGenerator(Class<T> generatorClass) {
		try {
			return generatorClass.newInstance();
		} catch (Exception e) {
			throw new IllegalStateException("Unable to instantiate " + generatorClass.getName(), e);
		}
	}
	
	private enum AvailableCoordinateGenerator {
		
		RANDOM(RandomCoordinateGenerator.class);
		
		private Class<? extends CoordinateGenerator> generator;
		
		private AvailableCoordinateGenerator(Class<? extends CoordinateGenerator> generator) {
			this.generator = generator;
		}
	}
	
	private enum AvailableStarNameGenerator {
		
		CONSTELLATIONS(ConstellationStarNameGenerator.class),
		RANDOM(RandomStarNameGenerator.class);
		
		private Class<? extends StarNameGenerator> generator;
		
		private AvailableStarNameGenerator(Class<? extends StarNameGenerator> generator) {
			this.generator = generator;
		}
	}
	
	private enum AvailableMainSequenceStarGenerator {
		
		STATISTICAL(StatisticalStarGenerator.class);
		
		private Class<? extends MainSequenceStarGenerator> generator;
		
		private AvailableMainSequenceStarGenerator(Class<? extends MainSequenceStarGenerator> generator) {
			this.generator = generator;
		}
	}

}
