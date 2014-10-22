package phyraxi.engine.commands;

import phyraxi.engine.commands.processors.CommandProcessor;

public class NewGame implements Command {
	
	public final int maxPlayers;
	public final int numberOfStars;
	
	public NewGame(int maxPlayers, int numberOfStars) {
		this.maxPlayers = maxPlayers;
		this.numberOfStars = numberOfStars;
	}

	@Override
	public void process(CommandProcessor processor) {
		processor.process(this);
	}

}
