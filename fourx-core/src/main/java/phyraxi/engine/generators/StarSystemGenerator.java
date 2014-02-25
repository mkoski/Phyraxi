package phyraxi.engine.generators;

import java.util.List;

import phyraxi.command.GameSettings;
import phyraxi.domain.Coordinates;
import phyraxi.domain.StarSystem;


/**
 * 
 */
public interface StarSystemGenerator {

	List<StarSystem> generateStarSystems(List<Coordinates> coordinates, GameSettings gameSettings);

}
