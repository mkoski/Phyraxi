package fourx.command;

/**
 * Base class for client commands.
 */
public abstract class BaseCommand {

    /**
     * Gets the type of the command.
     * 
     * @return	command type.
     */
    public abstract CommandType getType();

    /**
     * Describes the command and it's parameters.
     */
    public abstract String toString();

}
