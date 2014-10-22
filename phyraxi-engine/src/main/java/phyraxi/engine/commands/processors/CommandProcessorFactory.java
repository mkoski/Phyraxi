package phyraxi.engine.commands.processors;

import java.util.SortedMap;
import java.util.TreeMap;

import phyraxi.engine.commands.Command;

/**
 * A factory class for {@link CommandProcessor}s. <b>Note:</b> this class is not designed
 * for thread-safety, access must be externally synchronized.
 * 
 * @author Jani Kaarela (@gmail.com)
 */
public class CommandProcessorFactory {
	
	private static final String COMMAND_PROCESSOR_PACKAGE = CommandProcessorFactory.class.getPackage().getName();
	private static final String COMMAND_PROCESSOR_POSTFIX = "CommandProcessor";
	private final SortedMap<String, CommandProcessor<Command<?>, ?>> processors = new TreeMap<>();
	
	public CommandProcessor<Command<?>, ?> getProcessor(Command<?> command) {
		String processorName = getProcessorName(command);
		CommandProcessor<Command<?>, ?> processor = processors.get(processorName);
		if (processor == null) {
			processor = getProcessorInstance(processorName);
			processors.put(processorName, processor);
		}
		return processor;
	}
	
	String getProcessorName(Command<?> command) {
		return COMMAND_PROCESSOR_PACKAGE + '.' + command.getClass().getSimpleName() + COMMAND_PROCESSOR_POSTFIX;
	}
	
	@SuppressWarnings("unchecked")
	CommandProcessor<Command<?>, ?> getProcessorInstance(String processorName) {
		try {
			Class<?> processorClass = Class.forName(processorName);
			return (CommandProcessor<Command<?>, ?>) processorClass.newInstance();
		} catch (ClassNotFoundException cnfe) {
			throw new IllegalArgumentException("No such processor class: " + processorName);
		} catch (InstantiationException ie) {
			throw new RuntimeException("Could not instantiate processor: " + processorName, ie);
		} catch (IllegalAccessException iae) {
			throw new RuntimeException("Could not instantiate processor: " + processorName, iae);
		}
	}

}
