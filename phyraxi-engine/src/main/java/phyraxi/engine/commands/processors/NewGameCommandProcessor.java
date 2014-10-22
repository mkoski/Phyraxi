package phyraxi.engine.commands.processors;

import java.util.List;

import phyraxi.domain.StarSystem;
import phyraxi.engine.commands.NewGame;
import phyraxi.engine.generators.UniverseGenerator;
import phyraxi.engine.state.Game;

/**
 * @author Jani Kaarela (@gmail.com)
 *
 */
public class NewGameCommandProcessor implements CommandProcessor<NewGame, Game> {

	@Override
	public Game process(NewGame command) {
		// TODO: get generator based on command
		UniverseGenerator generator = new UniverseGenerator();
		List<StarSystem> universe = generator.generate();
		Game game = new Game(universe, command.maxPlayers);
		return game;
	}

}
