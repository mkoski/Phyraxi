package phyraxi.engine.generators;

import org.junit.Ignore;
import org.junit.Test;

import phyraxi.engine.generators.ConstellationStarNameGenerator;
import phyraxi.engine.generators.StarNameGenerator;

/**
 * @author Jani Kaarela (@gmail.com)
 *
 */
public class ConstellationStarNameGeneratorTest {
	
	@Ignore("Doesn't really test anything, can be used to try out generating names.")
	@Test
	public void testGenerateName() {
		StarNameGenerator generator = new ConstellationStarNameGenerator();
		for (int i = 0; i < 20; i++) {
			System.out.println(generator.generateName());
		}
	}

}
