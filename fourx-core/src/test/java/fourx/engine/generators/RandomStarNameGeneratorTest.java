package fourx.engine.generators;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Jani Kaarela (@gmail.com)
 *
 */
public class RandomStarNameGeneratorTest {
	
	@Ignore("Doesn't really test anything, only demonstrates generated names.")
	@Test
	public void testGenerateName() {
		RandomStarNameGenerator generator = new RandomStarNameGenerator();
		for (int i = 0; i < 20; i++) {
			System.out.println(generator.generateName());
		}
	}

}
