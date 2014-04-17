package phyraxi.engine.generators;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

import phyraxi.domain.LuminosityClass;
import phyraxi.domain.SpectralType;
import phyraxi.domain.Star;
import phyraxi.domain.StarPopulation;


/**
 * A star generator which aims to create &quot;statistically sound&quot; star populations, with a
 * considerable nod towards diversity at the expense of realism.
 * 
 * @author Jani Kaarela
 */
public class StatisticalStarGenerator implements MainSequenceStarGenerator {
	
	// the previous class is added up, to get an upper limit of number range
	private static final int O_CLASS_PERCENTAGE = 3;
	private static final int B_CLASS_PERCENTAGE = 5 + O_CLASS_PERCENTAGE; 
	private static final int A_CLASS_PERCENTAGE = 7 + B_CLASS_PERCENTAGE;
	private static final int F_CLASS_PERCENTAGE = 10 + A_CLASS_PERCENTAGE;
	private static final int G_CLASS_PERCENTAGE = 15 + F_CLASS_PERCENTAGE;
	private static final int K_CLASS_PERCENTAGE = 20 + G_CLASS_PERCENTAGE;
	private static final int M_CLASS_PERCENTAGE = 40 + K_CLASS_PERCENTAGE;
	
	private StarPropertiesCalculator calculator = new StarPropertiesCalculator();
	private StarNameGenerator nameGenerator = new ConstellationStarNameGenerator(); 
	
	public Star generateStar(StarPopulation population) {
		return generateMainSequenceStar(population);
	}
	
	public void setNameGenerator(StarNameGenerator starNameGenerator) {
		this.nameGenerator = starNameGenerator;
	}
	
	protected Star generateMainSequenceStar(StarPopulation population) {
		LuminosityClass luminosityClass = determineLuminosityClass(population);
		SpectralType spectralType = determineSpectralType(population, luminosityClass);
		double mass = determineMass(population, luminosityClass, spectralType);
		int effectiveTemperature = determineEffectiveTemperature(spectralType);
		int spectralNumber = determineSpectralNumber(spectralType, effectiveTemperature);
		double brightness = BigDecimal.valueOf(calculator.calculateBrightness(mass))
				.setScale(3, RoundingMode.HALF_UP).doubleValue();
		
		String name = generateName();
		return new Star(name, population, spectralType, spectralNumber, luminosityClass, mass, brightness,
				effectiveTemperature);
	}

	protected LuminosityClass determineLuminosityClass(StarPopulation generation) {
		return LuminosityClass.MAIN_SEQUENCE;
	}
	
	protected SpectralType determineSpectralType(StarPopulation generation, LuminosityClass luminosityClass) {
		SpectralType spectralType = null;
		int randomInt = new Random().nextInt(100) + 1;
		if (randomInt <= O_CLASS_PERCENTAGE) {
			spectralType = SpectralType.O;
		} else if (randomInt <= B_CLASS_PERCENTAGE) {
			spectralType = SpectralType.B;
		} else if (randomInt <= A_CLASS_PERCENTAGE) {
			spectralType = SpectralType.A;
		} else if (randomInt <= F_CLASS_PERCENTAGE) {
			spectralType = SpectralType.F;
		} else if (randomInt <= G_CLASS_PERCENTAGE) {
			spectralType = SpectralType.G;
		} else if (randomInt <= K_CLASS_PERCENTAGE) {
			spectralType = SpectralType.K;
		} else if (randomInt <= M_CLASS_PERCENTAGE) {
			spectralType = SpectralType.M;
		} else {
			throw new IllegalStateException("This shouldn't happen - be very alarmed.");
		}
		return spectralType;
	}
	
	protected double determineMass(StarPopulation generation, LuminosityClass luminosityClass, SpectralType spectralType) {
		double random = Math.random();
		double result = spectralType.getMinMass() + ((spectralType.getMaxMass() - spectralType.getMinMass()) * random);
		return BigDecimal.valueOf(result).setScale(3, RoundingMode.HALF_UP).doubleValue();
	}
	
	protected int determineEffectiveTemperature(SpectralType spectralType) {
		double random = Math.random();
		return spectralType.getMinEffectiveTemperature() + ((int) Math.floor(
				(spectralType.getMaxEffectiveTemperature() - spectralType.getMinEffectiveTemperature()) * random));
	}
	
	protected int determineSpectralNumber(SpectralType spectralType, int effectiveTemperature) {
		int range = spectralType.getMaxEffectiveTemperature() - spectralType.getMinEffectiveTemperature();
		int step = range / 9;
		return ((spectralType.getMaxEffectiveTemperature() - effectiveTemperature) / step);
	}
	
	protected String generateName() {
		return nameGenerator.generateName();
	}

}
