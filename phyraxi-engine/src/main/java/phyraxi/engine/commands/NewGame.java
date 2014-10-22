package phyraxi.engine.commands;

import static phyraxi.engine.generators.GeneratorFactory.*;

import phyraxi.engine.state.Game;

public class NewGame extends Command<Game> {
	
	public final int maxPlayers;
	public final int numberOfStars;
	public final String coordinateGeneratorName;
	public final String starNameGeneratorName;
	public final String starGeneratorName;
	
	public NewGame(int maxPlayers, int numberOfStars) {
		this(maxPlayers, numberOfStars, DEFAULT_COORDINATE_GENERATOR_KEY, DEFAULT_STAR_NAME_GENERATOR_KEY,
				DEFAULT_STAR_GENERATOR_KEY);
	}
	
	public NewGame(int maxPlayers, int numberOfStars, String coordinateGeneratorName, String starNameGeneratorName,
			String starGeneratorName) {
		this.maxPlayers = maxPlayers;
		this.numberOfStars = numberOfStars;
		this.coordinateGeneratorName = coordinateGeneratorName;
		this.starNameGeneratorName = starNameGeneratorName;
		this.starGeneratorName = starGeneratorName;
	}

}
