package phyraxi.engine.commands;

import phyraxi.engine.commands.processors.CommandProcessor;

public interface Command {
	
	void process(CommandProcessor processor);

}
