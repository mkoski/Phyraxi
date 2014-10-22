package phyraxi.engine.commands;

import phyraxi.engine.commands.processors.CommandProcessor;

public abstract class Command<T> {
	
	public T process(CommandProcessor<Command<T>, T> processor) {
		return processor.process(this);
	}

}
