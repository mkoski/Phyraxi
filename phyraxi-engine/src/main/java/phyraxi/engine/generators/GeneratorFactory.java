package phyraxi.engine.generators;

import phyraxi.engine.generators.names.ConstellationStarNameGenerator;
import phyraxi.engine.generators.names.RandomStarNameGenerator;
import phyraxi.engine.generators.names.StarNameGenerator;
import phyraxi.engine.generators.stars.CoordinateGenerator;
import phyraxi.engine.generators.stars.MainSequenceStarGenerator;
import phyraxi.engine.generators.stars.RandomCoordinateGenerator;
import phyraxi.engine.generators.stars.StatisticalStarGenerator;

/**
 * Factory class for universe generators.
 * 
 * @author jakaarl
 */
public class GeneratorFactory {
	
	// TODO: this isn't pretty, do something about it!
	// what was I thinking in the first place?!
	
	public static final String DEFAULT_COORDINATE_GENERATOR_KEY = AvailableCoordinateGenerator.RANDOM.name();
	public static final String DEFAULT_STAR_NAME_GENERATOR_KEY = AvailableStarNameGenerator.CONSTELLATIONS.name();
	public static final String DEFAULT_STAR_GENERATOR_KEY = AvailableMainSequenceStarGenerator.STATISTICAL.name();
	
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
	
	public CoordinateGenerator defaultCoordinateGenerator() {
		return instantiateGenerator(AvailableCoordinateGenerator.RANDOM.generator);
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
	
	public StarNameGenerator defaultStarNameGenerator() {
		return instantiateGenerator(AvailableStarNameGenerator.RANDOM.generator);
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
	
	public MainSequenceStarGenerator defaultMainSequenceStarGenerator() {
		return instantiateGenerator(AvailableMainSequenceStarGenerator.STATISTICAL.generator);
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
