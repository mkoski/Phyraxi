package phyraxi.engine.generators;

/**
 * Star name generator.
 * 
 * @author Jani Kaarela (@gmail.com)
 */
public interface StarNameGenerator {
	
	/**
	 * Generate a new star name. It's up to the implementation to ensure the generated name is unique.
	 * Implementations using a list of predefined named or some other &quot;limited source&quot; should
	 * fall back to random names, if they run out of names.
	 * 
	 * @return	generated star name.
	 */
	String generateName();

}
