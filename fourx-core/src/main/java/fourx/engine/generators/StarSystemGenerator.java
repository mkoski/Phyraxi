package fourx.engine.generators;

import java.util.List;

import fourx.command.GameSettings;
import fourx.domain.Coordinates;
import fourx.domain.StarSystem;

/**
 * 
 */
public interface StarSystemGenerator {

	List<StarSystem> generateStarSystems(List<Coordinates> coordinates, GameSettings gameSettings);

}
