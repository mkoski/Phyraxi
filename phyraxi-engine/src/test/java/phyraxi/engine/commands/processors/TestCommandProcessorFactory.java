package phyraxi.engine.commands.processors;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import phyraxi.engine.commands.Command;
import phyraxi.engine.commands.NewGame;

public class TestCommandProcessorFactory {
	
	private final CommandProcessorFactory factory = new CommandProcessorFactory();

	@Test
	public void shouldReturnNewGameCommandProcessor() {
		Command<?> command = new NewGame(2, 100);
		CommandProcessor<Command<?>, ?> processor = factory.getProcessor(command);
		assertTrue(processor.getClass().isAssignableFrom(NewGameCommandProcessor.class));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowOnUnknownCommand() {
		Command<?> command = new DummyCommand();
		factory.getProcessor(command);
	}
	
	private static class DummyCommand extends Command<Object> {
		// dummy command
	}

}
