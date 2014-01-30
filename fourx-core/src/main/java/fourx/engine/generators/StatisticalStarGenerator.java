package fourx.engine.generators;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

import fourx.domain.LuminosityClass;
import fourx.domain.SpectralType;
import fourx.domain.Star;
import fourx.domain.Star.Generation;

/**
 * A star generator which aims to create &quot;statistically sound&quot; star populations, with a
 * slight nod towards diversity at the expense of realism.
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

	public Star generateStar(Generation generation) {
		return generateMainSequenceStar(generation);
	}
	
	protected Star generateMainSequenceStar(Generation generation) {
		LuminosityClass luminosityClass = determineLuminosityClass(generation);
		SpectralType spectralType = determineSpectralType(generation, luminosityClass);
		double mass = determineMass(generation, luminosityClass, spectralType);
		int effectiveTemperature = determineEffectiveTemperature(spectralType);
		int spectralNumber = determineSpectralNumber(spectralType, effectiveTemperature);
		double brightness = calculator.calculateBrightness(mass);
		
		String name = generateName();
		return new Star(name, spectralType, spectralNumber, luminosityClass, mass, brightness, effectiveTemperature);
	}

	protected LuminosityClass determineLuminosityClass(Generation generation) {
		return LuminosityClass.MAIN_SEQUENCE;
	}
	
	protected SpectralType determineSpectralType(Generation generation, LuminosityClass luminosityClass) {
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
	
	protected double determineMass(Generation generation, LuminosityClass luminosityClass, SpectralType spectralType) {
		double random = weightedRandom();
		double result = spectralType.getMinMass() + ((spectralType.getMaxMass() - spectralType.getMinMass()) * random);
		return BigDecimal.valueOf(result).setScale(3, RoundingMode.HALF_UP).doubleValue();
	}
	
	private double weightedRandom() {
		int weightingPasses;
		double random = Math.random();
		if (random < 0.075) {
			weightingPasses = 0;
		} else if (random < 0.15) {
			weightingPasses = 1;
		} else if (random < 0.3) {
			weightingPasses = 2;
		} else if (random < 0.6){
			weightingPasses = 3;
		} else {
			weightingPasses = 4;
		}
		for (int i = 0; i < weightingPasses; i++) {
			random = random * Math.random();
		}
		return random;
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
		// TODO generate random name
		return "";
	}

}
