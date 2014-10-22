package phyraxi.engine.commands.processors;

import phyraxi.engine.commands.Command;

public interface CommandProcessor<C extends Command<?>, T> {
	
	T process(C command);

}
